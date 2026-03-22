package src;

/**
 * @author José Kauan Pereira dos Reis - 20250022120
 * Representa um cliente usuário do sistema
 * Mantém o registro de gastos acumulados e preferências gastronômicas
 */
public class Cliente {
    private String nomeCliente;
    private String email;
    private double gastoTotal;
    private String pratoPreferido;

    /**
     * Construtor do Cliente
     * @param nome  Nome do cliente
     * @param email Email do cliente (identificador único)
     */
    public Cliente(String nome, String email) {
        this.nomeCliente = nome;
        this.email = email;
        this.gastoTotal = 0.0;
        this.pratoPreferido = "Prato preferido não definido";
    }

    /**
     * Adiciona um valor ao montante total gasto pelo cliente
     * @param valor O valor a ser somado
     */
    public void registrarGasto(double valor) {
     this.gastoTotal += valor; 
    }

    /**
     * Define o prato preferido do cliente
     * Geralmente atualizado quando o cliente vota em um restaurante
     * @param prato Nome do prato preferido
     */
    public void setPratoPreferido(String prato) { 
        this.pratoPreferido = prato; 
    }

    /**
     * Métodos Gets do Cliente
     * 
     * @return o atributo especificado pelo get
     * */
    public String getNome() { 
        return nomeCliente; 
    }
    public String getEmail() {
        return email; 
    }
    public double getGastoTotal() { 
        return gastoTotal; 
    }
    public String getPratoPreferido() { 
        return pratoPreferido; 
    }
}