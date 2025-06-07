package br.inatel.DAO;
import br.inatel.Model.Personagens.Padrinhos;

import java.sql.*;

public class PadrinhosDAO extends ConnectionDao{
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🧚‍♀️ Conexão encantada! Padrinhos Mágicos conectados!");
        } catch (SQLException exc) {
            System.out.println("🚫 Erro na conexão dos Padrinhos: " + exc.getMessage());
        }
    }
    public boolean insertPadrinho(Padrinhos padrinho) {
        connectToDb();
        String sql = "INSERT INTO Padrinhos (nomeFada, tipoFada, Varinha_idSerial, Crianca_idCrianca) VALUES (?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, padrinho.getNomeFada());
            pst.setString(2, padrinho.getTipoFada());
            pst.setInt(3, padrinho.getVarinha_idSerial());
            pst.setInt(4, padrinho.getCrianca_idCrianca());

            pst.execute();
            System.out.println("🧚‍♀️ Novo Padrinho Mágico cadastrado com sucesso!");
            return true;

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao cadastrar Padrinho: " + exc.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }
}
