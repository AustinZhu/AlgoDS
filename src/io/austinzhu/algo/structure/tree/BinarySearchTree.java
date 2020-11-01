package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.util.Random;

public class BinarySearchTree<T> extends BaseBinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(Node<T> root) {
        super(root);
    }

    public static BinarySearchTree<Integer> init(int size, int bound) {
        Random random = new Random();
        int capacity = random.nextInt(size);
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        for (int i = 0; i < capacity; i++) {
            binaryTree.append(random.nextInt(bound));
        }
        return binaryTree;
    }

    @Algorithm
    @Override
    public void append(T element) {
        Node<T> newNode = new Node<>(element);
        Node<T> iterator = getRoot();
        while (iterator != null) {
            if (newNode.getKey() < iterator.getKey()){
                if (!iterator.hasLeft()) {
                    iterator.setLeft(newNode);
                    return;
                }
                iterator = iterator.getLeft();
            } else if (newNode.getKey() > iterator.getKey()) {
                if (!iterator.hasRight()) {
                    iterator.setRight(newNode);
                    return;
                }
                iterator = iterator.getRight();
            } else {
                return;
            }
        }
        setRoot(newNode);
    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException, ElementNotFoundException {
        super.set(id, object);
    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        return super.get(id);
    }

    @Algorithm
    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        Node<T> parent = null;
        Node<T> iterator = getRoot();
        boolean isLeft = false;
        while (iterator.getKey() != id) {
            parent = iterator;
            if (iterator.getKey() > id) {
                if (!iterator.hasLeft()) {
                    throw new ElementNotFoundException("Not found");
                }
                iterator = iterator.getLeft();
                isLeft = true;
            } else {
                if (!iterator.hasRight()) {
                    throw new ElementNotFoundException("Not found");
                }
                iterator = iterator.getRight();
                isLeft = false;
            }
        }
        boolean isRoot = parent == null;
        // Case 1: Node to be deleted has no child
        if (iterator.isLeaf()) {
            if (isRoot) {
                setRoot(null);
                return;
            }
            if (isLeft) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
            return;
        }
        // Case 2: Node to be deleted has only left child
        if (iterator.hasLeft() && !iterator.hasRight()) {
            if (isRoot) {
                setRoot(iterator.getLeft());
                return;
            }
            if (isLeft) {
                parent.setLeft(iterator.getLeft());
            } else {
                parent.setRight(iterator.getRight());
            }
            return;
        }
        // Case 3: Node to be deleted has only right child
        if (iterator.hasRight() && !iterator.hasLeft()) {
            if (isRoot) {
                setRoot(iterator.getRight());
                return;
            }
            if (isLeft) {
                parent.setLeft(iterator.getRight());
            } else {
                parent.setRight(iterator.getRight());
            }
            return;
        }
        // Case 4: Node to be deleted has both children
        if (iterator.hasLeft() && iterator.hasRight()) {
            Node<T> succ = iterator.ejectSuccessor();
            succ.setLeft(iterator.getLeft());
            succ.setRight(iterator.getRight());
            if (isRoot) {
                setRoot(succ);
                return;
            }
            if (isLeft) {
                parent.setLeft(succ);
            } else {
                parent.setRight(succ);
            }
        }
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return super.search(element, sa);
    }

    @Override
    public boolean exist(T element) {
        return super.exist(element);
    }
}