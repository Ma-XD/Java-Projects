package queue;

import java.util.Arrays;

/**
 * Model:
 *  массив elements[i] i = 0..n-1
 *  n -- размер очереди
 * Inv:
 *  n >= 0
 *  ∀ i = 0..n-1: elements[i] != null
 */
public class ArrayQueueModule {
    private static int head = 0;
    private static int tail = 0;
    private static Object[] elements = new Object[5];

    /**
     * Pred: element != null
     * Post: n == n' + 1 && elements[n - 1] == element && ∀ i = 0..n'-1: elements[i] == elements'[i]
     */
    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size() + 1);
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity >= elements.length) {
            int size = size();
            Object[] temp = Arrays.copyOf(elements, elements.length);
            elements = new Object[capacity * 2];
            for (int i = head; i < head + size; i++) {
                elements[i - head] = temp[i % temp.length];
            }
            tail = size;
            head = 0;
        }
    }

    /**
     * Pred n > 0
     * Post R == a[0] и очередь не изменяется
     */
    public static Object element() {
        assert size() > 0;
        return elements[head];
    }

    /**
     * Pred n > 0
     * Post R == a[0] && n == n' - 1 && ∀ i = 0..n-1: elements[i] == elements'[i + 1]
     */
    public static Object dequeue() {
        assert size() > 0;
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return result;
    }

    /**
     * Pred: true
     * Post: R == n && очередь не изменится
     * */
    public static int size() {
        return tail >= head? tail - head: elements.length + tail - head;
    }

    /**
     * Pred: true
     * Post: R == (n == 0) && очередь не изменится
     * */
    public static boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Pred: true
     * Post: n == 0 && R - new
     * */
    public static void clear() {
        Arrays.fill(elements, null);
        tail = 0;
        head = 0;
    }

    /**
     * Pred: 0 <= index < size
     * Post: R == elements[index] && очередь не изменится
     * */
    public static Object get(int index) {
        assert index >= 0;
        assert index < size();
        return elements[(head + index) % elements.length];
    }

    /**
     * Pred: 0 <= index < size && value != null
     * Post: elements[index] == value && вся остальная очередь не изменится
     * */
    public static void set(int index, Object value) {
        assert index >= 0;
        assert index < size();
        assert value != null;
        elements[(head + index) % elements.length] = value;
    }
}
