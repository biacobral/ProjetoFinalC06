package br.inatel.Model;

import java.util.Random;

public class Desejos {
    private int idDesejos;
    private String descricao;
    private boolean statusDesejo;
    private static int felicidade = 0;
    Random rand = new Random();
    public static String[] listaDesejos = {
        "Quero lanche infinito...",
        "Quero virar heroi...",
        "Quero ferias infinitas...",
        "Quero voar...",
        "Quero um castelo...",
        "Quero ser o mais inteligente da escola...",
        "Quero ser um Padrinho...",
        "Quero um robô ajudante...",
        "Quero entender as meninas...",
        "Quero ter superpoderes...",
        "Quero um cachorro falante...",
        "Quero que as meninas se apaixonem por mim..."
    };


    public Desejos(int idDesejos, String descricao, boolean statusDesejo) {
        this.idDesejos = idDesejos;
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }

    public Desejos(String descricao, boolean statusDesejo) {
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }

    //8
    public boolean robo(){
        System.out.println("8. Quero um robô ajudante...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    //9
    public boolean entender(){
        System.out.println("9. Quero entender as meninas...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    //10
    public boolean superpoderes(){
        System.out.println("10. Quero ter superpoderes...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    //11
    public boolean cachorro(){
        System.out.println("11. Quero um cachorro falante...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    //12
    public boolean apaixonar(){
        //proibido
        System.out.println("Quero que as meninas se apaixonem por mim...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

}
