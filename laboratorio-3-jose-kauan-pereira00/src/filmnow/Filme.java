package filmnow;

/**
 * Representa um filme no sistema FilmNow.
 * Responsável por armazenar os dados básicos de um filme (nome, ano e local)
 * e verificar igualdade entre filmes.
 * @author José Kauan Pereira dos Reis - 20250022120
 */
public class Filme {
	
	private final String nome;
	private final String local;
	private final int ano;
	
	/**
	 * Constrói um novo objeto Filme.
	 * * @param nomeFilme O título do filme.
	 * @param anoLancamento O ano de lançamento.
	 * @param local O serviço de streaming ou local onde o filme pode ser assistido.
	 */
	public Filme(String nomeFilme, int anoLancamento, String local) {
		this.nome = nomeFilme;
		this.ano = anoLancamento;
		this.local = local;
	}

	/**
	 * Recupera o nome do filme.
	 * @return O nome do filme.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recupera o local de exibição do filme.
	 * @return O local (ex: Netflix, Cinema).
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * Recupera o ano de lançamento.
	 * @return O ano do filme.
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * Retorna uma representação em String do filme.
	 * Formato: "Nome (Ano) - Local"
	 * * @return String formatada com os dados do filme.
	 */
	@Override
	public String toString() {
		return nome + " (" + ano + ") - " + local;
	}

	/**
	 * Verifica se dois filmes são iguais.
	 * Critério de igualdade: Mesmo Nome e mesmo Ano de Lançamento.
	 * * @param obj O objeto a ser comparado.
	 * @return true se forem iguais, false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Filme outroFilme = (Filme) obj;
		return this.nome.equals(outroFilme.nome) && this.ano == outroFilme.ano;
	}

}