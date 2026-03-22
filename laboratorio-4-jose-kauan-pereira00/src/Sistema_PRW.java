package src;

import java.util.*;

/**
 * @author José Kauan Pereira dos Reis - 20250022120
 * Classe principal do sistema de gestão de restaurantes e clientes.
 * Responsável por gerenciar cadastros, visitas, votações e relatórios.
 */
public class Sistema_PRW {
    /**
     * Um HashMap para armazenar os clientes antravés do email
     * Um HashMap para armazenar os Restaurantes através do nome
     * */
    private Map<String, Cliente> clientes = new HashMap<>();
    private Map<String, Restaurante> restaurantes = new HashMap<>();

    /**
     * Adiciona um novo restaurante ao sistema.
     * O sistema possui um limite máximo de 50 restaurantes.
     *
     * @param nome  O nome do restaurante (deve ser único).
     * @param prato O prato principal servido pelo restaurante.
     * @return true se o restaurante foi adicionado com sucesso; 
     * false se o limite foi atingido ou se o nome já existe.
     */
    public boolean adicionarRestaurante(String nome, String prato) {
        if (restaurantes.size() >= 50 || restaurantes.containsKey(nome)) return false;
        restaurantes.put(nome, new Restaurante(nome, prato));
        return true;
    }

    /**
     * Adiciona um novo cliente ao sistema.
     * O sistema possui um limite máximo de 100 clientes.
     *
     * @param nome  O nome do cliente.
     * @param email O email do cliente (usado como identificador único).
     * @return true se o cliente foi adicionado com sucesso; 
     * false se o limite foi atingido ou se o email já existe.
     */
    public boolean adicionarCliente(String nome, String email) {
        if (clientes.size() >= 100 || clientes.containsKey(email)) return false;
        clientes.put(email, new Cliente(nome, email));
        return true;
    }

    /**
     * Registra uma visita de um cliente a um restaurante sem comentário.
     *
     * @param email    O email do cliente que está visitando.
     * @param nomeRest O nome do restaurante visitado.
     * @throws IllegalArgumentException se o cliente ou restaurante não existirem.
     */
    public void visitarRestaurante(String email, String nomeRest) { 
        visitarRestaurante(email, nomeRest, ""); 
    }

    /**
     * Registra uma visita de um cliente a um restaurante com um comentário.
     * Este método também atualiza o gasto total do cliente.
     *
     * @param email      O email do cliente que está visitando.
     * @param nomeRest   O nome do restaurante visitado.
     * @param comentario O comentário sobre a visita.
     * @throws IllegalArgumentException se o cliente ou restaurante não existirem.
     */
    public void visitarRestaurante(String email, String nomeRest, String comentario) {
        Cliente c = getCliente(email);
        Restaurante r = getRestaurante(nomeRest);
        r.adicionaVisita(c, comentario);
    }

    /**
     * Registra o voto de um cliente no seu prato preferido.
     * O voto só é computado se o cliente já tiver visitado o restaurante anteriormente.
     * Se confirmado, o prato do restaurante se torna o favorito do cliente.
     *
     * @param email    O email do cliente votante.
     * @param nomeRest O nome do restaurante em que se deseja votar.
     * @return Uma mensagem indicando "Voto registrado com sucesso!" ou "Voto não registrado".
     */
    public String votarPratoPreferido(String email, String nomeRest) {
        Cliente c = getCliente(email);
        Restaurante r = getRestaurante(nomeRest);
        if (r.jaFoiVisitadoPor(email)) {
            r.receberVoto();
            c.setPratoPreferido(r.getPrato());
            return "Voto registrado com sucesso!";
        }
        return "Voto não registrado";
    }

    /**
     * Retorna o total gasto pelo cliente em todas as visitas.
     *
     * @param email O email do cliente.
     * @return O valor total gasto (double).
     * @throws IllegalArgumentException se o cliente não existir.
     */
    public double obtemGastoCliente(String email) { 
        return getCliente(email).getGastoTotal(); 
    }

    /**
     * Gera uma lista formatada dos clientes que visitaram determinado restaurante.
     * Se o prato do restaurante for o favorito do cliente, adiciona uma observação especial.
     *
     * @param nomeRest O nome do restaurante.
     * @return Um array de Strings contendo os nomes dos clientes e, se aplicável, a tag de prato preferido.
     */
    public String[] listarClientesRestaurante(String nomeRest) {
        Restaurante r = getRestaurante(nomeRest);
        List<Visita> visitas = r.getVisitas();        
        Set<Cliente> clientesUnicos = new LinkedHashSet<>();
        for (Visita v : visitas) {
            clientesUnicos.add(v.getCliente());
        }

        String[] resultado = new String[clientesUnicos.size()];
        int i = 0;
        for (Cliente c : clientesUnicos) {
            String sufixo = "";
            if (r.getPrato().equals(c.getPratoPreferido())) {
                sufixo = ", Prato preferido da casa";
            }
            resultado[i++] = "Cliente: " + c.getNome() + sufixo;
        }
        return resultado;
    }

    /**
     * Lista todas as visitas registradas no livro de visitas do restaurante.
     *
     * @param nomeRest O nome do restaurante.
     * @return Um array de Strings com as representações textuais de todas as visitas.
     */
    public String[] listarLivroVisitas(String nomeRest) {
        Restaurante r = getRestaurante(nomeRest);
        List<Visita> listaDeVisitas = r.getVisitas();
        String[] resultado = new String[listaDeVisitas.size()];
        
        for (int i = 0; i < listaDeVisitas.size(); i++) {
            resultado[i] = listaDeVisitas.get(i).toString();
        }
        return resultado;
    }

