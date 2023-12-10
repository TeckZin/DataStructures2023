package Tree;

import Queue.QueueArray;
import Queue.QueueInterface;
import Stack.ArrayStack;
import Stack.LinkedStack;


import java.util.EmptyStackException;
import java.util.Iterator;




public class BinaryTree<T> implements BinaryTreeInterface<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(T rootData){
        root = new BinaryNode<>(rootData);

    }
    public BinaryTree (T rootData, BinaryTree <T> leftTree, BinaryTree <T> rightTree) {
        initializeTree(rootData, leftTree, rightTree);
    }
    private void initializeTree (T rootData, BinaryTree <T> leftTree, BinaryTree <T> rightTree) {
        root = new BinaryNode <> (rootData);
        if (leftTree != null) root.setLeftChild (leftTree.getRootNode());
        if (rightTree != null) root.setRightChild (rightTree.getRootNode());
    }

    private BinaryNode<T> getRootNode(){
        return root;
    }



    @Override
    public void setTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    @Override
    public void setTree(BinaryNode<T> root) {
        this.root = root;
    }

    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        initializeTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
    }

    @Override
    public T getRootData() {
        return root.getData();
    }

    @Override
    public int getHeight() {
        if (isEmpty()){
            return 0;
        }
        return root.getHeight();
    }

    @Override
    public int getNumberOfNodes() {
        if(isEmpty()) return 0;
        return root.getNumberOfNodes();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;

    }
    private BinaryNode<T> leftMostLeaf(BinaryNode<T> node) {
        LinkedStack <BinaryNode <T>> nodeStack = new LinkedStack<>();
        if (node == null)
            return null;
        boolean left, right;
        while (node != null) {
            nodeStack.push(node);
            left = node.hasLeftChild();
            right = node.hasRightChild();
            if (left && right) {
                nodeStack.push (node.getRightChild());
                node = node.getLeftChild();
            }
            else if (right) {
                node = node.getRightChild(); // no push
            }
            else
                node = node.getLeftChild(); // no push
        }
        return nodeStack.pop();
    }
    private class PreorderIterator implements Iterator <T> {
        private LinkedStack <BinaryNode <T>> nodeStack;
        public PreorderIterator (){
            if (root == null)
                throw new IllegalArgumentException ("No iteration on empty tree");
            nodeStack = new LinkedStack<>();
            nodeStack.push (root);
        }
        @Override
        public boolean hasNext() {
            return (!nodeStack.isEmpty());

        }
        @Override
        public T next() {
            BinaryNode <T> currNode = nodeStack.pop();
            T item = currNode.getData();
            if (currNode.hasRightChild())
                nodeStack.push(currNode.getRightChild());
            if (currNode.hasLeftChild())
                nodeStack.push(currNode.getLeftChild());
            return item;
        }
        @Override
        public void remove (){
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    private class PostOrderIterator implements Iterator <T> {
        private LinkedStack<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currNode = null;
        private BinaryNode<T> nextNode;

        public PostOrderIterator() {
            nodeStack = new LinkedStack<>();
            nextNode = leftMostLeaf(root);
        }

       @Override
        public boolean hasNext() {
            return (!nodeStack.isEmpty());

        }
        @Override
        public T next() {
            BinaryNode <T> currNode = nodeStack.pop();
            T item = currNode.getData();
            if (currNode.hasRightChild())
                nodeStack.push(currNode.getRightChild());
            if (currNode.hasLeftChild())
                nodeStack.push(currNode.getLeftChild());
            return item;
        }
    }
    @Override
    public Iterator<T> getPostorderIterator() {
        return new PostOrderIterator();
    }
    private class InOrderIterator implements Iterator <T> {
        private LinkedStack <BinaryNode <T>> nodeStack;
        public InOrderIterator (){
            BinaryNode <T> currNode = root;
            nodeStack = new LinkedStack <>();
            while (currNode != null) {
                nodeStack.push(currNode);
                currNode = currNode.getLeftChild();
            }
        }
        @Override
        public boolean hasNext() {
            return (!nodeStack.isEmpty ());
        }
        @Override
        public T next () {
            if (!hasNext()) throw new EmptyStackException();
            BinaryNode <T> currNode = nodeStack.pop();
            T data = currNode.getData();
            currNode = currNode.getRightChild();
            while (currNode != null){
                nodeStack.push (currNode);
                currNode = currNode.getLeftChild();
            }
            return data;
        }
    }
    @Override
    public Iterator<T> getInorderIterator() {
        return new InOrderIterator();
    }
    private class LevelorderIterator implements Iterator <T> {
        private QueueInterface<BinaryNode<T>> nodeQueue;
        private BinaryNode <T> currNode;
        public LevelorderIterator() {
            nodeQueue = new QueueArray<>(); // or LinkedQueue<>();
            nodeQueue.enqueue(root);
        }

        @Override
        public boolean hasNext() {
            return (!nodeQueue.isEmpty());
        }

        @Override
        public T next() {
            currNode = nodeQueue.dequeue();
            T outData = currNode.getData();
            BinaryNode<T> left = currNode.getLeftChild();
            BinaryNode<T> right = currNode.getRightChild();
            if (left != null)
                nodeQueue.enqueue(left);
            if (right != null)
                nodeQueue.enqueue(right);
            return outData;
        }
    }
    @Override
    public Iterator<T> getLevelorderIterator() {
        return new LevelorderIterator();
    }
}
