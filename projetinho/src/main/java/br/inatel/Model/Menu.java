package br.inatel.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.Random;

public class Menu {
    private Scanner scanner;
    private Random random;
    static Set<Integer> pedidosFeitos = new HashSet<Integer>();//Por enquanto vou fazer um hashset, que condiz, já que a gente não quer repetição de opções
    boolean diferente = false;

    // Lista de 12 pedidos possíveis
    private static final String[] PEDIDOS_DISPONIVEIS = {
            "Ter superpoderes incríveis",
            "Escola virar parque diversões",
            "Transformar Vicky em pessoa legal",
            "Conseguir notas máximas sempre",
            "Fazer vegetais desaparecerem",
            "Ter um robô gigante pessoal",
            "Voar como um super-herói",
            "Controlar o tempo e espaço",
            "Ter poderes de teletransporte",
            "Criar portais dimensionais",
            "Falar com todos os animais",
            "Nunca mais fazer lição de casa"
    };

    //Os dois pedidos aleatórios que a gente vai disponibilizar por ano
    private String pedidoEscolhido1;
    private String pedidoEscolhido2;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        try {
            sortearPedidos();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortearPedidos() {
        int indice1;
        int indice2;
        do {
            // Escolhe dois pedidos diferentes aleatoriamente
            indice1 = random.nextInt(PEDIDOS_DISPONIVEIS.length);

            // Garante que os dois pedidos sejam diferentes
            do {
                indice2 = random.nextInt(PEDIDOS_DISPONIVEIS.length);
            } while (indice2 == indice1);
            for(int j: pedidosFeitos) {
                if(j != indice2 && j != indice1) {
                    diferente = true;
                }
            }
        }while(!diferente);

        pedidosFeitos.add(indice1);
        pedidosFeitos.add(indice2);
        pedidoEscolhido1 = PEDIDOS_DISPONIVEIS[indice1];
        pedidoEscolhido2 = PEDIDOS_DISPONIVEIS[indice2];
        exibirMenu();
    }

    public void exibirMenu() {
        // Calcula o tamanho necessário baseado nos pedidos sorteados
        int maxTamanho = Math.max(pedidoEscolhido1.length(), pedidoEscolhido2.length());
        maxTamanho = Math.max(maxTamanho, 30); // Tamanho mínimo

        String separador = "╔" + "═".repeat(maxTamanho + 8) + "╗";
        String rodape = "╚" + "═".repeat(maxTamanho + 8) + "╝";

        System.out.println("\n🌟 MENU DE DESEJOS MÁGICOS 🌟");
        System.out.println(separador);
        System.out.printf("║  1. 🎲 %-" + (maxTamanho - 2) + "s  ║%n", pedidoEscolhido1);
        System.out.printf("║  2. 🎭 %-" + (maxTamanho - 2) + "s  ║%n", pedidoEscolhido2);
        System.out.printf("║  3. ✍️  %-" + (maxTamanho - 3) + "s  ║%n", "Fazer Pedido Personalizado");
        System.out.printf("║  4. 🚪 %-" + (maxTamanho - 2) + "s  ║%n", "Sair");
        System.out.println(rodape);
        System.out.print("\n🪄 Digite sua escolha (1-4): ");
    }

    public int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("\n✨ Pedido escolhido: " + pedidoEscolhido1);
                break;
            case 2:
                System.out.println("\n✨ Pedido escolhido: " + pedidoEscolhido2);
                break;
            case 3:
                // Pedido personalizado
                System.out.print("\n🪄 Digite seu pedido personalizado: ");
                String pedidoPersonalizado = scanner.nextLine();
                System.out.println("\n✨ Seu pedido: " + pedidoPersonalizado);
                break;
            case 4:
                System.out.println("\n👋 Até logo!");
                break;
            default:
                System.out.println("\n❌ Opção inválida! Tente novamente.");
                break;
        }
    }

    // Método para sortear novos pedidos (opcional)
    public void sortearNovosPedidos() {
        sortearPedidos();
    }

    public void fecharScanner() {
        scanner.close();
    }

    // Getters para os pedidos sorteados (caso precise acessar de fora)
    public String getPedidoEscolhido1() {
        return pedidoEscolhido1;
    }

    public String getPedidoEscolhido2() {
        return pedidoEscolhido2;
    }
}