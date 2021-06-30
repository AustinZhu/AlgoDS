package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.util.*;

public sealed class BinaryTree<T>
        implements Tree<T>
        permits BinarySearchTree {

    private Node<T> root;

    protected int size;

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public static BinaryTree<Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for (var i = 0; i < capacity; i++) {
            binaryTree.append(random.nextInt(bound));
        }
        return binaryTree;
    }

    @Algorithm
    @Override
    public void append(T element) {
        if (root == null) {
            setRoot(new Node<>(element));
            return;
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        Node<T> newNode = new Node<>(element);
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (!temp.hasLeft()) {
                temp.left = newNode;
                size++;
                return;
            } else {
                nodeQueue.add(temp.left);
            }
            if (!temp.hasRight()) {
                temp.right = newNode;
                size++;
                return;
            } else {
                nodeQueue.add(temp.right);
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
                Node<T> left = temp.left;
                nodeQueue.add(left);
                if (left.isLeaf()) {
                    parent = temp;
                    last = left;
                }
            }
            if (temp.hasRight()) {
                Node<T> right = temp.right;
                nodeQueue.add(right);
                if (right.isLeaf()) {
                    parent = temp;
                    last = right;
                }
            }
        }
        if (last != null) {
            if (parent.hasRight()) {
                parent.right = null;
                size--;
            } else {
                parent.left = null;
                size--;
            }
        }
    }

    @SafeVarargs
    @Override
    public final void init(T... elements) {
        for (T elem : elements) {
            append(elem);
        }
    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.key == id) {
                temp.value = object;
                return;
            }
            if (temp.hasLeft()) {
                nodeQueue.add(temp.left);
            }
            if (temp.hasRight()) {
                nodeQueue.add(temp.right);
            }
        }
        throw new ElementNotFoundException("Not found");
    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.key == id) {
                return temp.value;
            }
            if (temp.hasLeft()) {
                nodeQueue.add(temp.left);
            }
            if (temp.hasRight()) {
                nodeQueue.add(temp.right);
            }
        }
        throw new ElementNotFoundException("Not Found");
    }

    @Algorithm
    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null head");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        Node<T> last = null, lastParent = null;
        Node<T> deleted = null, deletedParent = null;
        while (!nodeQueue.isEmpty()) {
            Node<T> iterator = nodeQueue.remove();
            if (iterator.hasLeft()) {
                Node<T> left = iterator.left;
                if (left.key == id) {
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
                Node<T> right = iterator.right;
                if (right.key == id) {
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
                lastParent.right = (null);
                size--;
            } else if (lastParent.hasLeft()) {
                lastParent.left = (null);
                size--;
            }
            last.left = (deleted.left);
            last.right = (deleted.right);
            if (deletedParent.left.key == id) {
                deletedParent.left = (last);
                return;
            }
            if (deletedParent.right.key == id) {
                deletedParent.right = (last);
                return;
            }
        }
        throw new ElementNotFoundException("No such index");
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.value == element) {
                return temp.key;
            }
            if (temp.hasLeft()) {
                nodeQueue.add(temp.left);
            }
            if (temp.hasRight()) {
                nodeQueue.add(temp.right);
            }
        }
        return -1;
    }

    @Override
    public boolean exist(T element) {
        return search(element, SearchingAlgorithm.BFS) != -1;
    }

    @Override
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
                    nodeQueue.add(temp.left);
                }
                if (temp.hasRight()) {
                    nodeQueue.add(temp.right);
                }
                size--;
            }
        }
        return height;
    }

    public Integer[] inOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        inOrderRecursive(root, list);
        return list.toArray(new Integer[0]);
    }

    public Integer[] preOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        preOrderRecursive(root, list);
        return list.toArray(new Integer[0]);
    }

    public Integer[] postOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        postOrderRecursive(root, list);
        return list.toArray(new Integer[0]);
    }

    public Integer[] levelOrder() {
        if (root == null) {
            return new Integer[]{};
        }
        ArrayList<Integer> list = new ArrayList<>();
        Deque<Node<T>> nodeDeque = new LinkedList<>();
        nodeDeque.addLast(root);
        while (!nodeDeque.isEmpty()) {
            Node<T> tmp = nodeDeque.removeFirst();
            list.add(tmp.key);
            if (tmp.hasLeft()) {
                nodeDeque.addLast(tmp.left);
            }
            if (tmp.hasRight()) {
                nodeDeque.addLast(tmp.right);
            }
        }
        return list.toArray(new Integer[0]);
    }

    @Override
    public String toString() {
        TreePrinter tp = new TreePrinter();
        tp.printTree(root);
        return tp.toString();
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    @Override
    public int getSize() {
        return size;
    }

    private void preOrderRecursive(Node<T> node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.key);
        preOrderRecursive(node.left, list);
        preOrderRecursive(node.right, list);
    }

    private void inOrderRecursive(Node<T> node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        inOrderRecursive(node.left, list);
        list.add(node.key);
        inOrderRecursive(node.right, list);
    }

    private void postOrderRecursive(Node<T> node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        postOrderRecursive(node.left, list);
        postOrderRecursive(node.right, list);
        list.add(node.key);
    }

    public Node<T> ejectPredecessor(Node<T> node) {
        if (!node.hasLeft()) {
            return null;
        }
        Node<T> parent = node;
        Node<T> pred = node.getLeft();
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

    public Node<T> ejectSuccessor(Node<T> node) {
        if (!node.hasRight()) {
            return null;
        }
        Node<T> parent = node;
        Node<T> succ = node.getRight();
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

    public Node<T> leftRotate(Node<T> root) {
        if (root.isLeaf()) {
            return root;
        }
        Node<T> right = root.right;
        root.right = right.left;
        right.left = root;
        return right;
    }

    public Node<T> rightRotate(Node<T> root) {
        if (root.isLeaf()) {
            return root;
        }
        Node<T> left = root.left;
        root.left = left.right;
        left.right = root;
        return left;
    }

    sealed static class Node<T> permits BinarySearchTree.Node {

        int key;

        T value;

        private Node<T> left;

        private Node<T> right;

        Node(T value) {
            this.key = value.hashCode();
            this.value = value;
        }

        Node<T> getLeft() {
            return this.left;
        }

        Node<T> getRight() {
            return this.right;
        }

        void setLeft(Node<T> node) {
            this.left = node;
        }

        void setRight(Node<T> node) {
            this.right = node;
        }

        boolean hasLeft() {
            return left != null;
        }

        boolean hasRight() {
            return right != null;
        }

        boolean isLeaf() {
            return !hasLeft() && !hasRight();
        }
    }
}
