package br.inatel.Model;

public abstract class Fada {
    int idFada;
    String nomeFada;
    String tipoFada;
    int Varinha_idSerial;

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

    public void setIdFada(int idFada) {
        this.idFada = idFada;
    }

    public String getNomeFada() {
        return nomeFada;
    }

    public void setNomeFada(String nomeFada) {
        this.nomeFada = nomeFada;
    }

    public String getTipoFada() {
        return tipoFada;
    }

    public void setTipoFada(String tipoFada) {
        this.tipoFada = tipoFada;
    }

    public int getVarinha_idSerial() {
        return Varinha_idSerial;
    }

    public void setVarinha_idSerial(int varinha_idSerial) {
        Varinha_idSerial = varinha_idSerial;
    }
}
