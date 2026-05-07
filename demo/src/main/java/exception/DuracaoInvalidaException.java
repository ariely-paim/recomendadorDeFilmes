package exception;

/**
 * Exceção lançada quando a configuração de faixa de duração é logicamente impossível 
 * (ex: mínima > máxima).
 */
public class DuracaoInvalidaException extends RuntimeException {
    public DuracaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
