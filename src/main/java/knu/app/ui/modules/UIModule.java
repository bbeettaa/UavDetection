package knu.app.ui.modules;

import java.util.concurrent.CompletableFuture;
import knu.app.bll.utils.MatWrapper;

public interface UIModule<T> {
    String getName();
    void render();
    T execute(T o);

    void show();
    void toggle();
    boolean isOpened();
}
