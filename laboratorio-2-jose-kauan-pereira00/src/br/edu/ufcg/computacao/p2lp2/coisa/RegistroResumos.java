package br.edu.ufcg.computacao.p2lp2.coisa;

/**
 * Classe responsável por gerenciar resumos de conteúdos estudados pelo aluno durante o período.
 * Permite adicionar, consultar e imprimir resumos. A capacidade de resumos é limitada.
 * * @author José Kauan Pereira dos Reis - 2025002212
 */
public class RegistroResumos {

	/**
	 * Array para armazenar os temas (títulos) dos resumos.
	 */
	private final String[] temas;
	/**
	 * Array para armazenar os conteúdos dos resumos.
	 */
	private final String[] conteudos;
	/**
	 * O número máximo de resumos que podem ser armazenados.
	 */
	private final int limite;
	/**
	 * A quantidade atual de resumos armazenados.
	 */
	private int quantidade;
	/**
	 * A próxima posição disponível para a inserção de um novo resumo, implementando um buffer circular.
	 */
	private int proximaPosicao;

	/**
	 * Constrói um RegistroResumos com capacidade para armazenar um número máximo de resumos.
	 * * @param numeroDeResumos O número máximo de resumos que podem ser armazenados.
	 */
	public RegistroResumos(int numeroDeResumos) {
		
		this.limite = numeroDeResumos;
		this.temas = new String[limite];
		this.conteudos = new String[limite];
		this.quantidade = 0;
		this.proximaPosicao = 0;	
	}

	/**
	 * Adiciona um resumo ao registro. Se o tema já existir, o conteúdo é atualizado. 
	 * Se o registro estiver cheio, o resumo mais antigo é sobrescrito (comportamento de buffer circular).
	 * * @param tema O título do resumo.
	 * @param conteudo O conteúdo do resumo.
	 */
	public void adiciona(String tema, String conteudo) {

		for(int i =  0; i < quantidade; i++) {
			if(temas[i].equals(tema)) {
				conteudos[i] = conteudo;
				return;
			}
		}

		temas[proximaPosicao] = tema;
		conteudos[proximaPosicao] = conteudo;

		if(quantidade < limite) {
			quantidade++;
		}

		proximaPosicao = (proximaPosicao + 1) % limite;
		
	}

	/**
	 * Retorna um array com todos os resumos armazenados no formato "Tema:Conteúdo".
	 * * @return Um array de strings contendo os resumos.
	 */
	public String[] pegaResumos() {

		String[] resultados = new String[quantidade];

		for(int i = 0; i < quantidade; i++) {
			resultados[i] = temas[i] + ":" + conteudos[i];
		}

 		return resultados;
	}

	/**
	 * Retorna o número total de resumos armazenados.
	 * * @return O número de resumos.
	 */
	public int conta() {

		return quantidade;
	}

	/**
	 * Imprime todos os temas dos resumos armazenados em formato de string.
	 * O formato é: "- Qtd resumo(s) cadastrado(s):\n- Tema1 | Tema2 | ...".
	 * * @return Uma string contendo o número e a lista de temas dos resumos.
	 */
	public String imprimeResumos() {

		StringBuilder sb = new StringBuilder();

		sb.append("- ").append(quantidade).append(" resumo(s) cadastrado(s):\n-");
		for(int i = 0; i < quantidade; i++) {
			sb.append(temas[i]);
			if(i < quantidade - 1) {
				sb.append(" | ");
			}
		}

		return sb.toString();
	}

	/**
	 * Verifica se existe um resumo com o título (tema) especificado.
	 * @param tema O título do resumo a ser verificado.
	 * @return `true` se existir um resumo com o tema, `false` caso contrário.
	 */
	public boolean temResumo(String string) {

		for(int i = 0; i < quantidade; i++) {
			if(temas[i].equals(string)) {
				return true;
			}
		}

		return false;
	}

}
