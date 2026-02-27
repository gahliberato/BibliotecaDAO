import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsuarioDAO {

    public void adicionarU(Usuario usuario) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO usuario(matricula, nome, qtEmprestimo) VALUES(?,?,?)");
            stmt.setInt(1, usuario.getMatricula());
            stmt.setString(2, usuario.getNome());
            stmt.setInt(3, usuario.getQtEmprestimo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public void removerU(int matriculaUsuario) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        try (Connection conexao = postgres.getConection();
                PreparedStatement stmt = conexao.prepareStatement("DELETE FROM usuario WHERE matricula = ?")) {
            stmt.setInt(1, matriculaUsuario);
            stmt.executeUpdate();
            System.out.println("Usuario removido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarU() {
        List<Usuario> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM usuario ORDER BY matricula");
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(rs.getInt("matricula"), 
                                      rs.getInt("qtEmprestimo"), 
                                      rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return lista;
    }

    public void atualizarU(int matriculaAtualizar, int qtEmprestimoAtualizar, String novoNome) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE usuario SET nome=?, qtEmprestimo=? WHERE matricula=?");
            stmt.setString(1, novoNome);
            stmt.setInt(2, qtEmprestimoAtualizar);
            stmt.setInt(3, matriculaAtualizar);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }
        finally {
                postgres.close(stmt, conexao);
            }
    }

}