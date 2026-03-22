package facade;

import java.util.*;

public class FacadeEcoPontos {
    private int idOrientacao;
    private int idEntrega;
    private Map<String, Morador> moradores;
    private Map<Integer, OrientacaoAmbiental> orientacoes;

    public FacadeEcoPontos(){
        this.idOrientacao = 0;
        this.idEntrega = 0;
        this.moradores = new HashMap<>();
        this.orientacoes = new HashMap<>();
    }
    
    // =========================
    // Moradores
    // =========================
    
    public boolean criarMorador(String nome, String cpf, String senha, String residencia) {
        Morador m = new Morador(nome, cpf, senha, residencia);
        moradores.put(cpf, m);
        return true;
    }

    public String[] exibirMoradores() {
        List<Morador> listaMor = new ArrayList<>(moradores.values());
        Collections.sort(listaMor);
        String[] resultado = new String[listaMor.size()];

        for(int i = 0; i < listaMor.size(); i++){
            resultado[i] = listaMor.get(i).toString();
        }
        return resultado;
    }

    public boolean alterarSenhaMorador(String cpf, String senhaAntiga, String novaSenha) {
        Morador m = moradores.get(cpf);
        if(m != null && m.validarSenha(senhaAntiga)) {
            m.setSenha(novaSenha);
            return true;
        }
        return false;
    }

    // =========================
    // Orientações Ambientais
    // =========================

    public int criarOrientacao(String cpf, String senha, String tipo) {
        if(cpf == null || !moradores.containsKey(cpf)) throw new IllegalArgumentException("CPF Inexistente");
        Morador m = moradores.get(cpf);
        if(!m.validarSenha(senha)) throw new IllegalArgumentException("Senha Incorreta");

        OrientacaoAmbiental oa = new OrientacaoAmbiental(cpf, tipo);
        orientacoes.put(idOrientacao, oa);
        m.adicionarOA(oa);

        return idOrientacao++;
    }

    public boolean adicionarTextoOrientacao(String cpf, String senha, int idOrientacao, String texto) {
        validarAcessoOrientacao(cpf, senha, idOrientacao);
        orientacoes.get(idOrientacao).adicionarItem(new TextoOrientacao(texto));
        return true;
    }

    public boolean adicionarItemMidiaOrientacao(String cpf, String senha, int idOrientacao, String link, String titulo, int duracaoSegundos) {
        validarAcessoOrientacao(cpf, senha, idOrientacao);
        orientacoes.get(idOrientacao).adicionarItem(new MidiaOrientacao(link, titulo, duracaoSegundos));
        return true;
    }

    public boolean adicionarItemFonteOrientacao(String cpf, String senha, int idOrientacao, String titulo, String fonte, int ano, boolean verificada, int relevancia) {
        validarAcessoOrientacao(cpf, senha, idOrientacao);
        orientacoes.get(idOrientacao).adicionarItem(new FonteOrientacao(titulo, fonte, ano, verificada, relevancia));
        return true;
    }

    private void validarAcessoOrientacao(String cpf, String senha, int idOrientacao) {
        if(cpf == null || !moradores.containsKey(cpf)) throw new IllegalArgumentException("CPF inexistente");
        if(!moradores.get(cpf).validarSenha(senha)) throw new IllegalArgumentException("Senha Incorreta");
        if(!orientacoes.containsKey(idOrientacao)) throw new IllegalArgumentException("OrientacaoAmbiental Inexistente");
    }

    public String[] listarOrientacoes() {
        String[] resultado = new String[orientacoes.size()];
        int index = 0;
        for (int i = 0; i < idOrientacao; i++) {
            if (orientacoes.containsKey(i)) {
                resultado[index++] = listarOrientacao(i);
            }
        }
        return resultado;
    }

    public String[] listarOrientacoesDetalhadas() {
        String[] resultado = new String[orientacoes.size()];
        int index = 0;
        for (int i = 0; i < idOrientacao; i++) {
            if (orientacoes.containsKey(i)) {
                resultado[index++] = listarOrientacaoDetalhada(i);
            }
        }
        return resultado;
    }

    public String listarOrientacao(int idOrientacao) {
        if (!orientacoes.containsKey(idOrientacao)) throw new IllegalArgumentException("Orientacao Inexistente");
        OrientacaoAmbiental oa = orientacoes.get(idOrientacao);
        Morador m = moradores.get(oa.getCpfMorador());
        
        return String.format("ID: %d - %s - %s - %d itens - %d curtidas",
                idOrientacao, oa.getTipo(), m.getNome(), oa.getQuantidadeItens(), oa.getCurtidas());
    }

