package src;

import java.util.*;

/**
 * @author José Kauan Pereira dos Reis - 20250022120
 * Representa um restaurante no sistema.
 * Armazena informações sobre o prato principal, visitas recebidas e votos acumulados.
 */
public class Restaurante {

    /**
     * Uma Lista que armazena as Visitas ao Restaruante
     * Um conjunto que armazena os clientes que ja visitaram o restaurante pelo seu Email
     * O nome do Restaurante
     * O Prato Escolhido do Restaurante
     * A quantidade de votos do restaurante
     * */
    private List<Visita> visitas;
    private Set<String> emailsVisitantes; 
    private String nomeRestaurante;
    private String prato;
    private int votos;

    /**
     * Construtor do Restaurante
     * @param nome  Nome do restaurante
     * @param prato Prato especialidade da casa
     */
    public Restaurante(String nome, String prato) {
        this.nomeRestaurante = nome;
        this.prato = prato;
        this.visitas = new ArrayList<>();
        this.emailsVisitantes = new HashSet<>();
        this.votos = 0;
    }

    /**
     * Adiciona uma visita ao histórico do restaurante
     * Registra também o email do visitante e contabiliza o custo fixo (89.90) no gasto do cliente
     *
     * @param c          O objeto Cliente que realizou a visita
     * @param comentario O comentário deixado pelo cliente
     */
    public void adicionaVisita(Cliente c, String comentario) {
        this.visitas.add(new Visita(c, comentario));
        this.emailsVisitantes.add(c.getEmail());
        c.registrarGasto(89.90);
    }

    /**
     * Verifica se um determinado email já realizou uma visita a este restaurante
     *
     * @param email O email a ser verificado
     * @return true se o email constar na lista de visitantes, false caso contrário
     */
    public boolean jaFoiVisitadoPor(String email) { 
        return emailsVisitantes.contains(email); 
    }

    /**
     * Incrementa o contador de votos do restaurante
     */
    public void receberVoto() { 
        this.votos++; 
    }

    /**
     * Métodos Gets do Restaurante
     * 
     * @return o Atributo do get
     * */
    public String getNome() { 
        return nomeRestaurante; 
    }
    public String getPrato() { 
        return prato; 
    }
    public int getVotos() { 
        return votos; 
    }
    public List<Visita> getVisitas() { 
        return visitas; 
    }
    public Set<String> getEmailsVisitantes() { 
        return emailsVisitantes; 
    }
}