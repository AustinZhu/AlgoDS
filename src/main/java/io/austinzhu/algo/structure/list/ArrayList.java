package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

public final class ArrayList<T extends Comparable<T>> implements List<T>, Queue<T>, Stack<T> {

    public static ArrayList<Integer> init(int size, int bound, Random random) {
        return null;
    }

    @Override
    public void fill(T... elements) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void set(int idx, T object) {

    }

    @Override
    public T get(int idx) {
        return null;
    }

    @Override
    public void insert(int idx, T object) {

    }

    @Override
    public T delete(int idx) {
        return null;
    }

    @Override
    public void append(T element) {

    }

    @Override
    public T eject() {
        return null;
    }

    @Override
    public void prepend(T element) {

    }

    @Override
    public T pop() {
        return null;
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
    public int search(T element, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
        return 0;
    }

    @Override
    public int search(T element, int start, int end, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
        return 0;
    }

    @Override
    public boolean exist(T element) {
        return false;
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(int start, int end) {

    }

    @Override
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }

    @Override
    public void sort(int start, int end, SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }

    @Override
    public T head() {
        return null;
    }

    @Override
    public T last() {
        return null;
    }
}
