package br.edu.ufcg.computacao.p2lp2.coisa;

/**
 * O registro de temp online deve ser uma classe responsável por manter a informação sobre 
 * quantidade de horas de internet que o aluno tem dedicado a uma disciplina remota.
 * 
 * @author José Kauan Pereira dos Reis - 2025002212
 */
public class RegistroTempoOnline {

	/**
	 * O total de tempo online dedicado à disciplina (em horas).
	 */
	private int tempoOnline = 0;
	/**
	 * O tempo online esperado para a disciplina (em horas).
	 */
	private final int tempoEsperado;
	/**
	 * O nome da disciplina ou atividade.
	 */
	private final String disciplina;


	/**
	 * Constrói um registro de tempo online com a disciplina e o tempo esperado especificados.
	 * * @param disciplina nome da atividade/disciplina
	 * @param tempoEsperado tempo esperado de dedicação à atividade (em horas)
	 */
	public RegistroTempoOnline(String disciplina, int tempoEsperado){
		this.tempoEsperado = tempoEsperado;
		this.disciplina = disciplina;
	}
	
	/**
	 * Constrói um registro de tempo online com a disciplina especificada e um tempo esperado padrão de 120 horas.
	 * * @param disciplina nome da atividade/disciplina
	 */
	public RegistroTempoOnline(String disciplina) {

		this.disciplina = disciplina;
		tempoEsperado = 120;
	}

	/**
	 * Adiciona o tempo dedicado à disciplina ao total de tempo online.
	 * * @param tempo O tempo (em horas) a ser adicionado.
	 */
	public void adicionaTempoOnline(int tempo) {

		this.tempoOnline  += tempo;
	}

	/**
	 * Verifica se o estudante atingiu ou superou a meta de tempo online.
	 * * @return `true` se o tempo online for maior ou igual ao tempo esperado, `false` caso contrário.
	 */
	public boolean atingiuMetaTempoOnline() {
		return this.tempoOnline >= this.tempoEsperado;
	}
	
	/**
	 * Retorna a representação em String do RegistroTempoOnline. 
	 * O formato é: "DISCIPLINA TEMPO_ONLINE/TEMPO_ESPERADO".
	 * * @return A string que representa o registro.
	 */
	@Override
	public String toString(){
		return disciplina + " " + tempoOnline + "/" + tempoEsperado;
	}
}
