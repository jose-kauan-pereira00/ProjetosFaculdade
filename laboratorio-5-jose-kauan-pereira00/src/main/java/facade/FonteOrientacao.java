package facade;

/**
 * Representa uma fonte de pesquisa ou artigo anexado a uma Orientação Ambiental.
 */
public class FonteOrientacao extends ItemInformativo{
	private String titulo;
	private String fonte;
	private int ano;
	private boolean verificado;
	private int relevancia;

	/**
	 * Construtor para uma Fonte de Orientação.
	 * * @param titulo     O título da fonte.
	 * @param fonte      O nome do veículo ou autor da fonte.
	 * @param ano        O ano de publicação.
	 * @param verificado Indica se a fonte foi verificada.
	 * @param relevancia Um índice ou peso indicando a relevância da fonte.
	 */
	public FonteOrientacao(String titulo, String fonte, int ano, boolean verificado, int relevancia){
		this.titulo = titulo;
		this.fonte = fonte;
		this.ano = ano; 
		this.verificado = verificado;
		this.relevancia = relevancia;
	}

	/**
	 * Calcula a pontuação baseada na verificação da fonte.
	 * Fontes verificadas geram 15 pontos; não verificadas geram 5 pontos.
	 * * @return Os pontos gerados pela fonte.
	 */
	@Override
	public int calcularPontos() {
		if(verificado){
			return 15;
		}
		return 5;
	}
}