public class SequentialSum {

    public static void main(String[] args) {
        // Criação de um array com 10 milhões de elementos
        int[] array = new int[10_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        // Soma dos elementos do array de forma sequencial
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int num : array) {
            sum += num;
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Soma: " + sum);
        System.out.println("Tempo gasto: " + (endTime - startTime) + " ms");
    }
}

