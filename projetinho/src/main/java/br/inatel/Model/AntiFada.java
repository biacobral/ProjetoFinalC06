package br.inatel.Model;

public class AntiFada extends Fada{

    public AntiFada(int idFada, String nomeFada, String tipoFada, int varinha_idSerial) {
        super(idFada, nomeFada, tipoFada, varinha_idSerial);
    }

    public AntiFada(String nomeFada, String tipoFada, int varinha_idSerial) {
        super(nomeFada, tipoFada, varinha_idSerial);
    }


}
