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

    public boolean updateVarinha(int id, Varinha varinha) {
        connectToDb();
        String sql = "UPDATE Varinha SET varinhaCor = ?, statusVarinha = ? WHERE idSerial = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, varinha.getVarinhaCor());
            pst.setString(2, varinha.getStatusVarinha());
            pst.setInt(3, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Varinha reencantada no arsenal mágico!");
                return true;
            } else {
                System.out.println("🔍 Nenhuma varinha encontrada com o ID Serial especificado.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao reencatar varinha: " + exc.getMessage());
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

    public boolean deleteVarinha(int id) {
        connectToDb();
        String sql = "DELETE FROM Varinha WHERE idSerial = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("💔 Varinha removida do arsenal mágico permanentemente!");
                return true;
            } else {
                System.out.println("🔍 Nenhuma varinha encontrada com o ID Serial especificado para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao remover varinha do arsenal: " + exc.getMessage());
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
