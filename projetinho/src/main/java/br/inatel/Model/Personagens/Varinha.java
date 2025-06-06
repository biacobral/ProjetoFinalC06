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

    public Varinha(String varinhaCor, String statusVarinha) {
        this.varinhaCor = varinhaCor;
        this.statusVarinha = statusVarinha;
    }

    private boolean podeFazerDesejo(Varinha varinha) {
        if(varinha.statusVarinha.equals("Funcionando")) {
            return true;
        }
        return false;
    }


    public int getIdSerial() {
        return idSerial;
    }

    public void setIdSerial(int idSerial) {
        this.idSerial = idSerial;
    }

    public String getVarinhaCor() {
        return varinhaCor;
    }

    public void setVarinhaCor(String varinhaCor) {
        this.varinhaCor = varinhaCor;
    }

    public String getStatusVarinha() {
        return statusVarinha;
    }

    public void setStatusVarinha(String statusVarinha) {
        this.statusVarinha = statusVarinha;
    }
}
