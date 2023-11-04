package List;
// https://wentworth.brightspace.com/d2l/le/content/40338/viewContent/468794/View
public interface ListInterface <T> {

    /**
     * add anEntry value to the top of the list
     *
     * @param anEntry
     */
    public void add(T anEntry);

    /**
     *
     * add anEntry value at the index idx and push the rest up
     * @param anEntry
     * @param idx
     */
    public void add(T anEntry, int idx);

    /**
     *
     * remove the value at given index idx
     * and return the remove value
     * @param idx
     * @return T
     */

    public T remove(int idx);

    /**
     *
     * return true if the given anEntry is remove from the list
     * @param anEntry
     * @return T
     */

    public boolean remove(T anEntry);

    /**
     *
     * clea the list by removing all entries
     */

    public void clear();

    /**
     *
     * replace the value at index idx with the given anEntry
     * return the value that was replace
     *
     * @param newEntry
     * @param idx
     * @return T
     */

    public T replace(int idx, T newEntry);

    /**
     *
     * get the entry from the idx given
     *
     * @param idx
     * @return T
     */

    public T getEntry(int idx);

    /**
     *
     * return a new array contain the enities in list in the same
     * order
     * @return Object[]
     */

    public Object[] toArray();

    /**
     * does anEntry exist in the list
     * @param anEntry
     * @return boolean
     */

    public boolean contains(T anEntry);

    /**
     * get the length of the List
     * @return int
     */

    public int getLength();

    /**
     * check if empty
     *
     * @return boolean
     */

    public boolean isEmpty();






}
