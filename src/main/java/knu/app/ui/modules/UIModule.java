package knu.app.ui.modules;

public interface UIModule<T> {
    String getName();
    void render();
    T execute(T o);

    void show();
    void toggle();
    boolean isOpened();
}
