package service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.Filme;
import model.Usuario;

public class RecomendadorService {
    private final CatalogoFilmesAPI catalogoApi;
    private final HistoricoUsuarioRepository historicoRepo;
    private final NotificadorPush notificador;
    private final GeradorAleatorio gerador;
    private final CalculadoraScore calculadora;
    private final FiltroFilmes filtro;

    public RecomendadorService(CatalogoFilmesAPI api, HistoricoUsuarioRepository repo, 
                               NotificadorPush notificador, GeradorAleatorio gerador,
                               CalculadoraScore calc, FiltroFilmes filtro) {
        this.catalogoApi = api;
        this.historicoRepo = repo;
        this.notificador = notificador;
        this.gerador = gerador;
        this.calculadora = calc;
        this.filtro = filtro;
    }

    public List<Recomendacao> recomendar(Usuario usuario, int topN) {
        try {
            List<Filme> todosFilmes = catalogoApi.buscarTodos();
            List<Filme> filtrados = filtro.filtrar(todosFilmes, usuario.getPerfil());
            
            List<Recomendacao> recomendacoes = filtrados.stream()
                .map(f -> new Recomendacao(f, calculadora.calcular(f, usuario.getPerfil())))
                .sorted(Comparator.comparing(Recomendacao::getScore).reversed()
                    .thenComparing((r) -> r.getFilme().getPopularidade(), Comparator.reverseOrder()))
                .limit(topN)
                .collect(Collectors.toList());

            historicoRepo.registrarRecomendacao(usuario, recomendacoes);
            
            if (usuario.isNotificacoesAtivas()) {
                notificador.enviar("Sua recomendação está pronta!");
            }

            return recomendacoes;
        } catch (Exception e) {
            return Collections.emptyList(); // Resiliência [cite: 214, 333]
        }
    }
}
