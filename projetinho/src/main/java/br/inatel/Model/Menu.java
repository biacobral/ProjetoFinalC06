package br.inatel.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.Random;

import static br.inatel.Model.Desejos.listaDesejos;

public class Menu {
    private Scanner scanner;
    private Random random;
    static Set<Integer> pedidosFeitos = new HashSet<Integer>();//Por enquanto vou fazer um hashset, que condiz, já que a gente não quer repetição de opções

    //Os dois pedidos aleatórios que a gente vai disponibilizar por ano
    private String pedidoEscolhido1;
    private String pedidoEscolhido2;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }



    private void sortearPedidos() {
        int indice1;
        int indice2;

        // Escolhe dois pedidos diferentes aleatoriamente
        indice1 = random.nextInt(12);

        // Garante que os dois pedidos sejam diferentes
        do {
            indice2 = random.nextInt(12);
        } while (indice2 == indice1);
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
        System.out.println("║  " + getPedidoEscolhido1() +"║");
        System.out.println("║  " + getPedidoEscolhido2() +"║");
        System.out.println("║  3. ✍️  Fazer Pedido Personalizado   ║");
        System.out.println("║  4. 🚪 Sair                          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\n🪄 Digite sua escolha (1-4): ");
    }
}