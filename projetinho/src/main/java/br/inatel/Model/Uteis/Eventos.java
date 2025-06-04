package br.inatel.Model.Uteis;

import br.inatel.Model.Personagens.*;
import br.inatel.Model.Personagens.Crianca;
import br.inatel.Model.Personagens.Fada;
import br.inatel.Model.Personagens.Padrinhos;

import java.util.Random;

import static br.inatel.Model.Personagens.Desejos.felicidade;
import static br.inatel.Model.Uteis.Util.diminuirFelicidade;
import static br.inatel.Model.Uteis.Util.esperaAi;

public class Eventos {
    /*
    1. Anti-Fada pega a varinha do padrinho -> sem desejos naquele ano
    2. Duelo de desejos -> criança vs criança
    3. Dia com a Vicky -felicidade
    4. Fofoca na escola sobre você -felicidade
    5. Escolher alguem para chamar para o baile da escola -> pedido extra
    6. Duelo AntiFada vs Padrinho
     */
    private final String[] eventos = {
            "Anti-fadas",
            "Duelo de desejos",
            "Dia com a Vicky",
            "Fofoca na escola sobre você",
            "Baile da escola",
            "Duelo"
    };

    public static void decidirEvento(Fada antiFada, Padrinhos padrinho, Crianca crianca1, Crianca crianca2) {
        Random random = new Random();
        int decisao = random.nextInt(6);
        decisao = 3;
        switch (decisao) {
            case 0:
                combateFada(antiFada, padrinho);
                break;
            case 1:
                dueloDesejos();
                break;
            case 2:
                vicky();
                break;
            case 3:
                fofoca();
                break;
            case 4:
                baileEscola();
                break;
            case 5:
                combateCrianca(crianca1, crianca2);
        }
    }

    //Anti-fadas
    private static void combateFada(Fada antiFada, Padrinhos padrinho) {

    }

    //Duelo de desejos
    private static void dueloDesejos() {

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
        System.out.println("👹        A CHEGADA DO TERROR           👹");
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
        felicidade -= 5;
        esperaAi(1500);
        System.out.println("😭 Felicidade atual: " + felicidade + " pontos");
        esperaAi(1000);
        System.out.println("🌩️  As nuvens se formam... a tempestade se aproxima...");
        System.out.println("⚡ Que os jogos... COMECEM! ⚡");
    }

    //Fofoca
    private static void fofoca() {
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
                } catch (NaoPodeSerTriste e) {
                    System.out.println(e);
                    System.out.println("💙 Sua felicidade agora é 0...");
                    felicidade = 0;
                }
                break;

            case 9:
                System.out.println("🚫 Cancelaram a excursão que todo mundo estava esperando!");
                System.out.println("😭 Por causa de 'problemas orçamentários'...");
                try {
                    diminuirFelicidade(25);
                    System.out.println("💙 Sua felicidade diminuiu em 25 pontos...");
                } catch (NaoPodeSerTriste e) {
                    System.out.println(e);
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
                } catch (NaoPodeSerTriste e) {
                    System.out.println(e);
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
                } catch (NaoPodeSerTriste e) {
                    System.out.println(e);
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
                } catch (NaoPodeSerTriste e) {
                    System.out.println(e);
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
                } catch (NaoPodeSerTriste e) {
                    System.out.println(e);
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
                } catch (NaoPodeSerTriste e) {
                    System.out.println(e);
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

    //Baile da escola
    private static void baileEscola() {

    }

    //Duelo
    private static void combateCrianca(Crianca crianca1, Crianca crianca2) {

    }
}
