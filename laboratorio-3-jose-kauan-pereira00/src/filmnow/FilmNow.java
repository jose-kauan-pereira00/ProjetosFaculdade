package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes. 
 * 
 * @author eliane
 *
 */
public class FilmNow {

	private static final int TAMANHO = 100;
	private static final int HOT_LIST = 10;
	


	private final Filme[] filmes;
	private final Filme[] hotList;


	/**
	 * Cria o FilmNow.
	 */
	public FilmNow() {
		this.filmes = new Filme[TAMANHO];
		this.hotList = new Filme[HOT_LIST];
	}
	
	/**
	 * Acessa a lista de filmes mantida.
	 * @return O array de filmes.
	 */
	public Filme[] getFilmes() {
		return this.filmes.clone();
	}

	/**		
	 * Acessa os dados de um filme específico.
	 * @param posicao Posição do filme no sistema.
	 * @return Dados do filme. Null se não há filme na posição.
	 */
	public Filme getFilme(int posicao) {
		return filmes[posicao - 1];
	}

	public Filme[] getHotList() {
		
		return this.hotList.clone();
	}

	/**
	 * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior. 
	 * @param posicao Posição do filme.
	 * @param nome Nome do filme.
	 * @param ano Ano de lançamento do filme.
	 * @param local Local onde o filme pode ser assitido.
	 */
	public String cadastraFilme(int posicao, String nome, int ano, String local) {
		
		if(posicao < 1 || posicao > TAMANHO) {
			return "POSIÇÃO INVÁLIDA!";
		}

		if(nome == null || nome.isBlank() || local == null || local.isBlank()) {
			return "FILME INVÁLIDO!";
		}

		Filme novoFilme = new Filme(nome, ano, local);

		for(Filme filmeExistente : filmes) {
			
			if(filmeExistente != null && novoFilme.equals(filmeExistente)) {
				return "FILME JÁ ADICIONADO!";
			}
		}

		filmes[posicao - 1] = novoFilme;
		return "FILME ADICIONADO";
	}

	/**
	 * Retorna os detalhes de um filme, verificando se ele pertence à HotList.
	 * Caso pertença, adiciona um emoji de fogo (🔥) antes do nome.
	 * * @param posicao A posição do filme na lista principal.
	 * @return String com os detalhes do filme ou mensagem de erro.
	 */
	public String detalharFilme(int posicao) {
		if(posicao < 1 || posicao > TAMANHO) {
			return "POSIÇÃO INVÁLIDA!";
		}

		Filme filme = filmes[posicao - 1];
		if(filme == null) {
			return "FILME INEXISTENTE!";
		}

		String prefixo = "";
		if(isHot(filme)) {
			prefixo = "🔥 ";
		}
		return prefixo + filme.toString();
	}

	/**
	 * Verifica internamente se um filme está presente na HotList.
	 * * @param filme O objeto Filme a ser verificado.
	 * @return true se o filme estiver na HotList, false caso contrário.
	 */
	private boolean isHot(Filme filme) {
		for(Filme hot : hotList) {
			if(hot != null && filme.equals(hot)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adiciona um filme existente na lista principal para a HotList.
	 * Valida posições e se o filme já está na HotList.
	 * * @param posicaoFilme A posição do filme na lista geral (1-100).
	 * @param posicaoHot A posição desejada na HotList (1-10).
	 * @return Mensagem de sucesso ou erro.
	 */
	public String adicionarHot(int posicaoFilme, int posicaoHot) {
		if(posicaoHot < 1 || posicaoHot > HOT_LIST) {
			return "POSIÇÃO INVÁLIDA!";
		}

		if (posicaoFilme < 1 || posicaoFilme > 100 || filmes[posicaoFilme - 1] == null) {
             return "POSIÇÃO INVÁLIDA"; // Ou filme inexistente
		}

		Filme filmeParaAdicionar = filmes[posicaoFilme - 1];

		if(isHot(filmeParaAdicionar)) {
			return "FILME JÁ ESTÁ NA HOT LIST!";
		}

		this.hotList[posicaoHot -1] = filmeParaAdicionar;
		return "ADICIONADO A HOT LIST NA POSIÇÃO " + posicaoHot + "!";
	}

	/**
	 * Remove um filme da HotList com base na posição.
	 * * @param posicaoHot A posição na HotList a ser limpa.
	 */
	public void removerHot(int posicaoHot) {
		if(posicaoHot < 1 || posicaoHot > HOT_LIST) {
			return;
		}

		this.hotList[posicaoHot -1] = null;
	}
}