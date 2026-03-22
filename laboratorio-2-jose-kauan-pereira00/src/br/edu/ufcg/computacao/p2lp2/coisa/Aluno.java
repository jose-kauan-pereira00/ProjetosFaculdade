package br.edu.ufcg.computacao.p2lp2.coisa;

/**
* Representação de um estudante, especificamente de computação, matriculado da 
* UFCG. Todo aluno precisa ter uma matrícula e é identificado unicamente
* por esta matrícula.
* 
* @author José Kauan Pereira dos Reis - 2025002212
*/
public class Aluno {
	/**
	 *  Nome Remete ao Nome do Aluno;
	 */
	private final String nome;
	/**
	 * Ano de Nascimento Remete ao Ano de Nascimento do Aluno em formato yyyy;
	 */
	private final int anoNascimento;
	/**
	 * CRA Remete ao Coeficiente de Rendimento Acadêmico do Aluno;
	 */
	public  double cra;
	
	
	/**
	* Constrói um aluno a partir de seu nome, ano de nascimento e CRA.
	* Todo aluno começa com o campo CRA como nulo.
	*
	* @param anoNascimento de Nascimento do aluno, no formato “YYYY".
	* @param nome o nome do aluno
	*/
	public Aluno(String nome, int anoNascimento) {

		this.nome = nome;
		this.cra = 0.0;
		this.anoNascimento = anoNascimento;
	}

	
	/**Esse método atribui o valor ao CRA
	* @return Retorna Vazio
	*/
	public void setCra(double cra) {
			this.cra = cra;
	}

	/**
	* Retorna um Inteiro(int) que representa a Idade do aluno. A representação segue o 
	* formato “(YY) ex: 25”.
	*
	* @return a represetação em inteiro da idade.
	*/
	public int getIdade() {
			return 2025 - anoNascimento;
	}

	/**
	* Retorna a String que representa o nome do aluno. A representação segue o 
	* formato “Aluno - Nome do Aluno”.
	*
	* @return a representação em String do nome de um aluno.
	*/
	@Override
	public String toString() {
		return "Aluno - "  + this.nome;
	}
}

