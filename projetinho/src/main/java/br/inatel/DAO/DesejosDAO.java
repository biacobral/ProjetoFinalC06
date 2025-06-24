package br.inatel.DAO;

import br.inatel.Model.Personagens.Desejos;

import java.sql.*;
import java.util.ArrayList;

public class DesejosDAO extends ConnectionDao {
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🌟 Conexão mágica estabelecida! Livro de desejos aberto!");
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao abrir livro de desejos: " + exc.getMessage());
        }
    }

    public boolean insertDesejo(Desejos desejo) {
        connectToDb();
        String sql = "INSERT INTO Desejos (descricao, statusDesejo) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, desejo.getDescricao());
            pst.setBoolean(2, desejo.isStatusDesejo());

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

    public boolean updateDesejo(int id, Desejos desejo) {
        connectToDb();
        String sql = "UPDATE Desejos SET descricao = ?, statusDesejo = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, desejo.getDescricao());
            pst.setBoolean(2, desejo.isStatusDesejo());
            pst.setInt(3, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Desejo atualizado com sucesso no livro mágico!");
                return true;
            } else {
                System.out.println("📝 Nenhum desejo encontrado com o ID fornecido.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao atualizar desejo: " + exc.getMessage());
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

    public boolean deleteDesejo(int id) {
        connectToDb();
        String sql = "DELETE FROM Desejos WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🗑️ Desejo removido do livro mágico com sucesso!");
                return true;
            } else {
                System.out.println("📝 Nenhum desejo encontrado com o ID fornecido para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao remover desejo: " + exc.getMessage());
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

    public ArrayList<Desejos> selectDesejos() {
        connectToDb();

        ArrayList<Desejos> desejos = new ArrayList<>();
        String sql = "SELECT * FROM Desejos";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("🌟 Consultando livro mágico de desejos:");
            while (rs.next()) {
                Desejos desejoAux = new Desejos(
                        rs.getString("descricao"),
                        rs.getBoolean("statusDesejo")
                );
                String status = desejoAux.isStatusDesejo() ? "✅ Realizado" : "⏳ Pendente";
                System.out.println("📝 Desejo: " + desejoAux.getDescricao() + " | Status: " + status);
                System.out.println("--------------------");
                desejos.add(desejoAux);
            }
            System.out.println("🌟 Total de desejos no livro: " + desejos.size());
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao consultar livro de desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return desejos;
    }

    // Método SELECT - Buscar apenas as DESCRIÇÕES de todos os Desejos
    public ArrayList<String> selectAllDescricoesDesejos() {
        connectToDb();

        ArrayList<String> descricoes = new ArrayList<>();
        String sql = "SELECT descricao FROM Desejos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📝 Lista de descrições dos desejos:");
            while (rs.next()) {
                String descricao = rs.getString("descricao");
                descricoes.add(descricao);
                System.out.println("💫 " + descricao);
            }
            System.out.println("🌟 Total de descrições listadas: " + descricoes.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar descrições dos desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return descricoes;
    }

    // Método SELECT - Buscar apenas os STATUS de todos os Desejos
    public ArrayList<Boolean> selectAllStatusDesejos() {
        connectToDb();

        ArrayList<Boolean> status = new ArrayList<>();
        String sql = "SELECT statusDesejo FROM Desejos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("✨ Lista de status dos desejos:");
            while (rs.next()) {
                boolean statusDesejo = rs.getBoolean("statusDesejo");
                status.add(statusDesejo);
                String statusTexto = statusDesejo ? "Realizado ✅" : "Pendente ⏳";
                System.out.println("🎯 " + statusTexto);
            }
            System.out.println("🌟 Total de status listados: " + status.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar status dos desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return status;
    }

    // Método SELECT - Buscar apenas os IDs de todos os Desejos
    public ArrayList<Integer> selectAllIdsDesejos() {
        connectToDb();

        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM Desejos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🆔 Lista de IDs dos desejos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                ids.add(id);
                System.out.println("🔢 ID: " + id);
            }
            System.out.println("🌟 Total de IDs listados: " + ids.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar IDs dos desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return ids;
    }

    // Método SELECT - Buscar apenas DESEJOS REALIZADOS
    public ArrayList<String> selectDesejosRealizados() {
        connectToDb();

        ArrayList<String> desejosRealizados = new ArrayList<>();
        String sql = "SELECT descricao FROM Desejos WHERE statusDesejo = true";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("✅ Lista de desejos realizados:");
            while (rs.next()) {
                String descricao = rs.getString("descricao");
                desejosRealizados.add(descricao);
                System.out.println("🌟 " + descricao);
            }
            System.out.println("✨ Total de desejos realizados: " + desejosRealizados.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar desejos realizados: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return desejosRealizados;
    }

    // Método SELECT - Buscar apenas DESEJOS PENDENTES
    public ArrayList<String> selectDesejosPendentes() {
        connectToDb();

        ArrayList<String> desejosPendentes = new ArrayList<>();
        String sql = "SELECT descricao FROM Desejos WHERE statusDesejo = false";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("⏳ Lista de desejos pendentes:");
            while (rs.next()) {
                String descricao = rs.getString("descricao");
                desejosPendentes.add(descricao);
                System.out.println("💫 " + descricao);
            }
            System.out.println("⌛ Total de desejos pendentes: " + desejosPendentes.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar desejos pendentes: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return desejosPendentes;
    }

    // Método SELECT - Contar desejos por status
    public void contarDesejosPorStatus() {
        connectToDb();

        String sql = "SELECT statusDesejo, COUNT(*) as quantidade FROM Desejos GROUP BY statusDesejo";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📊 Estatísticas do livro de desejos:");
            while (rs.next()) {
                boolean status = rs.getBoolean("statusDesejo");
                int quantidade = rs.getInt("quantidade");

                String statusTexto = status ? "Realizados ✅" : "Pendentes ⏳";
                System.out.println("🎯 " + statusTexto + ": " + quantidade + " desejos");
            }
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao contar desejos por status: " + exc.getMessage());
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

    // Método SELECT - Buscar IDs de desejos realizados
    public ArrayList<Integer> selectIdsDesejosRealizados() {
        connectToDb();

        ArrayList<Integer> idsRealizados = new ArrayList<>();
        String sql = "SELECT id FROM Desejos WHERE statusDesejo = true";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🆔 IDs dos desejos realizados:");
            while (rs.next()) {
                int id = rs.getInt("id");
                idsRealizados.add(id);
                System.out.println("✅ ID: " + id);
            }
            System.out.println("🌟 Total de IDs de desejos realizados: " + idsRealizados.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar IDs de desejos realizados: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return idsRealizados;
    }

    // Método SELECT - Buscar IDs de desejos pendentes
    public ArrayList<Integer> selectIdsDesejosPendentes() {
        connectToDb();

        ArrayList<Integer> idsPendentes = new ArrayList<>();
        String sql = "SELECT id FROM Desejos WHERE statusDesejo = false";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🆔 IDs dos desejos pendentes:");
            while (rs.next()) {
                int id = rs.getInt("id");
                idsPendentes.add(id);
                System.out.println("⏳ ID: " + id);
            }
            System.out.println("💫 Total de IDs de desejos pendentes: " + idsPendentes.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar IDs de desejos pendentes: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return idsPendentes;
    }
}