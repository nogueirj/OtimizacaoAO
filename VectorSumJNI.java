public class VectorSumJNI {

    // Declaração do método nativo
    public native double sumVector(double[] vector);

    // Bloco estático para carregar a biblioteca nativa
    static {
        System.loadLibrary("VectorSumJNI"); // Nome da biblioteca nativa
    }

    public static void main(String[] args) {
        double[] vector = new double[1000000];

        // Preenche o vetor com valores
        for (int i = 0; i < vector.length; i++) {
            vector[i] = i + 1;
        }

        // Cria uma instância da classe e chama o método nativo
        VectorSumJNI vectorSum = new VectorSumJNI();
        double sum = vectorSum.sumVector(vector);

        // Exibe o resultado
        System.out.println("Soma dos elementos do vetor: " + sum);
    }
}
