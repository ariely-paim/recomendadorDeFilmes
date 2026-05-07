import exception.DuracaoInvalidaException;
import exception.PesoInvalidoException;
import model.enums.Genero;
import model.PerfilCinefilo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PerfilCinefiloTest {
    private PerfilCinefilo perfil;

    @BeforeEach
    void setup() {
        perfil = new PerfilCinefilo();
    }

    @Test
    @DisplayName("deve criar perfil com pesos válidos")
    void deve_CriarPerfil_Quando_PesosValidos() {
        assertDoesNotThrow(() -> perfil.setPesoGenero(Genero.ACAO, 0.8)); // [cite: 223]
    }

    @Test
    @DisplayName("deve lançar exceção quando peso for maior que 1.0")
    void deve_LancarExcecao_Quando_PesoMaiorQueUm() {
        assertThrows(PesoInvalidoException.class, () -> perfil.setPesoGenero(Genero.DRAMA, 1.5)); // [cite: 189, 223]
    }

    @Test
    @DisplayName("deve lançar exceção quando duração mínima for maior que a máxima")
    void deve_LancarExcecao_Quando_DuracaoMinimaMaiorQueMaxima() {
        assertThrows(DuracaoInvalidaException.class, () -> perfil.setFaixaDuracao(120, 90)); // [cite: 190]
    }
}
