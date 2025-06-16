package knu.app.bll.buffers;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class FilterBufferableLinkedList<T> implements BufferableList<T> {
    private  int capacity;
    private final List<T> buf;
    private final Predicate<T> condition;

    public FilterBufferableLinkedList(int capacity, Predicate<T> condition) {
        this.capacity = capacity;
        this.condition = condition;
        this.buf = new LinkedList<>();
    }


    @Override
    public void put(T element) {
        if (getSize() + 1 >= capacity)
            clear();

        if (getSize() + 1 < capacity)
            buf.add(element);
    }

    @Override
    public List<T> get() {
        return buf;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return buf.size();
    }

    @Override
    public void clear() {
        buf.removeIf(condition);
    }

    @Override
    public void clearAll() {
        buf.clear();
    }

    @Override
    public void setNewCapacity(int c) {
        this.capacity = c;
    }
}