    public String listarOrientacaoDetalhada(int idOrientacao) {
        if (!orientacoes.containsKey(idOrientacao)) throw new IllegalArgumentException("Orientacao Inexistente");
        OrientacaoAmbiental oa = orientacoes.get(idOrientacao);
        Morador m = moradores.get(oa.getCpfMorador());
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID: %d - %s - %s - %d itens - %d curtidas\n",
                idOrientacao, oa.getTipo(), m.getNome(), oa.getQuantidadeItens(), oa.getCurtidas()));
        sb.append("- Itens:\n");
        sb.append(oa.listarItens());
        return sb.toString();
    }

    // =========================
    // Engajamento
    // =========================

    public int consultarCreditosEngajamento(String cpf, String senha) {
        if(cpf == null || !moradores.containsKey(cpf)) throw new IllegalArgumentException("Cpf Invalido");
        Morador m = moradores.get(cpf);
        if(senha == null || !m.validarSenha(senha)) throw new IllegalArgumentException("Senha Invalida");
        
        return m.calcularCreditoEntregas() + m.calcularCreditoOrientacoes();
    }

    public void curtirOrientacao(String cpf, String senha, int idOrientacao) {
       validarAcessoOrientacao(cpf, senha, idOrientacao);
       orientacoes.get(idOrientacao).darCurtida();
    }

    public String[] rankingMoradoresPorEngajamento() {
        List<Morador> lista = new ArrayList<>(moradores.values());
        
        lista.sort((m1, m2) -> {
            int cred1 = m1.calcularCreditoEntregas() + m1.calcularCreditoOrientacoes();
            int cred2 = m2.calcularCreditoEntregas() + m2.calcularCreditoOrientacoes();
            if (cred1 != cred2) return Integer.compare(cred2, cred1);
            return m1.getNome().compareTo(m2.getNome());
        });

        String[] ranking = new String[lista.size()];
        for(int i = 0; i < lista.size(); i++){
            Morador m = lista.get(i);
            int creditos = m.calcularCreditoEntregas() + m.calcularCreditoOrientacoes();
            ranking[i] = String.format("%d. %s - %s - %d", (i+1), m.getNome(), m.getResidencia(), creditos);
        }
        return ranking;
    }

    // =========================
    // Entregas em Ecopontos
    // =========================

    public int registrarEntregaPapel(String cpf, String senha, double quantidadeKg, String descricao, String linkComprovacao) {
        Morador m = validarMoradorEntrega(cpf, senha);
        m.adicionarEntrega(new EntregaPapel(cpf, quantidadeKg, descricao, linkComprovacao));
        return idEntrega++;
    }

    public int registrarEntregaPlastico(String cpf, String senha, double quantidadeKg, String descricao, String linkComprovacao) {
        Morador m = validarMoradorEntrega(cpf, senha);
        m.adicionarEntrega(new EntregaPlastico(cpf, quantidadeKg, descricao, linkComprovacao));
        return idEntrega++;
    }

    public int registrarEntregaVidro(String cpf, String senha, double quantidadeKg, String descricao, String linkComprovacao) {
        Morador m = validarMoradorEntrega(cpf, senha);
        m.adicionarEntrega(new EntregaVidro(cpf, quantidadeKg, descricao, linkComprovacao));
        return idEntrega++;
    }

    public int registrarEntregaEletronico(String cpf, String senha, int quantidadeItens, String descricao, String linkComprovacao) {
        Morador m = validarMoradorEntrega(cpf, senha);
        m.adicionarEntrega(new EntregaEletronicos(cpf, quantidadeItens, descricao, linkComprovacao));
        return idEntrega++;
    }

    private Morador validarMoradorEntrega(String cpf, String senha) {
        if(cpf == null || !moradores.containsKey(cpf)) throw new IllegalArgumentException("Cpf Invalido");
        Morador m = moradores.get(cpf);
        if(senha == null || !m.validarSenha(senha)) throw new IllegalArgumentException("Senha Invalida");
        return m;
    }

    public String consultarRegraCreditos(String cpf, String senha, String tipoEntrega) {
        String papel = "No caso de papel, a unidade de contagem é quilograma (kg). A conversão deve gerar 1 crédito ambiental a cada 5kg entregues, de forma que uma entrega com quantidade inferior a 5kg não gera crédito e quantidades maiores acumulem créditos proporcionalmente, sempre com truncamento. O total de créditos acumulados nesse tipo possui um teto máximo de 8 créditos por entrega.";
        String plastico = "No caso de plástico, a unidade de contagem também é quilograma e a conversão deve gerar crédito ambiental a cada 3kg entregues, aplicando truncamento no cálculo. O total de créditos acumulados nesse tipo possui um teto máximo de 10 créditos por entrega.";
        String vidro = "No caso de vidro, a unidade de contagem é quilograma e a conversão deve gerar 1 crédito ambiental a cada 10kg entregues, também com truncamento quando necessário. O total de créditos acumulados nesse tipo possui um teto máximo de 6 créditos por entrega.";
        String eletronico = "Para resíduos eletrônicos, a unidade de contagem é a quantidade de itens, e cada item entregue deve gerar diretamente 2 créditos ambientais, refletindo o impacto ambiental do descarte inadequado desse material. Para esse tipo, o total de créditos acumulados possui um teto máximo de 12 créditos por entrega.";
        
        if(tipoEntrega.equalsIgnoreCase("papel")) return papel;
        else if(tipoEntrega.equalsIgnoreCase("plastico")) return plastico;
        else if(tipoEntrega.equalsIgnoreCase("vidro")) return vidro;
        else return eletronico;
    }

    // =========================
    // Boletins
    // =========================

    public String gerarBoletim(String cpf, String senha) {
        return validarMoradorEntrega(cpf, senha).boletimGeral();
    }

    public String gerarBoletimPorTipoMaterial(String cpf, String senha, String tipoMaterial) {
        return validarMoradorEntrega(cpf, senha).boletimPorMaterial(tipoMaterial);
    }

    public String gerarBoletimPorTipoOrientacao(String cpf, String senha, String tipoOrientacao) {
        return validarMoradorEntrega(cpf, senha).boletimOrientacao(tipoOrientacao);
    }
}