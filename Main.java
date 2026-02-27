import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MenuUsuario MenuUsuario = new MenuUsuario();
        MenuLivro MenuLivro = new MenuLivro();
        MenuFuncionario MenuFuncionario = new MenuFuncionario();
        MenuEmprestimo MenuEmprestimo = new MenuEmprestimo();

        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Usuários");
            System.out.println("2 - Livros");
            System.out.println("3 - Funcionários");
            System.out.println("4 - Empréstimos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    MenuUsuario.executar(sc);
                    break;
                case 2:
                    MenuLivro.executar(sc);
                    break;
                case 3:
                    MenuFuncionario.executar(sc);
                    break;
                case 4:
                    MenuEmprestimo.executar(sc);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}