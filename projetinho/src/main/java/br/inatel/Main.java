package br.inatel;

import br.inatel.Model.*;
import static br.inatel.Model.Util.esperaAi;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

//Importando a função que imprime o menu
import static br.inatel.Model.Desejos.felicidade;
import static br.inatel.Model.Menu.*;

/*
        Notas para nós mesmos:
        - Pensar se vale a pena fazer uma interface para os seres mágicos
        - -THREAD_MILLIS- Feito e tratado
 */
public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados

        Menu menu = new Menu();
        boolean taCerto = false;
        //Opção que vai ser escolhida pelo jogador
        int opcaoEscolhida;
        // Definindo nome e sexo do jogador
        System.out.print("Insira seu nome: ");
        String nomeJogador = scanner.nextLine(); // Scanner -> String
        String sexoJogador = "";
        do {
            try {
                System.out.print("Insira seu sexo (F, M, NB): ");
                sexoJogador = scanner.nextLine(); // Scanner -> String
                if (!Objects.equals(sexoJogador, "F") && !Objects.equals(sexoJogador, "M") && !Objects.equals(sexoJogador, "NB")) {
                    throw new Exception("Faz certo, cabeça de ovo!");
                }
                taCerto = true;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!taCerto); // try catch dentro do while - substituir if else e criar exceção geral opção invalida
        // Criação do Jogador "Criança"
        Crianca jogador = new Crianca(1, nomeJogador, 12, sexoJogador, true, "Rua dos Desejos, nº72"); // criando jogador
        // Criação dos Padrinhos
        Padrinhos nossoPadrinho = new Padrinhos(1, "Grimbolino, o Estagiário da Magia", "Padrinho", 1);
        Padrinhos nossaMadrinha = new Padrinhos(2, "Celestina Cintilante, a Matriarca da Magia", "Madrinha", 2);

        // Criação das Varinhas
        Varinha varinha1 = new Varinha(1, "Azul", "Funcionando");
        Varinha varinha2 = new Varinha(2, "Roxo", "Funcionando");
        Varinha antiVarinha1 = new Varinha(3, "Amarelo", "Em manutenção");
        Varinha antiVarinha2 = new Varinha(4, "Vermelho", "Em manutenção");

        // Criação dos Anti-Fada
        Fada antiPadrinho = new AntiFada(3, "Grimbolona", "Anti-Fada", 3);
        Fada antiMadrinha = new AntiFada(4, "Celestina Obscura", "Anti-Fada", 4);

        // Criação do General Fada
        Fada general = new GeneralFada(5, "Jorgen Von, o Estranho", "General Fada", 5);

        // Boas-vindas!!
        esperaAi(400);
        System.out.print("Olá, " + nomeJogador + "! Parabéns por ganhar seus Padrinhos Mágicos! \n" +
                "Você tem 12 anos e mora em Dimmsdale, no endereço " + jogador.getEnderecoCrianca() +
                " e ");
        Random randPadrinho = new Random();
        int idP = randPadrinho.nextInt(1) + 1;
        if (idP == nossoPadrinho.getIdFada()) {
            nossoPadrinho.setCrianca_idCrianca(1);
            System.out.println("o seu padrinho será: ");
            esperaAi(1000);
            System.out.println(nossoPadrinho.getNomeFada());
        } else {
            nossaMadrinha.setCrianca_idCrianca(1);
            System.out.println("a sua madrinha será: ");
            esperaAi(1000);
            System.out.println(nossaMadrinha.getNomeFada());
        }
        esperaAi(400);
        menu.warning();
        esperaAi(3500);

        //Aqui é o big-for(eventos)
        // Tratar aqui!!!!
            for (int i = jogador.getIdadeCrianca(); i < 18; i++) {
                try{
                esperaAi(300);
                System.out.println("Bem vindo ao seu " + (i - 11) + "° ano com seu padrinho");
                menu.mostraMenu();
                menu.setOpcaoEscolhida(scanner.nextInt()); // int set String
                if (menu.getOpcaoEscolhida() != 1 && menu.getOpcaoEscolhida() != 2 && menu.getOpcaoEscolhida() != 3) {
                    throw new Exception("Faz certo, cabeça de ovo!");
                }

                System.out.println("Sua felicidade até agora é: " + felicidade + "\n");


            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}