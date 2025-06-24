package br.inatel.DAO;

import br.inatel.Model.Personagens.Crianca;
import br.inatel.Model.Personagens.Padrinhos;

import java.sql.*;
import java.util.ArrayList;

public class PadrinhosDAO extends ConnectionDao {
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🧚‍♀️ Conexão encantada! Padrinhos Mágicos conectados!");
        } catch (SQLException exc) {
            System.out.println("🚫 Erro na conexão dos Padrinhos: " + exc.getMessage());
        }
    }

    // Método original otimizado para mostrar TODOS os atributos
    public ArrayList<Padrinhos> selectAllPadrinhos() {
        connectToDb();

        ArrayList<Padrinhos> listaPadrinhos = new ArrayList<>();
        String sql = "SELECT * FROM Padrinhos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("🧚‍♀️✨ === LISTA COMPLETA DE PADRINHOS MÁGICOS === ✨🧚‍♀️");
            System.out.println();

            while (rs.next()) {
                Padrinhos padrinho = new Padrinhos(
                        rs.getString("nomePadrinho"),
                        rs.getString("tipoPadrinho"),
                        rs.getInt("Varinha_idSerial"),
                        rs.getInt("Crianca_idCrianca")
                );

                // Mostrando TODOS os dados de cada padrinho
                System.out.println("🆔 ID: " + rs.getInt("id"));
                System.out.println("📛 Nome do Padrinho: " + rs.getString("nomePadrinho"));
                System.out.println("🧚‍♀️ Tipo de Padrinho: " + rs.getString("tipoPadrinho"));
                System.out.println("🪄 ID da Varinha: " + rs.getInt("Varinha_idSerial"));
                System.out.println("👶 ID da Criança: " + rs.getInt("Crianca_idCrianca"));
                System.out.println("═══════════════════════════════════════════");
                System.out.println();

                listaPadrinhos.add(padrinho);
            }

            System.out.println("📊 Total de Padrinhos encontrados: " + listaPadrinhos.size());

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao buscar Padrinhos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return listaPadrinhos;
    }

    // Método para buscar padrinho específico por ID
    public Padrinhos selectPadrinhoById(int id) {
        connectToDb();
        Padrinhos padrinho = null;
        String sql = "SELECT * FROM Padrinhos WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                padrinho = new Padrinhos(
                        rs.getString("nomePadrinho"),
                        rs.getString("tipoPadrinho"),
                        rs.getInt("Varinha_idSerial"),
                        rs.getInt("Crianca_idCrianca")
                );

                System.out.println("🔍 Padrinho encontrado:");
                System.out.println("🆔 ID: " + rs.getInt("id"));
                System.out.println("📛 Nome: " + rs.getString("nomePadrinho"));
                System.out.println("🧚‍♀️ Tipo: " + rs.getString("tipoPadrinho"));
                System.out.println("🪄 ID Varinha: " + rs.getInt("Varinha_idSerial"));
                System.out.println("👶 ID Criança: " + rs.getInt("Crianca_idCrianca"));
            } else {
                System.out.println("🔍 Nenhum Padrinho encontrado com ID: " + id);
            }

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao buscar Padrinho por ID: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return padrinho;
    }

    // Método para buscar padrinhos por tipo
    public ArrayList<Padrinhos> selectPadrinhosByTipo(String tipo) {
        connectToDb();
        ArrayList<Padrinhos> listaPadrinhos = new ArrayList<>();
        String sql = "SELECT * FROM Padrinhos WHERE tipoPadrinho = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, tipo);
            rs = pst.executeQuery();

            System.out.println("🔍 Padrinhos do tipo: " + tipo);
            System.out.println();

            while (rs.next()) {
                Padrinhos padrinho = new Padrinhos(
                        rs.getString("nomePadrinho"),
                        rs.getString("tipoPadrinho"),
                        rs.getInt("Varinha_idSerial"),
                        rs.getInt("Crianca_idCrianca")
                );

                System.out.println("🆔 ID: " + rs.getInt("id"));
                System.out.println("📛 Nome: " + rs.getString("nomePadrinho"));
                System.out.println("🧚‍♀️ Tipo: " + rs.getString("tipoPadrinho"));
                System.out.println("🪄 ID Varinha: " + rs.getInt("Varinha_idSerial"));
                System.out.println("👶 ID Criança: " + rs.getInt("Crianca_idCrianca"));
                System.out.println("─────────────────────────────────────────");

                listaPadrinhos.add(padrinho);
            }

            System.out.println("📊 Total encontrados: " + listaPadrinhos.size());

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao buscar Padrinhos por tipo: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return listaPadrinhos;
    }

    // Método para buscar padrinhos de uma criança específica
    public ArrayList<Padrinhos> selectPadrinhosByCrianca(int idCrianca) {
        connectToDb();
        ArrayList<Padrinhos> listaPadrinhos = new ArrayList<>();
        String sql = "SELECT * FROM Padrinhos WHERE Crianca_idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);
            rs = pst.executeQuery();

            System.out.println("👶 Padrinhos da Criança ID: " + idCrianca);
            System.out.println();

            while (rs.next()) {
                Padrinhos padrinho = new Padrinhos(
                        rs.getString("nomePadrinho"),
                        rs.getString("tipoPadrinho"),
                        rs.getInt("Varinha_idSerial"),
                        rs.getInt("Crianca_idCrianca")
                );

                System.out.println("🆔 ID Padrinho: " + rs.getInt("id"));
                System.out.println("📛 Nome: " + rs.getString("nomePadrinho"));
                System.out.println("🧚‍♀️ Tipo: " + rs.getString("tipoPadrinho"));
                System.out.println("🪄 ID Varinha: " + rs.getInt("Varinha_idSerial"));
                System.out.println("👶 ID Criança: " + rs.getInt("Crianca_idCrianca"));
                System.out.println("─────────────────────────────────────────");

                listaPadrinhos.add(padrinho);
            }

            System.out.println("📊 Total de padrinhos para esta criança: " + listaPadrinhos.size());

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao buscar Padrinhos por criança: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return listaPadrinhos;
    }

    // Método para mostrar apenas os nomes dos padrinhos
    public void selectApenasNomes() {
        connectToDb();
        String sql = "SELECT id, nomePadrinho FROM Padrinhos ORDER BY nomePadrinho";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📝 Lista de Nomes dos Padrinhos:");
            System.out.println("═══════════════════════════════");

            while (rs.next()) {
                System.out.println("🆔 " + rs.getInt("id") + " - 📛 " + rs.getString("nomePadrinho"));
            }

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao buscar nomes: " + exc.getMessage());
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

    // Método para mostrar estatísticas gerais
    public void showEstatisticas() {
        connectToDb();

        try {
            // Contar total de padrinhos
            String sqlTotal = "SELECT COUNT(*) as total FROM Padrinhos";
            st = con.createStatement();
            rs = st.executeQuery(sqlTotal);

            if (rs.next()) {
                System.out.println("📊 ESTATÍSTICAS DOS PADRINHOS MÁGICOS");
                System.out.println("═══════════════════════════════════════");
                System.out.println("🧚‍♀️ Total de Padrinhos: " + rs.getInt("total"));
            }

            // Contar por tipo
            String sqlPorTipo = "SELECT tipoPadrinho, COUNT(*) as quantidade FROM Padrinhos GROUP BY tipoPadrinho";
            rs = st.executeQuery(sqlPorTipo);

            System.out.println("\n🎭 Padrinhos por Tipo:");
            while (rs.next()) {
                System.out.println("   " + rs.getString("tipoPadrinho") + ": " + rs.getInt("quantidade"));
            }

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao gerar estatísticas: " + exc.getMessage());
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

    public boolean updatePadrinho(int id, Padrinhos padrinho) {
        connectToDb();
        String sql = "UPDATE Padrinhos SET nomePadrinho = ?, tipoPadrinho = ?, Varinha_idSerial = ?, Crianca_idCrianca = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, padrinho.getNomeFada());
            pst.setString(2, padrinho.getTipoFada());
            pst.setInt(3, padrinho.getVarinha_idSerial());
            pst.setInt(4, padrinho.getCrianca_idCrianca());
            pst.setInt(5, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Dados do Padrinho Mágico atualizados com sucesso!");
                return true;
            } else {
                System.out.println("🔍 Nenhum Padrinho encontrado com o ID especificado.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao atualizar dados do Padrinho: " + exc.getMessage());
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

    public boolean deletePadrinho(int id) {
        connectToDb();
        String sql = "DELETE FROM Padrinhos WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("👋 Padrinho Mágico removido do cadastro com sucesso!");
                return true;
            } else {
                System.out.println("🔍 Nenhum Padrinho encontrado com o ID especificado para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao remover Padrinho do cadastro: " + exc.getMessage());
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

    public ArrayList<String> selectPadrinhosComCriancasEDesejos() {
        connectToDb();
        ArrayList<String> resultados = new ArrayList<>();

        String sql = """
                SELECT C.nomeCrianca, P.nomePadrinho, D.descricao
                FROM Padrinhos AS P
                JOIN Crianca AS C ON P.Crianca_idCrianca = C.idCrianca
                JOIN Crianca_Faz_Desejos AS CD ON C.idCrianca = CD.Crianca_idCrianca
                JOIN Desejos AS D ON CD.Desejos_idDesejos = D.id
                """;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🧚‍♀️👶🌟 Relação Padrinhos + Crianças + Desejos:");
            System.out.println("═══════════════════════════════════════════");

            while (rs.next()) {
                String linha = "Criança: " + rs.getString("nomeCrianca") +
                        " | Padrinho: " + rs.getString("nomePadrinho") +
                        " | Desejo: " + rs.getString("descricao");
                resultados.add(linha);
                System.out.println(linha);
            }

            System.out.println("📊 Total de relações encontradas: " + resultados.size());

        } catch (SQLException exc) {
            System.out.println("🚫 Erro ao buscar relação Padrinhos+Crianças+Desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return resultados;
    }
}