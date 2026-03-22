package facade;

/**
 * Classe abstrata que representa uma entrega genérica de materiais em um ecoponto.
 * Serve como base para os tipos específicos de entrega (Papel, Plástico, Vidro, Eletrônicos).
 */
public abstract class Entrega {

	private String cpf;
	protected String tipo;
	private String descricao;
	private String linkComprovacao;

	/**
	 * Construtor base para uma Entrega.
	 * * @param cpf             O CPF do morador que está realizando a entrega.
	 * @param descricao       Uma breve descrição da entrega.
	 * @param linkComprovacao O link da imagem ou documento que comprova a entrega.
	 */
	public Entrega(String cpf, String descricao, String linkComprovacao){
		this.cpf = cpf;
		this.descricao = descricao;
		this.linkComprovacao = linkComprovacao;
		this.tipo = null;
	}

	/**
	 * Calcula a quantidade de créditos gerados por esta entrega.
	 * * @return O valor em créditos ambientais calculados.
	 */
	public abstract int calcularCredito();

	/**
	 * Retorna o tipo de material desta entrega.
	 * * @return Uma String representando o tipo de material.
	 */
	public String getTipo(){
		return this.tipo;
	}
}