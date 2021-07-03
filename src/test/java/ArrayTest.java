import io.austinzhu.algo.interfaces.SortingAlgorithm;
import io.austinzhu.algo.structure.array.Array;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayTest {

    @Test
    void sort() throws NoSuchAlgorithmException {
        var a = new Integer[1500];
        for (int i = 0; i < a.length; i++) {
            a[i] = SecureRandom.getInstanceStrong().nextInt(50000);
        }
        var arr = new Array<Integer>(1500);
        arr.fromArray(a);
        arr.sort(SortingAlgorithm.QUICK);
        var a1 = arr.toArray();
        Arrays.sort(a);
        assertEquals(a, a1);
    }
}