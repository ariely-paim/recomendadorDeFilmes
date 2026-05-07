package service;

import java.util.List;

import model.Usuario;

public interface HistoricoUsuarioRepository {
    /** Persiste as recomendações geradas para um usuário. */
    void registrarRecomendacao(Usuario usuario, List<Recomendacao> recomendacoes);
}
