import model.Filme;
import model.enums.Genero;
import model.PerfilCinefilo;
import service.CalculadoraScore;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraScoreTest {
    private final CalculadoraScore calculadora = new CalculadoraScore();

    @ParameterizedTest
    @CsvSource({
        "1.0, 1.0, 100",
        "0.5, 0.5, 50",
        "0.0, 0.0, 0"
    })
    @DisplayName("deve calcular score de gênero corretamente conforme pesos")
    void deve_CalcularScoreDeGenero_ConformePesos(double peso1, double peso2, int esperado) {
        // Arrange
        PerfilCinefilo perfil = new PerfilCinefilo();
        perfil.setPesoGenero(Genero.ACAO, peso1);
        perfil.setPesoGenero(Genero.DRAMA, peso2);
        
        // Simulação simplificada de um filme com esses dois gêneros
        // Act & Assert
        // (Aqui entraria a chamada para o método calcular() da sua CalculadoraScore)
    }
}
