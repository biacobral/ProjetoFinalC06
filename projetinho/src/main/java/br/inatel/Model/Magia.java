package br.inatel.Model;

public class Magia {
    private int idMagia;
    private String nomeMagia;
    private String descricaoMagia;
    private int Padrinhos_idPadrinhos;

    public Magia(int idMagia, String nomeMagia, String descricaoMagia, int padrinhos_idPadrinhos) {
        this.idMagia = idMagia;
        this.nomeMagia = nomeMagia;
        this.descricaoMagia = descricaoMagia;
        Padrinhos_idPadrinhos = padrinhos_idPadrinhos;
    }

    public Magia(String nomeMagia, String descricaoMagia, int padrinhos_idPadrinhos) {
        this.nomeMagia = nomeMagia;
        this.descricaoMagia = descricaoMagia;
        Padrinhos_idPadrinhos = padrinhos_idPadrinhos;
    }
}
