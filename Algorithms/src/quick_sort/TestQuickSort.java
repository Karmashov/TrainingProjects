package quick_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestQuickSort {
    private QuickSort quickSort;
    private int size = 1000;
    private int[] array = new int[size];

    @Test
    public void testQuickSort() {
        quickSort = new QuickSort();

        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 500);
        }

        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        quickSort.sort(array);

        Assert.assertArrayEquals(array, sortedArray);
    }
}
