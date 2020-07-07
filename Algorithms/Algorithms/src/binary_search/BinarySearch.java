package binary_search;

import java.util.ArrayList;

public class BinarySearch
{
    private ArrayList<String> list;

    public BinarySearch(ArrayList<String> list)
    {
        this.list = list;
    }

    public int search(String query)
    {
        return search(query, 0, list.size() - 1);
    }

    private int search(String query, int from, int to)
    {
        list.sort(String::compareTo);
        int middle = from + (to - from) / 2;
        if (from <= to) {
            int comparison = query.compareTo(list.get(middle));
            if (comparison == 0) {
                return middle;
            } else if (comparison > 0) {
                return search(query, middle + 1, to);
            } else if (comparison < 0) {
                return search(query, from, middle);
            }
        }
        return -1;

        //или вот так (так как в условии сказано что в случае отсутствия элемента возвращать "-1", добавил блок if)
//        int result = Collections.binarySearch(list, query);
//        if (result < 0) {
//            return -1;
//        }
//        return result;
    }
}
