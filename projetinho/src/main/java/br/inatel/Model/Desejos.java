package br.inatel.Model;

import java.util.Random;

public class Desejos {
    private int idDesejos;
    private String descricao;
    private boolean statusDesejo;
    private static int felicidade = 0;
    Random rand = new Random();

    public Desejos(int idDesejos, String descricao, boolean statusDesejo) {
        this.idDesejos = idDesejos;
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }

    public Desejos(String descricao, boolean statusDesejo) {
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }
    // 1
    public boolean LancheInfinito(){
        System.out.println("1. Quero lanche infinito...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    // 2
    public boolean VirarHeroi(){
        System.out.println("2. Quero virar heroi...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    // 3
    public boolean Ferias(){
        System.out.println("3. Quero ferias infinitas...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    // 4
    public boolean Voar(){
        System.out.println("4. Quero voar...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    // 5
    public boolean Castelo(){
        System.out.println("5. Quero um castelo...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

    // 6
    public boolean Inteligente(){
        System.out.println("6. Quero ser o mais inteligente da escola...");
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }
    /*
            ('Quero um lanche infinito', 'Concedido'),
            ('Quero virar um super-herói', 'Concedido'),
            ('Quero férias eternas', 'Negado'),
            ('Quero voar', 'Concedido'),
            ('Quero um castelo','Pendente'),
            ('Quero ser o mais inteligente da escola', 'Pendente'),
            ('Quero um robô ajudante', 'Concedido'),
            ('Quero entender as meninas', 'Negado'),
            ('Quero ter superpoderes', 'Concedido'),
            ('Quero um cachorro falante', 'Pendente');
     */
}
