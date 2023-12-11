package Tree;


public class BinaryNode <T> implements BinaryNodeInterface <T>{

    private T data;
    private BinaryNode <T> leftChild;
    private BinaryNode <T> rightChild;

    public BinaryNode (T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        leftChild = left;
        if (right == left && right != null)
            rightChild = (BinaryNode<T>) right.copy();
        else
            rightChild = right;
    }

    public BinaryNode (T data) {
        this(data, null, null);
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T newData) {
        data = newData;

    }

    @Override
    public BinaryNode <T> getLeftChild() {
        return leftChild;
    }

    @Override
    public BinaryNode<T> getRightChild() {
        return rightChild;
    }

    @Override
    public void setLeftChild(BinaryNode<T> leftChild) {
        this.leftChild = leftChild;

    }

    @Override
    public void setRightChild(BinaryNode <T> rightChild) {
        this.rightChild = rightChild;

    }

    @Override
    public boolean hasLeftChild() {
        return leftChild != null;
    }

    @Override
    public boolean hasRightChild() {
        return rightChild != null;
    }

    @Override
    public boolean isLeaf() {
        return rightChild == null && leftChild == null;
    }

    @Override
    public int getNumberOfNodes() {
        int n = 1;
        if (leftChild != null)
            n += leftChild.getNumberOfNodes();
        if (rightChild != null)
            n += rightChild.getNumberOfNodes();
        return n;
    }

    @Override
    public int getHeight() {
        int leftHeight = leftChild == null ? 0 : leftChild.getHeight();
        int rightHeight = rightChild == null ? 0 : rightChild.getHeight();
        return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
    }

    @Override
    public BinaryNode <T> copy() {
        BinaryNode <T> copied = new BinaryNode(data);
        if (hasLeftChild())
            copied.setLeftChild(leftChild.copy());
        if (hasRightChild())
            copied.setRightChild(rightChild.copy());
        return copied;
    }

}
