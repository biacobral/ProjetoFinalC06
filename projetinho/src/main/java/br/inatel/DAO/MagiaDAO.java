package br.inatel.DAO;
import br.inatel.Model.Personagens.Magia;

import java.sql.*;
import java.util.ArrayList;

public class MagiaDAO extends ConnectionDao{
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("✨ Conexão encantada! Fluxo mágico ativado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("💥 Erro no fluxo mágico: " + exc.getMessage());
        }
    }

    public boolean insertMagia(Magia magia) {
        connectToDb();
        String sql = "INSERT INTO Magia (nomeMagia, descricaoMagia, Padrinhos_idPadrinhos) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, magia.getNomeMagia());
            pst.setString(2, magia.getDescricaoMagia());
            pst.setInt(3, magia.getPadrinhos_idPadrinhos());

            pst.execute();
            System.out.println("✨ Nova magia inscrita no grimório!");
            return true;

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao inscrever magia: " + exc.getMessage());
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

    public ArrayList<Magia> selectMagia() {
        connectToDb();

        ArrayList<Magia> MagiasExistentes = new ArrayList<>();
        String sql = "SELECT * FROM Magia";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de usuários:");
            while (rs.next()) {
                Magia MagiasAux = new Magia(rs.getString("nomeMagia"), rs.getString("descricaoMagia"), rs.getInt("Padrinhos_idPadrinhos"));
                System.out.println("--------------------");
                System.out.println("Nome: " + MagiasAux.getNomeMagia());
                MagiasExistentes.add(MagiasAux);
            }
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        } finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return MagiasExistentes;
    }

    public boolean updateMagia(int id, Magia magia) {
        connectToDb();
        String sql = "UPDATE Magia SET nomeMagia = ?, descricaoMagia = ?, Padrinhos_idPadrinhos = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, magia.getNomeMagia());
            pst.setString(2, magia.getDescricaoMagia());
            pst.setInt(3, magia.getPadrinhos_idPadrinhos());
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🔮 Magia reformulada no grimório com sucesso!");
                return true;
            } else {
                System.out.println("📜 Nenhuma magia encontrada com o ID especificado.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao reformular magia: " + exc.getMessage());
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

    public boolean deleteMagia(int id) {
        connectToDb();
        String sql = "DELETE FROM Magia WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🗑️ Magia apagada do grimório permanentemente!");
                return true;
            } else {
                System.out.println("📜 Nenhuma magia encontrada com o ID especificado para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao apagar magia do grimório: " + exc.getMessage());
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
