package br.inatel.Model;

import java.util.Random;
import static br.inatel.Model.Util.esperaAi;
public class tribunalDaMagia {
    public static final String[] Consequencias = {
            "SEPARAÇÃO: Você será separado de seu padrinho e esquecerá de tudo que viveu com ele!",
            "CASTIGO MÁGICO: Você perdeu o direito de fazer mais desejos!",
            "PUNIÇÃO SEVERA: Todos os seus desejos anteriores foram DESFEITOS!",
            "CRISTAL QUEBRADO: Sua alma foi SELADA por 100 anos!",
            "BURACO NEGRO MÁGICO: Você foi sugado para o vazio eterno!",
            "MORTE NO VULCÃO: Você foi jogado dentro de um vulcão em atividade!",
            "DISTORÇÃO TEMPORAL: Você ficou preso em um loop infinito de sofrimento!"
    };
    public static int Julgamento(String pedido, String regraViolada, String ondeTa){
        Random rand = new Random();
        int consequencia = rand.nextInt(Consequencias.length);
        esperaAi(2000);
        System.out.println("Seja bem vindo ao Tribunal da Magia.");
        esperaAi(2000);
        System.out.println("Você está aqui porque você pediu que lhe fosse concedido o seguinte desejo: ");
        esperaAi(2000);
        System.out.println(">" + pedido + "<");
        esperaAi(2000);
        System.out.println("Dado seu pedido, a seguinte regra foi violada no trecho: ");
        esperaAi(2000);
        System.out.println(">" + regraViolada + "<");
        esperaAi(2000);
        System.out.println(">" + ondeTa + "<");
        esperaAi(2000);
        System.out.println("Assim sendo, o Tribunal das Fadas irá realizar seu julgamento!");
        esperaAi(2000);
        System.out.println("Após análise do que foi cometido, a seguinte penalidade será imposta:");
        esperaAi(2000);
        System.out.println(Consequencias[consequencia]);
        return consequencia;
    }
}
