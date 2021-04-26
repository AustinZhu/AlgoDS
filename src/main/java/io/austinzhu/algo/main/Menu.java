package io.austinzhu.algo.main;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.lang.reflect.Method;
import java.util.*;

public class Menu {

    private static final String[] CATEGORIES = new String[]{"Array", "Heap", "List", "Tree", "Network"};

    private static final HashMap<String, String[]> DATA_STRUCTURE_MAP =
            new HashMap<>(Map.of("Array", new String[]{"Array", "Matrix"},
                    "Heap", new String[]{"Beap", "BinaryHeap", "BinomialHeap", "FibonacciHeap", "Treap"},
                    "List", new String[]{"ArrayList", "DoublyLinkedList", "LinkedList", "Queue", "Stack", "UnrolledLinkedList"},
                    "Network", new String[]{"Graph"},
                    "Tree", new String[]{"AVLTree", "BinarySearchTree", "BinaryTree", "RedBlackTree"}));

    private void printCategory() {
        StringBuilder builder = new StringBuilder("1. Choose the category: ");
        for (int i = 0; i < CATEGORIES.length; i++) {
            builder.append("\n").append("(").append(i + 1).append(") ").append(CATEGORIES[i]);
        }
        System.out.println(builder);
    }

    private void printSpecies(String category) {
        StringBuilder builder = new StringBuilder("2. Choose one data structure: ");
        String[] species = DATA_STRUCTURE_MAP.get(category);
        for (int i = 0; i < species.length; i++) {
            builder.append("\n").append("(").append(i + 1).append(") ").append(species[i]);
        }
        System.out.println(builder);
    }

    private Object dataStructureFactory(String category, String name) throws Exception {
        String className = "io.austinzhu.algo.structure." +
                category.toLowerCase() + "." +
                name;
        Class<?> clazz = Class.forName(className);
        int size = 20, bound = 100;
        Object dataStructure = clazz.getDeclaredMethod("init", Integer.TYPE, Integer.TYPE)
                .invoke(null, size, bound);
        System.out.println("This is your " + name + ": \n" + dataStructure);
        return dataStructure;
    }

    private Method[] getMethods(Class<?> clazz) {
        Method[] allMethods = clazz.getDeclaredMethods();
        List<Method> algorithms = new ArrayList<>();
        StringBuilder builder = new StringBuilder("4. Choose a method: ");
        int i = 1;
        for (Method method : allMethods) {
            if (method.getAnnotation(Algorithm.class) != null) {
                algorithms.add(method);
                builder.append("\n").append("(").append(i).append(") ").append(method.getName());
                i++;
            }
        }
        System.out.println(builder);
        return algorithms.toArray(new Method[0]);
    }

    private SortingAlgorithm sortSelection(Scanner scanner) {
        System.out.println("Select your preferred sorting algorithm: ");
        StringBuilder builder = new StringBuilder();
        SortingAlgorithm[] values = SortingAlgorithm.values();
        for (int i = 0; i < values.length; i++) {
            builder.append("\n").append("(").append(i + 1).append(") ").append(values[i]);
        }
        System.out.println(builder);
        int selection = scanner.nextInt();
        return values[selection - 1];
    }

    public static void start() throws Exception {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        menu.printCategory();
        int firstSelection = scanner.nextInt();
        String category = CATEGORIES[firstSelection - 1];
        menu.printSpecies(category);
        int secondSelection = scanner.nextInt();
        String name = DATA_STRUCTURE_MAP.get(category)[secondSelection - 1];
        System.out.println("3. Initializing " + name);
        Object dataStructure = menu.dataStructureFactory(category, name);
        Method[] methods = menu.getMethods(dataStructure.getClass());
        if (methods.length == 0) {
            return;
        }
        int thirdSelection = scanner.nextInt();
        Method selectedMethod = methods[thirdSelection - 1];
        if ("search".equals(selectedMethod.getName())) {
            int element = scanner.nextInt();
            Integer result = (Integer) selectedMethod.invoke(dataStructure, element, SearchingAlgorithm.BINARY);
            if (result.equals(-1)) {
                System.out.println("Element not found");
            }
            System.out.println("Element at position: " + result);
        }
        if ("sort".equals(selectedMethod.getName())) {
            SortingAlgorithm algorithm = menu.sortSelection(scanner);
            selectedMethod.invoke(dataStructure, algorithm);
            System.out.println("After sorting: ");
            System.out.println(dataStructure);
        }
        if ("delete".equals(selectedMethod.getName())) {
            int element = scanner.nextInt();
            selectedMethod.invoke(dataStructure, element);
            try {
                System.out.println("After deletion: ");
                System.out.println(dataStructure);
            } catch (ElementNotFoundException e1) {
                System.out.println(e1.getMessage());
            }
        }
        if ("append".equals(selectedMethod.getName())) {
            int element = scanner.nextInt();
            selectedMethod.invoke(dataStructure, element);
            try {
                System.out.println("After append: ");
                System.out.println(dataStructure);
            } catch (ElementNotFoundException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
}
