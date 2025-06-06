package br.inatel.Model.Personagens;

public class Padrinhos extends Fada {

    private int Crianca_idCrianca;
    private Varinha varinha;

    // Construtor com objeto Varinha
    public Padrinhos(int idFada, String nomeFada, String tipoFada, Varinha varinha) {
        super(idFada, nomeFada, tipoFada, varinha.getIdSerial());
        this.varinha = varinha;
    }

    public Padrinhos(String nomeFada, String tipoFada, Varinha varinha) {
        super(nomeFada, tipoFada, varinha.getIdSerial());
        this.varinha = varinha;
    }

    public int getCrianca_idCrianca() {
        return Crianca_idCrianca;
    }

    public void setCrianca_idCrianca(int crianca_idCrianca) {
        Crianca_idCrianca = crianca_idCrianca;
    }

    public Varinha getVarinha() {
        return varinha;
    }

    public void setVarinha(Varinha varinha) {
        this.varinha = varinha;
    }
}
