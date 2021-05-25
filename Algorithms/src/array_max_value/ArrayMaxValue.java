package array_max_value;

public class ArrayMaxValue
{
    public static int getMaxValue(int[] values) throws ArrayIndexOutOfBoundsException
    {
        int maxValue = values[0];
        for(int value : values)
        {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}