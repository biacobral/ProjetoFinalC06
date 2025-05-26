package br.inatel.Model;

public class Desejos {
    private int idDesejos;
    private String descricao;
    private String statusDesejo;

    public Desejos(int idDesejos, String descricao, String statusDesejo) {
        this.idDesejos = idDesejos;
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }

    public Desejos(String descricao, String statusDesejo) {
        this.descricao = descricao;
        this.statusDesejo = statusDesejo;
    }
}
