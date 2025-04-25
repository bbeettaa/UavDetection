package knu.app.buffers;

public interface Bufferable<T> {
    void put(BufferElement<T> element);
    BufferElement<T> get();
    boolean isEmpty();
    boolean isFull();
    int getSize();
    void clear();
}
