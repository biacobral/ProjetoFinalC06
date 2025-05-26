package br.inatel.Model;

public class GeneralFada extends Padrinhos{

    public GeneralFada(int idPadrinhos, String nomePadrinho, String tipoPadrinho, int varinha_idSerial, int crianca_idCrianca) {
        super(idPadrinhos, nomePadrinho, tipoPadrinho, varinha_idSerial, crianca_idCrianca);
    }

    public GeneralFada(String nomePadrinho, String tipoPadrinho, int varinha_idSerial, int crianca_idCrianca) {
        super(nomePadrinho, tipoPadrinho, varinha_idSerial, crianca_idCrianca);
    }
}