    /**
     * Lista as visitas mais recentes do restaurante, limitadas por um número N.
     *
     * @param nomeRest O nome do restaurante.
     * @param n        O número máximo de visitas recentes a retornar.
     * @return Um array de Strings com as visitas mais recentes.
     */
    public String[] listarLivroVisitas(String nomeRest, int n) {
        String[] todas = listarLivroVisitas(nomeRest);
        int quantidade = Math.min(todas.length, n);
        String[] recentes = new String[quantidade];
        
        for (int i = 0; i < quantidade; i++) {
            recentes[i] = todas[todas.length - 1 - i];
        }
        return recentes;
    }

    /**
     * Identifica o restaurante com o maior número de votos.
     *
     * @return Uma String formatada com o prato e o nome do restaurante vencedor.
     * Retorna string vazia se não houver vencedor.
     */
    public String melhorPrato() {
        Restaurante vencedor = null;
        int maxVotos = -1; 

        for (Restaurante r : restaurantes.values()) {
            if (r.getVotos() > maxVotos) {
                maxVotos = r.getVotos();
                vencedor = r;
            }
        }

        if (vencedor == null) return "";
        return "Melhor prato: " + vencedor.getPrato() + " Restaurante: " + vencedor.getNome();
    }

    /*
    *Retorna o cliente com base em seu email cadastrado
    */
    private Cliente getCliente(String email) {
        if (!clientes.containsKey(email)) throw new IllegalArgumentException("Cliente Inexistente.");
        return clientes.get(email);
    }

    /*
    *Retorna o restaurante com base em seu nome cadastrado
    */
    private Restaurante getRestaurante(String nome) {
        if (!restaurantes.containsKey(nome)) throw new IllegalArgumentException("Restaurante Inexistente.");
        return restaurantes.get(nome);
    }

    /**
     * Gera o Relatório Geral do evento.
     * @return Uma string formatada com dados de participação e médias.
     */
    public String gerarRelatorioGeral() {
        int totalClientes = clientes.size();
        int totalRestaurantes = restaurantes.size();
        int totalParticipantes = totalClientes + totalRestaurantes;
        int somaVisitantesUnicos = 0;
        int somaVisitasTotais = 0;
        Restaurante maisVisitado = null;
        int maxVisitas = -1;

        for (Restaurante r : restaurantes.values()) {
            int qtdUnicos = r.getEmailsVisitantes().size();
            int qtdTotais = r.getVisitas().size();

            somaVisitantesUnicos += qtdUnicos;
            somaVisitasTotais += qtdTotais;

            if (qtdTotais > maxVisitas) {
                maxVisitas = qtdTotais;
                maisVisitado = r;
            }
        }

        double mediaVisitantes = totalRestaurantes == 0 ? 0 : (double) somaVisitantesUnicos / totalRestaurantes;
        double mediaVisitas = totalRestaurantes == 0 ? 0 : (double) somaVisitasTotais / totalRestaurantes;

        return "=== RELATÓRIO GERAL ===\n" +
               "Quantidade de participantes: " + totalParticipantes + " (Clientes: " + totalClientes + ", Restaurantes: " + totalRestaurantes + ")\n" +
               "Média de visitantes únicos por restaurante: " + String.format("%.2f", mediaVisitantes) + "\n" +
               "Média de visitas por restaurante: " + String.format("%.2f", mediaVisitas) + "\n" +
               "Restaurante mais visitado: " + (maisVisitado != null ? maisVisitado.getNome() : "Nenhum");
    }

    /**
     * Gera o Relatório Financeiro do evento.
     * @return Uma string formatada com a receita total e médias financeiras.
     */
    public String gerarRelatorioFinanceiro() {
        double receitaTotal = 0;
        for (Cliente c : clientes.values()) {
            receitaTotal += c.getGastoTotal();
        }

        double mediaReceitaRest = restaurantes.isEmpty() ? 0 : receitaTotal / restaurantes.size();
        double mediaGastoCli = clientes.isEmpty() ? 0 : receitaTotal / clientes.size();

        return "=== RELATÓRIO FINANCEIRO ===\n" +
               "Receita total do evento: R$ " + String.format("%.2f", receitaTotal) + "\n" +
               "Média de receita por restaurante: R$ " + String.format("%.2f", mediaReceitaRest) + "\n" +
               "Média de gastos por cliente: R$ " + String.format("%.2f", mediaGastoCli);
    }

    /**
     * Gera o Relatório de Engajamento.
     * @return Um array de Strings contendo o mapa de engajamento por cliente e restaurante.
     */
    public String[] gerarRelatorioEngajamento() {
        List<String> engajamentoList = new ArrayList<>();

        for (Restaurante r : restaurantes.values()) {
            Map<String, Integer> contadorVisitasPorCliente = new HashMap<>();

            for (Visita v : r.getVisitas()) {
                String nomeCliente = v.getCliente().getNome();
                int contagemAtual = contadorVisitasPorCliente.getOrDefault(nomeCliente, 0);
                contadorVisitasPorCliente.put(nomeCliente, contagemAtual + 1);
            }

            for (Map.Entry<String, Integer> entry : contadorVisitasPorCliente.entrySet()) {
                String linha = "Cliente: " + entry.getKey() + 
                               " | Restaurante: " + r.getNome() +
                               " | Visitas: " + entry.getValue();
                engajamentoList.add(linha);
            }
        }

        return engajamentoList.toArray(new String[0]);
    }
}