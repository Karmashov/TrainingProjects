package merge_sort;

public class MergeSort
{
    public static void mergeSort(int[] array)
    {
        if (array.length < 2) {
            return;
        }

        int n = array.length;
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right)
    {
        int leftPos = 0;
        int rightPos = 0;
        for (int i = 0; i < (left.length + right.length); i++){
            if (leftPos == left.length){
                array[i] = right[rightPos];
                rightPos++;
            } else if (rightPos == right.length){
                array[i] = left[leftPos];
                leftPos++;
            } else if (left[leftPos] < right[rightPos]){
                array[i] = left[leftPos];
                leftPos++;
            } else {
                array[i] = right[rightPos];
                rightPos++;
            }
        }
    }
}
