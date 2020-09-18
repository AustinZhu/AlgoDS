package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.*;

import java.util.*;

public abstract class BaseBinaryTree<T> implements Interactable<T>, Operatable<T>, Searchable<T>, Traversable {
    private Node<T> root;

    public BaseBinaryTree(T root) {
        this.root = new Node<>(root);
    }

    @Override
    public void append(T element) {
        if (root == null) {
            root = new Node<>(element);
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        Node<T> newNode = new Node<>(element);
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (!temp.hasLeft()) {
                temp.setLeft(newNode);
                return;
            } else {
                nodeQueue.add(temp.getLeft());
            }
            if (!temp.hasRight()) {
                temp.setRight(newNode);
                return;
            } else {
                nodeQueue.add(temp.getRight());
            }
        }
    }

    @Override
    public void eject() throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Deque<Node<T>> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);
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
            } else {
                parent.setLeft(null);
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
    public void set(int id, T object) throws IndexOutOfBoundsException {

    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException {
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
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
        return null;
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {

    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return -1;
    }

    @Override
    public boolean exist(T element) {
        return false;
    }

    @Override
    public void travel() {

    }

    public void balance() {

    }

    public int getHeight() {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        int height = 0;
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
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
        return height;
    }

    @Override
    public String toString() {
        TreePrinter<Node<T>> tp = new TreePrinter<>(n -> n.getValue().toString(), Node::getLeft, Node::getRight);
        tp.printTree(root);
        return tp.toString();
    }
}

final class Node<T> {
    private int key;

    private T value;

    private Node<T> left;

    private Node<T> right;

    Node(T value) {
        this.key = this.hashCode();
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

    @Override
    public int hashCode() {
        int h = value.hashCode();
        h = h * 31 + Objects.hashCode(left);
        h = h * 31 + Objects.hashCode(right);
        return h;
    }
}
