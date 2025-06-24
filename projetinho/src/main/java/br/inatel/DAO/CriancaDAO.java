package br.inatel.DAO;

import java.util.ArrayList;

import br.inatel.Model.Personagens.Crianca;

import java.sql.*;

public class CriancaDAO extends ConnectionDao {

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

    // Método genérico para buscar todos os valores de um atributo específico
    public ArrayList<String> selectByAttribute(String attributeName) {
        connectToDb();

        ArrayList<String> valores = new ArrayList<>();
        String sql = "SELECT " + attributeName + " FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🔍 Explorando o mundo das crianças para atributo: " + attributeName);
            System.out.println("--------------------");

            while (rs.next()) {
                String valor = rs.getString(attributeName);
                valores.add(valor);
                System.out.println("👶 " + attributeName + ": " + valor);
            }

            System.out.println("--------------------");
            System.out.println("🌟 Total de registros encontrados: " + valores.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar atributo " + attributeName + ": " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return valores;
    }

    // Método específico para buscar todos os nomes das crianças
    public ArrayList<String> selectAllNomesCriancas() {
        connectToDb();

        ArrayList<String> nomes = new ArrayList<>();
        String sql = "SELECT nomeCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("👶 Lista de todas as crianças do mundo mágico:");
            System.out.println("--------------------");

            while (rs.next()) {
                String nome = rs.getString("nomeCrianca");
                nomes.add(nome);
                System.out.println("🌟 Criança: " + nome);
            }

            System.out.println("--------------------");
            System.out.println("🎈 Total de crianças registradas: " + nomes.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar nomes das crianças: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return nomes;
    }

    // Método específico para buscar todas as idades das crianças
    public ArrayList<Integer> selectAllIdadesCriancas() {
        connectToDb();

        ArrayList<Integer> idades = new ArrayList<>();
        String sql = "SELECT idadeCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🎂 Idades das crianças do mundo mágico:");
            System.out.println("--------------------");

            while (rs.next()) {
                int idade = rs.getInt("idadeCrianca");
                idades.add(idade);
                System.out.println("🎈 Idade: " + idade + " anos");
            }

            System.out.println("--------------------");
            System.out.println("🌟 Total de idades registradas: " + idades.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar idades das crianças: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return idades;
    }

    // Método específico para buscar todos os sexos das crianças
    public ArrayList<String> selectAllSexosCriancas() {
        connectToDb();

        ArrayList<String> sexos = new ArrayList<>();
        String sql = "SELECT sexoCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("👦👧 Gêneros das crianças do mundo mágico:");
            System.out.println("--------------------");

            while (rs.next()) {
                String sexo = rs.getString("sexoCrianca");
                sexos.add(sexo);
                System.out.println("🌟 Gênero: " + sexo);
            }

            System.out.println("--------------------");
            System.out.println("🎈 Total de registros de gênero: " + sexos.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar gêneros das crianças: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return sexos;
    }

    // Método específico para buscar todos os endereços das crianças
    public ArrayList<String> selectAllEnderecosCriancas() {
        connectToDb();

        ArrayList<String> enderecos = new ArrayList<>();
        String sql = "SELECT enderecoCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🏠 Endereços das crianças do mundo mágico:");
            System.out.println("--------------------");

            while (rs.next()) {
                String endereco = rs.getString("enderecoCrianca");
                enderecos.add(endereco);
                System.out.println("📍 Endereço: " + endereco);
            }

            System.out.println("--------------------");
            System.out.println("🌟 Total de endereços registrados: " + enderecos.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar endereços das crianças: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return enderecos;
    }

    // Método específico para buscar status de padrinhos (boolean)
    public ArrayList<Boolean> selectAllStatusPadrinhos() {
        connectToDb();

        ArrayList<Boolean> statusPadrinhos = new ArrayList<>();
        String sql = "SELECT temPadrinho FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🧚‍♀️ Status de Padrinhos das crianças:");
            System.out.println("--------------------");

            while (rs.next()) {
                boolean temPadrinho = rs.getBoolean("temPadrinho");
                statusPadrinhos.add(temPadrinho);
                String status = temPadrinho ? "Tem padrinho mágico ✨" : "Sem padrinho 💔";
                System.out.println("👶 Status: " + status);
            }

            System.out.println("--------------------");
            System.out.println("🌟 Total de registros de status: " + statusPadrinhos.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar status dos padrinhos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return statusPadrinhos;
    }

    // Método para buscar valores únicos de um atributo (sem duplicatas)
    public ArrayList<String> selectUniqueByAttribute(String attributeName) {
        connectToDb();

        ArrayList<String> valoresUnicos = new ArrayList<>();
        String sql = "SELECT DISTINCT " + attributeName + " FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🔍 Valores únicos do atributo: " + attributeName);
            System.out.println("--------------------");

            while (rs.next()) {
                String valor = rs.getString(attributeName);
                valoresUnicos.add(valor);
                System.out.println("👶 " + attributeName + ": " + valor);
            }

            System.out.println("--------------------");
            System.out.println("🌟 Total de valores únicos: " + valoresUnicos.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar valores únicos de " + attributeName + ": " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return valoresUnicos;
    }

    // Método para buscar crianças com padrinho
    public ArrayList<String> selectCriancasComPadrinho() {
        connectToDb();

        ArrayList<String> criancasComPadrinho = new ArrayList<>();
        String sql = "SELECT nomeCrianca FROM Crianca WHERE temPadrinho = true";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🧚‍♀️ Crianças que têm padrinho mágico:");
            System.out.println("--------------------");

            while (rs.next()) {
                String nome = rs.getString("nomeCrianca");
                criancasComPadrinho.add(nome);
                System.out.println("✨ " + nome + " - Tem padrinho mágico!");
            }

            System.out.println("--------------------");
            System.out.println("🌟 Total de crianças com padrinho: " + criancasComPadrinho.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar crianças com padrinho: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return criancasComPadrinho;
    }

    // Método para buscar crianças sem padrinho
    public ArrayList<String> selectCriancasSemPadrinho() {
        connectToDb();

        ArrayList<String> criancasSemPadrinho = new ArrayList<>();
        String sql = "SELECT nomeCrianca FROM Crianca WHERE temPadrinho = false";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("💔 Crianças que precisam de um padrinho mágico:");
            System.out.println("--------------------");

            while (rs.next()) {
                String nome = rs.getString("nomeCrianca");
                criancasSemPadrinho.add(nome);
                System.out.println("🙏 " + nome + " - Precisa de um padrinho!");
            }

            System.out.println("--------------------");
            System.out.println("🌟 Total de crianças sem padrinho: " + criancasSemPadrinho.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar crianças sem padrinho: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return criancasSemPadrinho;
    }

    // Método para buscar crianças por faixa etária
    public ArrayList<String> selectCriancasPorIdade(int idadeMinima, int idadeMaxima) {
        connectToDb();

        ArrayList<String> criancasPorIdade = new ArrayList<>();
        String sql = "SELECT nomeCrianca, idadeCrianca FROM Crianca WHERE idadeCrianca BETWEEN ? AND ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idadeMinima);
            pst.setInt(2, idadeMaxima);
            rs = pst.executeQuery();

            System.out.println("🎂 Crianças entre " + idadeMinima + " e " + idadeMaxima + " anos:");
            System.out.println("--------------------");

            while (rs.next()) {
                String nome = rs.getString("nomeCrianca");
                int idade = rs.getInt("idadeCrianca");
                String info = nome + " (" + idade + " anos)";
                criancasPorIdade.add(info);
                System.out.println("🎈 " + info);
            }

            System.out.println("--------------------");
            System.out.println("🌟 Total de crianças nesta faixa etária: " + criancasPorIdade.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao consultar crianças por idade: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return criancasPorIdade;
    }

    public ArrayList<String> selectCriancasComDesejos() {
        connectToDb();
        ArrayList<String> resultados = new ArrayList<>();

        String sql = """
                SELECT C.nomeCrianca, D.descricao, D.statusDesejo
                FROM Crianca AS C
                JOIN Crianca_Faz_Desejos AS CD ON C.idCrianca = CD.Crianca_idCrianca
                JOIN Desejos AS D ON CD.Desejos_idDesejos = D.id
                """;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("👶🌟 Desejos das Crianças:");
            System.out.println("═══════════════════════════════════════════");

            while (rs.next()) {
                String status = rs.getBoolean("statusDesejo") ? "✅ Realizado" : "⏳ Pendente";
                String linha = "Criança: " + rs.getString("nomeCrianca") +
                        " | Desejo: " + rs.getString("descricao") +
                        " | Status: " + status;
                resultados.add(linha);
                System.out.println(linha);
            }

            System.out.println("📊 Total de desejos encontrados: " + resultados.size());

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao buscar desejos das crianças: " + exc.getMessage());
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