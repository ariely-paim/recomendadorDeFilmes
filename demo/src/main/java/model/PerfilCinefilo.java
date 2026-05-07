package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PerfilCinefilo {
    private final Map<Genero, Double> pesosGenero = new HashMap<>();
    private int duracaoMinima;
    private int duracaoMaxima;
    private ClassificacaoEtaria classificacaoMaxima;
    private final Set<Idioma> idiomasAceitos = new HashSet<>();
    private final Set<String> historicoFilmes = new HashSet<>();
    private final Map<String, Integer> notasFilmes = new HashMap<>();

    public void setPesoGenero(Genero genero, double peso) {
        if (peso < 0.0 || peso > 1.0) {
            throw new PesoInvalidoException("O peso do gênero deve estar entre 0.0 e 1.0");
        }
        pesosGenero.put(genero, peso);
    }

    public void setFaixaDuracao(int minima, int maxima) {
        if (minima > maxima) {
            throw new DuracaoInvalidaException("A duração mínima não pode ser superior à máxima");
        }
        this.duracaoMinima = minima;
        this.duracaoMaxima = maxima;
    }

    public void adicionarNota(String filmeId, int nota) {
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("A nota deve ser um valor inteiro entre 1 e 5");
        }
        notasFilmes.put(filmeId, nota);
    }

    public void registrarFilmeAssistido(String filmeId) {
        historicoFilmes.add(filmeId);
    }

    public double getPesoOuZero(Genero genero) {
        return pesosGenero.getOrDefault(genero, 0.0);
    }
}
