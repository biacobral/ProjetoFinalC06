package br.inatel.DAO;

import br.inatel.Model.Personagens.Varinha;

import java.sql.*;
import java.util.ArrayList;

public class VarinhaDAO extends ConnectionDao {
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🪄 Conexão mágica! Arsenal de varinhas acessível!");
        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao acessar arsenal de varinhas: " + exc.getMessage());
        }
    }

    // Método otimizado para mostrar TODOS os atributos de TODAS as varinhas
    public ArrayList<Varinha> selectAllVarinhas() {
        connectToDb();

        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha ORDER BY idSerial";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("🪄✨ === ARSENAL COMPLETO DE VARINHAS MÁGICAS === ✨🪄");
            System.out.println();

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                // Mostrando TODOS os dados de cada varinha
                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor da Varinha: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("═══════════════════════════════════════════");
                System.out.println();

                varinhas.add(varinha);
            }

            System.out.println("📊 Total de varinhas no arsenal: " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao consultar arsenal completo: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinhas;
    }

    // Método para buscar varinha específica por ID Serial
    public Varinha selectVarinhaById(int idSerial) {
        connectToDb();
        Varinha varinha = null;
        String sql = "SELECT * FROM Varinha WHERE idSerial = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idSerial);
            rs = pst.executeQuery();

            if (rs.next()) {
                varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                System.out.println("🔍 Varinha encontrada no arsenal:");
                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
            } else {
                System.out.println("🔍 Nenhuma varinha encontrada com ID Serial: " + idSerial);
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinha por ID: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinha;
    }

    // Método para buscar varinhas por cor
    public ArrayList<Varinha> selectVarinhasByCor(String cor) {
        connectToDb();
        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha WHERE varinhaCor = ? ORDER BY idSerial";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cor);
            rs = pst.executeQuery();

            System.out.println("🎨 Varinhas da cor: " + cor);
            System.out.println();

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("─────────────────────────────────────────");

                varinhas.add(varinha);
            }

            System.out.println("📊 Total de varinhas " + cor + " encontradas: " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinhas por cor: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinhas;
    }

    // Método para buscar varinhas por status
    public ArrayList<Varinha> selectVarinhasByStatus(String status) {
        connectToDb();
        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha WHERE statusVarinha = ? ORDER BY idSerial";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, status);
            rs = pst.executeQuery();

            System.out.println("⚡ Varinhas com status: " + status);
            System.out.println();

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("─────────────────────────────────────────");

                varinhas.add(varinha);
            }

            System.out.println("📊 Total de varinhas com status '" + status + "': " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinhas por status: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinhas;
    }

    // Método para buscar varinhas disponíveis (não atribuídas)
    public ArrayList<Varinha> selectVarinhasDisponiveis() {
        connectToDb();
        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha WHERE statusVarinha = 'Disponível' ORDER BY varinhaCor, idSerial";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🆓 Varinhas Disponíveis no Arsenal:");
            System.out.println();

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("─────────────────────────────────────────");

                varinhas.add(varinha);
            }

            System.out.println("📊 Total de varinhas disponíveis: " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinhas disponíveis: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinhas;
    }

    // Método para mostrar inventário resumido
    public void showInventarioResumo() {
        connectToDb();
        String sql = "SELECT idSerial, varinhaCor FROM Varinha ORDER BY varinhaCor, idSerial";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📋 Inventário Resumido do Arsenal:");
            System.out.println("═══════════════════════════════════");

            while (rs.next()) {
                System.out.println("🔢 " + rs.getInt("idSerial") + " - 🎨 " + rs.getString("varinhaCor"));
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao gerar inventário resumido: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

    // Método para mostrar estatísticas do arsenal
    public void showEstatisticasArsenal() {
        connectToDb();

        try {
            // Contar total de varinhas
            String sqlTotal = "SELECT COUNT(*) as total FROM Varinha";
            st = con.createStatement();
            rs = st.executeQuery(sqlTotal);

            if (rs.next()) {
                System.out.println("📊 ESTATÍSTICAS DO ARSENAL MÁGICO");
                System.out.println("═══════════════════════════════════════");
                System.out.println("🪄 Total de Varinhas: " + rs.getInt("total"));
            }

            // Contar por cor
            String sqlPorCor = "SELECT varinhaCor, COUNT(*) as quantidade FROM Varinha GROUP BY varinhaCor ORDER BY quantidade DESC";
            rs = st.executeQuery(sqlPorCor);

            System.out.println("\n🎨 Varinhas por Cor:");
            while (rs.next()) {
                System.out.println("   " + rs.getString("varinhaCor") + ": " + rs.getInt("quantidade"));
            }

            // Contar por status
            String sqlPorStatus = "SELECT statusVarinha, COUNT(*) as quantidade FROM Varinha GROUP BY statusVarinha";
            rs = st.executeQuery(sqlPorStatus);

            System.out.println("\n⚡ Varinhas por Status:");
            while (rs.next()) {
                System.out.println("   " + rs.getString("statusVarinha") + ": " + rs.getInt("quantidade"));
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao gerar estatísticas do arsenal: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

    // Método para buscar varinhas em uso (com padrinho)
    public void selectVarinhasEmUso() {
        connectToDb();
        String sql = """
                    SELECT v.idSerial, v.varinhaCor, v.statusVarinha, p.nomePadrinho 
                    FROM Varinha v 
                    INNER JOIN Padrinhos p ON v.idSerial = p.Varinha_idSerial
                    ORDER BY v.idSerial
                """;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🧚‍♀️ Varinhas Atualmente em Uso:");
            System.out.println("═══════════════════════════════════════");

            while (rs.next()) {
                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("🧚‍♀️ Padrinho: " + rs.getString("nomePadrinho"));
                System.out.println("─────────────────────────────────────────");
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinhas em uso: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

    // Métodos originais mantidos para compatibilidade
    public boolean insertVarinha(Varinha varinha) {
        connectToDb();
        String sql = "INSERT INTO Varinha (varinhaCor, statusVarinha) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, varinha.getVarinhaCor());
            pst.setString(2, varinha.getStatusVarinha());

            pst.execute();
            System.out.println("🪄 Nova varinha forjada no arsenal mágico!");
            return true;

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao forjar varinha: " + exc.getMessage());
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

    public boolean updateVarinha(int id, Varinha varinha) {
        connectToDb();
        String sql = "UPDATE Varinha SET varinhaCor = ?, statusVarinha = ? WHERE idSerial = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, varinha.getVarinhaCor());
            pst.setString(2, varinha.getStatusVarinha());
            pst.setInt(3, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Varinha reencantada no arsenal mágico!");
                return true;
            } else {
                System.out.println("🔍 Nenhuma varinha encontrada com o ID Serial especificado.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao reencatar varinha: " + exc.getMessage());
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

    public boolean deleteVarinha(int id) {
        connectToDb();
        String sql = "DELETE FROM Varinha WHERE idSerial = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("💔 Varinha removida do arsenal mágico permanentemente!");
                return true;
            } else {
                System.out.println("🔍 Nenhuma varinha encontrada com o ID Serial especificado para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao remover varinha do arsenal: " + exc.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return false;
    }

    // Método original renomeado para compatibilidade
    public ArrayList<Varinha> selectVarinha() {
        return selectAllVarinhas();
    }
}