package knu.app.buffers;

public class BufferElement<T> {
    private final T data;
    private final long timestamp;

    public BufferElement(T data) {
        this.data = data;
        this.timestamp = System.nanoTime();
    }

    public T getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
