package knu.app.bll.postprocessors;

public interface FramePostprocessorValue<T> extends FramePostprocessor {
    void setValue(T val);
}
