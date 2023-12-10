package Tree;

public interface BinaryNodeInterface <T> {
    public T getData();

    public void setData (T newData);

    public BinaryNode <T> getLeftChild();

    public BinaryNode <T> getRightChild();

    public void setLeftChild (BinaryNode <T> leftChild);

    public void setRightChild (BinaryNode <T> rightChild);

    public boolean hasLeftChild();

    public boolean hasRightChild();

    public boolean isLeaf();

    public int getNumberOfNodes();

    public int getHeight();

    public BinaryNode <T> copy();


}
