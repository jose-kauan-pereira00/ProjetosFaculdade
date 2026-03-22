package facade;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um morador registrado no sistema EcoPontos.
 * Armazena seus dados pessoais, entregas e orientações criadas.
 */
public class Morador implements Comparable<Morador>{
    private final String cpf;
    private String nome;
    private String senha;
    private String residencia;
    private List<OrientacaoAmbiental> orientacoesMorador;
    private List<Entrega> entregas;

    /**
     * Construtor para criar um novo morador.
     * * @param nome       O nome do morador.
     * @param cpf        O CPF do morador (deve conter 11 caracteres).
     * @param senha      A senha do morador (mínimo de 8 caracteres).
     * @param residencia O código ou endereço de residência validado.
     * @throws IllegalArgumentException Se CPF, senha ou residência forem inválidos.
     */
    public Morador(String nome, String cpf, String senha, String residencia) {
        if (cpf == null || cpf.length() != 11){
            throw new IllegalArgumentException("CPF inválido");
        }
        if (senha == null || senha.length() < 8){
            throw new IllegalArgumentException("Senha curta");
        }
        if(!this.validaResidencia(residencia)){
            throw new IllegalArgumentException("Residencia invalida");
        }

        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.residencia = residencia;
        this.orientacoesMorador = new ArrayList<>();
        this.entregas = new ArrayList<>();
    }

