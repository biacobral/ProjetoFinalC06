package br.inatel.DAO;

import java.util.ArrayList;
import br.inatel.Model.Personagens.Crianca;
import java.sql.*;

public class CriancaDAO extends ConnectionDao{

    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("👶 Conexão estabelecida! Mundo das crianças acessível!");
        } catch (SQLException exc) {
            System.out.println("😢 Erro ao conectar com as crianças: " + exc.getMessage());
        }
    }
/*
private int idCrianca;
    private String nomeCrianca;
    private int idadeCrianca;
    private String sexoCrianca;
    private boolean temPadrinho;
    private String enderecoCrianca;
    public static int felicidade = 0;
 */
    public boolean insertCrianca(Crianca crianca) {
        connectToDb();
        String sql = "INSERT INTO Crianca (nomeCrianca, idadeCrianca, sexoCrianca, temPadrinho, enderecoCrianca) VALUES (?, ?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, crianca.getNomeCrianca());
            pst.setInt(2, crianca.getIdadeCrianca());
            pst.setString(3, crianca.getSexoCrianca());
            pst.setBoolean(4, crianca.getTemPadrinho());
            pst.setString(5, crianca.getEnderecoCrianca());

            pst.execute();
            System.out.println("👶 Nova criança registrada no mundo mágico!");
            return true;

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao registrar criança: " + exc.getMessage());
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

    public ArrayList<Crianca> selectCrianca() {
        connectToDb();

        ArrayList<Crianca> Criancas = new ArrayList<>();
        String sql = "SELECT * FROM Crianca";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de usuários:");
            while (rs.next()) {
                Crianca CriancaAux = new Crianca(rs.getString("nomeCrianca"), rs.getInt("idadeCrianca"), rs.getString("sexoCrianca"), rs.getBoolean("temPadrinho"), rs.getString("enderecoCrianca"));
                System.out.println("Nome: " + CriancaAux.getNomeCrianca());
                System.out.println("--------------------");
                Criancas.add(CriancaAux);
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
        return Criancas;
    }

}
