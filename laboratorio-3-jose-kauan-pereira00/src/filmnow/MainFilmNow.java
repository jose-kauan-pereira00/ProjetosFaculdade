package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema FilmNow.
 * 
 * @author eliane
 *
 */
public class MainFilmNow {

	public static void main(String[] args) {
		FilmNow fn = new FilmNow();

		System.out.println("Carregando filmes ...");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaFilmes("filmes_inicial.csv", fn);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha;
		while (true) {
			escolha = menu(scanner);
			comando(escolha, fn, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário/a.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println("""
                                   
                                   ---
                                   MENU
                                   (A)Adicionar filme
                                   (M)Mostrar todos
                                   (D)Detalhar filme
                                   (E)Exibir HotList
                                   (H)Atribuir Hot
                                   (R)Remover Hot
                                   (S)Sair
                                   
                                   Op\u00e7\u00e3o> """);
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param fn  O sistema FilmNow que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, FilmNow fn, Scanner scanner) {
		switch (opcao) {
		case "A" -> adicionaFilme(fn, scanner);
        case "M" -> mostrarFilmes(fn);
        case "D" -> detalharFilme(fn, scanner);
        case "E" -> exibirHotList(fn);
        case "H" -> atribuirHot(fn, scanner);
        case "R" -> removerHot(fn, scanner);
        case "S" -> sai();
        default -> System.out.println("OPÇÃO INVÁLIDA!");
		}
	}

	/**
	 * Imprime lista de filmes.
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 */
	private static void mostrarFilmes(FilmNow fn) {

		Filme[] filmes = fn.getFilmes();
		for (int i = 0; i < filmes.length; i++) {
			if (filmes[i] != null) {
				System.out.println(1 + i + " - " + filmes[i].getNome());
			}
		}
	}

	/**
	 * Imprime os detalhes de um dos filmes. 
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void detalharFilme(FilmNow fn, Scanner scanner) {
		System.out.print("\nQual filme> ");
		int posicao = scanner.nextInt();

		String resultado = fn.detalharFilme(posicao);

		if(!resultado.isEmpty()){
			System.out.println("\n" + resultado);
		} else {
			System.out.println("FILME NÃO ENCONTRADO");
		}
	}
	
	/**
	 * Exibe no console a lista de filmes marcados como HotList.
	 * * @param fn O sistema FilmNow contendo a HotList.
	 */
	private static void exibirHotList(FilmNow fn) {
		Filme[] hotList = fn.getHotList();

        for (int i = 0; i < hotList.length; i++) {
            if (hotList[i] != null) {
                System.out.println((i + 1) + " - " + hotList[i].toString());
            }
        }
		
	}

	/**
	 * Captura dados do usuário e atribui um filme à HotList.
	 * * @param fn O sistema FilmNow.
	 * @param scanner Scanner para capturar posições.
	 */
	private static void atribuirHot(FilmNow fn, Scanner scanner) {
        System.out.print("\nFilme> ");
        int posFilme = scanner.nextInt();
        System.out.print("\nPosicao> ");
        int posHot = scanner.nextInt();
        System.out.println(fn.adicionarHot(posFilme, posHot));
    }

	/**
	 * Captura a posição e remove um filme da HotList.
	 * * @param fn O sistema FilmNow.
	 * @param scanner Scanner para capturar a posição a ser removida.
	 */
	private static void removerHot(FilmNow fn, Scanner scanner) {
        System.out.print("\nPosicao> ");
        int posHot = scanner.nextInt();
        fn.removerHot(posHot);
    }

	/**
	 * Formata um filme para impressão. 
	 * 
	 * @param posicao A posição do filme (que é exibida)/
	 * @param filme O filme a ser impresso.
	 * @return A String formatada.
	 */

	/**
	 * Cadastra um filme no sistema. 
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void adicionaFilme(FilmNow fn, Scanner scanner) {
		System.out.print("\nPosição no sistema> ");
        int posicao = scanner.nextInt();
        scanner.nextLine(); // LIMPEZA DO BUFFER (Importante!)

        System.out.print("\nNome> ");
        String nome = scanner.nextLine(); // nextLine permite nomes com espaços
        
        System.out.print("\nAno> ");
        String anoStr = scanner.nextLine();
        // Tratamento simples caso o usuário digite algo não numérico no ano
        int ano = 0;
        try {
             if(!anoStr.isEmpty()) ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
             // Deixa 0 ou trata como inválido depois
        }
        
        System.out.print("\nLocal> ");
        String local = scanner.nextLine();

        // Passamos a responsabilidade da mensagem para a classe FilmNow
        System.out.println(fn.cadastraFilme(posicao, nome, ano, local));
    }
	

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê carga de filmes de um arquivo csv. 
	 * 
	 * @param arquivoFilmes O caminho para o arquivo.
	 * @param fn O sistema FilmNow a ser populado com os dados.
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaFilmes(String arquivoFilmes, FilmNow fn) throws FileNotFoundException, IOException {
		LeitorFilmNow leitor = new LeitorFilmNow();
		
		int carregados =  leitor.carregaContatos(arquivoFilmes, fn);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
