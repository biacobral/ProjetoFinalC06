package br.inatel.Model.Personagens;

public class Desejos {
    private int idDesejos;
    private String descricao;
    private boolean statusDesejo;

    public final static String[] listaDesejos = {
        "Quero lanche infinito...",
        "Quero virar heroi...",
        "Quero ferias infinitas...",
        "Quero voar...",
        "Quero um castelo...",
        "Quero ser o mais inteligente da escola...",
        "Quero um chocolate quente...",
        "Quero um robô ajudante...",
        "Quero entender as meninas...",
        "Quero ter superpoderes...",
        "Quero um cachorro falante...",
        "Quero que dinossauros voltem a existir...",
        "Quero ser famoso mundialmente...",
        "Quero ser popular..."
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

    public String getDescricao() {
        return descricao;
    }

    public boolean isStatusDesejo() {
        return statusDesejo;
    }
}
