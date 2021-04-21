package queue;

import java.util.Arrays;

public class ArrayQueue extends AbstractQueue  {
    private int head = 0;
    private Object[] elements = new Object[5];

    protected void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        elements[getIndex(size)] = element;
    }

    private void ensureCapacity(int capacity) {
        if (capacity >= elements.length) {
            elements = Arrays.copyOf(toArray(), capacity * 2);
            head = 0;
        }
    }

    protected Object elementImpl() {
        return elements[head];
    }

    protected void dequeueImpl() {
        elements[head] = null;
        head = getIndex(1);
    }

    protected void clearImpl() {
        head = 0;
        Arrays.fill(elements, null);
    }

    private int getIndex(int i) {
        return (head + i) % elements.length;
    }

    public Object get(int index) {
        assert index >= 0;
        assert index < size();
        return elements[getIndex(index)];
    }

    public void set(int index, Object value) {
        assert index >= 0;
        assert index < size();
        assert value != null;
        elements[getIndex(index)] = value;
    }
}
