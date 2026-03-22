package facade;

/**
 * Representa um item informativo composto apenas por texto simples, 
 * concebido para ser anexado a uma Orientação Ambiental.
 */
public class TextoOrientacao extends ItemInformativo {
    private String texto;

    /**
     * Construtor para um item de texto de orientação.
     * * @param texto O conteúdo em texto da orientação.
     */
    public TextoOrientacao(String texto) {
        this.texto = texto;
    }

    /**
     * Calcula os pontos gerados por este texto.
     * A regra define a atribuição de 1 ponto por cada 20 caracteres inseridos,
     * com um limite máximo de 10 pontos por texto.
     * * @return A quantidade de pontos calculada para o texto.
     */
    @Override
    public int calcularPontos() {
        int pontos = texto.length() / 20;
        return Math.min(pontos, 10);
    }

    /**
     * Retorna uma representação em texto do item.
     * * @return Uma String formatada exibindo o conteúdo do texto.
     */
    @Override
    public String toString() {
        return "Texto: " + texto;
    }
}