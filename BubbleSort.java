import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(array));

        bubbleSort(array);

        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        long startTime = System.currentTimeMillis();
        for (int phase = 0; phase < n; phase++) {
            int phaseType = phase % 2;
            for (int i = 0; i < n - 1; i++) {
                if ((i % 2 == phaseType) && array[i] > array[i + 1]) {
                    // Swap
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo gasto: " + (endTime - startTime) + " ms");
    }
}

