package knu.app.bll.buffers;

import knu.app.bll.utils.MatWrapper;

public class BufferElement<T>  implements Comparable<BufferElement> {
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

    @Override
    public int compareTo(BufferElement other) {
        return Long.compare(this.timestamp, other.timestamp);
    }
}
