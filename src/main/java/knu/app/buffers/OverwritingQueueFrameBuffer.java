package knu.app.buffers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class OverwritingQueueFrameBuffer<T> implements Bufferable<T> {
    private final int capacity;
    private final Queue<BufferElement<T>> queue;
    private final String name;


    public OverwritingQueueFrameBuffer(String name, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Buffer capacity must be > 0");
        }
        this.capacity = capacity;
        this.name = name;
        this.queue = new LinkedList<>();
    }


    public BufferElement<T> get() {
        return queue.isEmpty() ? null : queue.poll(); // Неблокирующее получение

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
