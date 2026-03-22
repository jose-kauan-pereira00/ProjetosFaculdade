import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import filmnow.Filme;


/**
 * @author José Kauan Pereira dos Reis - 20250022120
 */
public class FilmeTest {

    private Filme filmeBase;

    @BeforeEach
    void setUp() {
        filmeBase = new Filme("Avatar", 2009, "Disney+");
    }

    @Test
    void testToString() {
        assertEquals("Avatar (2009) - Disney+", filmeBase.toString(), "O toString deve retornar 'Nome, Ano'");
    }

    @Test
    void testEqualsFilmesIguais() {
        Filme filmeIgual = new Filme("Avatar", 2009, "Cinema");
        assertEquals(filmeBase, filmeIgual, "Filmes com mesmo nome e ano devem ser iguais");
    }

    @Test
    void testNotEqualsAnoDiferente() {
        Filme filmeDiferente = new Filme("Avatar", 2022, "Disney+");
        assertNotEquals(filmeBase, filmeDiferente, "Filmes com anos diferentes não devem ser iguais");
    }

    @Test
    void testNotEqualsNomeDiferente() {
        Filme filmeDiferente = new Filme("Avatar 2", 2009, "Disney+");
        assertNotEquals(filmeBase, filmeDiferente);
    }
}