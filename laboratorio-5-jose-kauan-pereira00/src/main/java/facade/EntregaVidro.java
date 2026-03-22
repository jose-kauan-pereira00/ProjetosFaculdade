package facade;

/**
 * Representa uma entrega de vidro em um ecoponto.
 */
public class EntregaVidro extends Entrega{
	private double quantidadeKg;

	/**
	 * Construtor para entrega de vidro.
	 * * @param cpf             O CPF do morador.
	 * @param quantidadeKg    A quantidade em quilogramas (kg) de vidro.
	 * @param descricao       Descrição da entrega.
	 * @param linkComprovacao Link de comprovação da entrega.
	 */
	public EntregaVidro(String cpf, double quantidadeKg, String descricao, String linkComprovacao) {
		super(cpf, descricao, linkComprovacao);
		this.quantidadeKg = quantidadeKg;
		super.tipo = "Vidro";
	}

	/**
	 * Calcula o crédito da entrega de vidro.
	 * A cada 10kg é gerado 1 crédito (truncado), com limite máximo de 10 créditos por entrega.
	 * * @return A quantidade de créditos calculada.
	 */
	@Override
	public int calcularCredito() {
		int pontuacao = (int)this.quantidadeKg / 10;
		int max = 10;

		if(pontuacao > max){
			return max;
		}

		return pontuacao;
	}
}