package br.inatel.Model.Uteis;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável pelo menu principal do sistema dos Padrinhos Mágicos
 * Gerencia a interface de usuário e navegação entre funcionalidades
 */
public class MenuBD {
    // Constantes para melhor manutenibilidade
    private static final String SEPARADOR = "=".repeat(50);
    private static final String TITULO_PRINCIPAL = "🧚‍♀️ MENU PADRINHOS MÁGICOS 🧚‍♂️";
    private static final String MENSAGEM_OPCAO_INVALIDA = "⚠️ Opção inválida! Digite apenas números válidos.";
    private static final String MENSAGEM_PAUSAR = "\nPressione ENTER para continuar...";

    // Enums para melhor organização das opções
    private enum OpcaoMenuPrincipal {
        GERENCIAR_CRIANCAS(1, "👶 Gerenciar Crianças"),
        GERENCIAR_PADRINHOS(2, "🧚 Gerenciar Padrinhos"),
        GERENCIAR_ANTI_FADAS(3, "🦹 Gerenciar Anti-Fadas"),
        GERENCIAR_GENERAL_FADA(4, "⭐ Gerenciar General Fada"),
        GERENCIAR_VARINHAS(5, "🪄 Gerenciar Varinhas"),
        GERENCIAR_DESEJOS(6, "✨ Gerenciar Desejos"),
        GERENCIAR_RELACIONAMENTOS(7, "🔗 Relacionar Desejos e Crianças"),
        SAIR(8, "🚪 Sair");

        private final int codigo;
        private final String descricao;

