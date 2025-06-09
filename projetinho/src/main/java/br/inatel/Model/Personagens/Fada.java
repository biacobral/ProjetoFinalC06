package br.inatel.Model.Personagens;

public abstract class Fada {
    protected int idFada;
    protected String nomeFada;
    protected String tipoFada;
    protected int Varinha_idSerial;

    public Fada(int idFada, String nomeFada, String tipoFada, int varinha_idSerial) {
        this.idFada = idFada;
        this.nomeFada = nomeFada;
        this.tipoFada = tipoFada;
        Varinha_idSerial = varinha_idSerial;
    }

    public Fada(String nomeFada, String tipoFada, int varinha_idSerial) {
        this.nomeFada = nomeFada;
        this.tipoFada = tipoFada;
        Varinha_idSerial = varinha_idSerial;
    }

    public int getIdFada() {
        return idFada;
    }

    public String getNomeFada() {
        return nomeFada;
    }

    public String getTipoFada() {
        return tipoFada;
    }

    public int getVarinha_idSerial() {
        return Varinha_idSerial;
    }

}
