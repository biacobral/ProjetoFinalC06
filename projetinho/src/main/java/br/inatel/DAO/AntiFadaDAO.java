package br.inatel.DAO;

import br.inatel.Model.Personagens.AntiFada;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class AntiFadaDAO extends ConnectionDao {
    // AntiFadaDAO.java
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("⚔️ Conexão estabelecida com sucesso! Anti-Fadas prontas para a batalha!");
        } catch (SQLException exc) {
            System.out.println("❌ Erro na conexão das Anti-Fadas: " + exc.getMessage());
        }
    }

    public boolean insertAntiFada(AntiFada antiFada) {
        connectToDb();
        String sql = "INSERT INTO AntiFada (nomeFada, tipoFada, Varinha_idSerial) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, antiFada.getNomeFada());
            pst.setString(2, antiFada.getTipoFada());
            pst.setInt(3, antiFada.getVarinha_idSerial());

            pst.execute();
            System.out.println("⚔️ Nova Anti-Fada registrada no exército das trevas!");
            return true;

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao registrar Anti-Fada: " + exc.getMessage());
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

    // Método UPDATE - Atualizar dados da Anti-Fada
    public boolean updateAntiFada(int id, AntiFada antiFada) {
        connectToDb();
        String sql = "UPDATE AntiFada SET nomeFada = ?, tipoFada = ?, Varinha_idSerial = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, antiFada.getNomeFada());
            pst.setString(2, antiFada.getTipoFada());
            pst.setInt(3, antiFada.getVarinha_idSerial());
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚡ Anti-Fada " + antiFada.getNomeFada() + " teve seus poderes das trevas atualizados!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada com ID " + id + " não foi encontrada no exército das trevas!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao atualizar poderes da Anti-Fada: " + exc.getMessage());
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

    // Método UPDATE específico - Atualizar apenas a varinha da Anti-Fada
    public boolean updateAntiFadaVarinha(int id, int novaVarinhaId) {
        connectToDb();
        String sql = "UPDATE AntiFada SET Varinha_idSerial = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, novaVarinhaId);
            pst.setInt(2, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🪄 Anti-Fada equipou nova varinha das trevas! Poder aumentado!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada com ID " + id + " não foi encontrada para troca de varinha!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao equipar nova varinha na Anti-Fada: " + exc.getMessage());
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

    public boolean deleteAntiFada(String nomeFada) {
        connectToDb();
        String sql = "DELETE FROM AntiFada WHERE nomeFada = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeFada);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚔️ Anti-Fada " + nomeFada + " foi banida do exército das trevas!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada " + nomeFada + " não foi encontrada no exército!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao banir Anti-Fada: " + exc.getMessage());
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

    // Alternativa: DELETE por ID (caso você tenha um campo ID)
    public boolean deleteAntiFadaById(int id) {
        connectToDb();
        String sql = "DELETE FROM AntiFada WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚔️ Anti-Fada com ID " + id + " foi banida do exército das trevas!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada com ID " + id + " não foi encontrada no exército!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao banir Anti-Fada: " + exc.getMessage());
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

    public ArrayList<AntiFada> selectAntiFada() {
        connectToDb();

        ArrayList<AntiFada> antiFadas = new ArrayList<>();
        String sql = "SELECT * FROM AntiFada";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("⚔️ Listando exército das Anti-Fadas:");
            while (rs.next()) {
                AntiFada antiFadaAux = new AntiFada(
                        rs.getString("nomeFada"),
                        rs.getString("tipoFada"),
                        rs.getInt("Varinha_idSerial")
                );
                System.out.println("👹 Nome: " + antiFadaAux.getNomeFada() + " | Tipo: " + antiFadaAux.getTipoFada());
                System.out.println("--------------------");
                antiFadas.add(antiFadaAux);
            }
            System.out.println("⚔️ Total de Anti-Fadas no exército: " + antiFadas.size());
        } catch (SQLException exc) {
            System.out.println("❌ Erro ao consultar exército das Anti-Fadas: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return antiFadas;
    }

    // Método SELECT - Buscar apenas os NOMES de todas as Anti-Fadas
    public ArrayList<String> selectAllNomesAntiFada() {
        connectToDb();

        ArrayList<String> nomes = new ArrayList<>();
        String sql = "SELECT nomeFada FROM AntiFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📜 Lista de nomes das Anti-Fadas:");
            while (rs.next()) {
                String nome = rs.getString("nomeFada");
                nomes.add(nome);
                System.out.println("👹 " + nome);
            }
            System.out.println("⚔️ Total de nomes listados: " + nomes.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao buscar nomes das Anti-Fadas: " + exc.getMessage());
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

    // Método SELECT - Buscar apenas os TIPOS de todas as Anti-Fadas
    public ArrayList<String> selectAllTiposAntiFada() {
        connectToDb();

        ArrayList<String> tipos = new ArrayList<>();
        String sql = "SELECT tipoFada FROM AntiFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🌟 Lista de tipos das Anti-Fadas:");
            while (rs.next()) {
                String tipo = rs.getString("tipoFada");
                tipos.add(tipo);
                System.out.println("🔮 " + tipo);
            }
            System.out.println("⚔️ Total de tipos listados: " + tipos.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao buscar tipos das Anti-Fadas: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return tipos;
    }

    // Método SELECT - Buscar apenas os IDs das VARINHAS de todas as Anti-Fadas
    public ArrayList<Integer> selectAllVarinhasAntiFada() {
        connectToDb();

        ArrayList<Integer> varinhas = new ArrayList<>();
        String sql = "SELECT Varinha_idSerial FROM AntiFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🪄 Lista de IDs das varinhas das Anti-Fadas:");
            while (rs.next()) {
                int varinhaId = rs.getInt("Varinha_idSerial");
                varinhas.add(varinhaId);
                System.out.println("⚡ Varinha ID: " + varinhaId);
            }
            System.out.println("⚔️ Total de varinhas listadas: " + varinhas.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao buscar IDs das varinhas: " + exc.getMessage());
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

    // Método SELECT - Buscar apenas os IDs de todas as Anti-Fadas (caso exista campo ID)
    public ArrayList<Integer> selectAllIdsAntiFada() {
        connectToDb();

        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM AntiFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🆔 Lista de IDs das Anti-Fadas:");
            while (rs.next()) {
                int id = rs.getInt("id");
                ids.add(id);
                System.out.println("🔢 ID: " + id);
            }
            System.out.println("⚔️ Total de IDs listados: " + ids.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao buscar IDs das Anti-Fadas: " + exc.getMessage());
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

    // Método SELECT - Buscar tipos ÚNICOS (sem repetição)
    public ArrayList<String> selectTiposUnicosAntiFada() {
        connectToDb();

        ArrayList<String> tiposUnicos = new ArrayList<>();
        String sql = "SELECT DISTINCT tipoFada FROM AntiFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🌟 Tipos únicos de Anti-Fadas no exército:");
            while (rs.next()) {
                String tipo = rs.getString("tipoFada");
                tiposUnicos.add(tipo);
                System.out.println("🔮 " + tipo);
            }
            System.out.println("⚔️ Total de tipos únicos: " + tiposUnicos.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao buscar tipos únicos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return tiposUnicos;
    }

    // Método SELECT - Buscar apenas os NOMES de todas as Crianças
    public ArrayList<String> selectAllNomesCrianca() {
        connectToDb();

        ArrayList<String> nomes = new ArrayList<>();
        String sql = "SELECT nomeCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📋 Lista de nomes das crianças:");
            while (rs.next()) {
                String nome = rs.getString("nomeCrianca");
                nomes.add(nome);
                System.out.println("👶 " + nome);
            }
            System.out.println("🎉 Total de nomes listados: " + nomes.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao buscar nomes das crianças: " + exc.getMessage());
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

    // Método SELECT - Buscar apenas as IDADES de todas as Crianças
    public ArrayList<Integer> selectAllIdadesCrianca() {
        connectToDb();

        ArrayList<Integer> idades = new ArrayList<>();
        String sql = "SELECT idadeCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🎂 Lista de idades das crianças:");
            while (rs.next()) {
                int idade = rs.getInt("idadeCrianca");
                idades.add(idade);
                System.out.println("🎈 " + idade + " anos");
            }
            System.out.println("🎉 Total de idades listadas: " + idades.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao buscar idades das crianças: " + exc.getMessage());
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

    // Método SELECT - Buscar apenas os SEXOS de todas as Crianças
    public ArrayList<String> selectAllSexosCrianca() {
        connectToDb();

        ArrayList<String> sexos = new ArrayList<>();
        String sql = "SELECT sexoCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("👫 Lista de sexos das crianças:");
            while (rs.next()) {
                String sexo = rs.getString("sexoCrianca");
                sexos.add(sexo);
                System.out.println("🚻 " + sexo);
            }
            System.out.println("🎉 Total de sexos listados: " + sexos.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao buscar sexos das crianças: " + exc.getMessage());
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

    // Método SELECT - Buscar apenas o STATUS DE PADRINHO de todas as Crianças
    public ArrayList<Boolean> selectAllTemPadrinhoCrianca() {
        connectToDb();

        ArrayList<Boolean> temPadrinhos = new ArrayList<>();
        String sql = "SELECT temPadrinho FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🧚‍♀️ Lista de status de padrinho das crianças:");
            while (rs.next()) {
                boolean temPadrinho = rs.getBoolean("temPadrinho");
                temPadrinhos.add(temPadrinho);
                String status = temPadrinho ? "Tem padrinho ✨" : "Não tem padrinho 💔";
                System.out.println("🧚 " + status);
            }
            System.out.println("🎉 Total de status listados: " + temPadrinhos.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao buscar status de padrinho: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return temPadrinhos;
    }

    // Método SELECT - Buscar apenas os ENDEREÇOS de todas as Crianças
    public ArrayList<String> selectAllEnderecosCrianca() {
        connectToDb();

        ArrayList<String> enderecos = new ArrayList<>();
        String sql = "SELECT enderecoCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🏠 Lista de endereços das crianças:");
            while (rs.next()) {
                String endereco = rs.getString("enderecoCrianca");
                enderecos.add(endereco);
                System.out.println("📍 " + endereco);
            }
            System.out.println("🎉 Total de endereços listados: " + enderecos.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao buscar endereços das crianças: " + exc.getMessage());
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

    // Método SELECT - Buscar apenas os IDs de todas as Crianças
    public ArrayList<Integer> selectAllIdsCrianca() {
        connectToDb();

        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT idCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🆔 Lista de IDs das crianças:");
            while (rs.next()) {
                int id = rs.getInt("idCrianca");
                ids.add(id);
                System.out.println("🔢 ID: " + id);
            }
            System.out.println("🎉 Total de IDs listados: " + ids.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao buscar IDs das crianças: " + exc.getMessage());
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

    // Método SELECT - Buscar SEXOS ÚNICOS (sem repetição)
    public ArrayList<String> selectSexosUnicosCrianca() {
        connectToDb();

        ArrayList<String> sexosUnicos = new ArrayList<>();
        String sql = "SELECT DISTINCT sexoCrianca FROM Crianca";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("👫 Sexos únicos no mundo das crianças:");
            while (rs.next()) {
                String sexo = rs.getString("sexoCrianca");
                sexosUnicos.add(sexo);
                System.out.println("🚻 " + sexo);
            }
            System.out.println("🎉 Total de sexos únicos: " + sexosUnicos.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao buscar sexos únicos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return sexosUnicos;
    }

    // Método SELECT - Contar crianças com e sem padrinho
    public void contarCriancasPorPadrinho() {
        connectToDb();

        String sql = "SELECT temPadrinho, COUNT(*) as quantidade FROM Crianca GROUP BY temPadrinho";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📊 Estatísticas de padrinhos:");
            while (rs.next()) {
                boolean temPadrinho = rs.getBoolean("temPadrinho");
                int quantidade = rs.getInt("quantidade");

                String status = temPadrinho ? "Com padrinho ✨" : "Sem padrinho 💔";
                System.out.println("🧚 " + status + ": " + quantidade + " crianças");
            }
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("😢 Erro ao contar crianças por padrinho: " + exc.getMessage());
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
}