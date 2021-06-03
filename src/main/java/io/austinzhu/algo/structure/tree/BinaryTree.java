package io.austinzhu.algo.structure.tree;

import java.util.Random;

public class BinaryTree<T> extends BaseBinaryTree<T>{

    public BinaryTree() {
        super();
    }

    public BinaryTree(Node<T> root) {
        super(root);
    }

    public static BinaryTree<Integer> init(int size, int bound) {
        Random random = new Random();
        int capacity = random.nextInt(size);
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for (int i = 0; i < capacity; i++) {
            binaryTree.append(random.nextInt(bound));
        }
        return binaryTree;
    }
}
