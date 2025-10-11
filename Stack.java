import java.util.ArrayDeque;

/** Implements the StackADT interface using an ArrayDeque */
class Stack<T> implements StackADT<T> {
    /** The backing data structure */
    public ArrayDeque<T> d = new ArrayDeque<>();

    /** Add an item to the stack */
    public void push(T v) {
        d.push(v);
    }

    /** Remove an item from the stack */
    public T pop() {
        return d.pop();
    }

    /** Reveals the top item from the stack without removing it */
    public T peek() {
        return d.peek();
    }

    /** True when stack cannot be popped */
    public boolean isEmpty() {
        return d.isEmpty();
    }

    /** True when stack cannot be pushed */
    public boolean isFull() {
        return false;
    }

    /** Returns a string representation of the stack */
    public String toString() {
        return d.toString();
    }
}
