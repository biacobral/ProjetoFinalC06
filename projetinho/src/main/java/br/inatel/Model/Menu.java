package br.inatel.Model;

import java.util.*;

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
                indice1 = random.nextInt(12);

                // Garante que os dois pedidos sejam diferentes
                do {
                    indice2 = random.nextInt(12);
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
                ==================================================================== ATENÇÃO ====================================================================
                
                Você é uma criança triste, por isso lhe foi concedido um padrinho. Cada pedido que você faz te gera felicidade,
                 caso a sua felicidade chegue ao ápice, seu padrinho terá completado sua missão, irá embora para sempre e você se esquecerá dele...
                
                Lhe será concedido um único pedido por ano. Desses, você poderá escolher entre 3 possíveis opções
                 sendo que as duas primeiras serão opções únicas e pré selecionadas pelo sistema e a terceira será um pedido de
                 sua própria escolha. Seu padrinho ou madrinha será quem decidirá se irá ou não conceder o pedido escolhido.
                 Mas lembre-se! Os padrinhos possuem suas próprias regras e caso você faça e lhe seja concedido um pedido
                 proibido, o Tribunal da Magia virá atrás de você!""");
        System.out.println();
        System.out.println("================================================= TENHA CUIDADO COM O QUE VOCÊ IRÁ PEDIR! ==================================================");
        System.out.println();
    }

    public void mostraMenu(){
        sortearPedidos();
        System.out.println("\n====== 🌟 MENU DE DESEJOS MÁGICOS 🌟 ======");
        System.out.println("1. " + getPedidoEscolhido1());
        System.out.println("2. " + getPedidoEscolhido2());
        System.out.println("3. ✍️  Fazer Pedido Personalizado");
        System.out.println("============================================");
        System.out.print("\n🪄 Digite sua escolha (1-3): ");
    }

    public void setOpcaoEscolhida(int opcaoEscolhida) {
        this.opcaoEscolhida = opcaoEscolhida;
        eventos();
    }

    public int getOpcaoEscolhida() {
        return opcaoEscolhida;
    }

    public void eventos(){
        boolean realizar = random.nextBoolean();
        switch(opcaoEscolhida) {
            case 1:
                switch (this.indice1) {
                    case 0: // lanche
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 20;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 1: // herói
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 5;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 2: // férias
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 20;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 3: // voar
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 15;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 4: // castelo
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 5;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 5: // inteligente
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 15;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 6: // padrinho
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 50;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 7: // robô
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 5;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 8: // entender meninas
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 10;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 9: // super poder
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 15;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 10: // cachorro falante
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 7;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 11: // paixão
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 10;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 12: // famoso
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 15;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 13: // somente eu padrinhos
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 50;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                    default:
                        System.out.println("Opção inválida!Tente novamente...");
                        break;
                }
                break;
            case 2:
                switch (this.indice2) {
                    case 0: // lanche
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 20;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 1: // herói
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 5;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 2: // férias
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 20;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 3: // voar
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 15;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 4: // castelo
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 5;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 5: // inteligente
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 15;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 6: // padrinho
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 50;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 7: // robô
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 5;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 8: // entender meninas
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 10;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 9: // super poder
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 15;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 10: // cachorro falante
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 7;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 11: // paixão
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 10;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 12: // famoso
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 15;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                        break;
                    case 13: // somente eu padrinhos
                        if (realizar) {
                            System.out.println("Pedido concedido!");
                            felicidade += 50;
                        } else System.out.println("Pedido não concedido pelo padrinho!");
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }
                break;
            case 3: // escolhas pessoais

                break;
            default:
                while (true) {
                    System.out.println("Opção inválida! Tente novamente.");
                    try {
                        int novaOpcao = scanner.nextInt();
                        if (novaOpcao >= 1 && novaOpcao <= 3) {
                            setOpcaoEscolhida(novaOpcao);
                            break;
                        }
                    } catch (InputMismatchException e) {
                        scanner.next(); // limpar buffer
                    }
                }
                break;
        }
        }
    }