/**
 * Interface for queue functionality
 */
public interface QueueADT<T> {
    /** Add item onto tail of queue */
    public void add(T value);

    /** Remove head item from the queue */
    public T remove();

    /** Returns head item from the queue without removing */
    public T peek();

    /** Returns true if there are no elements to remove */
    public boolean isEmpty();

    /** Returns true if there is no space to add */
    public boolean isFull();
}
