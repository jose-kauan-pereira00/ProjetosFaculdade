package facade;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma orientação ambiental criada por um morador no sistema.
 * Agrupa diferentes itens informativos (texto, mídia, fontes) sobre um determinado tema 
 * e contabiliza o engajamento (curtidas) recebido.
 */
public class OrientacaoAmbiental {
    private String cpfMorador;
    private String tipo;
    private int curtidas;
    private List<ItemInformativo> itens;

    /**
     * Construtor para criar uma nova Orientação Ambiental.
     * * @param cpfMorador O CPF do morador que está a criar a orientação.
     * @param tipo       O tipo ou categoria da orientação ambiental.
     */
    public OrientacaoAmbiental(String cpfMorador, String tipo) {
        this.cpfMorador = cpfMorador;
        this.tipo = tipo;
        this.curtidas = 0;
        this.itens = new ArrayList<>();
    }

    /**
     * Retorna o CPF do morador autor desta orientação.
     * * @return O CPF do morador.
     */
    public String getCpfMorador() { 
        return cpfMorador; 
    }

    /**
     * Retorna o tipo/categoria da orientação.
     * * @return O tipo da orientação.
     */
    public String getTipo() { 
        return tipo; 
    }

    /**
     * Retorna a quantidade total de curtidas que esta orientação recebeu.
     * * @return O número de curtidas.
     */
    public int getCurtidas() { 
        return curtidas; 
    }

    /**
     * Incrementa em um o número de curtidas (engajamento) desta orientação.
     */
    public void darCurtida() { 
        this.curtidas++; 
    }

    /**
     * Adiciona um novo item informativo (Texto, Mídia ou Fonte) a esta orientação.
     * * @param item O item informativo a ser adicionado.
     */
    public void adicionarItem(ItemInformativo item) { 
        this.itens.add(item); 
    }

    /**
     * Retorna a quantidade de itens informativos anexados a esta orientação.
     * * @return O número de itens na lista.
     */
    public int getQuantidadeItens() { 
        return itens.size(); 
    }

    /**
     * Calcula a pontuação total gerada por esta orientação.
     * A pontuação é a soma dos pontos de cada item informativo associado, 
     * mais um bónus de engajamento (5 pontos a cada 10 curtidas recebidas).
     * * @return A pontuação total calculada.
     */
    public int calcularPontuacao() {
        int pontos = 0;
        for (ItemInformativo item : itens) {
            pontos += item.calcularPontos();
        }
        pontos += (curtidas / 10) * 5; // Bónus de engajamento
        return pontos;
    }

    /**
     * Gera uma listagem em texto de todos os itens informativos contidos nesta orientação.
     * * @return Uma String formatada com a descrição de cada item, ou uma mensagem padrão se estiver vazia.
     */
    public String listarItens() {
        if (itens.isEmpty()) return "Nenhum item cadastrado.";
        StringBuilder sb = new StringBuilder();
        for (ItemInformativo item : itens) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString().trim();
    }
}