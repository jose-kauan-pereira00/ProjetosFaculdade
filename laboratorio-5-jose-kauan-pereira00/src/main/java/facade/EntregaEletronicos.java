package facade;

/**
 * Representa uma entrega de resíduos eletrônicos em um ecoponto.
 */
public class EntregaEletronicos extends Entrega{
	private int quantidadeItens;

	/**
	 * Construtor para entrega de eletrônicos.
	 * * @param cpf             O CPF do morador.
	 * @param quantidadeItens A quantidade de itens eletrônicos entregues.
	 * @param descricao       Descrição da entrega.
	 * @param linkComprovacao Link de comprovação da entrega.
	 * @throws IllegalArgumentException Se a quantidade de itens for negativa.
	 */
	public EntregaEletronicos(String cpf, int quantidadeItens, String descricao, String linkComprovacao) {
		super(cpf, descricao, linkComprovacao);
		if(quantidadeItens < 0){
			throw new IllegalArgumentException("Quantidade Invalida");
		}
		super.tipo = "Residuos Eletronicos";
		this.quantidadeItens = quantidadeItens;
	}

	/**
	 * Calcula o crédito da entrega de eletrônicos.
	 * Cada item gera 2 créditos, com um limite máximo de 12 créditos por entrega.
	 * * @return A quantidade de créditos calculada.
	 */
	@Override
	public int calcularCredito() {
		int max = 12;
		int pontuacao = this.quantidadeItens * 2;

		if(pontuacao > max){
			return max;
		}
		return pontuacao;
	}
}