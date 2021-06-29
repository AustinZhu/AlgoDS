package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.*;

import java.util.Arrays;

/**
 * @author Austin Zhu
 */
public abstract class BaseArray<T extends Comparable<T>> implements Operatable<T>, Searchable<T>, Sortable, Interactable<T> {

    protected int length;

    protected int upperBound;

    protected int lowerBound;

    protected T[] data;

    @SuppressWarnings("unchecked")
    protected BaseArray(int capacity) {
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
        updateLength();
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
    public void join(BaseArray<T> another) {
        int capacity = this.getLength() + another.getLength();
        T[] newArray = (T[]) new Comparable[capacity];
        if (this.getLength() >= 0) {
            System.arraycopy(data, lowerBound, newArray, 0, getLength());
            System.arraycopy(another.data, lowerBound, newArray, getLength(), another.getLength());
        }
        data = newArray;
        upperBound = capacity;
        updateLength();
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {
        if (id >= lowerBound && id < upperBound) {
            data[id] = null;
        }
        throw new IndexOutOfBoundsException("Index out of bound");
    }

    @Override
    public boolean exist(T element) {
        return search(element, SearchingAlgorithm.BINARY) >= 0;
    }

    @Override
    public void sort() {
        try {
            sort(SortingAlgorithm.QUICK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public BaseArray<T> slice(int init, int end) throws IndexOutOfBoundsException {
        if (init > upperBound || end < lowerBound || init > end) {
            throw new IndexOutOfBoundsException("Wrong bound");
        }
        setLowerBound(init);
        setUpperBound(end);
        return this;
    }

    public int getLength() {
        return length;
    }

    private void updateLength() {
        this.length = upperBound - lowerBound;
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        if (data.length <= this.data.length) {
            System.arraycopy(data, 0, this.data, lowerBound, data.length);
        } else {
            throw new IndexOutOfBoundsException("Data to large for this array");
        }
    }

    public void replaceBy(BaseArray<T> source) {
        data = source.data;
        lowerBound = source.lowerBound;
        upperBound = source.upperBound;
        length = source.length;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
        updateLength();
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
        updateLength();
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
        return Arrays.toString(data);
    }
}
