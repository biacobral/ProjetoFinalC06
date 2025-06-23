package br.inatel.DAO;
import br.inatel.Model.Personagens.GeneralFada;

import java.sql.*;
public class GeneralFadaDAO extends ConnectionDao{
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🎖️ Conexão militar estabelecida! General das Fadas em comando!");
        } catch (SQLException exc) {
            System.out.println("⚠️ Erro na comunicação com o General: " + exc.getMessage());
        }
    }

    public boolean insertGeneralFada(GeneralFada general) {
        connectToDb();
        String sql = "INSERT INTO GeneralFada (nomeFada, tipoFada, Varinha_idSerial) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, general.getNomeFada());
            pst.setString(2, general.getTipoFada());
            pst.setInt(3, general.getVarinha_idSerial());

            pst.execute();
            System.out.println("🎖️ Novo General promovido no Alto Comando das Fadas!");
            return true;

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro na promoção do General: " + exc.getMessage());
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

    public boolean updateGeneralFada(int id, GeneralFada general) {
        connectToDb();
        String sql = "UPDATE GeneralFada SET nomeFada = ?, tipoFada = ?, Varinha_idSerial = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, general.getNomeFada());
            pst.setString(2, general.getTipoFada());
            pst.setInt(3, general.getVarinha_idSerial());
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚡ General atualizado no Alto Comando das Fadas!");
                return true;
            } else {
                System.out.println("🔍 Nenhum General encontrado com o ID especificado.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao atualizar dados do General: " + exc.getMessage());
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

    public boolean deleteGeneralFada(int id) {
        connectToDb();
        String sql = "DELETE FROM GeneralFada WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🎖️ General removido do Alto Comando das Fadas!");
                return true;
            } else {
                System.out.println("🔍 Nenhum General encontrado com o ID especificado para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao dispensar General do comando: " + exc.getMessage());
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
