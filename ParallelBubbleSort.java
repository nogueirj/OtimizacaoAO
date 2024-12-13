import java.util.Arrays;

public class ParallelBubbleSort {

    public static void main(String[] args) throws InterruptedException {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(array));

        parallelBubbleSort(array);

        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    public static void parallelBubbleSort(int[] array) throws InterruptedException {
        int n = array.length;
        
        // Number of threads to use
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];
        long startTime = System.currentTimeMillis();
        for (int phase = 0; phase < n; phase++) {
            int phaseType = phase % 2;
            for (int t = 0; t < numThreads; t++) {
                final int threadId = t;

                threads[t] = new Thread(() -> {
                    for (int i = threadId; i < n - 1; i += numThreads) {
                        if ((i % 2 == phaseType) && array[i] > array[i + 1]) {
                            // Swap
                            int temp = array[i];
                            array[i] = array[i + 1];
                            array[i + 1] = temp;
                        }
                    }
                });

                threads[t].start();
            }

            // Wait for all threads to complete
            for (Thread thread : threads) {
                thread.join();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo gasto: " + (endTime - startTime) + " ms");
    }
}

