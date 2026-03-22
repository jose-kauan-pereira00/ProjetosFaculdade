package br.edu.ufcg.computacao.p2lp2.coisa;

/**
 * Classe de demonstração para a criação e manipulação de objetos Aluno.
 */
public class ControleAcademico {
	public static void main(String[] args) {
	Aluno aluno1 = new Aluno("José Kauan", 2006);
	Aluno aluno2 = new Aluno("Kauan Pereira", 2007);
	aluno1.cra = 8.5;
	
	System.out.println(aluno1);
	System.out.println(aluno2);
	}
}
