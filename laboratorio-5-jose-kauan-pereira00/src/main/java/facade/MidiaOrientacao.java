package facade;

/**
 * Representa um conteúdo em mídia (vídeo, áudio) anexado a uma Orientação Ambiental.
 */
public class MidiaOrientacao extends ItemInformativo{
	private String link;
	private String titulo;
	private int duracaoSegundo;

	/**
	 * Construtor para uma Mídia de Orientação.
	 * * @param link           O link para acessar a mídia.
	 * @param titulo         O título da mídia.
	 * @param duracaoSegundo A duração da mídia em segundos.
	 */
	public MidiaOrientacao(String link, String titulo, int duracaoSegundo){
		this.link = link;
		this.titulo = titulo;
		this.duracaoSegundo = duracaoSegundo;
	}

	/**
	 * Calcula a pontuação da mídia com base em sua duração.
	 * Cada minuto completo gera 5 pontos, limitados a um máximo de 50 pontos.
	 * * @return Os pontos gerados pela mídia.
	 */
	@Override
	public int calcularPontos() {
		int min = duracaoSegundo /  60;

		int pontuacao = min * 5;
		int max = 50;
		if(pontuacao > max){
			return 50;
		}

		return pontuacao;
	}

	@Override
	public String toString(){
		return null;
	}
}