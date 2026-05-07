package util;

import java.util.Random;

/**
 * Implementação padrão de produção que utiliza a classe Random do Java.
 */
public class GeradorAleatorioImpl implements GeradorAleatorio {
    private final Random random;

    public GeradorAleatorioImpl() {
        this.random = new Random();
    }

    @Override
    public int sortearInteiro(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Mínimo não pode ser maior que o máximo.");
        }
        return random.nextInt((max - min) + 1) + min;
    }
}
