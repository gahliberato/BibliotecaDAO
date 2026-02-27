import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    private UsuarioDAO dao = new UsuarioDAO();

    public void executar(Scanner sc) {

        int opcao;

        do {
            System.out.println("\n--- MENU USUÁRIO ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Atualizar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1:
                    System.out.print("Matrícula do Usuário: ");
                    int matricula = Integer.parseInt(sc.nextLine());



                    System.out.print("Nome do usuário: ");
                    String nome = sc.nextLine();

                    System.out.print("Quantidade de empréstimos: ");
                    int qtEmprestimo = Integer.parseInt(sc.nextLine());

                    Usuario usuario = new Usuario(matricula, qtEmprestimo, nome);
                    dao.adicionarU(usuario);
                    break;

                case 2:
                    List<Usuario> lista = dao.listarU();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        for (Usuario u : lista) {
                            System.out.println(u);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite a matrícula do usuário para remover: ");
                    int matriculaRemover = Integer.parseInt(sc.nextLine());
                    dao.removerU(matriculaRemover);
                    break;

                case 4:
                    System.out.print("Matrícula do usuário: ");
                    int matriculaAtualizar = Integer.parseInt(sc.nextLine());

                    System.out.print("Quantidade de empréstimos: ");
                    int qtEmprestimoAtualizar = Integer.parseInt(sc.nextLine());

                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();

                    dao.atualizarU(matriculaAtualizar, qtEmprestimoAtualizar, novoNome);
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