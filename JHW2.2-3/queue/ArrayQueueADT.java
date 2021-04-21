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
public class ArrayQueueADT {
    private int head = 0;
    private int tail = 0;
    private Object[] elements = new Object[5];

    /**
     * Pred: true
     * Post: R.n == 0 && R новый
     * */
    public static ArrayQueueADT create() {
        return new ArrayQueueADT();
    }

    /**
     * Pred: element != null
     * Post: n == n' + 1 && elements[n - 1] == element && ∀ i = 0..n'-1: elements[i] == elements'[i]
     */
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, size(queue) + 1);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity >= queue.elements.length) {
            int size = size(queue);
            Object[] temp = Arrays.copyOf(queue.elements, queue.elements.length);
            queue.elements = new Object[capacity * 2];
            for (int i = queue.head; i < queue.head + size; i++) {
                queue.elements[i - queue.head] = temp[i % temp.length];
            }
            queue.tail = size;
            queue.head = 0;
        }
    }

    /**
     * Pred n > 0
     * Post R == a[0] и очередь не изменяется
     */
    public static Object element(ArrayQueueADT queue) {
        assert size(queue) > 0;
        return queue.elements[queue.head];
    }

    /**
     * Pred n > 0
     * Post R == a[0] && n == n' - 1 && ∀ i = 0..n-1: elements[i] == elements'[i + 1]
     */
    public static Object dequeue(ArrayQueueADT queue) {
        assert size(queue) > 0;
        Object result = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return result;
    }

    /**
     * Pred: true
     * Post: R == n && очередь не изменится
     * */
    public static int size(ArrayQueueADT queue) {
        return queue.tail >= queue.head? queue.tail - queue.head: queue.elements.length + queue.tail - queue.head;
    }

    /**
     * Pred: true
     * Post: R == (n == 0) && очередь не изменится
     * */
    public static boolean isEmpty(ArrayQueueADT queue) {
        return size(queue) == 0;
    }

    /**
     * Pred: true
     * Post: n == 0 && R - new
     * */
    public static void clear(ArrayQueueADT queue) {
        Arrays.fill(queue.elements, null);
        queue.tail = 0;
        queue.head = 0;
    }

    /**
     * Pred: 0 <= index < size
     * Post: R == elements[index] && очередь не изменится
     * */
    public static Object get(ArrayQueueADT queue, int index) {
        assert index >= 0;
        assert index < size(queue);
        return queue.elements[(queue.head + index) % queue.elements.length];
    }

    /**
     * Pred: 0 <= index < size && value != null
     * Post: elements[index] == value && вся остальная очередь не изменится
     * */
    public static void set(ArrayQueueADT queue, int index, Object value) {
        assert index >= 0;
        assert index < size(queue);
        assert value != null;
        queue.elements[(queue.head + index) % queue.elements.length] = value;
    }
}
