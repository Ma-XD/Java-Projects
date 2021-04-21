package queue;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object element() {
        assert size() > 0;
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public Object dequeue() {
        assert size() > 0;
        Object result = element();
        size--;
        dequeueImpl();
        return result;
    }

    protected abstract void dequeueImpl();

    public void clear() {
        size = 0;
        clearImpl();
    }

    protected abstract void clearImpl();


    public Object get(int index) {
        assert index >= 0;
        assert index < size;
        Object[] array = toArray();
        return array[index];
    }

    public void set(int index, Object value) {
        assert index >= 0;
        assert index < size;
        assert value != null;
        Object[] array = putToArray();
        array[index] = value;
        for (Object o : array) {
            enqueue(o);
        }
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        Object[] array = putToArray();
        for (Object o : array) {
            enqueue(o);
        }
        return array;
    }

    private Object[] putToArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = dequeue();
        }
        return array;
    }
}
