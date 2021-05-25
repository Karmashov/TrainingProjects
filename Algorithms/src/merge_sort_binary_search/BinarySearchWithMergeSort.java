package merge_sort_binary_search;

public class BinarySearchWithMergeSort<T extends Comparable<T>> {
    private T[] list;

    public BinarySearchWithMergeSort(T[] list) {
        this.list = list;
    }

    public int search(T query) {
        return search(query, 0, list.length - 1);
    }

    public T[] getList() {
        return list;
    }

    private int search(T query, int from, int to) {
        mergeSort(list);
        int middle = from + (to - from) / 2;
        if (from <= to) {
            int comparison = query.compareTo(list[middle]);
            if (comparison == 0) {
                return middle;
            } else if (comparison > 0) {
                return search(query, middle + 1, to);
            } else {
                return search(query, from, middle);
            }
        }
        return -1;
    }

    private void mergeSort(T[] array) {
        if (array.length < 2) {
            return;
        }
        int n = array.length;
        int middle = n / 2;
        T[] leftArray = (T[]) new Comparable[middle];
        T[] rightArray = (T[]) new Comparable[n - middle];

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

    private void merge(T[] array, T[] left, T[] right) {
        int leftPos = 0;
        int rightPos = 0;
        for (int i = 0; i < (left.length + right.length); i++){
            if (leftPos == left.length){
                array[i] = (T) right[rightPos];
                rightPos++;
            } else if (rightPos == right.length){
                array[i] = (T) left[leftPos];
                leftPos++;
            } else if (left[leftPos].compareTo(right[rightPos]) < 0){
                array[i] = left[leftPos];
                leftPos++;
            } else {
                array[i] = right[rightPos];
                rightPos++;
            }
        }
    }
}
