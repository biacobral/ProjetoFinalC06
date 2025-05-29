package br.inatel.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.Random;

import static br.inatel.Model.Desejos.*;

public class Menu {
    private Scanner scanner;
    private Random random;
    //Indíces do pedido escolhido aleatoriamente pelo sistema
    private int indice1;
    private int indice2;
    //Por enquanto vou fazer um hashset, que condiz, já que a gente não quer repetição de opções
    //Os dois pedidos aleatórios que a gente vai disponibilizar por ano
    static Set<Integer> pedidosFeitos = new HashSet<Integer>();
    private String pedidoEscolhido1;
    private String pedidoEscolhido2;
    //Opção que o jogador vai escolher dentro do menu
    private int opcaoEscolhida;
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }



    private void sortearPedidos() {
        do {
            try {
                // Escolhe dois pedidos diferentes aleatoriamente
                indice1 = random.nextInt(11);

                // Garante que os dois pedidos sejam diferentes
                do {
                    indice2 = random.nextInt(11);
                } while (indice2 == indice1);
            }
            catch(Exception e){
                System.out.println("Erro ao sortear os pedidos! Tente novamente.");
            }
        }while(pedidosFeitos.contains(indice1) || pedidosFeitos.contains(indice2));
        pedidosFeitos.add(indice1);
        pedidosFeitos.add(indice2);

        pedidoEscolhido1 = listaDesejos[indice1];
        pedidoEscolhido2 = listaDesejos[indice2];

    }

    public String getPedidoEscolhido2() {
        return pedidoEscolhido2;
    }

    public String getPedidoEscolhido1() {
        return pedidoEscolhido1;
    }

    public void warning(){
        System.out.println();
        System.out.println("""
                Lhe será concedido um único pedido por ano. Desses, você poderá escolher entre 3 possíveis opções
                 sendo que as duas primeiras serão opções únicas e pré selecionadas pelo sistema e a terceira será um pedido de
                 sua própria escolha. Seu padrinho ou madrinha será quem decidirá se irá ou não conceder o pedido escolhido, sendo
                 ele um pedido pré selecionado ou um pedido feito por você. Mas lembre-se! As fadas possuem suas próprias regras e caso
                 você faça e lhe seja concedido um pedido proibido, o tribunal da magia virá atrás de você!""");
        System.out.println();
        System.out.println("TENHA CUIDADO COM O QUE VOCÊ IRÁ PEDIR!");
        System.out.println();
    }

    public void mostraMenu(){
        sortearPedidos();
        System.out.println("\n🌟 MENU DE DESEJOS MÁGICOS 🌟");
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║  1. " + getPedidoEscolhido1() +" ║");
        System.out.println("║  2. " + getPedidoEscolhido2() +" ║");
        System.out.println("║  3. ✍️  Fazer Pedido Personalizado   ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\n🪄 Digite sua escolha (1-4): ");
    }

    public void setOpcaoEscolhida(int opcaoEscolhida) {
        this.opcaoEscolhida = opcaoEscolhida;
        eventos();
    }

    public void eventos(){
        switch(opcaoEscolhida){
            case 1:
                switch (this.indice1){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (this.indice2){
                    case 0:
                        felicidade += 10;
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }
}