package knu.app.bll.buffers;

import java.util.List;

public interface BufferableList<T> {
    void put(T element);

    List<T> get();

    boolean isEmpty();

    boolean isFull();

    int getCapacity();

    int getSize();

    void clear();

    void clearAll();

    void setNewCapacity(int c);

}
