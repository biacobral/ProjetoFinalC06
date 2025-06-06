package br.inatel.Model.Personagens;

public class Crianca {
    private int idCrianca;
    private String nomeCrianca;
    private int idadeCrianca;
    private String sexoCrianca;
    private boolean temPadrinho;
    private String enderecoCrianca;
    public static int felicidade = 0;

    public Crianca(int idCrianca, String nomeCrianca, int idadeCrianca, String sexoCrianca, boolean temPadrinho, String enderecoCrianca) {
        this.idCrianca = idCrianca;
        this.nomeCrianca = nomeCrianca;
        this.idadeCrianca = idadeCrianca;
        this.sexoCrianca = sexoCrianca;
        this.temPadrinho = temPadrinho;
        this.enderecoCrianca = enderecoCrianca;
    }

    public Crianca(String nomeCrianca, int idadeCrianca, String sexoCrianca, boolean temPadrinho, String enderecoCrianca) {
        this.nomeCrianca = nomeCrianca;
        this.idadeCrianca = idadeCrianca;
        this.sexoCrianca = sexoCrianca;
        this.temPadrinho = temPadrinho;
        this.enderecoCrianca = enderecoCrianca;
    }

    public int getIdCrianca() {
        return idCrianca;
    }

    public void setIdCrianca(int idCrianca) {
        this.idCrianca = idCrianca;
    }

    public String getNomeCrianca() {
        return nomeCrianca;
    }

    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public int getIdadeCrianca() {
        return idadeCrianca;
    }

    public void setIdadeCrianca(int idadeCrianca) {
        this.idadeCrianca = idadeCrianca;
    }

    public String getSexoCrianca() {
        return sexoCrianca;
    }

    public void setSexoCrianca(String sexoCrianca) {
        this.sexoCrianca = sexoCrianca;
    }

    public boolean isTemPadrinho() {
        return temPadrinho;
    }

    public void setTemPadrinho(boolean temPadrinho) {
        this.temPadrinho = temPadrinho;
    }

    public String getEnderecoCrianca() {
        return enderecoCrianca;
    }

    public void setEnderecoCrianca(String enderecoCrianca) {
        this.enderecoCrianca = enderecoCrianca;
    }

    public boolean getTemPadrinho() {return temPadrinho;}

}
