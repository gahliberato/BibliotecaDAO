import java.util.List;
import java.util.Scanner;

public class MenuLivro {

    private LivroDAO dao = new LivroDAO();

    public void executar(Scanner sc) {

        int opcao;

        do {
            System.out.println("\n--- MENU LIVRO ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Atualizar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1:
                    System.out.print("ID do Livro: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Título do livro: ");
                    String nome = sc.nextLine();

                    System.out.print("Autor do livro: ");
                    String autor = sc.nextLine();

                    System.out.print("Ano do livro: ");
                    int ano = Integer.parseInt(sc.nextLine());

                    Livro livro = new Livro(id, nome, autor, ano);
                    dao.adicionarL(livro);
                    break;

                case 2:
                    List<Livro> lista = dao.listarL();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum livro cadastrado.");
                    } else {
                        for (Livro l : lista) {
                            System.out.println(l);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do livro para remover: ");
                    int idRemover = Integer.parseInt(sc.nextLine());
                    dao.removerL(idRemover);
                    break;

                case 4:
                    System.out.print("ID do livro a ser atualizado: ");
                    int idAtualizar = Integer.parseInt(sc.nextLine());

                    System.out.print("Novo título: ");
                    String novoTitulo = sc.nextLine();

                    System.out.print("Novo autor: ");
                    String novoAutor = sc.nextLine();

                    System.out.print("Novo ano: ");
                    int novoAno = Integer.parseInt(sc.nextLine());

                    dao.atualizarL(idAtualizar, novoTitulo, novoAutor, novoAno);
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}