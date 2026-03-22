package br.edu.ufcg.computacao.p2lp2.coisa;

/**
 * Classe CoisaPlus - Classe de demonstração que estende as funcionalidades de 
 * demonstração do Coisa, especificamente para registrar descanso com emojis.
 * * @author José Kauan Pereira dos Reis - 2025002212
 */
public class CoisaPlus {

    public static void main(String[] agrs){
        System.out.println("--------");
        registrarDescansoComEmoji();
        System.out.println("--------");
    }

    /** * Demonstra a funcionalidade de registro de descanso com a inclusão de emojis 
	 * para representar o status do estudante.
	 * * @return Retorna Vazio
	*/
    public static void registrarDescansoComEmoji() {

        Descanso descanso = new Descanso();
        System.out.println(descanso.getStatusGeral());
        descanso.defineHorasDescanso(30);
        descanso.defineNumeroSemanas(1);
        descanso.definirEmoji("😴");
        System.out.println(descanso.getStatusGeral());
        descanso.defineHorasDescanso(26);
        descanso.defineNumeroSemanas(2);
        descanso.definirEmoji("😌");
        System.out.println(descanso.getStatusGeral());
        descanso.defineHorasDescanso(26);
        descanso.defineNumeroSemanas(1);
        descanso.definirEmoji("😎");
        System.out.println(descanso.getStatusGeral());
    }
    
}