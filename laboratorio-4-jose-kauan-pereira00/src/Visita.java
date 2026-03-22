package src;

/**
 * @author José Kauan Pereira dos Reis - 20250022120
 * Representa o registro de uma visita feita por um cliente a um restaurante
 * Contém a data (fixa para fins de teste), o cliente e o comentário
 */
public class Visita {

    /**
     * O Cliente que vai ser armazenado em Restaurante
     * A Data da Visita
     * E o comentario
     * */
    private Cliente cliente; 
    private String data;
    private String comentario;

    /**
     * Cria uma nova visita
     * A data é definida automaticamente como "02/02/2026"
     *
     * @param cliente    O objeto Cliente que realizou a visita
     * @param comentario O comentário sobre a experiência
     */
    public Visita(Cliente cliente, String comentario) {
        this.cliente = cliente;
        this.comentario = comentario;
        this.data = "02/02/2026"; 
    }

    /**
     * Retorna o objeto Cliente associado a esta visita
     * @return O objeto Cliente
     */
    public Cliente getCliente() {
        return this.cliente;
    }

    /**
     * Retorna uma representação textual da visita
     * Formato: "DATA - NOME" ou "DATA - NOME: COMENTARIO" (se houver comentário)
     */
    @Override
    public String toString() {
        String nome = cliente.getNome(); 
        return comentario.isEmpty() ? data + " - " + nome : data + " - " + nome + ": " + comentario;
    }
}