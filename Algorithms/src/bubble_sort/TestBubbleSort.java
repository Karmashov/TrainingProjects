package bubble_sort;

import org.junit.Test;

import java.util.Arrays;

public class TestBubbleSort {
    private int size = 1000;
    private int[] array = new int[size];

    @Test
    public void testBubbleSort() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 500);
        }
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);

        BubbleSort.sort(array);

        assert Arrays.equals(sortedArray, array);
    }
}
