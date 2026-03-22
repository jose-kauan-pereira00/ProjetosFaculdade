package facade;

/**
 * Representa uma entrega de plástico em um ecoponto.
 */
public class EntregaPlastico extends Entrega{
	private double quantidadeKg;

	/**
	 * Construtor para entrega de plástico.
	 * * @param cpf             O CPF do morador.
	 * @param quantidadeKg    A quantidade em quilogramas (kg) de plástico.
	 * @param descricao       Descrição da entrega.
	 * @param linkComprovacao Link de comprovação da entrega.
	 */
	public EntregaPlastico(String cpf, double quantidadeKg, String descricao, String linkComprovacao) {
		super(cpf, descricao, linkComprovacao);
		super.tipo = "Plastico";
		this.quantidadeKg = quantidadeKg;
	}

	/**
	 * Calcula o crédito da entrega de plástico.
	 * A cada 3kg é gerado 1 crédito (truncado), com limite máximo de 10 créditos por entrega.
	 * * @return A quantidade de créditos calculada.
	 */
	@Override
	public int calcularCredito() {
		int pontuacao = (int) this.quantidadeKg / 3;
		int max = 10;

		if(pontuacao > max){
			return max;
		}

		return pontuacao;
	}
}