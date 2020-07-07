package merge_sort_binary_search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TestBinarySearchWithMergeSort {
    private BinarySearchWithMergeSort bswm;
    private Integer[] intArray;
    private String[] strArray;

    @Before
    public void setUp(){
        intArray = new Integer[7];
        intArray[0] = 546;
        intArray[1] = 784;
        intArray[2] = 478;
        intArray[3] = 864;
        intArray[4] = 543;
        intArray[5] = 789;
        intArray[6] = 321;

        strArray = new String[7];
        strArray[0] = "dfgs";
        strArray[1] = "sdfg";
        strArray[2] = "cxvn";
        strArray[3] = "tryeu";
        strArray[4] = "erqt";
        strArray[5] = "rtue";
        strArray[6] = "kjl";
    }

    @Test
    public void testStringSupport(){
        bswm = new BinarySearchWithMergeSort(strArray);
        Arrays.sort(strArray);

        //Проверка сортировки
        assert Arrays.equals(strArray, bswm.getList());
        //Проверка поиска
        Assert.assertEquals(2, bswm.search("erqt"));
    }

    @Test
    public void testIntegerSupport(){
        bswm = new BinarySearchWithMergeSort(intArray);
        Arrays.sort(intArray);

        //Проверка сортировки
        assert Arrays.equals(intArray, bswm.getList());
        //Проверка поиска
        Assert.assertEquals(4, bswm.search( 784));
    }

    @Test
    public void testFailOnSearch() {
        bswm = new BinarySearchWithMergeSort(intArray);
        Assert.assertEquals(-1,bswm.search(1111));
    }
}
