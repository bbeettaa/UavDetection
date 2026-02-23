package knu.app.bll.algorithms.kalman;

import knu.app.bll.utils.math.*;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

public class AccelerationKalmanFilter implements IKalmanFilter {

    private Matrix x;
    private Matrix P_root;
    private Matrix Q_root;
    private Matrix R_root;
    private Matrix C;

    private double currentDt = 0.033;
    private final Function physicsModel;

    public AccelerationKalmanFilter() {
        this.C = Matrix.zero(2, 6);
        this.C.set(0, 0, 1.0);
        this.C.set(1, 1, 1.0);

        this.Q_root = Matrix.zero(6, 6);

        for (int i = 0; i < 2; i++) this.Q_root.set(i, i, 0.0); // Позиция
        for (int i = 2; i < 4; i++) this.Q_root.set(i, i, 0.5);  // Скорость
        for (int i = 4; i < 6; i++) this.Q_root.set(i, i, 0.001); // Ускорение



        this.R_root = Matrix.zero(2, 2);
        double r_val = 0.5;
        this.R_root.set(0, 0, r_val);
        this.R_root.set(1, 1, r_val);


        this.physicsModel = new Function() {
            @Override
            public Matrix next(Matrix state, double dt) {
                double dt2 = 0.5 * dt * dt;
                Matrix n = Matrix.zero(6, 1);
                n.set(0, 0, state.get(0, 0) + state.get(2, 0) * dt + state.get(4, 0) * dt2);
                n.set(1, 0, state.get(1, 0) + state.get(3, 0) * dt + state.get(5, 0) * dt2);
                n.set(2, 0, state.get(2, 0) + state.get(4, 0) * dt);
                n.set(3, 0, state.get(3, 0) + state.get(5, 0) * dt);
                n.set(4, 0, state.get(4, 0));
                n.set(5, 0, state.get(5, 0));
                return n;
            }

            @Override
            public Matrix jacobian(Matrix state, double dt) {
                double dt2 = 0.5 * dt * dt;
                Matrix j = Matrix.zero(6, 6);
                // Диагональ
                for(int i=0; i<6; i++) j.set(i,i, 1.0);
                // x = x + v*dt + 0.5*a*dt^2
                j.set(0, 2, dt); j.set(0, 4, dt2);
                // y = y + v*dt + 0.5*a*dt^2
                j.set(1, 3, dt); j.set(1, 5, dt2);
                // v = v + a*dt
                j.set(2, 4, dt);
                j.set(3, 5, dt);
                return j;
            }
        };
    }

    @Override
    public void reset(Point2f initialPosition) {
        this.x = Matrix.zero(6, 1);
        this.x.set(0, 0, initialPosition.x());
        this.x.set(1, 0, initialPosition.y());


        this.P_root = Matrix.zero(6, 6);
        for (int i = 0; i < 6; i++) {
            this.P_root.set(i, i, 1.0);
        }
    }

    @Override
    public Point2f update(Point2f measurement) {
//        if (x == null) return measurement;
        if (x == null) {
            if (measurement == null) return null;
            reset(measurement);
        }

        Matrix[] pred = predictPhase(physicsModel, currentDt, P_root, x, Q_root);
        x = pred[0];
        P_root = pred[1];

        if (measurement != null) {
            Matrix z = Matrix.zero(2, 1);
            z.set(0, 0, measurement.x());
            z.set(1, 0, measurement.y());

            Matrix[] corr = updatePhase(R_root, P_root, C, x, z);
            x = corr[0];
            P_root = corr[1];
        }else {
            for (int i = 0; i < P_root.rowCount(); i++) {
                double val = P_root.get(i, i);
                val *= 1.05;  // немного увеличиваем уверенность
                P_root.set(i, i, val);
            }
        }

        return new Point2f((float) x.get(0, 0), (float) x.get(1, 0));
    }



