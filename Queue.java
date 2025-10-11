import java.util.ArrayDeque;

/**
 * Interface for queue functionality
 */
public class Queue<T> implements QueueADT<T> {
    /** The backing data structure */
    public ArrayDeque<T> d = new ArrayDeque<>();

    /** Add item onto tail of queue */
    public void add(T value) {
        d.addLast(value);
    }

    /** Remove head item from the queue */
    public T remove() {
        return d.removeFirst();
    }

    /** Returns head item from the queue without removing */
    public T peek() {
        return d.getFirst();
    }

    /** Returns true if there are no elements to remove */
    public boolean isEmpty() {
        return d.size() == 0;
    }

    /** Returns true if there is no space to add */
    public boolean isFull() {
        return false;
    }

    /** Returns a string representation of the queue */
    public String toString() {
        return d.toString();
    }
}
