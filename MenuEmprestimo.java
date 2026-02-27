
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MenuEmprestimo {

    private EmprestimoDAO dao = new EmprestimoDAO();

    public void executar(Scanner sc) {

        int opcao;

        do {
            System.out.println("\n--- MENU EMPRÉSTIMO ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Atualizar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1: {
                    System.out.print("ID do empréstimo: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Data de devolução: ");
                    Date devolucao = Date.valueOf(sc.nextLine());

                    System.out.print("Matrícula do usuário: ");
                    int matricula = Integer.parseInt(sc.nextLine());

                    System.out.print("CPF do funcionário que realizou o empréstimo: ");
                    int CPF = Integer.parseInt(sc.nextLine());

                    System.out.print("Nome do livro emprestado: ");
                    String nome = sc.nextLine();

                    Usuario usuario = new Usuario(matricula, 0, null);
                    Funcionario funcionario = new Funcionario(CPF, null, 0, null);
                    Livro livro = new Livro(0, nome, null, 0);

                    Emprestimo emprestimo = new Emprestimo(id, devolucao, usuario, funcionario, livro);

                    dao.adicionarE(emprestimo);
                    System.out.println("Empréstimo registrado com sucesso!");
                    break;
                }

                case 2: {
                    List<Emprestimo> lista = dao.listarE();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum empréstimo encontrado.");
                    } else {
                        for (Emprestimo e : lista) {
                            System.out.println(e);
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.print("Digite o ID do empréstimo para remover: ");
                    int idRemover = Integer.parseInt(sc.nextLine());
                    dao.removerE(idRemover);
                    break;
                }

                case 4: {
                    System.out.print("ID do empréstimo: ");
                    int idEmprestimo = Integer.parseInt(sc.nextLine());

                    System.out.print("Nova data de devolução: ");
                    Date novaDevolucao = Date.valueOf(sc.nextLine());

                    System.out.print("Nova matrícula do usuário: ");
                    int novaMatricula = Integer.parseInt(sc.nextLine());

                    System.out.print("Novo CPF do funcionário: ");
                    int novoCPF = Integer.parseInt(sc.nextLine());

                    System.out.print("Novo ID do livro: ");
                    int novoIdLivro = Integer.parseInt(sc.nextLine());

                    dao.atualizarE(idEmprestimo, novaDevolucao, novaMatricula, novoCPF, novoIdLivro);
                    System.out.println("Empréstimo atualizado com sucesso!");
                    break;
                }

                case 0: {
                    System.out.println("Voltando...");
                    break;
                }

                default: {
                    System.out.println("Opção inválida.");
                }
            }

        } while (opcao != 0);
    }
}