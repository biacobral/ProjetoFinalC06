package br.inatel.Model.Uteis;

import br.inatel.Model.Personagens.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static br.inatel.Model.Personagens.Crianca.felicidade;
import static br.inatel.Model.Uteis.Util.diminuirFelicidade;
import static br.inatel.Model.Uteis.Util.esperaAi;

public class Eventos {
    private static boolean []jaFoi = {false, false, false};

    public static void decidirEvento(Fada antiFada, Padrinhos padrinho, ArrayList<Crianca> crianca2, ArrayList<Magia> magiasExistentes) {
        padrinho.getVarinha().setStatusVarinha("Funcionando");
        Random random = new Random();
        int decisao = random.nextInt(3);
        switch (decisao) {
            case 0:
                if(!jaFoi[0]) {
                    baile(crianca2);
                    jaFoi[0] = true;
                }
                break;
            case 1:
                if(!jaFoi[1]) {
                    vicky();
                    jaFoi[1] = true;
                }
                break;
            case 2:
                if(!jaFoi[2]) {
                    combateFada(antiFada, padrinho, magiasExistentes);
                    jaFoi[2] = true;
                }
                break;
            default:
                System.out.println("Sem eventos esse ano.");
                break;
        }
    }

    // Combate de fadas
    private static void combateFada(Fada antiFada, Padrinhos padrinho, ArrayList<Magia> magiasExistentes) {

        AntiFada antiFadaCast = null;
        if (antiFada instanceof AntiFada) {
            antiFadaCast = (AntiFada) antiFada;
        }

        Random magia1 = new Random();
        Random magia2 = new Random();


        System.out.println("⚔️═══════════════════════════════════════⚔️");
        System.out.println("🌪️    CONFRONTO ENTRE FADA E ANTI-FADA    🌪️");
        System.out.println("⚔️═══════════════════════════════════════⚔️");
        esperaAi(1500);

        antiFadaCast.inicioCombate();
        System.out.println("🪄" + antiFada.getNomeFada() + " está tentando roubar a varinha mágica de " + padrinho.getNomeFada() + "!");
        esperaAi(2000);

        Magia magiaUsadaAntiFada = magiasExistentes.get(magia1.nextInt(magiasExistentes.size()));
        Magia magiaUsadaPadrinho = magiasExistentes.get(magia2.nextInt(magiasExistentes.size()));

        System.out.println("\n🌪️ De repente, o ar fica pesado e as nuvens escurecem...");
        esperaAi(1500);
        System.out.println("😈 " + antiFada.getNomeFada() + " ergue os braços e libera um poder sombrio!");
        esperaAi(2000);
        System.out.println("💥 Ela lançou a temida magia: " + magiaUsadaAntiFada.getNomeMagia() + "!");
        esperaAi(2000);
        System.out.println("📖 (" + magiaUsadaAntiFada.getDescricaoMagia() + ")");
        esperaAi(2000);

        System.out.println("\n🛡️ Mas " + padrinho.getNomeFada() + " não recua!");
        esperaAi(1500);
        System.out.println("✨ Com um brilho intenso e coragem inabalável...");
        esperaAi(2000);
        System.out.println("🪄 Ele revida com a magia: " + magiaUsadaPadrinho.getNomeMagia() + "!");
        esperaAi(2000);
        System.out.println("📖 (" + magiaUsadaPadrinho.getDescricaoMagia() + ")");
        esperaAi(2000);

        System.out.println("\n⚡ O céu se parte. As energias colidem em uma explosão de luz e trevas!");
        esperaAi(2500);

        if (felicidade > 50) {
            antiFadaCast.perdeBatalha();
            System.out.println("\n💖 Mas sua felicidade está irradiando tanto que cria uma barreira mágica!");
            esperaAi(1500);
            System.out.println("🛡️ " + padrinho.getNomeFada() + " consegue proteger sua varinha com sucesso!");
            esperaAi(1500);
            System.out.println("🎉 Nenhum pedido será perdido este ano!");
        } else {
            antiFadaCast.ganhaBatalha();
            System.out.println("\n💔 Sua felicidade está muito baixa para proteger seu padrinho...");
            esperaAi(1500);
            System.out.println("😵 " + antiFada.getNomeFada() + " conseguiu roubar a varinha mágica!");
            esperaAi(1500);
            System.out.println("📉 Você ficará sem desejos por um ano inteiro!");
            padrinho.getVarinha().setStatusVarinha("Roubada");
        }

        esperaAi(2000);
        System.out.println("\n✨═══════════════════════════════════════✨");
        System.out.println("💫 Fim do confronto mágico!");
        System.out.println("✨═══════════════════════════════════════✨");
    }

