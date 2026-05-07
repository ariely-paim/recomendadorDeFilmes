import model.*;
import service.*;
import util.GeradorAleatorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecomendadorServiceTest {

    @Mock private CatalogoFilmesAPI catalogoApi;
    @Mock private HistoricoUsuarioRepository historicoRepo;
    @Mock private NotificadorPush notificador;
    @Mock private GeradorAleatorio gerador;
    
    private RecomendadorService service;
    private Usuario maria;

    @BeforeEach
    void setup() {
        service = new RecomendadorService(
            catalogoApi, historicoRepo, notificador, gerador, 
            new CalculadoraScore(), new FiltroFilmes()
        );
        maria = new Usuario("Maria", 28);
    }

    @Test
    @DisplayName("deve retornar lista vazia quando a API do catálogo lançar exceção")
    void deve_RetornarListaVazia_Quando_CatalogoLancaExcecao() {
        // Arrange
        when(catalogoApi.buscarTodos()).thenThrow(new RuntimeException("Erro de Rede"));

        // Act
        List<Recomendacao> resultado = service.recomendar(maria, 5);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(notificador, never()).enviar(anyString());
    }

    @Test
    @DisplayName("deve capturar as recomendações enviadas para o repositório")
    void deve_ChamarHistoricoRepo_AoFinalizarRecomendacao() {
        // Arrange
        ArgumentCaptor<List<Recomendacao>> captor = ArgumentCaptor.forClass(List.class);
        when(catalogoApi.buscarTodos()).thenReturn(List.of()); // Simula catálogo vazio

        // Act
        service.recomendar(maria, 3);

        // Assert
        verify(historicoRepo).registrarRecomendacao(eq(maria), captor.capture());
        assertNotNull(captor.getValue());
    }
}