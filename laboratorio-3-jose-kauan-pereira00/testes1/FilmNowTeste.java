import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author José Kauan Pereira dos Reis - 20250022120
 */

import filmnow.*;

public class FilmNowTeste {

    private FilmNow sistema;

    @BeforeEach
    void setUp() {
        sistema = new FilmNow();
    }

    @Test
    void testAdicionaFilmePosicaoVazia() {
        String msg = sistema.cadastraFilme(1, "Avatar", 2009, "Disney+");
        assertEquals("FILME ADICIONADO", msg);
        assertEquals("Avatar", sistema.getFilme(1).getNome());
    }

    @Test
    void testAdicionaFilmePosicaoExistente() {
        sistema.cadastraFilme(1, "Avatar", 2009, "Disney+");
        String msg = sistema.cadastraFilme(1, "Barbie", 2023, "Cinema");
        
        assertEquals("FILME ADICIONADO", msg);
        assertEquals("Barbie", sistema.getFilme(1).getNome(), "O filme deve ser substituído");
    }

    @Test
    void testAdicionaFilmeDuplicado() {
        sistema.cadastraFilme(1, "Avatar", 2009, "Disney+");
        String msg = sistema.cadastraFilme(2, "Avatar", 2009, "HBO");
        
        assertEquals("FILME JÁ ADICIONADO!", msg);
    }
    
    @Test
    void testAdicionaFilmeInvalidoNomeVazio() {
        String msg = sistema.cadastraFilme(1, "", 2023, "Cinema");
        assertEquals("FILME INVÁLIDO!", msg);
    }
    
    @Test
    void testAdicionaFilmeInvalidoLocalVazio() {
        String msg = sistema.cadastraFilme(1, "Filme X", 2023, "   ");
        assertEquals("FILME INVÁLIDO!", msg);
    }

    @Test
    void testAdicionaFilmePosicaoLimiteSuperior() {
        String msg = sistema.cadastraFilme(100, "Duna", 2021, "Cinema");
        assertEquals("FILME ADICIONADO", msg);
    }

    @Test
    void testAdicionaFilmePosicaoInvalidaAcima() {
        String msg = sistema.cadastraFilme(101, "Duna", 2021, "Cinema");
        assertEquals("POSIÇÃO INVÁLIDA!", msg);
    }

    @Test
    void testAdicionaFilmePosicaoInvalidaAbaixo() {
        String msg = sistema.cadastraFilme(0, "Duna", 2021, "Cinema");
        assertEquals("POSIÇÃO INVÁLIDA!", msg);
    }


    @Test
    void testDetalharFilmeExistente() {
        sistema.cadastraFilme(1, "Oppenheimer", 2023, "IMAX");
        String detalhe = sistema.detalharFilme(1);
        assertTrue(detalhe.contains("Oppenheimer"));
        assertTrue(detalhe.contains("2023"));
        assertTrue(detalhe.contains("IMAX"));
    }

    @Test
    void testDetalharFilmeVazio() {
        String detalhe = sistema.detalharFilme(2);
        assertEquals("FILME INEXISTENTE!", detalhe, "Deve retornar vazio para posição sem filme");
    }

    @Test
    void testAdicionarHotListSucesso() {
        sistema.cadastraFilme(1, "Matrix", 1999, "Netflix");
        String msg = sistema.adicionarHot(1, 1);
        
        assertEquals("ADICIONADO A HOT LIST NA POSIÇÃO 1!", msg);
    }

    @Test
    void testHotListExibeFogo() {
        sistema.cadastraFilme(1, "Matrix", 1999, "Netflix");
        sistema.adicionarHot(1, 1);  
        String detalhe = sistema.detalharFilme(1);
        assertTrue(detalhe.contains("🔥"), "O detalhe do filme deve conter o emoji de fogo");
    }

    @Test
    void testAdicionarHotListPosicaoInvalida() {
        sistema.cadastraFilme(1, "Matrix", 1999, "Netflix");
        String msg = sistema.adicionarHot(1, 11);
        assertEquals("POSIÇÃO INVÁLIDA!", msg);
    }
    
    @Test
    void testAdicionarHotListFilmeDuplicado() {
        sistema.cadastraFilme(1, "Matrix", 1999, "Netflix");
        sistema.adicionarHot(1, 1);
        String msg = sistema.adicionarHot(1, 2); 
        assertEquals("FILME JÁ ESTÁ NA HOT LIST!", msg);
    }
}