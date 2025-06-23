package br.inatel.DAO;

import br.inatel.Model.Personagens.AntiFada;

import java.sql.DriverManager;
import java.sql.SQLException;

public class AntiFadaDAO extends ConnectionDao{
    // AntiFadaDAO.java
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("⚔️ Conexão estabelecida com sucesso! Anti-Fadas prontas para a batalha!");
        } catch (SQLException exc) {
            System.out.println("❌ Erro na conexão das Anti-Fadas: " + exc.getMessage());
        }
    }

    public boolean insertAntiFada(AntiFada antiFada) {
        connectToDb();
        String sql = "INSERT INTO AntiFada (nomeFada, tipoFada, Varinha_idSerial) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, antiFada.getNomeFada());
            pst.setString(2, antiFada.getTipoFada());
            pst.setInt(3, antiFada.getVarinha_idSerial());

            pst.execute();
            System.out.println("⚔️ Nova Anti-Fada registrada no exército das trevas!");
            return true;

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao registrar Anti-Fada: " + exc.getMessage());
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

    public boolean deleteAntiFada(String nomeFada) {
        connectToDb();
        String sql = "DELETE FROM AntiFada WHERE nomeFada = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeFada);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚔️ Anti-Fada " + nomeFada + " foi banida do exército das trevas!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada " + nomeFada + " não foi encontrada no exército!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao banir Anti-Fada: " + exc.getMessage());
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

    // Alternativa: DELETE por ID (caso você tenha um campo ID)
    public boolean deleteAntiFadaById(int id) {
        connectToDb();
        String sql = "DELETE FROM AntiFada WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚔️ Anti-Fada com ID " + id + " foi banida do exército das trevas!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada com ID " + id + " não foi encontrada no exército!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao banir Anti-Fada: " + exc.getMessage());
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
