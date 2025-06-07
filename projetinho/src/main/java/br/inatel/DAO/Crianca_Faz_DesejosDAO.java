package br.inatel.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Crianca_Faz_DesejosDAO extends ConnectionDao{
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("⭐ Conexão realizada! Portal de desejos ativo!");
        } catch (SQLException exc) {
            System.out.println("💔 Erro ao conectar portal de desejos: " + exc.getMessage());
        }
    }
    public boolean insertCriancaDesejo(int idCrianca, int idDesejo) {
        connectToDb();
        String sql = "INSERT INTO Crianca_Faz_Desejos (Crianca_idCrianca, Desejos_idDesejos) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);
            pst.setInt(2, idDesejo);

            pst.execute();
            System.out.println("⭐ Desejo vinculado à criança! Portal ativo!");
            return true;

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao ativar portal de desejos: " + exc.getMessage());
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
