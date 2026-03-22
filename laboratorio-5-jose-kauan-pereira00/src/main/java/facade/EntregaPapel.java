package facade;

/**
 * Representa uma entrega de papel em um ecoponto.
 */
public class EntregaPapel extends Entrega{
	private double quantidadeKg;

	/**
	 * Construtor para entrega de papel.
	 * * @param cpf             O CPF do morador.
	 * @param quantidadeKg    A quantidade em quilogramas (kg) de papel.
	 * @param descricao       Descrição da entrega.
	 * @param linkComprovacao Link de comprovação da entrega.
	 * @throws IllegalArgumentException Se a quantidade em kg for negativa.
	 */
	public EntregaPapel(String cpf, double quantidadeKg, String descricao, String linkComprovacao) {
		super(cpf, descricao, linkComprovacao);
		if(quantidadeKg < 0){
			throw new IllegalArgumentException("Quantidade Invalida");
		}
		super.tipo = "Papel";
		this.quantidadeKg = quantidadeKg;
	}

	/**
	 * Calcula o crédito da entrega de papel.
	 * A cada 5kg é gerado 1 crédito (truncado), com limite máximo de 8 créditos por entrega.
	 * * @return A quantidade de créditos calculada.
	 */
	@Override
	public int calcularCredito() {
		int pontuacao = (int)this.quantidadeKg / 5;
		int max = 8;
		if(pontuacao > max){
			return 8;
		}

		return pontuacao;
	}
}