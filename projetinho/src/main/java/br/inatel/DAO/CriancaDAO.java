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

    // Método DELETE - Remover criança por nome
    public boolean deleteCrianca(String nomeCrianca) {
        connectToDb();
        String sql = "DELETE FROM Crianca WHERE nomeCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeCrianca);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("👋 Criança " + nomeCrianca + " saiu do mundo mágico!");
                return true;
            } else {
                System.out.println("🔍 Criança " + nomeCrianca + " não foi encontrada no mundo mágico!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao remover criança: " + exc.getMessage());
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

    // Método DELETE por ID (alternativa)
    public boolean deleteCriancaById(int idCrianca) {
        connectToDb();
        String sql = "DELETE FROM Crianca WHERE idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("👋 Criança com ID " + idCrianca + " saiu do mundo mágico!");
                return true;
            } else {
                System.out.println("🔍 Criança com ID " + idCrianca + " não foi encontrada no mundo mágico!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao remover criança: " + exc.getMessage());
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

    // Método UPDATE - Atualizar dados da criança
    public boolean updateCrianca(int idCrianca, Crianca criancaAtualizada) {
        connectToDb();
        String sql = "UPDATE Crianca SET nomeCrianca = ?, idadeCrianca = ?, sexoCrianca = ?, temPadrinho = ?, enderecoCrianca = ? WHERE idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, criancaAtualizada.getNomeCrianca());
            pst.setInt(2, criancaAtualizada.getIdadeCrianca());
            pst.setString(3, criancaAtualizada.getSexoCrianca());
            pst.setBoolean(4, criancaAtualizada.getTemPadrinho());
            pst.setString(5, criancaAtualizada.getEnderecoCrianca());
            pst.setInt(6, idCrianca);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Dados da criança " + criancaAtualizada.getNomeCrianca() + " foram atualizados no mundo mágico!");
                return true;
            } else {
                System.out.println("🔍 Criança com ID " + idCrianca + " não foi encontrada para atualização!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao atualizar criança: " + exc.getMessage());
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

    // Método UPDATE específico - Atualizar apenas se tem padrinho
    public boolean updateTemPadrinho(int idCrianca, boolean temPadrinho) {
        connectToDb();
        String sql = "UPDATE Crianca SET temPadrinho = ? WHERE idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setBoolean(1, temPadrinho);
            pst.setInt(2, idCrianca);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                String mensagem = temPadrinho ? "🧚‍♀️ Criança ganhou um padrinho mágico!" : "💔 Criança perdeu seu padrinho mágico!";
                System.out.println(mensagem);
                return true;
            } else {
                System.out.println("🔍 Criança com ID " + idCrianca + " não foi encontrada!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao atualizar status do padrinho: " + exc.getMessage());
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
