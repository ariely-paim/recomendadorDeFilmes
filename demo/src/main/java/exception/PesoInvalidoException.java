package exception;

/**
 * Exceção lançada quando um peso de gênero é definido fora do intervalo de 0.0 a 1.0.
 */
public class PesoInvalidoException extends RuntimeException {
    public PesoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
