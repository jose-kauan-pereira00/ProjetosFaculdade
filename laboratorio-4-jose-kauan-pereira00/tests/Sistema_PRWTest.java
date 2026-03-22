package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Sistema_PRW;

public class Sistema_PRWTest {

    private Sistema_PRW sistema;

    @BeforeEach
    void setUp() {
        sistema = new Sistema_PRW();
        sistema.adicionarRestaurante("Sabor do Sertão", "Baião de Dois");
        sistema.adicionarRestaurante("Porto Mar", "Moqueca de Peixe");
        sistema.adicionarCliente("Alice", "alice@email.com");
        sistema.adicionarCliente("Bob", "bob@email.com");
        sistema.adicionarCliente("Charlie", "charlie@email.com");
        sistema.visitarRestaurante("alice@email.com", "Sabor do Sertão");
        sistema.visitarRestaurante("alice@email.com", "Sabor do Sertão"); 
        sistema.visitarRestaurante("bob@email.com", "Sabor do Sertão");
        sistema.visitarRestaurante("charlie@email.com", "Porto Mar");
    }

    @Test
    void testAdicionarRestaurante() {
        assertTrue(sistema.adicionarRestaurante("Tio João", "Sinfonia Marítima"));
        assertFalse(sistema.adicionarRestaurante("Tio João", "Outro Prato"));
    }

    @Test
    void testAdicionarCliente() {
        assertTrue(sistema.adicionarCliente("Alex", "alex@email.com"));
        assertFalse(sistema.adicionarCliente("Outro", "alex@email.com"));
    }

    @Test
    void testLimiteDeRestaurantes() {
        for (int i = 0; i < 50; i++) {
            sistema.adicionarRestaurante("Restaurante " + i, "Prato " + i);
        }
        assertFalse(sistema.adicionarRestaurante("Excedente", "Prato"));
    }

    @Test
    void testVisitarRestauranteSucesso() {
        sistema.adicionarRestaurante("Tio João", "Sinfonia Marítima");
        sistema.adicionarCliente("Alex", "alex@email.com");
        
        assertDoesNotThrow(() -> sistema.visitarRestaurante("alex@email.com", "Tio João"));
    }

    @Test
    void testVisitarRestauranteInexistente() {
        sistema.adicionarCliente("Alex", "alex@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            sistema.visitarRestaurante("alex@email.com", "Inexistente");
        });
    }

    @Test
    void testObtemGastoCliente() {
        sistema.adicionarRestaurante("Tio João", "Sinfonia Marítima");
        sistema.adicionarCliente("Alex", "alex@email.com");        
        sistema.visitarRestaurante("alex@email.com", "Tio João");
        sistema.visitarRestaurante("alex@email.com", "Tio João"); 
        assertEquals(179.80, sistema.obtemGastoCliente("alex@email.com"), 0.01);
    }

    @Test
    void testVotoValido() {
        sistema.adicionarRestaurante("Tio João", "Sinfonia Marítima");
        sistema.adicionarCliente("Alex", "alex@email.com");
        sistema.visitarRestaurante("alex@email.com", "Tio João");
        
        String resultado = sistema.votarPratoPreferido("alex@email.com", "Tio João");
        assertEquals("Voto registrado com sucesso!", resultado);
    }

    @Test
    void testVotoInvalidoSemVisita() {
        sistema.adicionarRestaurante("Tio João", "Sinfonia Marítima");
        sistema.adicionarCliente("Alex", "alex@email.com");
        String resultado = sistema.votarPratoPreferido("alex@email.com", "Tio João");
        assertEquals("Voto não registrado", resultado);
    }

    @Test
    void testListarClientesRestaurante() {
        sistema.adicionarRestaurante("Tio João", "Sinfonia Marítima");
        sistema.adicionarCliente("Alex", "alex@email.com");
        sistema.visitarRestaurante("alex@email.com", "Tio João");
        sistema.votarPratoPreferido("alex@email.com", "Tio João");
        String[] lista = sistema.listarClientesRestaurante("Tio João");
        assertEquals(1, lista.length);
        assertEquals("Cliente: Alex, Prato preferido da casa", lista[0]);
    }

    @Test
    void testMelhorPrato() {
        sistema.adicionarRestaurante("Rest A", "Prato A");
        sistema.adicionarRestaurante("Rest B", "Prato B");
        sistema.adicionarCliente("C1", "c1@e.com");
        sistema.adicionarCliente("C2", "c2@e.com");
        sistema.visitarRestaurante("c1@e.com", "Rest B");
        sistema.votarPratoPreferido("c1@e.com", "Rest B");
        sistema.visitarRestaurante("c2@e.com", "Rest B");
        sistema.votarPratoPreferido("c2@e.com", "Rest B");
        assertEquals("Melhor prato: Prato B Restaurante: Rest B", sistema.melhorPrato());
    }

    @Test
    void testListarLivroVisitasRecentes() {
        sistema.adicionarRestaurante("Tio João", "Sinfonia Marítima");
        sistema.adicionarCliente("Alex", "alex@email.com");
        sistema.visitarRestaurante("alex@email.com", "Tio João", "Bom");
        sistema.visitarRestaurante("alex@email.com", "Tio João", "Ótimo");
        String[] recentes = sistema.listarLivroVisitas("Tio João", 1);
        assertEquals(1, recentes.length);
        assertTrue(recentes[0].contains("Ótimo"));
    }

    /**
     * Testes Para a Implementação dos Relatórios
     * */

        
    @Test
    public void testRelatorioFinanceiro() {
        String relatorio = sistema.gerarRelatorioFinanceiro();
        assertTrue(relatorio.contains("359,60") || relatorio.contains("359.60"));
        assertTrue(relatorio.contains("119,87") || relatorio.contains("119.87"));
    }

    @Test
    public void testRelatorioEngajamento() {
        String[] engajamento = sistema.gerarRelatorioEngajamento();
        boolean encontrouAliceEngajada = false;
        for (String linha : engajamento) {
            if (linha.contains("Alice") && linha.contains("Sabor do Sertão") && linha.contains("Visitas: 2")) {
                encontrouAliceEngajada = true;
                break;
            }
        }
        
        assertTrue(encontrouAliceEngajada, "O relatório deveria mostrar que Alice visitou o Sabor do Sertão 2 vezes.");
        assertEquals(3, engajamento.length, "Deveriam existir 3 registros de engajamento (Alice, Bob e Charlie).");
    }
}