    //Baile crianças
    private static void baile(ArrayList<Crianca> crianca2) {
        System.out.println("💃═══════════════════════════════════════💃");
        System.out.println("🌟        BAILE DA ESCOLA CHEGOU!        🌟");
        System.out.println("💃═══════════════════════════════════════💃");
        esperaAi(2000);

        System.out.println("🎭 A escola toda está falando sobre o grande baile!");
        esperaAi(1500);
        System.out.println("💫 Você precisa escolher alguém para ser seu par...");
        esperaAi(1500);
        System.out.println("😊 Que tal dar uma olhada em quem está disponível?");
        esperaAi(2000);

        Scanner scanner = new Scanner(System.in);
        int escolha = -1;
        boolean escolhaValida = false;

        while (!escolhaValida) {
            try {
                System.out.println("👫 Escolha o número da criança que você quer convidar para o baile (1 a 7): ");
                for (int i = 1; i <= 7; i++) {
                    System.out.println("------=============------");
                    System.out.println(i + ". " + crianca2.get(i - 1).getNomeCrianca());
                }

                System.out.print("Digite sua escolha: ");
                escolha = scanner.nextInt();

                if (escolha >= 1 && escolha <= 7) {
                    escolhaValida = true;
                } else {
                    System.out.println("❌ Número inválido! Escolha entre 1 e 7.");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Entrada inválida! Digite apenas números.");
                scanner.nextLine(); // limpa o buffer
            } catch (Exception e) {
                System.out.println("⚠️ Ocorreu um erro inesperado. Tente novamente.");
                scanner.nextLine();
            }
        }
        String nomePar;
        switch (escolha) {
            case 1:
                nomePar = crianca2.get(0).getNomeCrianca();
                break;
            case 2:
                nomePar = crianca2.get(1).getNomeCrianca();
                break;
            case 3:
                nomePar = crianca2.get(2).getNomeCrianca();
                break;
            case 4:
                nomePar = crianca2.get(3).getNomeCrianca();
                break;
            case 5:
                nomePar = crianca2.get(4).getNomeCrianca();
                break;
            case 6:
                nomePar = crianca2.get(5).getNomeCrianca();
                break;
            case 7:
                nomePar = crianca2.get(6).getNomeCrianca();
                break;
            default:
                System.out.println("❌ Número inválido! Você ficou sem par 😢");
                return; // sai do metodo

        }

        Random random = new Random();
        boolean aceito = random.nextBoolean();
        if (aceito) {
            System.out.println("💖✨ Incrível! "+ nomePar + " olhou para você e sorriu.");
            esperaAi(1500);
            System.out.println("🕺💃 \"Claro que sim! Eu adoraria ir ao baile com você!\"");
            esperaAi(1500);
            System.out.println("🎉 Vocês dançam sob as estrelas, enquanto a música embala um momento inesquecível...");
            felicidade += 45;
            esperaAi(1500);
            System.out.println("😄 Sua felicidade transborda! (+45)");
            System.out.println("🌈 Felicidade atual: " + felicidade);
        } else {
            System.out.println("💔 " + nomePar + " parece hesitar, desviando o olhar...");
            esperaAi(1500);
            System.out.println("🙁 \"Desculpa... eu já tenho um par.\"");
            esperaAi(1500);
            System.out.println("🎶 A música toca ao fundo... mas seu coração parece desafinar com ela.");
            System.out.println("😞 Você se sente um pouco triste...");
            try {
                diminuirFelicidade(10);
                System.out.println("💙 Sua felicidade diminuiu em 10 pontos...");
            } catch (NaoPodeSerTristeException e) {

                System.out.println("💙 Sua felicidade agora é 0...");
                felicidade = 0;

            }
            esperaAi(1500);
            System.out.println("🌧️ Felicidade atual: " + felicidade);
        }
    }

    //Dia com a Vicky
    private static void vicky() {
        System.out.println("💔═══════════════════════════════════════💔");
        System.out.println("🌧️         O DIA QUE TUDO MUDOU          🌧️");
        System.out.println("💔═══════════════════════════════════════💔");
        esperaAi(2000);
        System.out.println("📅 Seus pais sussurram segredos no quarto...");
        esperaAi(1500);
        System.out.println("✈️  'Querido, decidimos fazer uma viagem romântica!'");
        esperaAi(1000);
        System.out.println("😢 'Para comemorar nosso aniversário de casamento...'");
        esperaAi(1500);
        System.out.println("💸 'Mas você... bem... não cabe no orçamento da viagem.'");
        esperaAi(2000);

        System.out.println("\n🏠 ════════ MEANWHILE ════════ 🏠");
        esperaAi(1000);
        System.out.println("📞 *RING RING* - Ligação dos Turners...");
        esperaAi(1500);
        System.out.println("👥 'Ah, conheço a babá PERFEITA para vocês!'");
        esperaAi(1000);
        System.out.println("😈 'O nome dela é... VICKY!'");
        esperaAi(2000);

        System.out.println("\n🚪═══════════════════════════════════════🚪");
        System.out.println("👹        A CHEGADA DO TERROR            👹");
        System.out.println("🚪═══════════════════════════════════════🚪");
        esperaAi(1500);
        System.out.println("*DONG DONG* - A campainha ecoa como sino fúnebre...");
        esperaAi(1500);
        System.out.println("🔓 A porta se abre revelando uma silhueta sinistra...");
        esperaAi(1000);
        System.out.println("😠 Vicky surge com um sorriso cruel no rosto!");
        esperaAi(1500);
        System.out.println("💀 'Olá, pequeno pestinha... vamos nos DIVERTIR muito!'");
        esperaAi(2000);

        System.out.println("\n⚡ Você sente um calafrio percorrer sua espinha...");
        esperaAi(1500);
        System.out.println("😨 Ela é conhecida por tornar a vida das crianças um INFERNO!");
        esperaAi(1500);
        System.out.println("👿 Seus olhos brilham com prazer sádico ao ver seu desespero...");
        esperaAi(2000);

        System.out.println("\n💸═══════════════════════════════════════💸");
        System.out.println("📉        FELICIDADE EM QUEDA LIVRE      📉");
        System.out.println("💸═══════════════════════════════════════💸");
        esperaAi(1000);
        System.out.println("💔 Sua alma se despedaça em mil pedacinhos...");
        System.out.println("😢 Sua felicidade despenca: -5 pontos!");
        esperaAi(1000);
        try {
            diminuirFelicidade(5);
        } catch (NaoPodeSerTristeException e) {
            System.out.println("💙 Sua felicidade agora é 0...");
            felicidade = 0;
        }
        esperaAi(1500);
        System.out.println("😭 Felicidade atual: " + felicidade + " pontos");
        esperaAi(1000);
        System.out.println("🌩️  As nuvens se formam... a tempestade se aproxima...");
        System.out.println("⚡ Que os jogos... COMECEM! ⚡");
    }

    //Fofoca
    public static void fofoca() {
        Random random = new Random();
        int fofocaEscolhida = random.nextInt(15); // 15 fofocas diferentes

        System.out.println("🏫═══════════════════════════════════════🏫");
        System.out.println("📢        FOFOCA DO DIA NA ESCOLA!       📢");
        System.out.println("🏫═══════════════════════════════════════🏫");
        esperaAi(1500);

        switch (fofocaEscolhida) {
            // FOFOCAS FELIZES (+15 a +25 felicidade)
            case 0:
                System.out.println("🎉 A cantina vai servir pizza grátis amanhã!");
                System.out.println("✨ Todo mundo está comemorando nos corredores!");
                felicidade += 20;
                System.out.println("💖 Sua felicidade aumentou em 20 pontos!");
                break;

            case 1:
                System.out.println("🎭 O professor mais chato da escola vai se aposentar!");
                System.out.println("🥳 Os alunos estão fazendo festa secreta no banheiro!");
                felicidade += 25;
                System.out.println("💖 Sua felicidade aumentou em 25 pontos!");
                break;

            case 2:
                System.out.println("💕 Seu crush perguntou sobre você para seus amigos!");
                System.out.println("😍 Parece que você despertou o interesse de alguém especial...");
                felicidade += 15;
                System.out.println("💖 Sua felicidade aumentou em 15 pontos!");
                break;

            case 3:
                System.out.println("🎪 Vai ter feira de ciências e você pode faltar 3 aulas!");
                System.out.println("🔬 E ainda por cima, sua equipe já tem o projeto pronto!");
                felicidade += 18;
                System.out.println("💖 Sua felicidade aumentou em 18 pontos!");
                break;

            case 4:
                System.out.println("🏆 Sua turma ganhou o concurso de decoração de sala!");
                System.out.println("🎊 Vocês vão ganhar um dia sem provas como prêmio!");
                felicidade += 22;
                System.out.println("💖 Sua felicidade aumentou em 22 pontos!");
                break;

            // FOFOCAS NEUTRAS (0 felicidade)
            case 5:
                System.out.println("🤷‍♀️ A professora de matemática trocou de perfume...");
                System.out.println("👃 Agora cheira a lavanda ao invés de eucalipto.");
                System.out.println("😐 Você se sente... indiferente sobre isso.");
                break;

            case 6:
                System.out.println("📚 Mudaram o horário da biblioteca em 15 minutos...");
                System.out.println("⏰ Agora fecha às 17:45 ao invés de 18:00.");
                System.out.println("🤨 Não afeta muito sua vida, na real...");
                break;

            case 7:
                System.out.println("🎨 Pintaram o corredor do segundo andar de azul claro...");
                System.out.println("🖌️ Antes era azul... um pouco mais escuro.");
                System.out.println("😑 Você mal notou a diferença...");
                break;

            // FOFOCAS TRISTES (-10 a -25 felicidade)
            case 8:
                System.out.println("😢 Seu professor favorito vai ser transferido para outra escola...");
                System.out.println("💔 Ele era o único que realmente te entendia...");
                try {
                    diminuirFelicidade(20);
                    System.out.println("💙 Sua felicidade diminuiu em 20 pontos...");
                } catch (NaoPodeSerTristeException e) {

                    System.out.println("💙 Sua felicidade agora é 0...");
                    felicidade = 0;
                }
                break;

            case 9:
                System.out.println("🚫 Cancelaram a excursão que todo mundo estava esperando!");
                System.out.println("😭 Por causa de 'problemas no orçamento'...");
                try {
                    diminuirFelicidade(25);
                    System.out.println("💙 Sua felicidade diminuiu em 25 pontos...");
                } catch (NaoPodeSerTristeException e) {

                    System.out.println("💙 Sua felicidade agora é 0...");
                    felicidade = 0;

                }
                break;

            case 10:
                System.out.println("📱 Descobriram seu crush beijando outra pessoa no intervalo...");
                System.out.println("💔 Lá se foram seus sonhos românticos...");
                try {
                    diminuirFelicidade(15);
                    System.out.println("💙 Sua felicidade diminuiu em 15 pontos...");
                } catch (NaoPodeSerTristeException e) {

                    System.out.println("💙 Sua felicidade agora é 0...");
                    felicidade = 0;

                }
                break;

            case 11:
                System.out.println("📝 Vão ter provas surpresa a semana toda!");
                System.out.println("😰 E você não estudou NADA ainda...");
                try {
                    diminuirFelicidade(18);
                    System.out.println("💙 Sua felicidade diminuiu em 18 pontos...");
                } catch (NaoPodeSerTristeException e) {

                    System.out.println("💙 Sua felicidade agora é 0...");
                    felicidade = 0;

                }
                break;

            case 12:
                System.out.println("🍕 A cantina aumentou o preço do lanche em 50%!");
                System.out.println("💸 Lá se vai sua grana do recreio...");
                try {
                    diminuirFelicidade(12);
                    System.out.println("💙 Sua felicidade diminuiu em 12 pontos...");
                } catch (NaoPodeSerTristeException e) {

                    System.out.println("💙 Sua felicidade agora é 0...");
                    felicidade = 0;

                }
                break;

            case 13:
                System.out.println("🏃‍♂️ Educação física agora vai ter corrida de 2km todo dia!");
                System.out.println("😵 E você mal consegue subir a escada sem ficar sem fôlego...");
                try {
                    diminuirFelicidade(22);
                    System.out.println("💙 Sua felicidade diminuiu em 22 pontos...");
                } catch (NaoPodeSerTristeException e) {

                    System.out.println("💙 Sua felicidade agora é 0...");
                    felicidade = 0;

                }
                break;

            case 14:
                System.out.println("📞 Seus pais foram chamados na escola por causa das suas notas...");
                System.out.println("😱 A conversa em casa hoje vai ser TENSA...");
                try {
                    diminuirFelicidade(25);
                    System.out.println("💙 Sua felicidade diminuiu em 25 pontos...");
                } catch (NaoPodeSerTristeException e) {

                    System.out.println("💙 Sua felicidade agora é 0...");
                    felicidade = 0;

                }
                break;

            default:
                System.out.println("🤐 Nenhuma fofoca interessante hoje...");
                System.out.println("😴 Que dia mais sem graça na escola...");
                break;
        }

        esperaAi(2000);
        System.out.println("\n🎭═══════════════════════════════════════🎭");
        System.out.println("💫 Felicidade atual: " + felicidade + " pontos");
        System.out.println("📚 Fim do dia letivo... até amanhã!");
        System.out.println("🎭═══════════════════════════════════════🎭");
    }

}
