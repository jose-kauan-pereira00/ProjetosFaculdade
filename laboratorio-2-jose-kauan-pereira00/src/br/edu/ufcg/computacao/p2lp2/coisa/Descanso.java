package br.edu.ufcg.computacao.p2lp2.coisa;
	
/**
 * Classe para acompanhar a rotina de descanso do esudante. Considera-se que 
 * o aluno começa cansado, caso não tenha registrado horas de descanso ou número
 * de semanas.
 * 
* @author José Kauan Pereira dos Reis - 2025002212
*/
public class Descanso {
	
	/**
	 * O status atual do estudante: "cansado" ou "descansado", o padrão é "cansado".
	 */
	private String statusAtual = "cansado";
	/**
	 * O total de horas de descanso registradas.
	 */
	private int horasDeDescanso;
	/**
	 * O número de semanas para o cálculo da média de tempo por semana. Padrão é 1.
	 */
	private int semanas = 1;
	/**
	 * O tempo médio de descanso por semana (horasDeDescanso / semanas).
	 */
	private int tempoPorSemana;
	/**
	 * O emoji associado ao status do estudante.
	 */
	private String emoji = "";

	/**
	 * Esse método define as horas de descanso do estudante
	 * 
	 * @param horas horas de descanso do estudante.
	 */
	public void defineHorasDescanso(int horas) {
		this.horasDeDescanso = horas;
	}
	
	/**
	 * Esse método define o número de semanas do estudante
	 * 
	 * @param numeros número de semanas do estudante
	 */
	public void defineNumeroSemanas(int numeros) {
		this.semanas = numeros;
	}

	/**
	 * Esse método retorna o status geral do estudante. 
	 * O estudante é considerado "descansado" se o tempo por semana for igual ou superior a 26 horas.
	 * * @return status geral do estudante, concatenado com o emoji se tiver sido definido, porém so implementado no plus..
	 */
	public String getStatusGeral() {
		tempoPorSemana = horasDeDescanso / semanas;
		if(tempoPorSemana >= 26) {
			statusAtual = "descansado";
		}else {
			statusAtual = "cansado";
		}
		
		return statusAtual + emoji;
	}
	 /**
	 * Define um emoji para ser exibido junto ao status.
	 * * @param emoji A string que representa o emoji a ser utilizado.
	 */
	public void definirEmoji(String emoji){
		this.emoji = emoji;
		if(this.statusAtual.equals("cansado")){
			this.statusAtual += "-" + this.emoji;
		}else{
			this.statusAtual += "-" + this.emoji;
		}
	}
}
