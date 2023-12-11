package Stack;

import java.util.EmptyStackException;

public interface StackInterface <T> {

    /**
     * adds new entry to the top of the stack
     * @param T call anEntry
     * @author Teck
     */
    public void push (T anEntry);

    /**
     * return the top entry and remove it if not empty
     * @return the top entry
     * @throws EmptyStackException
     * @author Teck
     */
    public T pop();

    /**
     * return the top entry without removing it and not empty
     * @return top entry
     * @throws EmptyStackException
     * @author Teck
     */
    public T peek();

    /**
     * check if the stack is empty will be use for pop and peek
     * @return if the stack is empty
     * @author Teck
     */
    public boolean isEmpty();

    /**
     *
     * removes all entry in the stack
     * @author Teck
     */
    public void clear();

}
