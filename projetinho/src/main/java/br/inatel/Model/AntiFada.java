package br.inatel.Model;

public class AntiFada extends Padrinhos{

    public AntiFada(int idPadrinhos, String nomePadrinho, String tipoPadrinho, int varinha_idSerial, int crianca_idCrianca) {
        super(idPadrinhos, nomePadrinho, tipoPadrinho, varinha_idSerial, crianca_idCrianca);
    }

    public AntiFada(String nomePadrinho, String tipoPadrinho, int varinha_idSerial, int crianca_idCrianca) {
        super(nomePadrinho, tipoPadrinho, varinha_idSerial, crianca_idCrianca);
    }
}
