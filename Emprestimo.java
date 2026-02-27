
import java.sql.Date;

public class Emprestimo {
    private int id;
    private Date devolucao;
    private Usuario usuario;
    private Funcionario funcionario;
    private Livro livro;

    public Emprestimo(int id, Date devolucao, Usuario usuario, Funcionario funcionario, Livro livro) {
        this.id = id;
        this.devolucao = devolucao;
        this.usuario = usuario;
        this.funcionario = funcionario;
        this.livro = livro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(Date devolucao) {
        this.devolucao = devolucao;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void exibirInfor() {
        System.out.println("/nId:" + id + "/nData de Devolução:" + devolucao + "/nUsuário:" + usuario.getNome() + "/nFuncionário:" + funcionario.getNome() + "/nLivro:"
                + livro.getTitulo());
        System.out.println("-----------------------");
    }

    public int getMatricula() {
        // return the matrícula from the associated Usuário object
        if (usuario != null) {
            return usuario.getMatricula();
        }
        // sensible default if there is no user
        return 0;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", devolucao=" + devolucao +
                ", usuario=" + (usuario != null ? usuario.getMatricula() : "null") +
                ", funcionario=" + (funcionario != null ? funcionario.getCPF() : "null") +
                ", livro=" + (livro != null ? livro.getTitulo() : "null") +
                '}';
    }
}
