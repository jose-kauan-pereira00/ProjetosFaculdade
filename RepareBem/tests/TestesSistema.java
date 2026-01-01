import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import src.Reparo;
import src.OrdemServico;

public class TestesSistema {

    @Test
    public void testReajustarPreco() {
        Reparo r = new Reparo("1", "Pintura", 100.0);
        r.reajustarPreco(0.10); // Aumenta 10%
        assertEquals(110.0, r.getPreco(), 0.01);
    }

    @Test
    public void testMudarStatusOS() {
        OrdemServico os = new OrdemServico("Cliente A", "Peça X", "Peca de roupas");
        os.setStatus("CONCLUIDO");
        assertEquals("CONCLUIDO", os.getStatus());
    }
}
