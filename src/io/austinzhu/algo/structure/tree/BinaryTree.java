package io.austinzhu.algo.structure.tree;

import java.util.Random;

public class BinaryTree<T> extends BaseBinaryTree<T>{

    public BinaryTree() {
        super();
    }

    public BinaryTree(Node<T> root) {
        super(root);
    }

    public static BinaryTree<Integer> init() {
        Random random = new Random();
        int capacity = random.nextInt(20);
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for (int i = 0; i < capacity; i++) {
            binaryTree.append(random.nextInt(100));
        }
        return binaryTree;
    }
}
