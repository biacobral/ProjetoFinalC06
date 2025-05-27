package br.inatel.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Crianca_Faz_DesejosDAO extends ConnectionDao{
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Crianca_Faz_Desejos conectado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}
