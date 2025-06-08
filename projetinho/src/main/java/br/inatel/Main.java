package br.inatel;

import br.inatel.DAO.*;
import br.inatel.Model.Personagens.*;
import br.inatel.Model.Uteis.Menu;

import static br.inatel.Model.Uteis.Eventos.decidirEvento;
import static br.inatel.Model.Uteis.Eventos.fofoca;
import static br.inatel.Model.Uteis.Util.esperaAi;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

//Importando a função que imprime o menu
import static br.inatel.Model.Personagens.Crianca.felicidade;

/*
        Notas para nós mesmos:
        Felipe - Seguinte, implementei as coisas que eu acredito que a gente vá precisar por enquanto
        nos DAOs, rodei eles e dá para ver que eles são criados e adicionados no BD quando chama a função.
            Consegui colocar a lista de crianças que tem no BD na opção de escolher para o baile também, se você rodar
        você vai ver que sai uma lista com as 7 crianças que tem no BD.
            Também mudei um pouco o general fada, agora ele tem um metodo só dele que chama o tribunal da magia,
        isso mais pra poder justificar que a gente criou o fada como abstrata porque o general tem seu próprio metodo,
        que uma fada não pode ter.
            Outra coisa, não mexe no for que mostra as crianças do baile.
            O que que é a ideia: A gente recebe um arraylist pela main com as crianças que já tão cadastradas, só que
        sempre que a gente cadastra o jogador, ele é lançado como uma criança nova no arraylist, o que significa que
        aquele for tem que rodar só até a última criança cadastrada que é a 7ª criança. O contador do for começa em 1
        para ficar bonitinho na hora de sair, mas eu chamo i-1 quando é pra sair a criança, então a lógica tá teoricamente
        certa.
            Outra coisa, pra poder testar o baile, eu tô setando a opção do evento como a opção do baile quando entra na
        função do baile, tem que mudar isso quando for arrumar os eventos.


        Agora o que tem que ser feito:

        * Colocar no switch do baile o que vai acontecer quando ele chamar uma criança pro baile (FEITO)

        * Resetar a varinha do padrinho que quebra quando ele perde pra uma anti-fada, que parece não estar funcionando, não
        sei se já tá implementada (FEITO)

        * Dar alguma utilidade pra Magia, que eu acho que podia ser no evento da anti-fada, eles lançarem umas magias um no
        outro um pouco, só pra falar que tamo usando FELIPE
            - fazer um random nas magias inseridas no BD e mostrar uma de cada fada no combate

        * Colocar um opção segura pra decisão do baile, aquilo ainda não tá nem um pouco seguro, vai cair no default e a pessoa
        vai perder o evento (FEITO) OBS.: QUANDO ARRUMEI ISSO DEU O ERRO DO PEDIDO PERSONALIZADO *WPP

            Acho que é isso, qualquer coisa me manda mensagem, só não sei que horas eu vou entrar amanhã, com certeza mesmo é
         só na hora que eu chegar em SRS
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
        ArrayList<Crianca> criancasExistentes = criancasDAO.selectCrianca();
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
        System.out.print("\n Olá, " + nomeJogador + "! Parabéns por ganhar seus Padrinhos Mágicos! \n" +
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
                    decidirEvento(antiPadrinho, nossoPadrinho, jogador, criancasExistentes);
                    if(nossoPadrinho.getVarinha().getStatusVarinha().equals("Funcionando")){
                        menu.mostraMenu();
                        int opcao = menu.lerOpcaoSegura("🪄 Digite sua escolha (1-3): ");
                        menu.setOpcaoEscolhida(opcao);
                        resultado = menu.eventos((GeneralFada) general, nossoPadrinho);
                    }
                }
                else{
                    decidirEvento(antiMadrinha, nossaMadrinha, jogador, criancasExistentes);
                    if(nossaMadrinha.getVarinha().getStatusVarinha().equals("Funcionando")){
                        menu.mostraMenu();
                        int opcao = menu.lerOpcaoSegura("🪄 Digite sua escolha (1-3): ");
                        menu.setOpcaoEscolhida(opcao);
                        resultado = menu.eventos((GeneralFada) general, nossaMadrinha);
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
                if (menu.getOpcaoEscolhida() != 1 && menu.getOpcaoEscolhida() != 2 && menu.getOpcaoEscolhida() != 3 && (!nossaMadrinha.getVarinha().getStatusVarinha().equals("Funcionando") || !nossoPadrinho.getVarinha().getStatusVarinha().equals("Funcionando")) ) {
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
                System.out.println("🌅 Após seis anos mágicos ao lado de seu padrinho, chega a hora do adeus...");
                System.out.println("💫 Foram tempos de aprendizado, aventuras e sonhos realizados.");
                System.out.println("📈 Você acumulou um total de: " + felicidade + " pontos de felicidade.");
                break;
            case 1:
                System.out.println("🌪️ Suas escolhas abriram um abismo entre você e seu padrinho.");
                System.out.println("🔗 A conexão mágica foi rompida à força.");
                System.out.println("😔 Ainda assim... você foi feliz enquanto pôde.");
                System.out.println("📈 Você acumulou um total de: " + felicidade + " pontos de felicidade.");
                break;
            case 2:
                System.out.println("⚖️ O Tribunal da Magia observou suas ações... e decidiu.");
                System.out.println("🚫 Você perdeu o direito de realizar mais desejos.");
                System.out.println("🧚 Seu padrinho agora se afasta, levando consigo o brilho da varinha.");
                System.out.println("📈 Você acumulou um total de: " + felicidade + " pontos de felicidade.");
                break;
            case 3:
                System.out.println("⏳ Algumas de suas decisões tiveram um preço.");
                System.out.println("🔁 Parte dos desejos realizados foi desfeita, como se o tempo tivesse voltado.");
                System.out.println("🧚‍♂️ Ainda assim, seu padrinho permaneceu mais um pouco... e novos desejos vieram.");
                System.out.println("📈 Você acumulou um total de: " + felicidade + " pontos de felicidade.");
                break;
            case 4:
                System.out.println("🔒 Como punição, você e seu padrinho foram selados em cristais mágicos por 100 anos.");
                System.out.println("⏳ O tempo deixará de existir para vocês... até que alguém os liberte.");
                System.out.println("📉 Você acumulou 0 pontos de felicidade.");
                break;
            case 5:
                System.out.println("🌌 Suas ações causaram um desequilíbrio irreversível.");
                System.out.println("🕳️ Você foi lançado ao Vazio Eterno, onde o tempo, som e luz não existem.");
                System.out.println("📉 Você acumulou 0 pontos de felicidade.");
                break;
            case 6:
                System.out.println("🔥 O Tribunal da Magia considerou sua existência uma afronta às leis mágicas.");
                System.out.println("🌋 A sentença foi implacável: você foi lançado em um vulcão em erupção.");
                System.out.println("💀 Você morreu.");
                break;
            case 7:
                System.out.println("🌀 Você caiu em um ciclo eterno de sofrimento — um feitiço que repete suas piores memórias sem fim.");
                System.out.println("🕰️ Cada momento se repete, sem escapatória, sem redenção.");
                System.out.println("📉 Você acumulou 0 pontos de felicidade.");
                break;
            case 8:
                System.out.println("🌈 Você viveu anos tão cheios de alegria que sua luz agora precisa alcançar outras vidas.");
                System.out.println("🧚 Seu padrinho precisa partir para ajudar uma nova criança, mas ele parte com orgulho.");
                System.out.println("😭 A despedida não é triste — é celebrada com gratidão.");
                System.out.println("🏆 Você conquistou o *pico da felicidade*!");
                break;
            default:
                break;
        }
        scanner.close();
    }
}