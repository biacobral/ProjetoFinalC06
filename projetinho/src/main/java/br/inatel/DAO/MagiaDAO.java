package br.inatel.DAO;
import br.inatel.Model.Personagens.Magia;

import java.sql.*;

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
}
