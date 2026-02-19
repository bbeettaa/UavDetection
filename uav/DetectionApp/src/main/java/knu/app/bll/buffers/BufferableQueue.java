package knu.app.bll.buffers;

import java.util.AbstractQueue;
import java.util.Queue;

public interface BufferableQueue<T>  {
    void put(BufferElement<T> element);
    BufferElement<T> get();
    boolean isEmpty();
    boolean isFull();
    int getSize();
    void clear();
}
