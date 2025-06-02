package br.inatel.Model;

import java.util.Random;

public class Desejos {
    private int idDesejos;
    private String descricao;
    private boolean statusDesejo;
    public static int felicidade = 0;
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
        "Quero que as pessoas se apaixonem por mim...",
        "Quero ser famoso mundialmente...",
        "Quero que só eu tenha padrinhos mágicos..."
    };//Deixei como final porque eu quero q seja publico para o menu acessar, mas não quer que haja nenhuma modificação externa


    public Desejos(int idDesejos, String descricao, boolean statusDesejo) {
        this.idDesejos = idDesejos;
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }

    public Desejos(String descricao, boolean statusDesejo) {
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }

    public void podeIssoArnaldo(){

    }
}
