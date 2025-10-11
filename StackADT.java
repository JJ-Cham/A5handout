/**
 * Interface for stack functionality
 */
public interface StackADT<T> {
    /** Add item onto stack */
    public void push(T value);

    /** Remove top item from the stack */
    public T pop();

    /** Returns top item from the stack without removing */
    public T peek();

    /** Returns true if there are no elements to pop */
    public boolean isEmpty();

    /** Returns true if there is no space to push */
    public boolean isFull();
}
