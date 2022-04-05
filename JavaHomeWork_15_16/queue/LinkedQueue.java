package queue;

public class LinkedQueue extends AbstractQueue {
    private Node tail;
    private Node head;

    protected void enqueueImpl(Object element) {
        Node prev = tail;
        tail = new Node(element, null);
        if (head == null) {
            head = tail;
        } else {
            prev.next = tail;
        }
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected void dequeueImpl() {
        head = head.next;
    }

    protected void clearImpl() {
        head = null;
    }

    public Object get(int index) {
        assert index >= 0;
        assert index < size;
        return getNode(index).value;
    }

    public void set(int index, Object value) {
        assert index >= 0;
        assert index < size;
        assert value != null;
        getNode(index).value = value;
    }

    public Node getNode(int index) {
        Node res = head;
        for (int i = 0; i < index; i++) {
            res = res.next;
        }
        return res;
    }

    public class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;
            this.value = value;
            this.next = next;
        }
    }
}
