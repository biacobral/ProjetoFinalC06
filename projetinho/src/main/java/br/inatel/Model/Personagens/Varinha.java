package br.inatel.Model.Personagens;

public class Varinha {
    private int idSerial;
    private String varinhaCor;
    private String statusVarinha;

    public Varinha(int idSerial, String varinhaCor, String statusVarinha) {
        this.idSerial = idSerial;
        this.varinhaCor = varinhaCor;
        this.statusVarinha = statusVarinha;
    }

    public int getIdSerial() {
        return idSerial;
    }

    public String getVarinhaCor() {
        return varinhaCor;
    }

    public String getStatusVarinha() {
        return statusVarinha;
    }

    public void setStatusVarinha(String statusVarinha) {
        this.statusVarinha = statusVarinha;
    }
}
