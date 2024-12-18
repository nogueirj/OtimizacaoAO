/**
 * Prompt: Um código em Java usando JOMP para o paralelismo de um código de ordenação usando o método bublesort
 */
import jomp.runtime.OMP;

public class CopilotJOMPBubleSort {

    public static void parallelBubbleSort(int[] array) {
        int n = array.length;
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            //!OMP PARALLEL FOR SHARED(array) PRIVATE(i) REDUCTION(||:swapped)
            for (int i = 0; i < n - 1; i++) {
                if (array[i] > array[i + 1]) {
                    // Troca os elementos
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 8, 4, 2, 7, 1, 10};
        parallelBubbleSort(array);

        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}