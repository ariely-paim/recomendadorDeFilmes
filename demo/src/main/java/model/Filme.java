package model;

import java.util.List;
import java.util.Objects;

public class Filme {
    private final String id;
    private final String titulo;
    private final int ano;
    private final int duracao;
    private final List<Genero> generos;
    private final ClassificacaoEtaria classificacao;
    private final String idioma;
    private final int popularidade;

    // Construtor com todos os atributos
    public Filme(String id, String titulo, int ano, int duracao, List<Genero> generos, 
                 ClassificacaoEtaria classificacao, String idioma, int popularidade) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.duracao = duracao;
        this.generos = List.copyOf(generos);
        this.classificacao = classificacao;
        this.idioma = idioma;
        this.popularidade = popularidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filme)) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
