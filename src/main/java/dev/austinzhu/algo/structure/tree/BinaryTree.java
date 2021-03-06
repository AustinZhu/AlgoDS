package dev.austinzhu.algo.structure.tree;

import dev.austinzhu.algo.interfaces.Algorithm;
import dev.austinzhu.algo.interfaces.SearchingAlgorithm;
import dev.austinzhu.algo.structure.Tree;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public sealed class BinaryTree<T>
        implements Tree<T>
        permits BinarySearchTree {

    private Node<T> root;

    int size;

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
    public T eject() throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Deque<Node<T>> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);
        Node<T> parent = null;
        Node<T> last = null;
        T del = null;
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
            del = last.value;
            if (parent.hasRight()) {
                parent.right = null;
                size--;
            } else {
                parent.left = null;
                size--;
            }
        }
        return del;
    }

    @Override
    public void prepend(T element) throws IndexOutOfBoundsException {

    }

    @Override
    public T pop() throws IndexOutOfBoundsException {
        return null;
    }

    @SafeVarargs
    @Override
    public final void fill(T... elements) {
        for (T elem : elements) {
            append(elem);
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void set(int idx, T object) throws IndexOutOfBoundsException, NoSuchElementException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.key == idx) {
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
        throw new NoSuchElementException("Not found");
    }

    @Override
    public T get(int idx) throws IndexOutOfBoundsException, NoSuchElementException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null root");
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            if (temp.key == idx) {
                return temp.value;
            }
            if (temp.hasLeft()) {
                nodeQueue.add(temp.left);
            }
            if (temp.hasRight()) {
                nodeQueue.add(temp.right);
            }
        }
        throw new NoSuchElementException("Not Found");
    }

    @Override
    public void insert(int idx, T object) throws IndexOutOfBoundsException {

    }

    @Algorithm
    @Override
    public T delete(int idx) throws IndexOutOfBoundsException, NoSuchElementException {
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
                if (left.key == idx) {
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
                if (right.key == idx) {
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
                lastParent.right = null;
                size--;
            } else if (lastParent.hasLeft()) {
                lastParent.left = null;
                size--;
            }
            last.left = (deleted.left);
            last.right = (deleted.right);
            if (deletedParent.left.key == idx) {
                deletedParent.left = last;
                return null; //fixme
            }
            if (deletedParent.right.key == idx) {
                deletedParent.right = last;
                return null; //fixme
            }
        }
        throw new NoSuchElementException("No such index");
    }

    @Override
    public int search(T element) {
        return 0;
    }

    @Override
    public int search(T element, int start, int end) {
        return 0;
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
    public int search(T element, int start, int end, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
        return 0;
    }

    @Override
    public boolean exist(T element) {
        return search(element, SearchingAlgorithm.BFS) != -1;
    }

    @Override
    public int max() {
        return 0;
    }

    @Override
    public int max(int start, int end) {
        return 0;
    }

    @Override
    public int min() {
        return 0;
    }

    @Override
    public int min(int start, int end) {
        return 0;
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

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        TreePrinter<Node<T>> tp = new TreePrinter<>(n -> n.value.toString(), Node::getLeft, Node::getRight);
        tp.printTree(root);
        return tp.toString();
    }

    static sealed class Node<T> extends Tree.Node<T> permits AVLTree.Node, RedBlackTree.Node {

        Node<T> left;

        Node<T> right;

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
