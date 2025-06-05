package br.inatel.DAO;

import java.util.ArrayList;
import br.inatel.Model.Personagens.Crianca;
import java.sql.*;

public class CriancaDAO extends ConnectionDao{

    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Criança conectado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
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
