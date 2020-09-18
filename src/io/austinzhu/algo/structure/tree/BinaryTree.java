package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;

import java.util.Random;

public class BinaryTree<T> extends BaseBinaryTree<T>{

    public BinaryTree(T root) {
        super(root);
    }

    @Override
    @Algorithm
    public void eject() throws IndexOutOfBoundsException {
        super.eject();
    }

    public static BinaryTree<Integer> init() {
        Random random = new Random();
        int capacity = random.nextInt(20);
        BinaryTree<Integer> binaryTree = new BinaryTree<>(null);
        for (int i = 0; i < capacity; i++) {
            binaryTree.append(random.nextInt(100));
        }
        return binaryTree;
    }
}
