package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.*;

import java.util.Random;

/**
 * @author Austin Zhu
 */
public abstract class BaseArray<T extends Comparable<T>> implements Traversable, Operatable<T>, Searchable<T>, Sortable, Interactable<T> {

    private int length;

    private int upperBound;

    private int lowerBound;

    private T[] data;

    @SuppressWarnings("unchecked")
    public BaseArray(int capacity) {
        this.length = 0;
        this.lowerBound = 0;
        this.upperBound = 0;
        this.data = (T[]) new Comparable[capacity];
    }

    @Override
    public void append(T element) throws IndexOutOfBoundsException {
        if (lowerBound + length > upperBound) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        data[upperBound] = element;
        upperBound++;
        updateLength();
    }

    @Override
    public void eject() throws IndexOutOfBoundsException {
        if (upperBound - lowerBound <= 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        data[upperBound] = null;
        upperBound--;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        Random random = new Random();
        int capacity = random.nextInt(20);
        Integer[] intArr = new Integer[capacity];
        for (int i = 0; i < capacity; i++) {
            intArr[i] = random.nextInt(100);
            upperBound++;
            updateLength();
        }
        data = (T[]) intArr;
    }

    @SafeVarargs
    @Override
    public final void init(T... elements) {
        for (T e : elements) {
            try {
                append(e);
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException {
        if (id >= lowerBound && id < upperBound) {
            data[id] = object;
        } else {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException {
        if (id >= lowerBound && id < upperBound) {
            return data[id];
        }
        throw new IndexOutOfBoundsException("Index out of bound");
    }

    @SuppressWarnings("unchecked")
    public BaseArray<T> join(BaseArray<T> another) {
        int capacity = this.getLength() + another.getLength();
        T[] newArray = (T[]) new Comparable[capacity];
        if (this.getLength() >= 0) {
            System.arraycopy(data, lowerBound, newArray, 0, getLength());
            System.arraycopy(another.data, lowerBound, newArray, getLength(), another.getLength());
        }
        data = newArray;
        return this;
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {
        if (id >= lowerBound && id < upperBound) {
            data[id] = null;
        }
        throw new IndexOutOfBoundsException("Index out of bound");
    }

    @Override
    public abstract boolean search(T element, SearchingAlgorithm sa);

    @Override
    public boolean exist(T element) {
        return search(element, SearchingAlgorithm.BINARY);
    }

    @Override
    public void sort() {
        try {
            sort(SortingAlgorithm.QUICK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException;

    @Override
    public void travel() {
        for (int i = lowerBound; i < upperBound; i++) {
        }
    }

    public int getLength() {
        return length;
    }

    public void updateLength() {
        this.length = upperBound - lowerBound;
        ;
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append('[');
        for (int i = lowerBound; i < upperBound - 1; i++) {
            stringBuilder.append(data[i]).append(", ");
        }
        return stringBuilder.append(data[upperBound - 1]).append(']').toString();
    }
}
