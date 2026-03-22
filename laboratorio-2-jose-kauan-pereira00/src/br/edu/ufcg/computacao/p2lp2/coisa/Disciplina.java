package br.edu.ufcg.computacao.p2lp2.coisa;

/**
 * Por padrão toda disciplina tem 4 notas. Calcula-se a média da disciplina,
 * fazendo a média aritmética dessas 4 notas (sem arredondamento). Todo aluno é considerado
 * aprovado quando atinge a média igual ou acima de 7.0. Caso alguma das notas não seja 
 * cadastrada, ela é considerada como zero.
 * Se a alguma nota ( nota 1, nota 2, nota 3 ou nota 4 ) for cadastrada mais de uma vez,
 * consideramos a última nota cadastrada (ou seja, é possível repor notas nesse sistema).
 * 
 * @author José Kauan Pereira dos Reis - 2025002212
 */
public class Disciplina {
	
	/**
	 * O total de horas que o aluno dedicou à disciplina.
	 */
	private int horasDedicadas = 0;
	/**
	 * A soma total das notas cadastradas (pode não ser a média final).
	 */
	private double notaTotal;
	/**
	 * O nome da disciplina.
	 */
	private final String disciplina;
	/**
	 * Array para armazenar as 4 notas da disciplina.
	 */
	private double notas[] = new double[4];
	

	/**
	 * Constrói uma disciplina com o nome especificado e inicializa as horas dedicadas e notas.
	 * * @param disciplina nome da disciplina
	 */
	public Disciplina(String disciplina) {

		this.disciplina = disciplina;
		this.horasDedicadas = 0;
		this.notas = new double[4];	
	}

	/**
	 * Adiciona as horas dedicadas pelo aluno à disciplina.
	 * * @param horas a quantidade de horas a ser adicionada.
	 */
	public void cadastraHoras(int horas) {
		horasDedicadas += horas;
	}

	/**
	 * Cadastra o valor de uma nota na posição correspondente.
	 * Se a nota for cadastrada mais de uma vez, o valor anterior é sobrescrito.
	 * * @param nota O número da nota a ser cadastrada (1, 2, 3 ou 4).
	 * @param valorNota O valor da nota.
	 */
	public void cadastraNota(int nota, double valorNota) {

		notaTotal += valorNota;
		 if (nota >= 1 && nota <= 4) {
            this.notas[nota - 1] = valorNota;	
		}
	}

	/**
	 * Retorna se o aluno está aprovado ou não na disciplina.
	 * A aprovação ocorre se a média das 4 notas (calculada com base em `notaTotal`) for maior ou igual a 7.0.
	 * * @return `true` se o aluno estiver aprovado (média >= 7.0), `false` caso contrário.
	 */
	public boolean aprovado() {
		return notaTotal / 4 >= 7.0;
	}
	
	/**
	 * Retorna a representação em String da Disciplina, incluindo nome, horas dedicadas,
	 * média das notas e o array de notas.
	 * * @return A string que representa a disciplina.
	 */
	@Override
	public String toString() {

		String notasString = String.format("[%.1f, %.1f, %.1f, %.1f]", notas[0], notas[1], notas[2], notas[3]);

		return disciplina + " " + horasDedicadas + " " + (notaTotal / 4) + " " + notasString;
	}

}