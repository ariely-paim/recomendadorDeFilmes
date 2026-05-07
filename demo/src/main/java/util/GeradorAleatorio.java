package util;

/**
 * Interface para abstrair a geração de números aleatórios, 
 * facilitando o controle determinístico durante os testes unitários.
 */
public interface GeradorAleatorio {
    /**
     * Sorteia um número inteiro entre o valor mínimo e máximo (inclusive).
     * @param min Valor inicial do intervalo.
     * @param max Valor final do intervalo.
     * @return O número sorteado.
     */
    int sortearInteiro(int min, int max);
}