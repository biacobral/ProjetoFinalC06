package br.inatel.DAO;
import br.inatel.Model.Personagens.Crianca;
import br.inatel.Model.Personagens.Padrinhos;

import java.sql.*;
import java.util.ArrayList;

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
        String sql = "INSERT INTO Padrinhos (nomePadrinho, tipoPadrinho, Varinha_idSerial, Crianca_idCrianca) VALUES (?, ?, ?, ?)";

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

    public ArrayList<Padrinhos> selectPadrinho() {
        connectToDb();

        ArrayList<Padrinhos> Padrinhos = new ArrayList<>();
        String sql = "SELECT * FROM Padrinhos";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de usuários:");
            while (rs.next()) {
                Padrinhos PadrinhosAux = new Padrinhos(rs.getString("nomePadrinho"), rs.getString("tipoPadrinho"), rs.getInt("Varinha_idSerial"), rs.getInt("Crianca_idCrianca"));
                System.out.println("Nome: " + PadrinhosAux.getNomeFada());
                System.out.println("--------------------");
                Padrinhos.add(PadrinhosAux);
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
        return Padrinhos;
    }
}