        OpcaoMenuPrincipal(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoMenuPrincipal fromCodigo(int codigo) {
            for (OpcaoMenuPrincipal opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoCriancas {
        LISTAR(1, "📋 Listar todas as crianças"),
        BUSCAR(2, "🔍 Buscar criança por ID"),
        INSERIR(3, "➕ Inserir nova criança"),
        EDITAR(4, "✏️ Editar criança"),
        EXCLUIR(5, "❌ Excluir criança");

        private final int codigo;
        private final String descricao;

        OpcaoCriancas(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoCriancas fromCodigo(int codigo) {
            for (OpcaoCriancas opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoVarinhas {
        LISTAR_TODAS(1, "📋 Listar todas as varinhas"),
        BUSCAR_POR_ID(2, "🔍 Buscar varinha por ID Serial"),
        BUSCAR_POR_COR(3, "🎨 Buscar varinhas por cor"),
        BUSCAR_POR_STATUS(4, "⚡ Buscar varinhas por status"),
        VARINHAS_DISPONIVEIS(5, "🆓 Listar varinhas disponíveis"),
        VARINHAS_EM_USO(6, "🧚‍♀️ Listar varinhas em uso"),
        INVENTARIO_RESUMIDO(7, "📋 Inventário resumido"),
        ESTATISTICAS(8, "📊 Estatísticas do arsenal"),
        INSERIR(9, "➕ Inserir nova varinha"),
        EDITAR(10, "✏️ Editar varinha"),
        EXCLUIR(11, "❌ Excluir varinha");

        private final int codigo;
        private final String descricao;

        OpcaoVarinhas(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoVarinhas fromCodigo(int codigo) {
            for (OpcaoVarinhas opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private final Scanner scanner;
    private boolean executando;

    public MenuBD() {
        this.scanner = new Scanner(System.in);
        this.executando = true;
    }

    /**
     * Inicia o sistema e exibe o menu principal
     */
    public void iniciar() {
        exibirMensagemBoasVindas();

        while (executando) {
            try {
                exibirMenuPrincipal();
                int opcao = lerOpcaoInt();
                processarOpcaoMenuPrincipal(opcao);

                if (executando) {
                    pausar();
                }
            } catch (Exception e) {
                System.err.println("❌ Erro inesperado: " + e.getMessage());
                pausar();
            }
        }

        fecharRecursos();
    }

    /**
     * Exibe mensagem de boas-vindas
     */
    private void exibirMensagemBoasVindas() {
        System.out.println("🌟 Bem-vindo ao Sistema dos Padrinhos Mágicos! 🌟");
        System.out.println("Conectando ao banco de dados...\n");
    }

    /**
     * Processa a opção selecionada no menu principal
     */
    private void processarOpcaoMenuPrincipal(int opcao) {
        OpcaoMenuPrincipal opcaoEnum = OpcaoMenuPrincipal.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida! Escolha entre 1 e " + OpcaoMenuPrincipal.values().length + ".");
            return;
        }

        switch (opcaoEnum) {
            case GERENCIAR_CRIANCAS:
                gerenciarCriancas();
                break;
            case GERENCIAR_PADRINHOS:
                gerenciarPadrinhos();
                break;
            case GERENCIAR_ANTI_FADAS:
                gerenciarAntiFadas();
                break;
            case GERENCIAR_GENERAL_FADA:
                gerenciarGeneralFada();
                break;
            case GERENCIAR_VARINHAS:
                gerenciarVarinhas();
                break;
            case GERENCIAR_DESEJOS:
                gerenciarDesejos();
                break;
            case GERENCIAR_RELACIONAMENTOS:
                gerenciarRelacionamentos();
                break;
            case SAIR:
                encerrarSistema();
                break;
        }
    }

    /**
     * Exibe o menu principal do sistema
     */
    private void exibirMenuPrincipal() {
        System.out.println("\n" + SEPARADOR);
        System.out.println(TITULO_PRINCIPAL);
        System.out.println(SEPARADOR);

        for (OpcaoMenuPrincipal opcao : OpcaoMenuPrincipal.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
    }

    // ===== MÉTODOS DE GERENCIAMENTO =====

    /**
     * Gerencia operações relacionadas às crianças
     */
    private void gerenciarCriancas() {
        System.out.println("\n👶 === GERENCIAR CRIANÇAS ===");

        for (OpcaoCriancas opcao : OpcaoCriancas.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        OpcaoCriancas opcaoEnum = OpcaoCriancas.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em Crianças.");
            return;
        }

        switch (opcaoEnum) {
            case LISTAR:
                listarTodasCriancas();
                break;
            case BUSCAR:
                buscarCriancaPorId();
                break;
            case INSERIR:
                inserirCrianca();
                break;
            case EDITAR:
                editarCrianca();
                break;
            case EXCLUIR:
                excluirCrianca();
                break;
        }
    }

    private void gerenciarPadrinhos() {
        System.out.println("\n🧚 === GERENCIAR PADRINHOS ===");
        // TODO: Implementar submenu de padrinhos
        System.out.println("Funcionalidade em desenvolvimento...");
    }

    private void gerenciarAntiFadas() {
        System.out.println("\n🦹 === GERENCIAR ANTI-FADAS ===");
        // TODO: Implementar submenu de anti-fadas
        System.out.println("Funcionalidade em desenvolvimento...");
    }

    private void gerenciarGeneralFada() {
        System.out.println("\n⭐ === GERENCIAR GENERAL FADA ===");
        // TODO: Implementar submenu de general fada
        System.out.println("Funcionalidade em desenvolvimento...");
    }

    private void gerenciarVarinhas() {
        System.out.println("\n🪄 === GERENCIAR VARINHAS ===");

        for (OpcaoVarinhas opcao : OpcaoVarinhas.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        processarOpcaoVarinhas(opcao);
    }

    /**
     * Processa as opções do menu de varinhas
     */
    private void processarOpcaoVarinhas(int opcao) {
        OpcaoVarinhas opcaoEnum = OpcaoVarinhas.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em Varinhas.");
            return;
        }

        // Criar instância do DAO
        br.inatel.DAO.VarinhaDAO varinhaDAO = new br.inatel.DAO.VarinhaDAO();

        switch (opcaoEnum) {
            case LISTAR_TODAS:
                varinhaDAO.selectAllVarinhas();
                break;
            case BUSCAR_POR_ID:
                buscarVarinhaPorId(varinhaDAO);
                break;
            case BUSCAR_POR_COR:
                buscarVarinhasPorCor(varinhaDAO);
                break;
            case BUSCAR_POR_STATUS:
                buscarVarinhasPorStatus(varinhaDAO);
                break;
            case VARINHAS_DISPONIVEIS:
                varinhaDAO.selectVarinhasDisponiveis();
                break;
            case VARINHAS_EM_USO:
                varinhaDAO.selectVarinhasEmUso();
                break;
            case INVENTARIO_RESUMIDO:
                varinhaDAO.showInventarioResumo();
                break;
            case ESTATISTICAS:
                varinhaDAO.showEstatisticasArsenal();
                break;
            case INSERIR:
                inserirVarinha(varinhaDAO);
                break;
            case EDITAR:
                editarVarinha(varinhaDAO);
                break;
            case EXCLUIR:
                excluirVarinha(varinhaDAO);
                break;
        }
    }

    private void gerenciarDesejos() {
        System.out.println("\n✨ === GERENCIAR DESEJOS ===");
        // TODO: Implementar submenu de desejos
        System.out.println("Funcionalidade em desenvolvimento...");
    }

    private void gerenciarRelacionamentos() {
        System.out.println("\n🔗 === RELACIONAR DESEJOS E CRIANÇAS ===");
        // TODO: Implementar submenu de relacionamentos
        System.out.println("Funcionalidade em desenvolvimento...");
    }

    // ===== OPERAÇÕES ESPECÍFICAS DE VARINHAS =====

    private void buscarVarinhaPorId(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.print("Digite o ID Serial da varinha: ");
        int id = lerOpcaoInt();
        varinhaDAO.selectVarinhaById(id);
    }

    private void buscarVarinhasPorCor(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.print("Digite a cor da varinha: ");
        String cor = lerTextoValidado("Cor não pode ser vazia!");
        varinhaDAO.selectVarinhasByCor(cor);
    }

    private void buscarVarinhasPorStatus(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.println("Status disponíveis: Disponível, Em Uso, Manutenção");
        System.out.print("Digite o status da varinha: ");
        String status = lerTextoValidado("Status não pode ser vazio!");
        varinhaDAO.selectVarinhasByStatus(status);
    }

    private void inserirVarinha(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.println("\n➕ === INSERIR NOVA VARINHA ===");
        System.out.print("Digite a cor da varinha: ");
        String cor = lerTextoValidado("Cor não pode ser vazia!");
        System.out.print("Digite o status da varinha (Disponível/Em Uso/Manutenção): ");
        String status = lerTextoValidado("Status não pode ser vazio!");

        // Assumindo que existe uma classe Varinha no pacote br.inatel.Model.Personagens
        try {
            br.inatel.Model.Personagens.Varinha varinha =
                    new br.inatel.Model.Personagens.Varinha(cor, status);

            if (varinhaDAO.insertVarinha(varinha)) {
                System.out.println("✅ Varinha inserida com sucesso!");
            } else {
                System.out.println("❌ Erro ao inserir varinha.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao criar varinha: " + e.getMessage());
        }
    }

    private void editarVarinha(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.println("\n✏️ === EDITAR VARINHA ===");
        System.out.print("Digite o ID Serial da varinha a ser editada: ");
        int id = lerOpcaoInt();

        // Primeiro, buscar e mostrar a varinha atual
        br.inatel.Model.Personagens.Varinha varinhaAtual = varinhaDAO.selectVarinhaById(id);

        if (varinhaAtual != null) {
            System.out.print("Nova cor da varinha: ");
            String novaCor = lerTextoValidado("Cor não pode ser vazia!");
            System.out.print("Novo status da varinha (Disponível/Em Uso/Manutenção): ");
            String novoStatus = lerTextoValidado("Status não pode ser vazio!");

            try {
                br.inatel.Model.Personagens.Varinha varinhaEditada =
                        new br.inatel.Model.Personagens.Varinha(novaCor, novoStatus);

                if (varinhaDAO.updateVarinha(id, varinhaEditada)) {
                    System.out.println("✅ Varinha editada com sucesso!");
                } else {
                    System.out.println("❌ Erro ao editar varinha.");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro ao criar varinha editada: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Varinha não encontrada com o ID fornecido.");
        }
    }

    private void excluirVarinha(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.println("\n❌ === EXCLUIR VARINHA ===");
        System.out.print("Digite o ID Serial da varinha a ser excluída: ");
        int id = lerOpcaoInt();

        // Mostrar a varinha antes de excluir
        br.inatel.Model.Personagens.Varinha varinha = varinhaDAO.selectVarinhaById(id);

        if (varinha != null) {
            System.out.print("Tem certeza que deseja excluir esta varinha? (S/N): ");
            String confirmacao = lerTexto().toUpperCase();

            if (confirmacao.equals("S") || confirmacao.equals("SIM")) {
                if (varinhaDAO.deleteVarinha(id)) {
                    System.out.println("✅ Varinha excluída com sucesso!");
                } else {
                    System.out.println("❌ Erro ao excluir varinha.");
                }
            } else {
                System.out.println("ℹ️ Operação cancelada.");
            }
        } else {
            System.out.println("❌ Varinha não encontrada com o ID fornecido.");
        }
    }

    // ===== OPERAÇÕES ESPECÍFICAS DE CRIANÇAS =====

    private void listarTodasCriancas() {
        System.out.println("\n📋 === LISTA DE CRIANÇAS ===");
        // TODO: Implementar lógica de listagem
        System.out.println("Implementar: Listar todas as crianças do banco de dados");
    }

    private void buscarCriancaPorId() {
        System.out.println("\n🔍 === BUSCAR CRIANÇA POR ID ===");
        System.out.print("Digite o ID da criança: ");
        int id = lerOpcaoInt();
        // TODO: Implementar busca por ID
        System.out.println("Implementar: Buscar criança com ID: " + id);
    }

    private void inserirCrianca() {
        System.out.println("\n➕ === INSERIR NOVA CRIANÇA ===");
        // TODO: Implementar inserção
        System.out.println("Implementar: Formulário para inserir nova criança");
    }

    private void editarCrianca() {
        System.out.println("\n✏️ === EDITAR CRIANÇA ===");
        // TODO: Implementar edição
        System.out.println("Implementar: Formulário para editar criança existente");
    }

    private void excluirCrianca() {
        System.out.println("\n❌ === EXCLUIR CRIANÇA ===");
        System.out.print("Digite o ID da criança a ser excluída: ");
        int id = lerOpcaoInt();
        // TODO: Implementar exclusão com confirmação
        System.out.println("Implementar: Excluir criança com ID: " + id);
    }

    // ===== MÉTODOS UTILITÁRIOS =====

    /**
     * Lê uma opção inteira do usuário com validação
     *
     * @return número inteiro válido
     */
    private int lerOpcaoInt() {
        while (true) {
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // limpa buffer
                return opcao;
            } catch (InputMismatchException e) {
                System.out.print(MENSAGEM_OPCAO_INVALIDA + " Digite novamente: ");
                scanner.nextLine(); // limpa buffer inválido
            }
        }
    }

    /**
     * Lê uma string do usuário
     *
     * @return string lida, sem espaços extras
     */
    private String lerTexto() {
        return scanner.nextLine().trim();
    }

    /**
     * Lê uma string do usuário com validação de entrada não vazia
     *
     * @param mensagemErro mensagem a ser exibida se a entrada for vazia
     * @return string válida e não vazia
     */
    private String lerTextoValidado(String mensagemErro) {
        String texto;
        do {
            texto = lerTexto();
            if (texto.isEmpty()) {
                System.out.print("⚠️ " + mensagemErro + " Tente novamente: ");
            }
        } while (texto.isEmpty());
        return texto;
    }

    /**
     * Pausa a execução até o usuário pressionar ENTER
     */
    private void pausar() {
        System.out.println(MENSAGEM_PAUSAR);
        scanner.nextLine();
    }

    /**
     * Encerra o sistema de forma controlada
     */
    private void encerrarSistema() {
        System.out.println("✨ Encerrando o sistema dos Padrinhos Mágicos...");
        System.out.println("Até a próxima aventura mágica! 🪄");
        executando = false;
    }

    /**
     * Fecha recursos utilizados pela classe
     */
    private void fecharRecursos() {
        if (scanner != null) {
            scanner.close();
        }
    }
}