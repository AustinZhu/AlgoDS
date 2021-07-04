package io.austinzhu.algo.main;

import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class Menu {

    private static final String[] CATEGORIES = new String[]{"Array", "Map", "Heap", "List", "Tree", "Network"};

    private static final HashMap<String, String[]> DATA_STRUCTURE_MAP =
            new HashMap<>(Map.of("Array", new String[]{"Array", "Matrix"},
                    "Heap", new String[]{"Beap", "BinaryHeap", "BinomialHeap", "FibonacciHeap", "Treap"},
                    "List", new String[]{"ArrayList", "DoublyLinkedList", "LinkedList", "Queue", "Stack", "UnrolledLinkedList"},
                    "Network", new String[]{"MatrixGraph", "ListGraph"},
                    "Map", new String[]{"HashMap"},
                    "Tree", new String[]{"AVLTree", "BinarySearchTree", "BinaryTree", "RedBlackTree"}));

    private final Random rand;

    private Menu() throws NoSuchAlgorithmException {
        this.rand = SecureRandom.getInstanceStrong();
    }

    private void printCategory() {
        var builder = new StringBuilder("1. Choose the category: ");
        for (var i = 0; i < CATEGORIES.length; i++) {
            builder.append("\n").append("(").append(i + 1).append(") ").append(CATEGORIES[i]);
        }
        System.out.println(builder);
    }

    private void printSpecies(String category) {
        var builder = new StringBuilder("2. Choose one data structure: ");
        String[] variants = DATA_STRUCTURE_MAP.get(category);
        for (var i = 0; i < variants.length; i++) {
            builder.append("\n").append("(").append(i + 1).append(") ").append(variants[i]);
        }
        System.out.println(builder);
    }

    private Object dataStructureFactory(String category, String name) throws Exception {
        String className = "io.austinzhu.algo.structure." + category.toLowerCase() + "." + name;
        Class<?> clazz = Class.forName(className);
        int size = 20, bound = 100;
        Object dataStructure = clazz.getDeclaredMethod("init", Integer.TYPE, Integer.TYPE, Random.class)
                .invoke(null, size, bound, this.rand);
        System.out.println("This is your " + name + ": \n" + dataStructure);
        return dataStructure;
    }

    private Method[] getMethods(Class<?> clazz) {
        Method[] allMethods = clazz.getDeclaredMethods();
        Map<String, Method> algorithms = new HashMap<>();
        var builder = new StringBuilder("4. Choose a method: ");
        var i = 1;
        for (Method method : allMethods) {
            if (method.getAnnotation(Algorithm.class) != null && !algorithms.containsKey(method.getName())) {
                algorithms.put(method.getName(), method);
                builder.append("\n(").append(i).append(") ").append(method.getName());
                i++;
            }
        }
        System.out.println(builder);
        return algorithms.values().toArray(new Method[0]);
    }

    private SortingAlgorithm sortSelection(Scanner scanner) {
        System.out.println("Select your preferred sorting algorithm: ");
        var builder = new StringBuilder();
        SortingAlgorithm[] values = SortingAlgorithm.values();
        for (var i = 0; i < values.length; i++) {
            builder.append("\n").append("(").append(i + 1).append(") ").append(values[i]);
        }
        System.out.println(builder);
        var selection = scanner.nextInt();
        return values[selection - 1];
    }

    public static void start() throws Exception {
        var menu = new Menu();
        var scanner = new Scanner(System.in);
        menu.printCategory();
        var firstSelection = scanner.nextInt();
        String category = CATEGORIES[firstSelection - 1];
        menu.printSpecies(category);
        var secondSelection = scanner.nextInt();
        String name = DATA_STRUCTURE_MAP.get(category)[secondSelection - 1];
        System.out.println("3. Initializing " + name);
        Object dataStructure = menu.dataStructureFactory(category, name);
        Method[] methods = menu.getMethods(dataStructure.getClass());
        if (methods.length == 0) {
            return;
        }
        var thirdSelection = scanner.nextInt();
        var selectedMethod = methods[thirdSelection - 1];
        switch (selectedMethod.getName()) {
            case "search" -> {
                var element = scanner.nextInt();
                Integer result = (Integer) selectedMethod.invoke(dataStructure, element, SearchingAlgorithm.LINEAR);
                if (result.equals(-1)) {
                    System.out.println("Element not found");
                }
                System.out.println("Element at position: " + result);
            }
            case "sort" -> {
                SortingAlgorithm algorithm = menu.sortSelection(scanner);
                selectedMethod.invoke(dataStructure, algorithm);
                System.out.println("After sorting: ");
                System.out.println(dataStructure);
            }
            case "delete" -> {
                var element = scanner.nextInt();
                selectedMethod.invoke(dataStructure, element);
                try {
                    System.out.println("After deletion: ");
                    System.out.println(dataStructure);
                } catch (NoSuchElementException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            case "append" -> {
                var element = scanner.nextInt();
                selectedMethod.invoke(dataStructure, element);
                try {
                    System.out.println("After append: ");
                    System.out.println(dataStructure);
                } catch (NoSuchElementException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            case "eject" -> {
                selectedMethod.invoke(dataStructure);
                try {
                    System.out.println("After ejection: ");
                    System.out.println(dataStructure);
                } catch (NoSuchElementException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            case "reverse" -> {
                selectedMethod.invoke(dataStructure);
                try {
                    System.out.println("After reversing: ");
                    System.out.println(dataStructure);
                } catch (NoSuchElementException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            case "searchPath" -> {
                var v1 = scanner.nextInt();
                var v2 = scanner.nextInt();
                List<Integer> result = (List<Integer>) selectedMethod.invoke(dataStructure, v1, v2, SearchingAlgorithm.DFS);
                if (result == null) {
                    System.out.println("Path not found");
                }
                System.out.println("Path: " + result);
            }
            default -> {
            }
        }
    }
}
