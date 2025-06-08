package br.inatel.Model.Uteis;

import br.inatel.Model.Personagens.GeneralFada;
import br.inatel.Model.Personagens.Padrinhos;

import java.util.*;

import static br.inatel.Model.Personagens.Desejos.*;
import static br.inatel.Model.Uteis.podeIssoArnaldo.*;
import static br.inatel.Model.Uteis.Util.esperaAi;
import static br.inatel.Model.Uteis.tribunalDaMagia.Julgamento;
import static br.inatel.Model.Personagens.Crianca.felicidade;

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

    // Metodo auxiliar para ler entrada de forma segura
    public int lerOpcaoSegura(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = this.scanner.nextLine().trim();

                if (entrada.isEmpty()) {
                    System.out.println("💫 As estrelas sussurram: entrada vazia! Tente novamente... ✨");
                    continue;
                }

                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("⚡ Os ventos da magia rejeitam sua escolha! Digite apenas números mágicos! 🌟");
            }
        }
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
            } catch (Exception e) {
                System.out.println("🌪️ Um tornado de magia perturbou o sorteio! Os fados conspiram... Tente novamente! ⚡");
            }
        } while (pedidosFeitos.contains(indice1) || pedidosFeitos.contains(indice2));
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

    public void warning() {
        System.out.println();
        System.out.println("🌟════════════════════════════════════════════════════════════════════════════════════════ ATENÇÃO ════════════════════════════════════════════════════════════════════════════════════════🌟");
        System.out.println();
        System.out.println("💔 Você é uma alma perdida em um mundo de tristeza, por isso os céus lhe concederam um padrinho mágico...");
        System.out.println("✨ Cada desejo que brota de seu coração é como uma centelha de esperança que ilumina sua existência sombria...");
        System.out.println("🌈 Mas cuidado, jovem sonhador! Se sua felicidade alcançar o ápice da plenitude...");
        System.out.println("💫 Seu padrinho mágico terá cumprido sua missão divina e desaparecerá para sempre nas brumas do esquecimento...");
        System.out.println();
        System.out.println("⏳ O destino lhe concede apenas UM desejo a cada ciclo solar que se completa...");
        System.out.println("🎭 Desses, três caminhos místicos se abrem diante de você:");
        System.out.println("🌟 Dois caminhos únicos, escolhidos pelos ventos do acaso e pelos sussurros das estrelas...");
        System.out.println("✍️ E um terceiro caminho, onde sua alma pode expressar seus mais profundos anseios...");
        System.out.println("🧙‍♂️ Mas lembre-se! Seu padrinho mágico possui o poder supremo de decisão...");
        System.out.println("⚖️ E o General da Magia vigia cada desejo proibido que ecoa pelos reinos místicos!");
        System.out.println();
        System.out.println("🔥═══════════════════════════════════════════════════════════════ TENHA CUIDADO COM O QUE VOCÊ IRÁ PEDIR! ═══════════════════════════════════════════════════════════════🔥");
        System.out.println();
    }

    public void mostraMenu() {
        sortearPedidos();
        System.out.println("\n🌟═══════════════════════════════════════🌟");
        System.out.println("✨        MENU DE DESEJOS MÁGICOS        ✨");
        System.out.println("🌟═══════════════════════════════════════🌟");
        System.out.println("1. 🎭 " + getPedidoEscolhido1());
        System.out.println("2. 🎪 " + getPedidoEscolhido2());
        System.out.println("3. ✍️ Fazer Pedido Personalizado");
        System.out.println("🌟═══════════════════════════════════════🌟");
    }

    public void setOpcaoEscolhida(int opcaoEscolhida) {
        this.opcaoEscolhida = opcaoEscolhida;
    }

    public int getOpcaoEscolhida() {
        return opcaoEscolhida;
    }

    // Metodo para pedido concedido com drama
    private void pedidoConcedidoDramatico(int felicidadeGanha) {
        System.out.println("\n✨═══════════════════════════════════════✨");
        System.out.println("🎆         MILAGRE ACONTECEU!            🎆");
        System.out.println("✨═══════════════════════════════════════✨");
        esperaAi(1500);
        System.out.println("🌟 As estrelas se alinham em perfeita harmonia!");
        esperaAi(1000);
        System.out.println("💫 Os ventos da magia sussurram seu nome!");
        esperaAi(1000);
        System.out.println("🎭 Seu desejo ecoa pelos reinos celestiais!");
        esperaAi(1500);
        System.out.println("💖 Seu coração transborda de pura alegria! (+" + felicidadeGanha + " Felicidade)");
        esperaAi(1000);
        System.out.println("🌈 A luz da esperança brilha mais intensa em sua alma!");
        felicidade += felicidadeGanha;
    }

    // Metodo para pedido negado com drama
    private void pedidoNegadoDramatico() {
        System.out.println("\n💔═══════════════════════════════════════💔");
        System.out.println("🌩️          DESTINO CRUEL!               🌩️");
        System.out.println("💔═══════════════════════════════════════💔");
        esperaAi(1500);
        System.out.println("😭 As lágrimas dos céus caem sobre você...");
        esperaAi(1000);
        System.out.println("🌫️ Seu desejo se dissolve como névoa ao amanhecer...");
        esperaAi(1000);
        System.out.println("💸 Os fados conspiram contra sua felicidade...");
        esperaAi(1500);
        System.out.println("😢 Talvez em outra vida, em outro tempo...");
        esperaAi(1000);
        System.out.println("🕊️ Que a esperança não abandone seu coração ferido...");
    }

    public int eventos(GeneralFada general, Padrinhos padrinho) {
        if(!padrinho.getVarinha().getStatusVarinha().equalsIgnoreCase("Funcionando")){
            System.out.println("⚠️ Oh, não! Um silêncio mágico paira no ar...");
            System.out.println("💔 A varinha do seu padrinho está =" + padrinho.getVarinha().getStatusVarinha() + "=!");
            System.out.println("🔮 Sem magia, sem desejos... parece que este ano você terá que contar apenas com a sorte.");
            return (-1);
        }

        boolean realizar = random.nextBoolean();
        switch (opcaoEscolhida) {
            case 1:
                switch (this.indice1) {
                    case 0: // lanche
                        if (realizar) {
                            pedidoConcedidoDramatico(20);
                        } else pedidoNegadoDramatico();
                        break;
                    case 1: // herói
                        if (realizar) {
                            pedidoConcedidoDramatico(5);
                        } else pedidoNegadoDramatico();
                        break;
                    case 2: // férias
                        if (realizar) {
                            pedidoConcedidoDramatico(20);
                        } else pedidoNegadoDramatico();
                        break;
                    case 3: // voar
                        if (realizar) {
                            pedidoConcedidoDramatico(15);
                        } else pedidoNegadoDramatico();
                        break;
                    case 4: // castelo
                        if (realizar) {
                            pedidoConcedidoDramatico(5);
                        } else pedidoNegadoDramatico();
                        break;
                    case 5: // inteligente
                        if (realizar) {
                            pedidoConcedidoDramatico(15);
                        } else pedidoNegadoDramatico();
                        break;
                    case 6: // padrinho
                        if (realizar) {
                            System.out.println("\n🌟═══════════════════════════════════════🌟");
                            System.out.println("✨      DESEJO DOS DESEJOS REALIZADO!    ✨");
                            System.out.println("🌟═══════════════════════════════════════🌟");
                            esperaAi(2000);
                            System.out.println("💫 O UNIVERSO INTEIRO TREME DE EMOÇÃO!");
                            esperaAi(1500);
                            System.out.println("🎆 EXPLOSÃO DE FELICIDADE CÓSMICA!");
                            esperaAi(1000);
                            System.out.println("🌈 SUA ALMA ASCENDE AOS CÉUS! (+50 Felicidade)");
                            felicidade += 50;
                        } else pedidoNegadoDramatico();
                        break;
                    case 7: // robô
                        if (realizar) {
                            pedidoConcedidoDramatico(5);
                        } else pedidoNegadoDramatico();
                        break;
                    case 8: // entender meninas
                        if (realizar) {
                            pedidoConcedidoDramatico(10);
                        } else pedidoNegadoDramatico();
                        break;
                    case 9: // super poder
                        if (realizar) {
                            pedidoConcedidoDramatico(15);
                        } else pedidoNegadoDramatico();
                        break;
                    case 10: // cachorro falante
                        if (realizar) {
                            pedidoConcedidoDramatico(7);
                        } else pedidoNegadoDramatico();
                        break;
                    case 11: // paixão
                        if (realizar) {
                            pedidoConcedidoDramatico(10);
                        } else pedidoNegadoDramatico();
                        break;
                    case 12: // famoso
                        if (realizar) {
                            pedidoConcedidoDramatico(15);
                        } else pedidoNegadoDramatico();
                        break;
                    case 13: // somente eu padrinhos
                        if (realizar) {
                            System.out.println("\n🌟═══════════════════════════════════════🌟");
                            System.out.println("👑      DESEJO SUPREMO CONCEDIDO!       👑");
                            System.out.println("🌟═══════════════════════════════════════🌟");
                            esperaAi(2000);
                            System.out.println("💫 VOCÊ SE TORNA O ESCOLHIDO DOS PADRINHOS!");
                            esperaAi(1500);
                            System.out.println("🎆 ÊXTASE ABSOLUTO TOMA CONTA DE SEU SER!");
                            esperaAi(1000);
                            System.out.println("🌈 FELICIDADE TRANSCENDENTAL! (+50 Felicidade)");
                            felicidade += 50;
                        } else pedidoNegadoDramatico();
                        break;
                    default:
                        System.out.println("🌪️ Os ventos místicos se confundem! Opção inválida nas estrelas... Tente novamente! ⚡");
                        break;
                }
                break;
            case 2:
                switch (this.indice2) {
                    case 0: // lanche
                        if (realizar) {
                            pedidoConcedidoDramatico(20);
                        } else pedidoNegadoDramatico();
                        break;
                    case 1: // herói
                        if (realizar) {
                            pedidoConcedidoDramatico(5);
                        } else pedidoNegadoDramatico();
                        break;
                    case 2: // férias
                        if (realizar) {
                            pedidoConcedidoDramatico(20);
                        } else pedidoNegadoDramatico();
                        break;
                    case 3: // voar
                        if (realizar) {
                            pedidoConcedidoDramatico(15);
                        } else pedidoNegadoDramatico();
                        break;
                    case 4: // castelo
                        if (realizar) {
                            pedidoConcedidoDramatico(5);
                        } else pedidoNegadoDramatico();
                        break;
                    case 5: // inteligente
                        if (realizar) {
                            pedidoConcedidoDramatico(15);
                        } else pedidoNegadoDramatico();
                        break;
                    case 6: // padrinho
                        if (realizar) {
                            System.out.println("\n🌟═══════════════════════════════════════🌟");
                            System.out.println("✨      DESEJO DOS DESEJOS REALIZADO!    ✨");
                            System.out.println("🌟═══════════════════════════════════════🌟");
                            esperaAi(2000);
                            System.out.println("💫 O UNIVERSO INTEIRO TREME DE EMOÇÃO!");
                            esperaAi(1500);
                            System.out.println("🎆 EXPLOSÃO DE FELICIDADE CÓSMICA!");
                            esperaAi(1000);
                            System.out.println("🌈 SUA ALMA ASCENDE AOS CÉUS! (+50 Felicidade)");
                            felicidade += 50;
                        } else pedidoNegadoDramatico();
                        break;
                    case 7: // robô
                        if (realizar) {
                            pedidoConcedidoDramatico(5);
                        } else pedidoNegadoDramatico();
                        break;
                    case 8: // entender meninas
                        if (realizar) {
                            pedidoConcedidoDramatico(10);
                        } else pedidoNegadoDramatico();
                        break;
                    case 9: // super poder
                        if (realizar) {
                            pedidoConcedidoDramatico(15);
                        } else pedidoNegadoDramatico();
                        break;
                    case 10: // cachorro falante
                        if (realizar) {
                            pedidoConcedidoDramatico(7);
                        } else pedidoNegadoDramatico();
                        break;
                    case 11: // paixão
                        if (realizar) {
                            pedidoConcedidoDramatico(10);
                        } else pedidoNegadoDramatico();
                        break;
                    case 12: // famoso
                        if (realizar) {
                            pedidoConcedidoDramatico(15);
                        } else pedidoNegadoDramatico();
                        break;
                    case 13: // somente eu padrinhos
                        if (realizar) {
                            System.out.println("\n🌟═══════════════════════════════════════🌟");
                            System.out.println("👑      DESEJO SUPREMO CONCEDIDO!       👑");
                            System.out.println("🌟═══════════════════════════════════════🌟");
                            esperaAi(2000);
                            System.out.println("💫 VOCÊ SE TORNA O ESCOLHIDO DOS PADRINHOS!");
                            esperaAi(1500);
                            System.out.println("🎆 ÊXTASE ABSOLUTO TOMA CONTA DE SEU SER!");
                            esperaAi(1000);
                            System.out.println("🌈 FELICIDADE TRANSCENDENTAL! (+50 Felicidade)");
                            felicidade += 50;
                        } else pedidoNegadoDramatico();
                        break;
                    default:
                        System.out.println("🌪️ Os ventos místicos se confundem! Opção inválida nas estrelas... Tente novamente! ⚡");
                        break;
                }
                break;
            case 3: // escolhas pessoais
                System.out.println("🌟═══════════════════════════════════════🌟");
                System.out.println("✨ O momento da verdade chegou...        ✨");
                System.out.println("🌟═══════════════════════════════════════🌟");
                esperaAi(1000);
                System.out.println("🌙 As estrelas aguardam em silêncio cósmico...");
                esperaAi(1000);
                System.out.println("💫 Sussurre seu desejo mais profundo ao vento...");
                System.out.print("🎭 Seu pedido do coração: ");
                String pedido = scanner.nextLine();
                esperaAi(1500);

                if (realizar) {
                    if (isDesejoProibido(pedido)) {
                        System.out.println("\n💫═══════════════════════════════════════💫");
                        System.out.println("🎆        ¡PEDIDO CONCEDIDO!             🎆");
                        System.out.println("💫═══════════════════════════════════════💫");
                        esperaAi(1500);
                        System.out.println("🌟 As estrelas se alinham em seu favor!");
                        esperaAi(1000);
                        System.out.println("🎆 Fogos de artifício explodem nos céus!");
                        esperaAi(1000);
                        System.out.println("💖 Sua alma transborda de júbilo! (+50 Felicidade)");
                        felicidade += 50;
                        esperaAi(2500);
                        general.FarejarOProibido();
                        int resultado = Julgamento(pedido, verificarRegraViolada(pedido), encontrarTextoProibido(pedido));
                        return resultado;
                    } else {
                        System.out.println("\n✨═══════════════════════════════════════✨");
                        System.out.println("🌸     PEDIDO CONCEDIDO COM GRAÇA!       🌸");
                        System.out.println("✨═══════════════════════════════════════✨");
                        esperaAi(1500);
                        System.out.println("🌸 Uma brisa suave traz pequena alegria!");
                        esperaAi(1000);
                        System.out.println("💖 Seu coração se aquece gentilmente (+10 Felicidade)");
                        felicidade += 10;
                        esperaAi(1500);
                        System.out.println("🕊️  Que a paz esteja contigo, jovem sonhador...");
                        esperaAi(1000);
                        System.out.println("🌈 E que seus sonhos floresçam como jardins eternos!");
                    }
                } else {
                    esperaAi(2000);
                    System.out.println("\n💔═══════════════════════════════════════💔");
                    System.out.println("🌩️      O DESTINO CONSPIRA CONTRA VOCÊ   🌩️");
                    System.out.println("💔═══════════════════════════════════════💔");
                    esperaAi(1500);
                    System.out.println("😭 As lágrimas dos anjos caem do céu...");
                    esperaAi(1000);
                    System.out.println("🌫️  Seu pedido se dissolve como névoa ao amanhecer...");
                    esperaAi(1000);
                    System.out.println("💸 Os ventos do infortúnio sopram contra você...");
                    esperaAi(1500);
                    System.out.println("😔 Talvez em outra vida, em outro tempo...");
                    esperaAi(1000);
                    System.out.println("🌟 Mas não desista! A esperança é eterna!");
                    esperaAi(1500);
                }
                break;
            default:
                while (true) {
                    System.out.println("🌪️ Os portais místicos se confundem! Opção inválida no cosmos! Tente novamente, jovem viajante! ⚡");
                    try {
                        int novaOpcao = scanner.nextInt();
                        if (novaOpcao >= 1 && novaOpcao <= 3) {
                            setOpcaoEscolhida(novaOpcao);
                            eventos(general, padrinho);
                            break;
                        }
                    } catch (InputMismatchException e) {
                        scanner.next(); // limpar buffer
                    }
                }
                break;
        }
        return (-1);
    }
}