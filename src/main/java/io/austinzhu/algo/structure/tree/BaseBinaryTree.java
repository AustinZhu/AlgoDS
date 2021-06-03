package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.util.*;

public abstract class BaseBinaryTree<T> implements Tree<T> {
    private Node<T> root;

    protected int height;

    protected int size;

    public BaseBinaryTree() {
        this.root = null;
        this.size = 0;
        this.height = -1;
    }

    public BaseBinaryTree(Node<T> root) {
        this.root = root;
    }

    @Algorithm
    @Override
    public void append(T element) {
        if (getRoot() == null) {
            setRoot(new Node<>(element));
            return;
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        Node<T> newNode = new Node<>(element);
        nodeQueue.add(getRoot());
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (!temp.hasLeft()) {
                temp.setLeft(newNode);
                size++;
                return;
            } else {
                nodeQueue.add(temp.getLeft());
            }
            if (!temp.hasRight()) {
                temp.setRight(newNode);
                size++;
                return;
            } else {
                nodeQueue.add(temp.getRight());
            }
        }
    }

    @Override
    public void eject() throws IndexOutOfBoundsException {
        if (getRoot() == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Deque<Node<T>> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(getRoot());
        Node<T> parent = null;
        Node<T> last = null;
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.hasLeft()) {
                Node<T> left = temp.getLeft();
                nodeQueue.add(left);
                if (left.isLeaf()) {
                    parent = temp;
                    last = left;
                }
            }
            if (temp.hasRight()) {
                Node<T> right = temp.getRight();
                nodeQueue.add(right);
                if (right.isLeaf()) {
                    parent = temp;
                    last = right;
                }
            }
        }
        if (last != null) {
            if (parent.hasRight()) {
                parent.setRight(null);
                size--;
            } else {
                parent.setLeft(null);
                size--;
            }
        }
    }

    @SafeVarargs
    @Override
    public final void init(T... elements) {
        for (T elem: elements) {
            append(elem);
        }
    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (getRoot() == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(getRoot());
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.getKey() == id) {
                temp.setValue(object);
                return;
            }
            if (temp.hasLeft()) {
                nodeQueue.add(temp.getLeft());
            }
            if (temp.hasRight()) {
                nodeQueue.add(temp.getRight());
            }
        }
        throw new ElementNotFoundException("Not found");
    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (getRoot() == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(getRoot());
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.getKey() == id) {
                return temp.getValue();
            }
            if (temp.hasLeft()) {
                nodeQueue.add(temp.getLeft());
            }
            if (temp.hasRight()) {
                nodeQueue.add(temp.getRight());
            }
        }
        throw new ElementNotFoundException("Not Found");
    }

    @Algorithm
    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (getRoot() == null) {
            throw new IndexOutOfBoundsException("Null head");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(getRoot());
        Node<T> last = null, lastParent = null;
        Node<T> deleted = null, deletedParent = null;
        while (!nodeQueue.isEmpty()) {
            Node<T> iterator = nodeQueue.remove();
            if (iterator.hasLeft()) {
                Node<T> left = iterator.getLeft();
                if (left.getKey() == id) {
                    deletedParent = iterator;
                    deleted = left;
                }
                if (left.isLeaf()) {
                    lastParent = iterator;
                    last = left;
                }
                nodeQueue.add(left);
            }
            if (iterator.hasRight()) {
                Node<T> right = iterator.getRight();
                if (right.getKey() == id) {
                    deletedParent = iterator;
                    deleted = right;
                }
                if (right.isLeaf()) {
                    lastParent = iterator;
                    last = right;
                }
                nodeQueue.add(right);
            }
        }
        if (deletedParent != null && lastParent != null) {
            if (lastParent.hasRight()) {
                lastParent.setRight(null);
                size--;
            } else if (lastParent.hasLeft()) {
                lastParent.setLeft(null);
                size--;
            }
            last.setLeft(deleted.getLeft());
            last.setRight(deleted.getRight());
            if (deletedParent.getLeft().getKey() == id) {
                deletedParent.setLeft(last);
                return;
            }
            if (deletedParent.getRight().getKey() == id) {
                deletedParent.setRight(last);
                return;
            }
        }
        throw new ElementNotFoundException("No such index");
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        if (getRoot() == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(getRoot());
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.getValue() == element) {
                return temp.getKey();
            }
            if (temp.hasLeft()) {
                nodeQueue.add(temp.getLeft());
            }
            if (temp.hasRight()) {
                nodeQueue.add(temp.getRight());
            }
        }
        return -1;
    }

    @Override
    public boolean exist(T element) {
        return search(element, SearchingAlgorithm.BFS) != -1;
    }

    public void leftRotate() {
        if (getRoot() == null) {
            return;
        }
        if (!getRoot().isLeaf()) {
            Node<T> right = getRoot().getRight();
            getRoot().setRight(right.getLeft());
            right.setLeft(getRoot());
            setRoot(right);
        }
    }

    public void rightRotate() {
        if (getRoot() == null) {
            return;
        }
        if (!getRoot().isLeaf()) {
            Node<T> left = getRoot().getLeft();
            getRoot().setLeft(left.getRight());
            left.setRight(getRoot());
            setRoot(left);
        }
    }

    @Override
    public int getHeight() {
        if (getRoot() == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        int height = 0;
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(getRoot());
        int size;
        while (!nodeQueue.isEmpty()) {
            height++;
            size = nodeQueue.size();
            while (size > 0) {
                Node<T> temp = nodeQueue.remove();
                if (temp.hasLeft()) {
                    nodeQueue.add(temp.getLeft());
                }
                if (temp.hasRight()) {
                    nodeQueue.add(temp.getRight());
                }
                size--;
            }
        }
        this.height = height;
        return height;
    }

    @Override
    public String toString() {
        TreePrinter<Node<T>> tp = new TreePrinter<>(n -> n.getValue().toString(), Node::getLeft, Node::getRight);
        tp.printTree(getRoot());
        return tp.toString();
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getRoot() == null;
    }

    protected static class Node<T> {
        int key;

        T value;

        Node<T> left;

        Node<T> right;

        Node(T value) {
            this.key = value.hashCode();
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        public boolean isLeaf() {
            return !hasLeft() && !hasRight();
        }

        public Node<T> ejectSuccessor() {
            if (!this.hasRight()) {
                return null;
            }
            Node<T> parent = this;
            Node<T> succ = this.getRight();
            if (!succ.hasLeft()) {
                parent.setRight(succ.getRight());
                return succ;
            }
            while (succ.hasLeft()) {
                parent = succ;
                succ = succ.getLeft();
            }
            parent.setLeft(null);
            return succ;
        }

        public Node<T> ejectPredecessor() {
            if (!this.hasLeft()) {
                return null;
            }
            Node<T> parent = this;
            Node<T> pred = this.getLeft();
            if (!pred.hasRight()) {
                parent.setLeft(pred.getLeft());
                return pred;
            }
            while (pred.hasRight()) {
                parent = pred;
                pred = pred.getRight();
            }
            parent.setRight(null);
            return pred;
        }

        @Override
        public int hashCode() {
            int h = Objects.hashCode(value);
            h = h * 31 + Objects.hashCode(left);
            h = h * 31 + Objects.hashCode(right);
            return h;
        }
    }
}
