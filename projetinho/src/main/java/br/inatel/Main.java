package br.inatel;

import br.inatel.DAO.*;
import br.inatel.Model.Personagens.*;
import br.inatel.Model.Uteis.Menu;

import static br.inatel.Model.Uteis.Eventos.decidirEvento;
import static br.inatel.Model.Uteis.Eventos.fofoca;
import static br.inatel.Model.Uteis.Util.esperaAi;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

//Importando a função que imprime o menu
import static br.inatel.Model.Personagens.Crianca.felicidade;

/*
        Notas para nós mesmos:
        - Pensar se vale a pena fazer uma interface para os seres mágicos
        Arrumar todas as coias do SQL

 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados
        //Criando os DAOs
        AntiFadaDAO antifadaDAO = new AntiFadaDAO();
        Crianca_Faz_DesejosDAO criancaFazDesejosDAO = new Crianca_Faz_DesejosDAO();
        CriancaDAO criancasDAO = new CriancaDAO();
        DesejosDAO desejosDAO = new DesejosDAO();
        GeneralFadaDAO generalFadaDAO = new GeneralFadaDAO();
        MagiaDAO magiaDAO = new MagiaDAO();
        PadrinhosDAO padrinhosDAO = new PadrinhosDAO();
        VarinhaDAO varinhaDAO = new VarinhaDAO();
        //Testando a conexão
        antifadaDAO.connectToDb();
        criancasDAO.connectToDb();
        criancaFazDesejosDAO.connectToDb();
        desejosDAO.connectToDb();
        generalFadaDAO.connectToDb();
        magiaDAO.connectToDb();
        padrinhosDAO.connectToDb();
        varinhaDAO.connectToDb();
        //Funções que estamos usando
        Menu menu = new Menu();
        boolean taCerto = false;
        boolean sairDoLoop = false;
        int oqAconteceu = 0;
        int resultado = -1;
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!taCerto); // try catch dentro do while - substituir if else e criar exceção geral opção invalida
        // Criação do Jogador "Criança"
        Crianca jogador = new Crianca(1, nomeJogador, 12, sexoJogador, true, "Rua dos Desejos, nº72"); // criando jogador
        criancasDAO.insertCrianca(jogador);

        // Criação das Varinhas
        Varinha varinha1 = new Varinha(1, "Azul", "Funcionando");
        varinhaDAO.insertVarinha(varinha1);
        Varinha varinha2 = new Varinha(2, "Roxo", "Funcionando");
        varinhaDAO.insertVarinha(varinha2);
        Varinha antiVarinha1 = new Varinha(3, "Amarelo", "Em manutenção");
        varinhaDAO.insertVarinha(antiVarinha1);
        Varinha antiVarinha2 = new Varinha(4, "Vermelho", "Em manutenção");
        varinhaDAO.insertVarinha(antiVarinha2);

        // Criação dos Padrinhos
        Padrinhos nossoPadrinho = new Padrinhos(1, "Grimbolino, o Estagiário da Magia", "Padrinho", varinha1);
        Padrinhos nossaMadrinha = new Padrinhos(2, "Celestina Cintilante, a Matriarca da Magia", "Madrinha", varinha2);


        // Criação dos Anti-Fada
        Fada antiPadrinho = new AntiFada(3, "Grimbolona", "Anti-Fada", 3);
        antifadaDAO.insertAntiFada((AntiFada) antiPadrinho);
        Fada antiMadrinha = new AntiFada(4, "Celestina Obscura", "Anti-Fada", 4);
        antifadaDAO.insertAntiFada((AntiFada) antiMadrinha);

        // Criação do General Fada
        Fada general = new GeneralFada(5, "Jorgen Von Estranho, O General da Magia", "General Fada", 5);
        generalFadaDAO.insertGeneralFada((GeneralFada) general);

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
            padrinhosDAO.insertPadrinho(nossoPadrinho);
            padrinhosDAO.insertPadrinho(nossaMadrinha);
            System.out.println("a sua madrinha será: ");
            esperaAi(1000);
            System.out.println(nossaMadrinha.getNomeFada());
        }
        esperaAi(400);
        menu.warning();
        esperaAi(3500);

        //Aqui é o big-for(eventos)
        for (int i = jogador.getIdadeCrianca(); i < 18 && !sairDoLoop; i++) {
            try {
                esperaAi(300);
                System.out.println("Bem vindo ao seu " + (i - 11) + "° ano com seu padrinho");
                fofoca(); // evento fofoca
                if(idP==1) {
                    decidirEvento(antiPadrinho, nossoPadrinho, jogador, jogador);
                    if(nossoPadrinho.getVarinha().getStatusVarinha().equals("Funcionando")){
                        menu.mostraMenu();
                        int opcao = menu.lerOpcaoSegura("🪄 Digite sua escolha (1-3): ");
                        menu.setOpcaoEscolhida(opcao);
                        resultado = menu.eventos(general.getNomeFada(), nossoPadrinho);
                    }
                }
                else{
                    decidirEvento(antiMadrinha, nossaMadrinha, jogador, jogador); // jogador jogador mudar
                    if(nossaMadrinha.getVarinha().getStatusVarinha().equals("Funcionando")){
                        menu.mostraMenu();
                        int opcao = menu.lerOpcaoSegura("🪄 Digite sua escolha (1-3): ");
                        menu.setOpcaoEscolhida(opcao);
                        resultado = menu.eventos(general.getNomeFada(), nossaMadrinha);
                    }
                }

                if (resultado != (-1)) {
                    switch (resultado) {
                        case 0://Separação
                            if (idP == nossoPadrinho.getIdFada()) {
                                nossoPadrinho.setCrianca_idCrianca(0);
                            } else {
                                nossaMadrinha.setCrianca_idCrianca(0);
                            }
                            oqAconteceu = 1;
                            sairDoLoop = true;
                            break;
                        case 1://Castigo mágico
                            sairDoLoop = true;
                            oqAconteceu = 2;
                            break;
                        case 2://Punição Severa
                            felicidade = 0;
                            oqAconteceu = 3;
                            break;
                        case 3://Sua alma foi selada por 100 anos
                            sairDoLoop = true;
                            oqAconteceu = 4;
                            break;
                        case 4://Buraco negro mágico: sugado para o vazio eterno
                            sairDoLoop = true;
                            oqAconteceu = 5;
                            break;
                        case 5://Morte mágica: morre
                            sairDoLoop = true;
                            oqAconteceu = 6;
                            break;
                        case 6://Distorção temporal: Loop temporal de sofrimento
                            sairDoLoop = true;
                            oqAconteceu = 7;
                            break;
                        default:
                            break;
                    }
                }
                if (menu.getOpcaoEscolhida() != 1 && menu.getOpcaoEscolhida() != 2 && menu.getOpcaoEscolhida() != 3) {
                    throw new Exception("Faz certo, cabeça de ovo!");
                }

                System.out.println("Sua felicidade até agora é: " + felicidade + "\n");
                if(felicidade>=100){
                    sairDoLoop = true;
                    oqAconteceu = 8;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        switch (oqAconteceu){
            case 0:
                System.out.println("Você viveu 6 bons anos com seu padrinho e chegou o momento de se despedirem.");
                System.out.println("Você acumulou um total de: " + felicidade + " pontos de felicidades.");
                break;
            case 1:
                System.out.println("Devido a suas próprias escolhas, você será forçadamente separado de seu padrinho.");
                System.out.println("Você foi feliz enquanto pode.");
                System.out.println("Você acumulou um total de: " + felicidade + " pontos de felicidades.");
                break;
            case 2:
                System.out.println("Você perdeu o direito de pedir por mais desejos por consequência de suas próprias decisões.");
                System.out.println("Você está sendo separado de seu padrinho.");
                System.out.println("Você acumulou um total de: " + felicidade + " pontos de felicidades.");
                break;
            case 3:
                System.out.println("Por consequência de suas próprias decisões, uma parte de seus desejos realizados foram desfeitos");
                System.out.println("Ainda assim, você conseguiu viver por mais um tempo com seu padrinho e realizou mais alguns desejos.");
                System.out.println("Você acumulou um total de: " + felicidade + " pontos de felicidades.");
                break;
            case 4:
                System.out.println("Por consequência de suas próprias decisões, você será separado de seu padrinho e ambos serão selados por 100 anos.");
                System.out.println("Com isso, você acumulou 0 pontos de felicidade.");
                break;
            case 5:
                System.out.println("Por consequência de suas próprias decisões, você será jogado em um vazio eterno.");
                System.out.println("Com isso, você acumulou 0 pontos de felicidade.");
                break;
            case 6:
                System.out.println("Por consequência de suas próprias decisões, o Tribunal da Magia julgou suas existência" +
                        " como uma ameaça aos costume das fadas e as regras do Tribunal da Magia e das fadas." +
                        " Assim, você é jogado dentro de um vulcão que está ativo.");
                System.out.println("Você morreu.");
                break;
            case 7:
                System.out.println("Por consequência de seus próprios atos, você foi preso em um loop de sofrimento.");
                System.out.println("Com isso, você acumulou 0 pontos de felicidade.");
                break;
            case 8:
                System.out.println("Você viveu anos felizes com seu padrinho. Felizes ao ponto de seu padrinho ser mais" +
                        " necessário para outra criança que sofre em algum lugar do mundo. Você se despedirá de seu padrinho," +
                        " não triste pelo o que deixará de acontecer, mas feliz pelo o que aconteceu.");
                System.out.println("Você conquistou o pico da felicidade!");
                break;
            default:
                break;
        }
        scanner.close();
    }
}