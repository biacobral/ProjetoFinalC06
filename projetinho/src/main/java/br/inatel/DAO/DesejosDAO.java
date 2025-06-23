package br.inatel.DAO;
import br.inatel.Model.Personagens.Desejos;

import java.sql.*;

public class DesejosDAO extends ConnectionDao{
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🌟 Conexão mágica estabelecida! Livro de desejos aberto!");
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao abrir livro de desejos: " + exc.getMessage());
        }
    }

    public boolean insertDesejo(Desejos desejo) {
        connectToDb();
        String sql = "INSERT INTO Desejos (descricao, statusDesejo) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, desejo.getDescricao());
            pst.setBoolean(2, desejo.isStatusDesejo());

            pst.execute();
            System.out.println("🌟 Novo desejo registrado no livro mágico!");
            return true;

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao registrar desejo: " + exc.getMessage());
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

    public boolean updateDesejo(int id, Desejos desejo) {
        connectToDb();
        String sql = "UPDATE Desejos SET descricao = ?, statusDesejo = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, desejo.getDescricao());
            pst.setBoolean(2, desejo.isStatusDesejo());
            pst.setInt(3, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Desejo atualizado com sucesso no livro mágico!");
                return true;
            } else {
                System.out.println("📝 Nenhum desejo encontrado com o ID fornecido.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao atualizar desejo: " + exc.getMessage());
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

    public boolean deleteDesejo(int id) {
        connectToDb();
        String sql = "DELETE FROM Desejos WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🗑️ Desejo removido do livro mágico com sucesso!");
                return true;
            } else {
                System.out.println("📝 Nenhum desejo encontrado com o ID fornecido para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao remover desejo: " + exc.getMessage());
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