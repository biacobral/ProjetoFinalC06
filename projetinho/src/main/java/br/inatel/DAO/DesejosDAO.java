package br.inatel.DAO;
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

    public boolean insertDesejo(Desejo desejo) {
        connectToDb();
        String sql = "INSERT INTO Desejos (descricaoDesejo, tipoDesejo, urgenciaDesejo, dataDesejo) VALUES (?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, desejo.getDescricaoDesejo());
            pst.setString(2, desejo.getTipoDesejo());
            pst.setInt(3, desejo.getUrgenciaDesejo());
            pst.setDate(4, new java.sql.Date(desejo.getDataDesejo().getTime()));

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
}
