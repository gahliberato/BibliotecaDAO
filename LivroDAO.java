import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LivroDAO {

    public void adicionarL(Livro livro) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                    "INSERT INTO livro(idL, titulo, autor, ano, disponibilidade) VALUES(?,?,?,?,?)");
            stmt.setInt(1, livro.getId());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setInt(4, livro.getAno());
            stmt.setBoolean(5, livro.isDisponibilidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public void removerL(int id) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        try (Connection conexao = postgres.getConection();
                PreparedStatement stmt = conexao.prepareStatement("DELETE FROM livro WHERE idL = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Livro removido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarL() {
        List<Livro> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM livro ORDER BY idL");
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Livro(rs.getInt("idL"),
                                    rs.getString("titulo"),
                                    rs.getInt("ano"),
                                    rs.getString("autor"), 
                                    rs.getBoolean("disponibilidade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return lista;
    }

    public void atualizarL(int idAtualizar, String novoTitulo, String novoAutor, int novoAno) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                    "UPDATE livro SET titulo=?, autor=?, ano=? WHERE idL=?");
            stmt.setString(1, novoTitulo);
            stmt.setString(2, novoAutor);
            stmt.setInt(3, novoAno);
            stmt.setInt(4, idAtualizar);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }
}