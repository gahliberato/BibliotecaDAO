
public class Usuario {
    private int matricula, qtEmprestimo;
    private String nome;

    public Usuario(int matricula, int qtEmprestimo, String nome) {
        this.matricula = matricula;
        this.qtEmprestimo = 0;
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getQtEmprestimo() {
        return qtEmprestimo;
    }

    public void setQtEmprestimo(int qtEmprestimo) {
        this.qtEmprestimo = qtEmprestimo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void exibirInfor() {
        System.out.println("Usuario: " + nome + "/nMatricula: " + matricula + "/nQtEmprestimo: " + qtEmprestimo);
        System.out.println("-----------------------");
    }
}
