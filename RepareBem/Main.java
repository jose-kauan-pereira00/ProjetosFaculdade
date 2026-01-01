import src.Reparo;
import src.RepareBem;
import src.OrdemServico;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        RepareBem rp = new RepareBem();
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o id do seu reparo: ");
        String id = sc.nextLine();

        System.out.println("Digite a descrição do seu reparo: ");
        String descricao = sc.nextLine();

        System.out.println("Digite o preço do seu reparo: ");
        double preco = sc.nextDouble();
        sc.nextLine(); //Para limpar o " " que fica depois de colocar o double

        rp.cadastrarReparo(id, descricao, preco);

        System.out.println("Digite o nome do cliente:  ");
        String nome = sc.nextLine();

        System.out.println("Digite o telefone do cliente:  ");
        String telefone = sc.nextLine();

        System.out.println("Digite a Roupa: ");
        String  roupa = sc.nextLine();

        rp.cadastrarOrdemDeServico(nome, telefone, roupa);

        System.out.println("Digite a OS que você deseja ver: ");
        int idOS = sc.nextInt();
        System.out.println(rp.exibirOrdemDeServico(idOS));
    }

}
