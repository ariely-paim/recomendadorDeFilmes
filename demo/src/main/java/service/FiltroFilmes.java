package service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.Filme;
import model.PerfilCinefilo;

public class FiltroFilmes {
    public List<Filme> filtrar(List<Filme> catalogo, PerfilCinefilo perfil) {
        if (catalogo == null || catalogo.isEmpty()) return Collections.emptyList();

        return catalogo.stream()
            .filter(f -> !perfil.getHistoricoAssistidos().contains(f.getId())) // Não assistido 
            .filter(f -> f.getClassificacao().getIdade() <= perfil.getClassificacaoMaxima().getIdade()) // Faixa etária 
            .filter(f -> perfil.getIdiomasAceitos().contains(f.getIdioma())) // Idioma
            .filter(f -> perfil.getPesoGenero(f.getGeneros().get(0)) > 0.0) // Gênero não odiado 
            .collect(Collectors.toList());
    }
}
