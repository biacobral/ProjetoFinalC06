package br.inatel.Model;

public class Padrinhos extends Fada {

    int Crianca_idCrianca;

    public Padrinhos(int idFada, String nomeFada, String tipoFada, int varinha_idSerial) {
        super(idFada, nomeFada, tipoFada, varinha_idSerial);

    }

    public Padrinhos(String nomeFada, String tipoFada, int varinha_idSerial) {
        super(nomeFada, tipoFada, varinha_idSerial);
    }

    public int getCrianca_idCrianca() {
        return Crianca_idCrianca;
    }

    public void setCrianca_idCrianca(int crianca_idCrianca) {
        Crianca_idCrianca = crianca_idCrianca;
    }
}
