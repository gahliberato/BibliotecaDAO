import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmprestimoDAO {

    public void adicionarE(Emprestimo emprestimo) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            // Note que no SQL usamos as colunas de ID
            String sql = "INSERT INTO Emprestimo(idE, devolucao, matricula, idF, idL) VALUES(?,?,?,?,?)";
            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, emprestimo.getId());
            stmt.setDate(2, emprestimo.getDevolucao());
            stmt.setInt(3, emprestimo.getMatricula());
            // idF should be set using the funcionario's CPF or id? original code used emprestimo.getId()
            stmt.setInt(4, emprestimo.getFuncionario() != null ? emprestimo.getFuncionario().getCPF() : 0);
            stmt.setInt(5, emprestimo.getLivro().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public void removerE(int idE) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        try (Connection conexao = postgres.getConection();
                PreparedStatement stmt = conexao.prepareStatement("DELETE FROM Emprestimo WHERE idE = ?")) {
            stmt.setInt(1, idE);
            stmt.executeUpdate();
            System.out.println("Emprestimo removido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emprestimo> listarE() {
        List<Emprestimo> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            // inner_join_para_encontrar_dadossssssssssssssssssssssss
            String sql = "SELECT e.*, l.titulo, f.nome, u.matricula " +
                    "FROM Emprestimo e " +
                    // assume livro's primary key column is called 'id'
                    "INNER JOIN Livro l ON e.idL = l.id " +
                    "INNER JOIN Funcionario f ON e.idF = f.idF " +
                    "INNER JOIN Usuario u ON e.matricula = u.matricula";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("idE"),
                        rs.getDate("devolucao"),
                        new Usuario(rs.getInt("matricula"), 0, ""),
                        new Funcionario(0, rs.getString("nome"), 0, ""),
                        new Livro(0, rs.getString("titulo"), 0, "", false));
                lista.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return lista;
    }

    public void atualizarE(int idE, Date novaDataDevolucao, int novoIdL, int novaMatricula, int novoIdF) {

        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();

        String sql = "UPDATE Emprestimo SET devolucao = ?, idL = ?, matricula = ?,  idF = ? WHERE idE = ?";

        try (Connection conexao = postgres.getConection();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDate(1, novaDataDevolucao);
            stmt.setInt(2, novoIdL);
            stmt.setInt(3, novaMatricula);
            stmt.setInt(4, novoIdF);
            stmt.setInt(5, idE);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Emprestimo atualizado com sucesso!");
            } else {
                System.out.println("Emprestimo não encontrado.");
            }
            // duplicate set of parameters was unnecessary and has been removed

            int linhas1 = stmt.executeUpdate();

            if (linhas1 > 0) {
                System.out.println("Emprestimo atualizado com sucesso!");
            } else {
                System.out.println("Emprestimo não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar emprestimo: " + e.getMessage());
        }
    }
}