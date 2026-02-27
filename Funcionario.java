
public class Funcionario {
    private int id;
    private String nome;
    private int CPF;
    private String funcao;

    public Funcionario(int id, String nome, int CPF, String funcao) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.funcao = funcao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void exibirInfor() {
        System.out.println("Id do Funcionário: " + id + "\nNome: " + nome + "\nCPF: " + CPF + "\nFunção: " + funcao);
        System.out.println("-----------------------");
    }
}
