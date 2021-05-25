package merge_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestMergeSort {
    private MergeSort mergeSort;
    private int size = 1000;
    private int[] array = new int[size];

    @Test
    public void testMergeSort() {
        mergeSort = new MergeSort();

        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 500);
        }

        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        mergeSort.mergeSort(array);

        Assert.assertArrayEquals(array, sortedArray);
    }
}
