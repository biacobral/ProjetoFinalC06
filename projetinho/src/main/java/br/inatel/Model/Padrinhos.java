package br.inatel.Model;

public class Padrinhos {
    int idPadrinhos;
    String nomePadrinho;
    String tipoPadrinho;
    int Varinha_idSerial;
    int Crianca_idCrianca;

    public Padrinhos(int idPadrinhos, String nomePadrinho, String tipoPadrinho, int varinha_idSerial, int crianca_idCrianca) {
        this.idPadrinhos = idPadrinhos;
        this.nomePadrinho = nomePadrinho;
        this.tipoPadrinho = tipoPadrinho;
        Varinha_idSerial = varinha_idSerial;
        Crianca_idCrianca = crianca_idCrianca;
    }

    public Padrinhos(String nomePadrinho, String tipoPadrinho, int varinha_idSerial, int crianca_idCrianca) {
        this.nomePadrinho = nomePadrinho;
        this.tipoPadrinho = tipoPadrinho;
        Varinha_idSerial = varinha_idSerial;
        Crianca_idCrianca = crianca_idCrianca;
    }

    public int getIdPadrinhos() {
        return idPadrinhos;
    }

    public String getNomePadrinho() {
        return nomePadrinho;
    }

    public String getTipoPadrinho() {
        return tipoPadrinho;
    }

    public int getVarinha_idSerial() {
        return Varinha_idSerial;
    }

    public int getCrianca_idCrianca() {
        return Crianca_idCrianca;
    }

    public void setCrianca_idCrianca(int crianca_idCrianca) {
        Crianca_idCrianca = crianca_idCrianca;
    }
}
