/**
 * Prompt: Um código em Java usando paralelismo de um código de ordenação usando o método bublesort
 */
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class CopilotParallelBubbleSort {

    private static class BubbleSortTask extends RecursiveAction {
        private final int[] array;
        private final int start;
        private final int end;

        public BubbleSortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= 1) {
                return;
            }

            boolean swapped;
            do {
                swapped = false;
                for (int i = start; i < end - 1; i++) {
                    if (array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        swapped = true;
                    }
                }
            } while (swapped);

            int mid = (start + end) / 2;
            invokeAll(new BubbleSortTask(array, start, mid), new BubbleSortTask(array, mid, end));
        }
    }

    public static void parallelBubbleSort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new BubbleSortTask(array, 0, array.length));
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 8, 4, 2, 7, 1, 10};
        parallelBubbleSort(array);

        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}