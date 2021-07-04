package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.interfaces.SortingAlgorithm;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayTest {

    Integer[] generateIntegerArray() {
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int capacity = 20;
        Integer[] integerArray = new Integer[capacity];
        for (var i = 0; i < capacity; i++) {
            integerArray[i] = random.nextInt(35);
        }
        return integerArray;
    }

    @Test
    void sort() throws NoSuchAlgorithmException {
        var arr = generateIntegerArray();
        var myArr = new Array<Integer>(20);
        myArr.fill(arr);
        Arrays.sort(arr);
        myArr.sort(SortingAlgorithm.RADIX);
        assertEquals(Arrays.toString(arr), myArr.toString());
    }
}