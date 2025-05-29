package br.inatel.Model;

import java.util.Random;

public class Desejos {
    private int idDesejos;
    private String descricao;
    private boolean statusDesejo;
    static int felicidade = 0;
    Random rand = new Random();
    public final static String[] listaDesejos = {
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
    };//Deixei como final pq eu quero q seja publico pro menu acessar, mas não quer que haja nenhuma modificação dentro


    public Desejos(int idDesejos, String descricao, boolean statusDesejo) {
        this.idDesejos = idDesejos;
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }

    public Desejos(String descricao, boolean statusDesejo) {
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }

    //Pra saber se o pedido vai ser realizado ou negado
    public boolean realizar(){
        statusDesejo = rand.nextBoolean();
        return statusDesejo;
    }

}
