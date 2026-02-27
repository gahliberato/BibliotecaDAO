import java.util.List;
import java.util.Scanner;

public class MenuFuncionario {

    private FuncionarioDAO dao = new FuncionarioDAO();

    public void executar(Scanner sc) {

        int opcao;

        do {
            System.out.println("\n--- MENU FUNCIONÁRIO ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Atualizar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1:
                    System.out.print("ID do Funcionário: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Nome do funcionário: ");
                    String nome = sc.nextLine();

                    System.out.print("CPF do funcionário: ");
                    int cpf = Integer.parseInt(sc.nextLine());

                    System.out.print("Cargo do funcionário: ");
                    String funcao = sc.nextLine();

                    Funcionario funcionario = new Funcionario(id, nome, cpf, funcao);
                    dao.adicionarF(funcionario);
                    break;

                case 2:
                    List<Funcionario> lista = dao.listarF();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        for (Funcionario f : lista) {
                            System.out.println(f);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do funcionário para remover: ");
                    int idRemover = Integer.parseInt(sc.nextLine());
                    dao.removerF(idRemover);
                    break;

                case 4:
                    System.out.print("ID do funcionário a ser atualizado: ");
                    int idAtualizar = Integer.parseInt(sc.nextLine());

                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();

                    System.out.print("Novo CPF: ");
                    int novoCpf = Integer.parseInt(sc.nextLine());

                    System.out.print("Nova função: ");
                    String novoFuncao = sc.nextLine();

                    dao.atualizarF(idAtualizar, novoNome, novoCpf, novoFuncao);
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