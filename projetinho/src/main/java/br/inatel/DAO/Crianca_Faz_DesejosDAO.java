package br.inatel.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Crianca_Faz_DesejosDAO extends ConnectionDao {
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

    public ArrayList<String> selectCriancaDesejos() {
        connectToDb();

        ArrayList<String> relacionamentos = new ArrayList<>();
        String sql = "SELECT * FROM Crianca_Faz_Desejos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("⭐ Consultando portal de desejos:");

            while (rs.next()) {
                int idCrianca = rs.getInt("Crianca_idCrianca");
                int idDesejo = rs.getInt("Desejos_idDesejos");
                String relacionamento = "Criança ID: " + idCrianca + " | Desejo ID: " + idDesejo;

                System.out.println("🌟 " + relacionamento);
                System.out.println("--------------------");
                relacionamentos.add(relacionamento);
            }
            System.out.println("⭐ Total de desejos vinculados: " + relacionamentos.size());

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao consultar portal de desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return relacionamentos;
    }

    public ArrayList<Integer> selectDesejosPorCrianca(int idCrianca) {
        connectToDb();

        ArrayList<Integer> desejos = new ArrayList<>();
        String sql = "SELECT Desejos_idDesejos FROM Crianca_Faz_Desejos WHERE Crianca_idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);
            rs = pst.executeQuery();

            System.out.println("🌟 Consultando desejos da criança ID " + idCrianca + ":");

            while (rs.next()) {
                int idDesejo = rs.getInt("Desejos_idDesejos");
                System.out.println("⭐ Desejo ID: " + idDesejo);
                desejos.add(idDesejo);
            }
            System.out.println("⭐ Total de desejos encontrados: " + desejos.size());

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao buscar desejos da criança: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return desejos;
    }

    public boolean updateCriancaDesejo(int idCriancaAntiga, int idDesejoAntigo, int idCriancaNova, int idDesejoNovo) {
        connectToDb();
        String sql = "UPDATE Crianca_Faz_Desejos SET Crianca_idCrianca = ?, Desejos_idDesejos = ? WHERE Crianca_idCrianca = ? AND Desejos_idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCriancaNova);
            pst.setInt(2, idDesejoNovo);
            pst.setInt(3, idCriancaAntiga);
            pst.setInt(4, idDesejoAntigo);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Portal de desejos reconfigurado com sucesso!");
                return true;
            } else {
                System.out.println("🔍 Relacionamento não encontrado para atualização!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao reconfigurar portal: " + exc.getMessage());
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

    public boolean transferirDesejo(int idDesejoTransferir, int idCriancaOrigem, int idCriancaDestino) {
        connectToDb();
        String sql = "UPDATE Crianca_Faz_Desejos SET Crianca_idCrianca = ? WHERE Desejos_idDesejos = ? AND Crianca_idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCriancaDestino);
            pst.setInt(2, idDesejoTransferir);
            pst.setInt(3, idCriancaOrigem);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🎁 Desejo transferido com sucesso entre crianças!");
                return true;
            } else {
                System.out.println("🔍 Desejo não encontrado para transferência!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao transferir desejo: " + exc.getMessage());
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

    public boolean deleteCriancaDesejo(int idCrianca, int idDesejo) {
        connectToDb();
        String sql = "DELETE FROM Crianca_Faz_Desejos WHERE Crianca_idCrianca = ? AND Desejos_idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);
            pst.setInt(2, idDesejo);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("💫 Desejo desvinculado da criança! Portal fechado!");
                return true;
            } else {
                System.out.println("🔍 Relacionamento não encontrado para remoção!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao desvincular desejo: " + exc.getMessage());
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

    public boolean deleteDesejosPorCrianca(int idCrianca) {
        connectToDb();
        String sql = "DELETE FROM Crianca_Faz_Desejos WHERE Crianca_idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🌟 Todos os desejos da criança ID " + idCrianca + " foram removidos!");
                System.out.println("📊 Total de desejos removidos: " + rowsAffected);
                return true;
            } else {
                System.out.println("🔍 Nenhum desejo encontrado para a criança ID " + idCrianca + "!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao remover desejos da criança: " + exc.getMessage());
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

    public boolean deleteDesejoDeTodas(int idDesejo) {
        connectToDb();
        String sql = "DELETE FROM Crianca_Faz_Desejos WHERE Desejos_idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idDesejo);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("💫 Desejo ID " + idDesejo + " foi removido de todas as crianças!");
                System.out.println("📊 Total de relacionamentos removidos: " + rowsAffected);
                return true;
            } else {
                System.out.println("🔍 Desejo ID " + idDesejo + " não estava vinculado a nenhuma criança!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao remover desejo de todas as crianças: " + exc.getMessage());
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