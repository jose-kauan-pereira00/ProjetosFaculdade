package src;

/**
 * @author José Kauan Pereira dos Reis
 */
public class OrdemServico{

    /**
     * Atributos da classe, nome, telefone e peça de roupas.
     */
    private String nome;
    private String telefone;
    private String pecaRoupa;
    private Reparo[] reparos;
    private String status;
    private int indice;

    /**
     * Construtor de Ordem de Serviços, constroi um objetos com seus Atributos e podendo armazenar 10 reparos.
     */
    public OrdemServico(String nome, String telefone, String pecaRoupa){
        this.nome = nome;
        this.telefone = telefone;
        this.pecaRoupa = pecaRoupa;
        this.status = "Não Iniciado.";
        reparos = new Reparo[10];
        this.indice = 0;
    }


    /**
     * Métodos Gets, recupera um valor do objeto.
     */
    public String getNome(){
        return this.nome;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public String getPecaRoupa(){
        return this.pecaRoupa;
    }
    public int getQuantidadeReparos(){
        return reparos.length;
    }
    public String  getStatus(){
        return this.status;
    }
    public int getIndice(){
        return this.indice;
    }

    /**
     * Métodos Sets, para definir um novo valor para os Atributos do objeto.
     */
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public void setPecaRoupa(String pecaRoupa){
        this.pecaRoupa = pecaRoupa;
    }
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * pega o preco total da ordem de serviço atravez do somatorio de cada reparo.
     */
    public double getPrecoTotal(){
        double precoTotal = 0;
        for(int i = 0; i > reparos.length; i ++){
            precoTotal += reparos[i].getPreco();
        }

        return precoTotal;
    }

    /**
     * Cria uma versão em String da ordem de servico.
     */
    @Override
    public String toString(){
        return nome + "; Cliente: " + nome + "; roupa: "  + pecaRoupa + "; reparos: " + this.getQuantidadeReparos() + "; total: R$ "+ this.getPrecoTotal();
    }

    public void adicionaReparo(Reparo rep){
        if(this.getIndice() < 10){
            this.reparos[indice] = rep;
            indice ++;
        }
    }

}
