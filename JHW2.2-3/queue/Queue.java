package queue;

/**
 * Model:
 *  очередь [a1,..,an]
 *  n -- размер очереди
 * Inv:
 *  n >= 0
 *  ∀ i = 1..n: a[n] != null
 */
public interface Queue {

    /**
     * Pred: element != null
     * Post: n == n' + 1 && a[n - 1] == element && ∀ i = 1..n': a[i] == a'[i]
     */
    void enqueue(Object element);

    /**
     * Pred n > 0
     * Post R == a[1] и очередь не изменяется
     */
    Object element();

    /**
     * Pred n > 0
     * Post R == a[1] && n == n' - 1 && ∀ i = 1..n: a[i] == a'[i + 1]
     */
    Object dequeue();

    /**
     * Pred: true
     * Post: R == n && очередь не изменится
     */
    int size();

    /**
     * Pred: true
     * Post: R == (n == 0) && очередь не изменится
     */
    boolean isEmpty();

    /**
     * Pred: true
     * Post: n == 0 && R - новая
     */
    void clear();

    /**
     * Pred: 0 <= index < n
     * Post: R == a[index + 1] && очередь не изменится
     */
    Object get(int index);

    /**
     * Pred: 0 <= index < n && value != null
     * Post: elements[index + 1] == value && ∀ i = {1,..,n} \ {index + 1} : a[i] == a'[i]
     */
    void set(int index, Object value);

    /**
     * Pred: true
     * Post: R == array && array.length == n && ∀ i = 1..n : array[i - 1] = a[i]
     */
    Object[] toArray();
}
