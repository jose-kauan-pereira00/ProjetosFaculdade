package src;

/**
 * @author José Kauan Pereira dos Reis
 */
public class Reparo{
    /**
     * atributos da classe, com identificador único no id.
     */
    private final String id;
    private String descricao;
    private double preco;

    /**
     * Construtor de reparo, cria uma instancia de reparo.
     */
    public Reparo(String id, String descricao, double preco){
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Colocara um valor entre que será a representação de quanto o vai passar a ser ex: preço = 10, percentual = 2
     * o preço passara a ser o dobro.
    @param percentual
    */
    public void reajustarPreco(double percentual){
    preco = preco * percentual;
    }

    /**
     * Métodos Gets, recupera o valor dos atributos da instancia dos objetos criados.
     */
    public String getId(){
        return this.id;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public double getPreco(){
        return this.preco;
    }

    /**
     * Métodos Sets, define um novo valor para o atributos do objetos, desde que não seja um identificador único(Ex: id).
     */
    public void setDescrico(String novaDescricao){
        this.descricao = novaDescricao;
    }

    /**
     * Verifivica se o ojetos possuem o mesmo id.
     */
    public boolean equals(Reparo obj){
        if(this.id == obj.getId()){
            return true;
        }
         return false;
    }

}
