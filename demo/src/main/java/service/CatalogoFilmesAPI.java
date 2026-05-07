package service;

import java.util.List;

import model.Filme;

public interface CatalogoFilmesAPI {
    /** Busca a lista completa de filmes disponíveis na fonte externa. */
    List<Filme> buscarTodos();
}
