package facade;

/**
 * Classe abstrata que representa um item informativo anexado a uma Orientação Ambiental.
 */
public abstract class ItemInformativo {

	/**
	 * Calcula a pontuação gerada por este item informativo específico.
	 * * @return Os pontos gerados pelo item.
	 */
	public abstract int calcularPontos();
}