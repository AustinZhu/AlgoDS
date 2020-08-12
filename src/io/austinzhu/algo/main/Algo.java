package io.austinzhu.algo.main;

import io.austinzhu.algo.interfaces.SortingAlgorithm;
import io.austinzhu.algo.structure.array.Array;
import io.austinzhu.algo.structure.array.BaseArray;

import java.util.Random;
import java.util.Scanner;

public class Algo {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Choose the data structure: \n(1) Array\n(2) Heap\n(3) List\n(4) Tree\n(5) Network");
        // todo hashmap selection (serializable)
        switch (scanner.nextInt()) {
            case 1 -> {
                System.out.println("2. Choose one data structure below: \n(1) Array\n(2) Matrix\n(3) Queue\n(4) Stack\n(5) String");
                switch (scanner.nextInt()) {
                    case 1 -> System.out.println("3. Initializing Array...");
                }
            }
            case 2 -> System.out.println("2. Choose one data structure below: \n1.Heap\n2.Beap\n3.BinaryHeap\n4.Binomial Heap\n5.Fibonacci Heap\n6.Treap");
            default -> menu();
        }
    }

    public static void main(String[] args) {
        menu();
        BaseArray<Integer> array= new Array<>(0);
        array.init();
        System.out.println("This is your Array: ".concat(array.toString()));
        array.sort(SortingAlgorithm.BUBBLE);
        System.out.println(array);
    }
}
