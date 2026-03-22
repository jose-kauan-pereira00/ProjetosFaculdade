package testes;

import facade.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaEcoPontosTest {

    private Morador morador;

    @BeforeEach
    void setUp() {
        morador = new Morador("Ana", "12345678901", "senhaForte123", "rifao79");
    }

    @Test
    void testCriacaoMoradorEAutenticacaoSucesso() {
        assertEquals("12345678901", morador.getCpf()); 
        assertTrue(morador.validarSenha("senhaForte123"));
        assertFalse(morador.validarSenha("senhaIncorreta"));
    }

    @Test
    void testCriacaoMoradorCpfInvalido() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Morador("Ana", "123", "senhaForte123", "rifao79")
        );
        assertThrows(IllegalArgumentException.class, () -> 
            new Morador("Ana", null, "senhaForte123", "rifao79")
        );
    }

    @Test
    void testCriacaoMoradorSenhaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Morador("Ana", "12345678901", "fraca", "rifao79")
        );
        assertThrows(IllegalArgumentException.class, () -> 
            new Morador("Ana", "12345678901", null, "rifao79")
        );
    }

    @Test
    void testCalculoCreditoEntregaPapel() {
        EntregaPapel ep = new EntregaPapel("12345678901", 12.0, "Cadernos", "link");
        assertEquals(2, ep.calcularCredito());

        EntregaPapel epMax = new EntregaPapel("12345678901", 50.0, "Jornais", "link");
        assertEquals(8, epMax.calcularCredito());
    }

    @Test
    void testCalculoCreditoEntregaPlastico() {
        EntregaPlastico epl = new EntregaPlastico("12345678901", 7.0, "Garrafas PET", "link");
        assertEquals(2, epl.calcularCredito());

        EntregaPlastico eplMax = new EntregaPlastico("12345678901", 40.0, "Plástico misto", "link");
        assertEquals(10, eplMax.calcularCredito());
    }

    @Test
    void testCalculoCreditoEntregaVidro() {
        EntregaVidro ev = new EntregaVidro("12345678901", 25.0, "Potes", "link");
        assertEquals(2, ev.calcularCredito());
        EntregaVidro evMax = new EntregaVidro("12345678901", 120.0, "Garrafas de vidro", "link");
        assertEquals(10, evMax.calcularCredito());
    }

    @Test
    void testCalculoCreditoEntregaEletronicos() {
        EntregaEletronicos ee = new EntregaEletronicos("12345678901", 3, "Mouses", "link");
        assertEquals(6, ee.calcularCredito());
        EntregaEletronicos eeMax = new EntregaEletronicos("12345678901", 10, "Teclados", "link");
        assertEquals(12, eeMax.calcularCredito());
    }

    @Test
    void testEntregasValoresNegativosLancamExcecao() {
        assertThrows(IllegalArgumentException.class, () -> 
            new EntregaPapel("12345678901", -5.0, "Papel", "link")
        );
        assertThrows(IllegalArgumentException.class, () -> 
            new EntregaEletronicos("12345678901", -1, "Mouse", "link")
        );
    }

    @Test
    void testPontuacaoTextoOrientacao() {
        String texto50 = "A".repeat(50);
        TextoOrientacao to = new TextoOrientacao(texto50);
        assertEquals(2, to.calcularPontos());
        String texto300 = "A".repeat(300);
        TextoOrientacao toMax = new TextoOrientacao(texto300);
        assertEquals(10, toMax.calcularPontos());
    }

    @Test
    void testPontuacaoMidiaOrientacao() {
        MidiaOrientacao mo = new MidiaOrientacao("link", "Video Curto", 130);
        assertEquals(10, mo.calcularPontos());
        MidiaOrientacao m = new MidiaOrientacao("link", "Video Longo", 900);
        assertEquals(50, m.calcularPontos());        
        
        ;
    }

    @Test
    void testPontuacaoFonteOrientacao() {
        FonteOrientacao fonteVerificada = new FonteOrientacao("Artigo", "Revista", 2023, true, 5);
        assertEquals(15, fonteVerificada.calcularPontos());

        FonteOrientacao fonteNaoVerificada = new FonteOrientacao("Blog", "Site", 2023, false, 2);
        assertEquals(5, fonteNaoVerificada.calcularPontos());
    }

    @Test
    void testCalculoPontuacaoOrientacaoEngajamento() {
        OrientacaoAmbiental oa = new OrientacaoAmbiental("12345678901", "Dica");        
        oa.adicionarItem(new TextoOrientacao("A".repeat(40)));

        oa.adicionarItem(new FonteOrientacao("Blog", "Site", 2023, false, 2));        
        assertEquals(7, oa.calcularPontuacao());
        
        for (int i = 0; i < 25; i++) {
            oa.darCurtida();
        }
        
        assertEquals(17, oa.calcularPontuacao());
    }

    @Test
    void testBoletimPorMaterial() {
        morador.adicionarEntrega(new EntregaPapel("12345678901", 10.0, "Papel", "link")); 
        morador.adicionarEntrega(new EntregaPapel("12345678901", 15.0, "Papelão", "link")); 
        morador.adicionarEntrega(new EntregaPlastico("12345678901", 6.0, "Plástico", "link")); 

        String boletim = morador.boletimPorMaterial("Papel");
        
        assertTrue(boletim.contains("Material: Papel"));
        assertTrue(boletim.contains("Creditos: 5")); 
    }
}