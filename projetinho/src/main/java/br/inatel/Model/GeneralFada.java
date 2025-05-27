package br.inatel.Model;

public class GeneralFada extends Fada{

    public GeneralFada(int idFada, String nomeFada, String tipoFada, int varinha_idSerial) {
        super(idFada, nomeFada, tipoFada, varinha_idSerial);
    }

    public GeneralFada(String nomeFada, String tipoFada, int varinha_idSerial) {
        super(nomeFada, tipoFada, varinha_idSerial);
    }
}
