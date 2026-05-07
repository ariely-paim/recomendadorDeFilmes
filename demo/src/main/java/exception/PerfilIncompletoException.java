package exception;

/**
 * Exceção lançada quando o perfil do usuário não possui informações suficientes 
 * para gerar uma recomendação válida.
 */
public class PerfilIncompletoException extends RuntimeException {
    public PerfilIncompletoException(String mensagem) {
        super(mensagem);
    }
}
