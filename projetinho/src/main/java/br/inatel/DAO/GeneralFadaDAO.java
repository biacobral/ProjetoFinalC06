package br.inatel.DAO;

import br.inatel.Model.Personagens.GeneralFada;

import java.sql.*;
import java.util.ArrayList;

public class GeneralFadaDAO extends ConnectionDao {
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🎖️ Conexão militar estabelecida! General das Fadas em comando!");
        } catch (SQLException exc) {
            System.out.println("⚠️ Erro na comunicação com o General: " + exc.getMessage());
        }
    }

    public boolean insertGeneralFada(GeneralFada general) {
        connectToDb();
        String sql = "INSERT INTO GeneralFada (nomeFada, tipoFada, Varinha_idSerial) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, general.getNomeFada());
            pst.setString(2, general.getTipoFada());
            pst.setInt(3, general.getVarinha_idSerial());

            pst.execute();
            System.out.println("🎖️ Novo General promovido no Alto Comando das Fadas!");
            return true;

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro na promoção do General: " + exc.getMessage());
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

    public boolean updateGeneralFada(int id, GeneralFada general) {
        connectToDb();
        String sql = "UPDATE GeneralFada SET nomeFada = ?, tipoFada = ?, Varinha_idSerial = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, general.getNomeFada());
            pst.setString(2, general.getTipoFada());
            pst.setInt(3, general.getVarinha_idSerial());
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚡ General atualizado no Alto Comando das Fadas!");
                return true;
            } else {
                System.out.println("🔍 Nenhum General encontrado com o ID especificado.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao atualizar dados do General: " + exc.getMessage());
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

    public boolean deleteGeneralFada(int id) {
        connectToDb();
        String sql = "DELETE FROM GeneralFada WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🎖️ General removido do Alto Comando das Fadas!");
                return true;
            } else {
                System.out.println("🔍 Nenhum General encontrado com o ID especificado para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao dispensar General do comando: " + exc.getMessage());
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

    public ArrayList<GeneralFada> selectGeneralFada() {
        connectToDb();

        ArrayList<GeneralFada> generais = new ArrayList<>();
        String sql = "SELECT * FROM GeneralFada";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("🎖️ Consultando Alto Comando das Fadas:");
            while (rs.next()) {
                GeneralFada generalAux = new GeneralFada(
                        rs.getString("nomeFada"),
                        rs.getString("tipoFada"),
                        rs.getInt("Varinha_idSerial")
                );
                System.out.println("🎖️ General: " + generalAux.getNomeFada() + " | Especialidade: " + generalAux.getTipoFada());
                System.out.println("--------------------");
                generais.add(generalAux);
            }
            System.out.println("🎖️ Total de Generais no Alto Comando: " + generais.size());
        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao consultar Alto Comando: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return generais;
    }

    // Método genérico para buscar todos os valores de um atributo específico
    public ArrayList<String> selectByAttribute(String attributeName) {
        connectToDb();

        ArrayList<String> valores = new ArrayList<>();
        String sql = "SELECT " + attributeName + " FROM GeneralFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🔍 Consultando atributo: " + attributeName);
            System.out.println("--------------------");

            while (rs.next()) {
                String valor = rs.getString(attributeName);
                valores.add(valor);
                System.out.println("📋 " + attributeName + ": " + valor);
            }

            System.out.println("--------------------");
            System.out.println("🎖️ Total de registros encontrados: " + valores.size());

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao consultar atributo " + attributeName + ": " + exc.getMessage());
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

    // Métodos específicos para cada atributo (mais seguros)
    public ArrayList<String> selectAllNomes() {
        connectToDb();

        ArrayList<String> nomes = new ArrayList<>();
        String sql = "SELECT nomeFada FROM GeneralFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🎖️ Lista de todos os Generais das Fadas:");
            System.out.println("--------------------");

            while (rs.next()) {
                String nome = rs.getString("nomeFada");
                nomes.add(nome);
                System.out.println("👑 General: " + nome);
            }

            System.out.println("--------------------");
            System.out.println("🎖️ Total de Generais: " + nomes.size());

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao consultar nomes dos Generais: " + exc.getMessage());
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

    public ArrayList<String> selectAllTipos() {
        connectToDb();

        ArrayList<String> tipos = new ArrayList<>();
        String sql = "SELECT tipoFada FROM GeneralFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("✨ Especialidades dos Generais das Fadas:");
            System.out.println("--------------------");

            while (rs.next()) {
                String tipo = rs.getString("tipoFada");
                tipos.add(tipo);
                System.out.println("🧚 Especialidade: " + tipo);
            }

            System.out.println("--------------------");
            System.out.println("🎖️ Total de especialidades: " + tipos.size());

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao consultar tipos das Fadas: " + exc.getMessage());
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

    public ArrayList<Integer> selectAllVarinhas() {
        connectToDb();

        ArrayList<Integer> varinhas = new ArrayList<>();
        String sql = "SELECT Varinha_idSerial FROM GeneralFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🪄 IDs das Varinhas dos Generais:");
            System.out.println("--------------------");

            while (rs.next()) {
                int varinhaId = rs.getInt("Varinha_idSerial");
                varinhas.add(varinhaId);
                System.out.println("🎭 Varinha ID: " + varinhaId);
            }

            System.out.println("--------------------");
            System.out.println("🎖️ Total de varinhas: " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao consultar IDs das Varinhas: " + exc.getMessage());
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

    // Método para buscar valores únicos de um atributo (sem duplicatas)
    public ArrayList<String> selectUniqueByAttribute(String attributeName) {
        connectToDb();

        ArrayList<String> valoresUnicos = new ArrayList<>();
        String sql = "SELECT DISTINCT " + attributeName + " FROM GeneralFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🔍 Valores únicos do atributo: " + attributeName);
            System.out.println("--------------------");

            while (rs.next()) {
                String valor = rs.getString(attributeName);
                valoresUnicos.add(valor);
                System.out.println("📋 " + attributeName + ": " + valor);
            }

            System.out.println("--------------------");
            System.out.println("🎖️ Total de valores únicos: " + valoresUnicos.size());

        } catch (SQLException exc) {
            System.out.println("⚠️ Erro ao consultar valores únicos de " + attributeName + ": " + exc.getMessage());
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
}
