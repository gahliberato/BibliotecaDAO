import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FuncionarioDAO {

    public void adicionarF(Funcionario funcionario) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO funcionario(idF, nome, CPF, funcao) VALUES(?,?,?,?)");
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getNome());
            stmt.setInt(3, funcionario.getCPF());
            stmt.setString(4, funcionario.getFuncao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public void removerF(int idF) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        try (Connection conexao = postgres.getConection();
                PreparedStatement stmt = conexao.prepareStatement("DELETE FROM funcionario WHERE idF = ?")) {
            stmt.setInt(1, idF);
            stmt.executeUpdate();
            System.out.println("Funcionário removido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Funcionario> listarF() {
        List<Funcionario> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM funcionario ORDER BY idF");
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Funcionario(rs.getInt("idF"), 
                                          rs.getString("nome"), 
                                          rs.getInt("CPF"),
                                          rs.getString("funcao")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return lista;
    }

    public void updateF(Funcionario funcionario) throws SQLException {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE funcionario SET nome=?, CPF=?, funcao=? WHERE idF=?");
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getNome());
            stmt.setInt(3, funcionario.getCPF());
            stmt.setString(4, funcionario.getFuncao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getNome());
            stmt.setInt(3, funcionario.getCPF());
            stmt.setString(4, funcionario.getFuncao());
            stmt.executeUpdate();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public void atualizarF(int idAtualizar, String novoNome, int novoCpf, String novoFuncao) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE funcionario SET nome=?, CPF=?, funcao=? WHERE idF=?");
            stmt.setString(1, novoNome);
            stmt.setInt(2, novoCpf);
            stmt.setString(3, novoFuncao);
            stmt.setInt(4, idAtualizar);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }
}