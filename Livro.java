public class Livro {
    // Atributos privados para segurança (encapsulamento) [4]
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponibilidade;

    // Construtores: obrigam a passar os dados ao criar o objeto
    public Livro(int id, String titulo, String autor, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponibilidade = true; // valor padrão inicial
    }

    // Sobrecarga usada pelo DAO quando a disponibilidade vem do banco
    public Livro(int id, String titulo, int ano, String autor, boolean disponibilidade) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponibilidade = disponibilidade;
    }

    // Métodos Getters e Setters para acessar e alterar os dados [6]
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    // Método para exibir as informações do livro como texto [5, 6]
    public void exibirInfor() {
        System.out.println("Id do Livro: " + id + "\nTítulo: " + titulo + "\nAutor: " + autor + "\nAno: " + ano + "\nDisponibilidade: " + (disponibilidade ? "Disponível" : "Indisponível"));
        System.out.println("-----------------------");
    }
}