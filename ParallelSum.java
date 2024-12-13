import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ParallelSum {

    // Tarefa Recursiva para dividir o trabalho
    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10_000_000; // Tamanho mínimo para divisão
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            // Se o segmento for pequeno o suficiente, soma diretamente
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                // Divide o trabalho em dois
                int mid = start + (end - start) / 2;
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);

                // Executa as subtarefas em paralelo
                leftTask.fork();
                long rightResult = rightTask.compute();
                long leftResult = leftTask.join();

                // Combina os resultados
                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        // Criação de um array com 10 milhões de elementos
        int[] array = new int[10_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        // Usa o ForkJoinPool para executar a soma em paralelo
        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.currentTimeMillis();
        long sum = pool.invoke(new SumTask(array, 0, array.length));
        long endTime = System.currentTimeMillis();

        System.out.println("Soma: " + sum);
        System.out.println("Tempo gasto: " + (endTime - startTime) + " ms");
    }
}