    /**
     * Verifica se a senha fornecida corresponde à senha atual do morador.
     * * @param senha A senha a ser validada.
     * @return true se for válida, false caso contrário.
     */
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }

    /**
     * Adiciona uma nova entrega ao histórico do morador.
     * * @param en A entrega a ser adicionada.
     */
    public void adicionarEntrega(Entrega en){
        this.entregas.add(en);
    }

    /**
     * Calcula o total de créditos obtidos através das orientações criadas pelo morador.
     * * @return Total de créditos de orientações.
     */
    public int calcularCreditoOrientacoes() {
            int pontos = 0;
            for(int i = 0; i < orientacoesMorador.size(); i++){
                pontos += orientacoesMorador.get(i).calcularPontuacao();
            }
            return pontos;
        }

    /**
     * Calcula o total de créditos obtidos através das entregas realizadas pelo morador.
     * * @return Total de créditos de entregas.
     */
    public int calcularCreditoEntregas(){
        int pontos = 0;
        for(int i = 0; i < entregas.size(); i++){
            pontos += entregas.get(i).calcularCredito();
        }
        return pontos;
    }

    /**
     * Associa uma nova Orientação Ambiental ao morador.
     * * @param oa A orientação ambiental criada pelo morador.
     */
    public void adicionarOA(OrientacaoAmbiental oa){
        orientacoesMorador.add(oa);
    }

    /**
     * Altera a senha do morador.
     * * @param novaSenha A nova senha a ser registrada.
     * @throws IllegalArgumentException Se a senha for nula ou menor que 8 caracteres.
     */
    public void setSenha(String novaSenha) {
        if(novaSenha.length() < 8 || novaSenha == null){
            throw new IllegalArgumentException("Senha Invalida");
        }
        this.senha = novaSenha;
    }

    /**
     * Valida o formato da residência (deve conter 7 caracteres alfanuméricos).
     * * @param residencia A string da residência.
     * @return true se for válida, false caso contrário.
     */
    private boolean validaResidencia(String residencia){
        if(residencia == null || residencia.length() != 7 || residencia.isEmpty()) return false;
           
        boolean temLetra = false;
        boolean temNum = false;

        for(int i = 0; i < residencia.length(); i++){
            char ch = residencia.charAt(i);
            if(Character.isLetter(ch)) temLetra = true;
            else if(Character.isDigit(ch)) temNum = true;
        }
        return temLetra && temNum;
    }

    /**
     * Métodos gets
     * */
    public String getCpf() { 
        return this.cpf; 
    }
    public String getNome(){ 
        return this.nome; 
    }
    public String getResidencia() { 
        return this.residencia; 
    }

    @Override
    public String toString() {
        return this.nome + ", " + this.residencia;
    }

    @Override
    public int compareTo(Morador outro) {
        return this.nome.compareTo(outro.getNome());
    }

    /**
     * Gera um boletim geral consolidado contendo todas as entregas e engajamentos do morador.
     * * @return Uma String formatada com o boletim do morador.
     */
    public String boletimGeral(){
           int papel = 0, plastico = 0, vidro = 0, eletronico = 0;
           for (Entrega en : entregas) {
               if (en.getTipo().equalsIgnoreCase("Papel")) papel += en.calcularCredito();
               else if (en.getTipo().equalsIgnoreCase("Plastico")) plastico += en.calcularCredito();
               else if (en.getTipo().equalsIgnoreCase("Vidro")) vidro += en.calcularCredito();
               else if (en.getTipo().equalsIgnoreCase("Residuos Eletronicos")) eletronico += en.calcularCredito();
           }

           return String.format(
               "BOLETIM ECOPONTOS - GERAL\n" +
               "MORADOR: %s | Residencia: %s\n" +
               "CREDITOS AMBIENTAIS (Por Materiais):\n" +
               "- Papel: %d\n" +
               "- Plastico: %d\n" +
               "- Vidro: %d\n" +
               "- Res. Eletronico: %d\n" +
               "TOTAL DE CREDITO (por Materiais): %d\n" +
               "ENGAJAMENTO (Orientacoes):\n" +
               "- Orientações cadastradas: %d\n" +
               "- Credito de Engajamento: %d\n" +
               "- Curtidas Recebidas: %d",
               this.nome, this.residencia, papel, plastico, vidro, eletronico, 
               (papel + plastico + vidro + eletronico), 
               this.orientacoesMorador.size(), calcularCreditoOrientacoes(), totalCurtidas());
       }

    /**
     * Calcula o total de curtidas recebidas em todas as orientações do morador.
     * * @return A soma de curtidas de todas as orientações.
     */
    private int totalCurtidas(){
            int curtidas = 0;
            for(OrientacaoAmbiental oa : orientacoesMorador){
                curtidas += oa.getCurtidas();
            }
            return curtidas;
        }

    /**
     * Gera um boletim focado apenas em um tipo específico de material.
     * * @param tipo O tipo de material filtrado (ex: "Papel", "Vidro").
     * @return Uma String formatada com os resultados daquele material.
     */
    public String boletimPorMaterial(String tipo){
            int pontos = 0;
            for (Entrega en : entregas) {
                if (en.getTipo().equalsIgnoreCase(tipo)) pontos += en.calcularCredito();
            }
            return String.format(
                "BOLETIM ECOPONTOS - POR MATERIAL\n" +
                "Morador: %s | Residencia: %s\n" +
                "Material: %s\n" +
                "Creditos: %d", this.nome, this.residencia, tipo, pontos);
        }

    /**
     * Gera um boletim filtrando o engajamento por um tipo específico de orientação.
     * * @param tipo O tipo da orientação buscada.
     * @return Uma String formatada com as métricas do tipo de orientação solicitada.
     */
    public String boletimOrientacao(String tipo){
            int pontuacao = 0;
            int quantidade = 0;

            for(OrientacaoAmbiental oa : orientacoesMorador){
                if(oa.getTipo().equalsIgnoreCase(tipo)){
                    pontuacao += oa.calcularPontuacao();
                    quantidade++;
                }
            }
            return String.format(
                "BOLETIM ECOPONTOS - POR TIPO DE ORIENTACAO\n" +
                "Morador: %s | Residencia: %s\n" +
                "Tipo da Orientacao: %s\n" +
                "Quantidade orientacoes do tipo: %d\n" +
                "Quantidade de creditos gerados: %d", this.nome, this.residencia, tipo, quantidade, pontuacao);
        }
}