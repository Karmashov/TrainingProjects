package quick_sort;

public class QuickSort
{
    public static void sort(int[] array)
    {
        if(array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int from, int to)
    {
        if(from < to)
        {
            int pivot = partition(array, from, to);
            sort(array, from, pivot - 1);
            sort(array, pivot + 1, to);
        }
    }

    private static int partition(int[] array, int from, int to)
    {
        int pivot = array[to];
        int i = (from - 1);

        for (int j = from; j < to; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i]= array[j];
                array[j]= temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1]= array[to];
        array[to]= temp;

        return i + 1;
    }

}
