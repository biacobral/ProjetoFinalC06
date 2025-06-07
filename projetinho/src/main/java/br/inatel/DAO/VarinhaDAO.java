package br.inatel.DAO;
import br.inatel.Model.Personagens.Varinha;

import java.sql.*;

public class VarinhaDAO extends ConnectionDao{
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🪄 Conexão mágica! Arsenal de varinhas acessível!");
        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao acessar arsenal de varinhas: " + exc.getMessage());
        }
    }

    public boolean insertVarinha(Varinha varinha) {
        connectToDb();
        String sql = "INSERT INTO Varinha (varinhaCor, statusVarinha) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, varinha.getVarinhaCor());
            pst.setString(2, varinha.getStatusVarinha());

            pst.execute();
            System.out.println("🪄 Nova varinha forjada no arsenal mágico!");
            return true;

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao forjar varinha: " + exc.getMessage());
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
