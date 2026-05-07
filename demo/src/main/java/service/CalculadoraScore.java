package service;

import java.util.List;
import java.util.stream.Collectors;

import model.Filme;
import model.PerfilCinefilo;
import model.Usuario;

public class CalculadoraScore {
    private final CatalogoFilmesAPI catalogoApi;
    private final HistoricoUsuarioRepository historicoRepo;
    private final NotificadorPush notificador;
    private final GeradorAleatorio gerador;
    private final CalculadoraScore calculadora;
    private final FiltroFilmes filtro;

    /** Injeção por construtor obrigatória para facilitar mocks nos testes. */
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

    /** Gera uma lista ranqueada de recomendações. */
    public List<Recomendacao> recomendar(Usuario usuario, int topN) {
        try {
            List<Filme> catalogo = catalogoApi.buscarTodos();
            List<Filme> filtrados = filtro.filtrar(catalogo, usuario.getPerfil());

            List<Recomendacao> resultado = filtrados.stream()
                .map(f -> new Recomendacao(f, calculadora.calcular(f, usuario.getPerfil()), "Baseado no seu gosto"))
                .sorted(Comparator.comparing(Recomendacao::getScore).reversed()
                    .thenComparing(r -> r.getFilme().getPopularidade(), Comparator.reverseOrder()))
                .limit(topN)
                .collect(Collectors.toList());

            historicoRepo.registrarRecomendacao(usuario, resultado);

            if (usuario.isNotificacoesAtivas()) {
                notificador.enviar("Sua recomendação do dia chegou!");
            }

            return resultado;
        } catch (Exception e) {
            // Tratamento de erro conforme requisito de resiliência
            return Collections.emptyList();
        }
    }

    /** Modo Surpreenda-me: retorna um filme aleatório entre os filtrados. */
    public Recomendacao recomendarAleatorio(Usuario usuario) {
        List<Filme> filtrados = filtro.filtrar(catalogoApi.buscarTodos(), usuario.getPerfil());
        if (filtrados.isEmpty()) return null;

        int index = gerador.sortearInteiro(0, filtrados.size() - 1);
        Filme sorteado = filtrados.get(index);
        
        return new Recomendacao(sorteado, calculadora.calcular(sorteado, usuario.getPerfil()), "Surpresa!");
    }
}
