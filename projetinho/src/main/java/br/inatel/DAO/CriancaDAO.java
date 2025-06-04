package br.inatel.DAO;

import java.sql.*;

public class CriancaDAO extends ConnectionDao{

    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Criança conectado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
    
}
