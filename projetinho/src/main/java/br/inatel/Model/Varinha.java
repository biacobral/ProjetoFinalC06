package br.inatel.Model;

public class Varinha {
    private int idSerial;
    private String varinhaCor;
    private String statusVarinha;

    public Varinha(int idSerial, String varinhaCor, String statusVarinha) {
        this.idSerial = idSerial;
        this.varinhaCor = varinhaCor;
        this.statusVarinha = statusVarinha;
    }

    public Varinha(String varinhaCor, String statusVarinha) {
        this.varinhaCor = varinhaCor;
        this.statusVarinha = statusVarinha;
    }
}
