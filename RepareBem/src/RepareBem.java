package src;
import java.util.List;
import java.util.ArrayList;
/**
 * @author José Kauan Pereira dos Reis
 */

/*
 * mudarStatusOrdemDeServico( idOS:int, status:String ): void
*obterValorOrdemServico( idOS:int ):double
*listarOrdemDeServico( status:String ): String
*listarOrdensDeServico( ):String //Serão listadas todas as OS cadastradas
*/
public class RepareBem{
    private List<Reparo> listaReparo;
    private List<OrdemServico>  listaOS;

    public RepareBem(){
        listaReparo = new ArrayList<Reparo>();
        listaOS = new ArrayList<OrdemServico>();
    }

    public void cadastrarReparo(String id, String descricao, double preco){
        Reparo reparo = new Reparo(id, descricao, preco);
        listaReparo.add(reparo);
    }

    public void reajustarPrecoReparo(String id, double percentual){
        for(Reparo reparo: listaReparo){
            if(reparo.getId() == id){
                reparo.reajustarPreco(percentual);
            }
        }
    }

    public int cadastrarOrdemDeServico(String nome ,String telefone ,String pecaRoupa ){
        OrdemServico os = new OrdemServico(nome, telefone, pecaRoupa);
        listaOS.add(os);

        return listaOS.size();
    }

    public String exibirOrdemDeServico(int idOS ){
        if(idOS < 1 || idOS > listaOS.size()){
            throw new IllegalArgumentException("Erro: Ordem de serviço "+ idOS+ " inexistente.");
        }
        return listaOS.get(idOS - 1).toString();
    }

    public void incluirReparoOrdemDeServico(int idOS, String id){
        Reparo rep = null;
        for(Reparo reparo : listaReparo){
            if(reparo.getId() == id){
                rep = reparo;
            }
        }
        listaOS.get(idOS).adicionaReparo(rep);
    }

    public void mudarStatusOrdemDeServico(int idOS , String status ){
        listaOS.get(idOS).setStatus(status);
    }

}
