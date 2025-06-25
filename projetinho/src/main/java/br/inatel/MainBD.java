package br.inatel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainBD {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🌟 Bem-vindo ao Sistema dos Padrinhos Mágicos! 🌟");
        System.out.println("Conectando ao banco de dados...\n");

        int opcao = 0;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcaoInt();

            switch (opcao) {
                case 1:
                    gerenciarCriancas();
                    break;
                case 2:
                    gerenciarPadrinhos();
                    break;
                case 3:
                    gerenciarAntiFadas();
                    break;
                case 4:
                    gerenciarGeneralFada();
                    break;
                case 5:
                    gerenciarVarinhas();
                    break;
                case 6:
                    gerenciarDesejos();
                    break;
                case 7:
                    gerenciarRelacionamentos();
                    break;
                case 8:
                    System.out.println("✨ Encerrando o sistema dos Padrinhos Mágicos...");
                    System.out.println("Até a próxima aventura mágica! 🪄");
                    break;
                default:
                    System.out.println("⚠️ Opção inválida! Escolha entre 1 e 8.");
            }
            if (opcao != 8) pausar();
        } while (opcao != 8);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("🧚‍♀️ MENU PADRINHOS MÁGICOS 🧚‍♂️");
        System.out.println("=".repeat(40));
        System.out.println("1. 👶 Gerenciar Crianças");
        System.out.println("2. 🧚 Gerenciar Padrinhos");
        System.out.println("3. 🦹 Gerenciar Anti-Fadas");
        System.out.println("4. ⭐ Gerenciar General Fada");
        System.out.println("5. 🪄 Gerenciar Varinhas");
        System.out.println("6. ✨ Gerenciar Desejos");
        System.out.println("7. 🔗 Relacionar Desejos e Crianças");
        System.out.println("8. 🚪 Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void gerenciarCriancas() {
        System.out.println("\n👶 === GERENCIAR CRIANÇAS ===");
        System.out.println("1. 📋 Listar todas as crianças");
        System.out.println("2. 🔍 Buscar criança por ID");
        System.out.println("3. ➕ Inserir nova criança");
        System.out.println("4. ✏️ Editar criança");
        System.out.println("5. ❌ Excluir criança");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();
        switch (opcao) {
            case 1: listarTodasCriancas(); break;
            case 2: buscarCriancaPorId(); break;
            case 3: inserirCrianca(); break;
            case 4: editarCrianca(); break;
            case 5: excluirCrianca(); break;
            default: System.out.println("⚠️ Opção inválida em Crianças.");
        }
    }

    private static void listarTodasCriancas() {
        System.out.println("\n📋 === LISTA DE CRIANÇAS ===");
        // SELECT
    }

    private static void buscarCriancaPorId() {
        System.out.print("Digite o ID da criança: ");
        int id = lerOpcaoInt();
        System.out.println("\n🔍 Buscando criança com ID: " + id);
        // SELECT
    }

    private static void inserirCrianca() {
        System.out.println("\n➕ === INSERIR NOVA CRIANÇA ===");
        System.out.print("Nome: ");
        String nome = lerTexto();
        System.out.print("Idade: ");
        int idade = lerOpcaoInt();
        System.out.print("Desejos realizados: ");
        int desejosRealizados = lerOpcaoInt();

        System.out.println("✅ Dados coletados para nova criança:");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Desejos realizados: " + desejosRealizados);
        // INSERT
    }

    private static void editarCrianca() {
        System.out.print("Digite o ID da criança para editar: ");
        int id = lerOpcaoInt();
        System.out.println("ID da criança a editar: " + id);
        // UPDATE
    }

    private static void excluirCrianca() {
        System.out.print("Digite o ID da criança para excluir: ");
        int id = lerOpcaoInt();
        System.out.print("Tem certeza que deseja excluir a criança com ID " + id + "? (S/N): ");
        String confirmacao = lerTexto().toUpperCase();

        if (confirmacao.equals("S") || confirmacao.equals("SIM")) {
            System.out.println("ID da criança a excluir: " + id);
            // DELETE
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private static void gerenciarPadrinhos() {
        System.out.println("\n🧚 === GERENCIAR PADRINHOS ===");
        System.out.println("1. 📋 Listar todos os padrinhos");
        System.out.println("2. 🔍 Buscar padrinho por ID");
        System.out.println("3. ➕ Inserir novo padrinho");
        System.out.println("4. ✏️ Editar padrinho");
        System.out.println("5. ❌ Excluir padrinho");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();
        switch (opcao) {
            case 1:
                System.out.println("📋 Listando todos os padrinhos...");
                // SELECT
                break;
            case 2:
                System.out.print("Digite o ID do padrinho: ");
                int id = lerOpcaoInt();
                System.out.println("🔍 Buscando padrinho com ID: " + id);
                // SELECT
                break;
            case 3:
                inserirPadrinho();
                break;
            case 4:
                System.out.print("Digite o ID do padrinho para editar: ");
                int idEdit = lerOpcaoInt();
                System.out.println("✏️ Editando padrinho com ID: " + idEdit);
                // UPDATE
                break;
            case 5:
                System.out.print("Digite o ID do padrinho para excluir: ");
                int idDel = lerOpcaoInt();
                System.out.println("❌ Excluindo padrinho com ID: " + idDel);
                // DELETE
                break;
            default:
                System.out.println("⚠️ Opção inválida em Padrinhos.");
        }
    }

    private static void inserirPadrinho() {
        System.out.println("\n➕ === INSERIR NOVO PADRINHO ===");

        // INSERT -> Padrinhos padrinho = new Padrinho?
    }

    private static void gerenciarRelacionamentos() {
        System.out.println("\n🔗 === RELACIONAR DESEJOS E CRIANÇAS ===");
        System.out.println("1. 📋 Listar todos os relacionamentos");
        System.out.println("2. 🔍 Buscar relacionamentos por criança");
        System.out.println("3. ➕ Criar novo relacionamento");
        System.out.println("4. ❌ Remover relacionamento");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();
        switch (opcao) {
            case 1:
                System.out.println("📋 Listando todos os relacionamentos...");
                // SELECT
                break;
            case 2:
                System.out.print("Digite o ID da criança: ");
                int idCrianca = lerOpcaoInt();
                System.out.println("🔍 Buscando relacionamentos da criança ID: " + idCrianca);
                // SELECT
                break;
            case 3:
                criarRelacionamento();
                break;
            case 4:
                removerRelacionamento();
                break;
            default:
                System.out.println("⚠️ Opção inválida em Relacionamentos.");
        }
    }

    private static void criarRelacionamento() {
        // INSERT
    }

    private static void removerRelacionamento() {

        // DELETE relacionamento
    }

    private static void gerenciarAntiFadas() {
        System.out.println("\n🦹 === GERENCIAR ANTI-FADAS ===");
        System.out.println("1. 📋 Listar todas as anti-fadas");
        System.out.println("2. ➕ Inserir nova anti-fada");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();
        switch (opcao) {
            case 1:
                System.out.println("📋 Listando todas as anti-fadas...");
                // SELECT anti-fadas
                break;
            case 2:
                System.out.print("Digite o nome da anti-fada: ");
                String nome = lerTexto();
                System.out.println("➕ Inserindo anti-fada: " + nome);
                // INSERT
                break;
            default:
                System.out.println("⚠️ Opção inválida em Anti-Fadas.");
        }
    }

    private static void gerenciarGeneralFada() {
        System.out.println("\n⭐ === GERENCIAR GENERAL FADA ===");
        System.out.println("1. Visualizar general");
        System.out.println("2. Editar general");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();
        switch (opcao) {
            case 1:
                System.out.println("Visualizando general...");
                // SELECT
                break;
            case 2:
                System.out.print("Digite o novo nome do general: ");
                String nome = lerTexto();
                System.out.println("Editando general para: " + nome);
                // UPDATE
                break;
            default:
                System.out.println("⚠️ Opção inválida em General Fada.");
        }
    }

    private static void gerenciarVarinhas() {
        System.out.println("\n🪄 === GERENCIAR VARINHAS ===");
        System.out.println("1. 📋 Listar varinhas");
        System.out.println("2. ➕ Inserir nova varinha");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();
        switch (opcao) {
            case 1:
                System.out.println("Listando varinhas...");
                // SELECT
                break;
            case 2:
                System.out.print("Digite o modelo da varinha: ");
                String modelo = lerTexto();
                System.out.println("Inserindo varinha: " + modelo);
                // INSERT
                break;
            default:
                System.out.println("⚠️ Opção inválida em Varinhas.");
        }
    }

    private static void gerenciarDesejos() {
        System.out.println("\n✨ === GERENCIAR DESEJOS ===");
        System.out.println("1. 📋 Listar desejos");
        System.out.println("2. ➕ Inserir novo desejo");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();
        switch (opcao) {
            case 1:
                System.out.println("Listando desejos...");
                // SELECT
                break;
            case 2:
                System.out.print("Digite a descrição do desejo: ");
                String desejo = lerTexto();
                System.out.println("Inserindo desejo: " + desejo);
                // INSERT
                break;
            default:
                System.out.println("⚠️ Opção inválida em Desejos.");
        }
    }

    private static int lerOpcaoInt() {
        while (true) {
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // limpa buffer
                return opcao;
            } catch (InputMismatchException e) {
                System.out.print("⚠️ Digite apenas números: ");
                scanner.nextLine();
            }
        }
    }

    private static String lerTexto() {
        return scanner.nextLine().trim();
    }

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