    @Override
    public Mat saveState() {
        if (x == null || P_root == null) return null;

        Mat stateMat = new Mat(6 + 36, 1, org.bytedeco.opencv.global.opencv_core.CV_32F); // 6 + 36 элементов
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            stateMat.ptr(idx++).putFloat((float) x.get(i, 0));
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                stateMat.ptr(idx++).putFloat((float) P_root.get(i, j));
            }
        }
        return stateMat;
    }

    @Override
    public void restoreState(Mat state) {
        if (state == null || state.rows() < 6 + 36) return;

        int idx = 0;
        for (int i = 0; i < 6; i++) {
            x.set(i, 0, state.ptr(idx++).getFloat());
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                P_root.set(i, j, state.ptr(idx++).getFloat());
            }
        }
    }

    @Override
    public void setDt(float dt) {
        this.currentDt = dt;
    }

    @Override
    public Point2f predict() {
        if (x == null) return new Point2f(0,0);

        Matrix[] pred = predictPhase(physicsModel, currentDt, P_root, x, Q_root);
        Matrix x_pred = pred[0];
        return new Point2f((float) x_pred.get(0, 0), (float) x_pred.get(1, 0));
    }


    @Override
    public Mat getState() {
        Mat result = new Mat(6, 1, 5);
        for (int i = 0; i < 6; i++) {
            result.ptr(i).putFloat((float) x.get(i, 0));
        }
        return result;
    }




    public static Matrix[] predictPhase(Function f, double time, Matrix P_0_sqrt, Matrix x, Matrix Q_root){
        int state_count = x.rowCount();
        Matrix estimate = f.next(x,time);
        Matrix jacobian = f.jacobian(x,time);

        Matrix tmp = Matrix.zero(state_count, state_count*2);
        tmp.setSubmatrix(0,0,state_count,state_count,Matrix.multiply(jacobian,P_0_sqrt));
        tmp.setSubmatrix(0,state_count,state_count,state_count,Q_root);

        Matrix QR_2[] = QR.QR(tmp.transpose());
        Matrix Q = QR_2[0];
        Matrix R = QR_2[1];

        Matrix covariance_sqrt = R.transpose();
        covariance_sqrt = covariance_sqrt.getSubmatrix(0,0,state_count,state_count);

        Matrix result[] = new Matrix[2];
        result[0] = estimate;
        result[1] = covariance_sqrt;
        return result;
    }

    public static Matrix[] updatePhase(Matrix R_root, Matrix P_root, Matrix C, Matrix estimate, Matrix measurement) {
        int m_count = C.rowCount();
        int s_count = estimate.rowCount();

        // tmp должна быть (state + measurement) x (state + measurement)
        Matrix tmp = Matrix.zero(s_count + m_count, s_count + m_count);
        tmp.setSubmatrix(0, 0, m_count, m_count, R_root);
        tmp.setSubmatrix(0, m_count, m_count, s_count, Matrix.multiply(C, P_root));
        tmp.setSubmatrix(m_count, m_count, s_count, s_count, P_root);

        // QR от транспонированной tmp
        Matrix[] QR_res = QR.QR(tmp.transpose());
        Matrix R = QR_res[1];

        // 1. Фикс знаков диагонали (обязательно ДО вырезания субматриц)
        for (int i = 0; i < R.colCount(); i++) {
            if (R.get(i, i) < 0) {
                for (int j = 0; j < R.colCount(); j++) R.set(i, j, -R.get(i, j));
            }
        }

        // 2. В SRKF матрицы X, Y, Z — это блоки ВЕРХНЕЙ треугольной матрицы R
        // R имеет вид:
        // [ X (m x m) | Y^T (m x s) ]
        // [ 0         | Z   (s x s) ]

        Matrix X = R.getSubmatrix(0, 0, m_count, m_count); // Инновационная ковариация (корень)
        Matrix Y_T = R.getSubmatrix(0, m_count, m_count, s_count); // Взаимная корреляция
        Matrix Z = R.getSubmatrix(m_count, m_count, s_count, s_count); // Новый P_root

        // Ошибка замера (innovation)
        Matrix innovation = Matrix.minus(measurement, Matrix.multiply(C, estimate));

        // Решаем X^T * d = innovation -> это дает промежуточный вектор
        // Но так как X верхнетреугольная, X^T — нижнетреугольная.
        Matrix d = TriangleSolve.lowerTriangleSolve(X.transpose(), innovation);

        // Поправка: x = x + Y_T^T * d
        Matrix correction = Matrix.multiply(Y_T.transpose(), d);
        Matrix estimate_next = Matrix.plus(estimate, correction);

        return new Matrix[]{estimate_next, Z};
    }
    public static Matrix[][] ddekf( Function f, double dt_between_measurements, double start_time, int state_count, int sensor_count, int measurement_count, Matrix C, Matrix Q_root, Matrix R_root, Matrix P_0_root, Matrix x_0, Matrix[] measurements){
        Matrix x_km1_p = x_0;
        Matrix P_root_km1_p = P_0_root;

        Matrix[] estimates = new Matrix[measurement_count+1];
        Matrix[] covariances = new Matrix[measurement_count+1];

        estimates[0] = x_km1_p;
        covariances[0] = P_root_km1_p;

        double current_time = start_time;

        for(int k=0; k<measurement_count; k++){
            Matrix resPredict[] = predictPhase(f,dt_between_measurements,P_root_km1_p,x_km1_p,Q_root);
            Matrix x_k_m = resPredict[0];
            Matrix P_root_km = resPredict[1];

            Matrix resUpdate[] = updatePhase(R_root,P_root_km,C,x_k_m,measurements[k]);

            x_km1_p = resUpdate[0];
            P_root_km1_p = resUpdate[1];

            current_time = current_time + dt_between_measurements;

            estimates[k+1] = x_km1_p;
            covariances[k+1] = Matrix.multiply(P_root_km1_p,P_root_km1_p.transpose());
        }

        Matrix result [][] = new Matrix[2][];
        result[0] = estimates;
        result[1] = covariances;
        return result;
    }

}