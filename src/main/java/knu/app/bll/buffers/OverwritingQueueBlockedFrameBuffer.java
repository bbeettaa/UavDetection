package knu.app.bll.buffers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OverwritingQueueBlockedFrameBuffer<T> implements Bufferable<T> {
    private final int capacity;
    private final Queue<BufferElement<T>> queue;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    public OverwritingQueueBlockedFrameBuffer( int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Buffer capacity must be > 0");
        }
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    @Override
    public void put(BufferElement<T> element) {
        lock.lock();
        try {
            if (queue.size() >= capacity) {
                queue.poll();
            }
            queue.offer(element);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public BufferElement<T> get() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            return queue.poll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // good practice
            return null;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return queue.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isFull() {
        lock.lock();
        try {
            return queue.size() >= capacity;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getSize() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            queue.clear();
        } finally {
            lock.unlock();
        }
    }
}
