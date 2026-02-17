package knu.app.bll.algorithms.kalman;

import knu.app.bll.utils.math.Function;
import knu.app.bll.utils.math.Gaussian;
import knu.app.bll.utils.math.Matrix;
import knu.app.bll.utils.math.QR;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

public class AccelerationKalmanFilter implements IKalmanFilter {


    private Matrix x;          // Состояние [6x1]: x, y, vx, vy, ax, ay
    private Matrix P_root;     // Корень ковариации [6x6]
    private Matrix Q_root;     // Корень шума процесса [6x6]
    private Matrix R_root;     // Корень шума измерения [2x2]
    private Matrix C;          // Матрица перехода к измерениям [2x6]

    private double currentDt = 0.033; // По умолчанию ~30 FPS
    private final Function physicsModel;

    // В конструкторе или блоке инициализации:
    public AccelerationKalmanFilter() {
        // Матрица селекции C [2x6]
        this.C = Matrix.zero(2, 6);
        this.C.set(0, 0, 1.0);
        this.C.set(1, 1, 1.0);

        // Инициализация шума процесса Q_root [6x6]
        this.Q_root = Matrix.zero(6, 6);
        double q_val = 0.1;
        for (int i = 0; i < 6; i++) {
            this.Q_root.set(i, i, q_val);
        }

        // Инициализация шума измерения R_root [2x2]
        this.R_root = Matrix.zero(2, 2);
        double r_val = 0.5;
        this.R_root.set(0, 0, r_val);
        this.R_root.set(1, 1, r_val);

        // Определение модели физики
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
        // Инициализация вектора состояния [6x1]
        this.x = Matrix.zero(6, 1);
        this.x.set(0, 0, initialPosition.x());
        this.x.set(1, 0, initialPosition.y());

        // Ручная инициализация единичной матрицы P_root [6x6]
        this.P_root = Matrix.zero(6, 6);
        for (int i = 0; i < 6; i++) {
            this.P_root.set(i, i, 1.0);
        }
    }
    @Override
    public Point2f update(Point2f measurement) {
        if (x == null) return measurement;

        // 1. Predict
        Matrix[] pred = predictPhase(physicsModel, currentDt, P_root, x, Q_root);
        x = pred[0];
        P_root = pred[1];

        // 2. Correct
        if (measurement != null) {
            Matrix z = Matrix.zero(2, 1);
            z.set(0, 0, measurement.x());
            z.set(1, 0, measurement.y());

            Matrix[] corr = updatePhase(R_root, P_root, C, x, z);
            x = corr[0];
            P_root = corr[1];
        }

        return new Point2f((float) x.get(0, 0), (float) x.get(1, 0));
    }

    @Override
    public void releaseResources() {

    }

    @Override
    public void setDt(float dt) {
        this.currentDt = dt;
    }

    @Override
    public Mat getState() {
        // Конвертация внутреннего Matrix в OpenCV Mat (4x1 или 6x1)
        Mat result = new Mat(6, 1, 5); // CV_32F или CV_64F в зависимости от Matrix
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

    public static Matrix[] updatePhase( Matrix R_root, Matrix P_root, Matrix C, Matrix estimate, Matrix measurement){
        int measurement_count = C.rowCount();
        int state_count = estimate.rowCount();

        Matrix tmp = Matrix.zero(state_count+measurement_count,state_count+measurement_count);
        tmp.setSubmatrix(0,0,measurement_count,measurement_count, R_root);
        tmp.setSubmatrix(0,measurement_count,measurement_count,state_count,Matrix.multiply(C,P_root));
        tmp.setSubmatrix(measurement_count,measurement_count,state_count,state_count,P_root);

        Matrix QR_2[] = QR.QR(tmp.transpose());
        Matrix Q = QR_2[0];
        Matrix R = QR_2[1];
        R = R.transpose();

        Matrix X = R.getSubmatrix(0,0,measurement_count,measurement_count);
        Matrix Y = R.getSubmatrix(measurement_count,0,state_count,measurement_count);
        Matrix Z = R.getSubmatrix(measurement_count,measurement_count,state_count,state_count);

        Matrix estimate_next = Matrix.plus(estimate,Matrix.multiply(Y, Gaussian.solve(X,Matrix.minus(measurement,Matrix.multiply(C,estimate)))));
        Matrix covariance_sqrt = Z;

        Matrix result[] = new Matrix[2];
        result[0] = estimate_next;
        result[1] = covariance_sqrt;
        return result;
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
            Matrix resPredict[] = predictPhase(f,current_time,P_root_km1_p,x_km1_p,Q_root);
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