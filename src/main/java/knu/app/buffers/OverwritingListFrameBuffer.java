package knu.app.buffers;

import java.util.concurrent.locks.ReentrantLock;

public class OverwritingListFrameBuffer<T> implements Bufferable<T> {
    private final BufferElement<T>[] buffer;
    private final int capacity;
    private int head = 0;     // вказівник на читання
    private int tail = 0;     // вказівник на запис
    private int size = 0;

    private final ReentrantLock lock = new ReentrantLock();

    @SuppressWarnings("unchecked")
    public OverwritingListFrameBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new BufferElement[capacity];
    }

    @Override
    public void put(BufferElement<T> element) {
        lock.lock();
        try {
            buffer[tail] = element;
            tail = (tail + 1) % capacity;
            if (size < capacity) {
                size++;
            } else {
                head = (head + 1) % capacity; // перезапис — посунути голову
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public BufferElement<T> get() {
        lock.lock();
        try {
            if (size == 0) return null;

            BufferElement<T> element = buffer[head];
            buffer[head] = null; // допомога GC
            head = (head + 1) % capacity;
            size--;
            return element;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return size == 0;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isFull() {
        lock.lock();
        try {
            return size == capacity;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getSize() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            for (int i = 0; i < capacity; i++) buffer[i] = null;
            head = 0;
            tail = 0;
            size = 0;
        } finally {
            lock.unlock();
        }
    }
}
