package knu.app.bll.buffers;

import java.util.LinkedList;
import java.util.Queue;


public class OverwritingQueueFrameBuffer<T> implements BufferableQueue<T> {
    private final int capacity;
    private final Queue<BufferElement<T>> queue;


    public OverwritingQueueFrameBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Buffer capacity must be > 0");
        }
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public BufferElement<T> get() {
        return queue.isEmpty() ? null : queue.poll();

    }

    @Override
    public void put(BufferElement<T> element) {
        if (isFull()) {
            queue.poll();
        }
        queue.offer(element);
    }

    @Override
    public boolean isEmpty() {
            return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
            return queue.size() >= capacity;
    }

    @Override
    public int getSize() {
            return queue.size();
    }

    @Override
    public void clear() {
            queue.clear();
    }
}
