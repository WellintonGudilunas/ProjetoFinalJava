import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
    
import controller.Biblioteca;
import controller.Emprestimo;
import controller.Livro;
import controller.Usuario;
import model.Log;
import model.LogEmprestimo;
import model.LogLivro;
import model.LogUsuario;
import model.Salvar;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        Boolean menu = true;
        Salvar salvar = new Salvar();
        Biblioteca biblioteca = new Biblioteca();

        Livro l1 = new Livro("A bela e a Fera", "Paulera", 2012, "Romance", 10, 1);
        Livro l2 = new Livro("Clean Code", "Jeff Espeto", 2003, "De programa", 1, 2);
        Livro l3 = new Livro("Clean house", "Jeff do Bigode", 2003, "De casa", 0, 3);
        biblioteca.cadastrarLivro(l1);
        biblioteca.cadastrarLivro(l2);
        biblioteca.cadastrarLivro(l3);

        Usuario u1 = new Usuario(555, "Paulo", "paulo", "paulo", "paulo", "paulo", "paulo");
        Usuario u2 = new Usuario(666, "Tiago", "Tiago", "Tiago", "Tiago", "Tiago", "Tiago");
        biblioteca.cadastrarUsuario(u1);
        biblioteca.cadastrarUsuario(u2);

        Emprestimo emprestimoTeste = new Emprestimo(l1, u1, 10);


        biblioteca.emprestarLivro(emprestimoTeste);

        while(menu){
            System.out.println(
                "\n\nBIBLIOTECA!!! \n\n" +
                "1- Cadastrar Livros \n" +
                "2- Cadastrar Usuários \n" +
                "3- Emprestar Livros \n" +
                "4- Devolver Livros \n" +
                "5- Buscar Livro \n" +
                "6- Buscar Usuario \n" +
                "200- Listar livros (tempo Excecução)\n" +
                "201- Listar usuários (tempo Excecução)\n" +
                "202- Listar empréstimos (tempo Excecução)\n" +
                "7- Log livros \n" +
                "8- Log usuários \n" +
                "9- Log Empréstimos \n" +
                "0- Sair"
                );
            System.out.print("Digite a opção desejada: ");
            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\n----------------------- \nVocê deve digitar um número !!!\n -----------------------");
                scanner.next();
                continue;
            }

            if(opcao == 0) {
                return;
            } else if (opcao == 1){

                System.out.println("\nCadastrar Livros \n");

                System.out.print("Digite o código do livro: ");
                scanner.nextLine();
                int codigo = scanner.nextInt();

                System.out.print("Digite o titulo do livro: ");
                scanner.nextLine();
                String titulo = scanner.nextLine();

                System.out.print("Digite o autor do livro: ");
                String autor = scanner.nextLine();
                
                System.out.print("Digite a categoria do livro: "); 
                String categoria = scanner.nextLine();

                System.out.print("Digite o ano de publicação do livro: ");
                int anoPublicacao = scanner.nextInt();

                
                System.out.print("Digite a quantidade disponivel do livro: ");
                int quantidadeDisponivel = scanner.nextInt();

                Livro livro = new Livro(titulo, autor, anoPublicacao, categoria, quantidadeDisponivel, codigo);
                biblioteca.cadastrarLivro(livro);

                salvar.adicionarLogLivro("Livro cadastrado em ", LocalDateTime.now(), livro);
                salvar.salvarLogLivro("logLivro.ser");

                System.out.println("\n-----------------------\nLivro cadastrado!!!\n -----------------------");
            } else if (opcao == 2){

                System.out.println("\nCadastrar Usuários \n");

                System.out.print("Digite o código do Usuário: ");
                int codigo = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Digite o nome do Usuário: ");

                String nome = scanner.nextLine();

                System.out.print("Digite o sobrenome do Usuário: ");
                String sobrenome = scanner.nextLine();

                System.out.print("Digite o endereco do Usuário: ");
                String endereco = scanner.nextLine();

                System.out.print("Digite o email do Usuário: ");
                String email = scanner.nextLine();

                System.out.print("Digite o cpf do Usuário: ");
                String cpf = scanner.nextLine();

                System.out.print("Digite o telefone do Usuário: ");
                String telefone = scanner.nextLine();

                Usuario user = new Usuario(codigo, nome, sobrenome, endereco, email, cpf, telefone);
                biblioteca.cadastrarUsuario(user);

                salvar.adicionarLogUsuario("Usuario cadastrado em ", LocalDateTime.now(), user);
                salvar.salvarLogUsuario("logUser.ser");
            } else if (opcao == 3){
                
                System.out.println("\nEmpréstimo de livros\n");
                System.out.print("Digite o código do empréstimo: ");
                int codigoEmprestimo = scanner.nextInt();

                System.out.print("Digite o código do usuário que vai emprestar o livro: ");
                int codigoUsuario = scanner.nextInt();

                System.out.print("Digite o código do livro que deseja emprestar: ");
                int codigoLivro = scanner.nextInt();

                Emprestimo emprestimo = new Emprestimo(biblioteca.pesquisarLivroCodigo2(codigoLivro), biblioteca.pesquisarUsuarioCodigo2(codigoUsuario), codigoEmprestimo);

                if(biblioteca.emprestarLivro(emprestimo)){
                    LocalDateTime data = LocalDateTime.now();
                    salvar.adicionarLogEmprestimo("Novo empréstimo cadastrado em ", data, emprestimo);
                    salvar.salvarLogEmprestimo("logEmprestimo.ser");
                }else {
                    System.out.println("Erro no empréstimo");
                }

            } else if (opcao == 4){
                // DEVOLVER LIVROS
                System.out.println("\nDevolução de livros\n");
                System.out.println("Digite o código do Empréstimo que você deseja devolver");
                int codigoEmprestimo = scanner.nextInt();

                LocalDateTime data = LocalDateTime.now();
                salvar.adicionarLogEmprestimo("Nova DEVOLUÇÃO em ", data, biblioteca.pesquisarEmprestimoCodigo2(codigoEmprestimo));
                if(biblioteca.devolverLivro(codigoEmprestimo)){
                    salvar.salvarLogEmprestimo("logEmprestimo.ser");
                }else {
                    System.out.println("Erro na devolução");
                }
                
            } else if (opcao == 5){
                System.out.println("\nBUSCAR LIVRO\n");
                int filtroInt;
                String filtroString;
                System.out.print("Você deseja buscar um livro por código, titulo ou categoria?");
                scanner.nextLine();
                String buscarPor = scanner.nextLine().toUpperCase();

                if(buscarPor.equals("CODIGO")){
                    System.out.print("Digite o código do livro: ");
                    filtroInt = scanner.nextInt();
                    biblioteca.pesquisarLivroCodigo(filtroInt);

                } else if (buscarPor.equals("TITULO")){
                    System.out.print("Digite o Título do livro: ");
                    filtroString = scanner.nextLine();
                    biblioteca.pesquisarLivroTitulo(filtroString);

                } else if (buscarPor.equals("CATEGORIA")){
                    System.out.print("Digite a Categoria do livro: ");
                    filtroString = scanner.nextLine();
                   biblioteca.pesquisarLivroCategoria(filtroString);

                } else {
                    System.out.println("Não é possivel buscar um livro por " + buscarPor);
                }

            } else if(opcao == 6){
                System.out.println("\nBUSCAR USUARIO\n");
                int filtroInt;
                String filtroString;
                System.out.print("Você deseja buscar um USUARIO por código ou nome?");
                scanner.nextLine();
                String buscarPor = scanner.nextLine().toUpperCase();

                if(buscarPor.equals("CODIGO")){
                    System.out.print("Digite o código do USUARIO: ");
                    filtroInt = scanner.nextInt();
                    biblioteca.pesquisarUsuarioCodigo(filtroInt);

                } else if (buscarPor.equals("NOME")){
                    System.out.print("Digite o nome do USUARIO: ");
                    filtroString = scanner.nextLine();
                    biblioteca.pesquisarUsuarioNome(filtroString);
                } else {
                    System.out.println("Não é possivel buscar um USUARIO por " + buscarPor);
                }

            } else if(opcao == 7){
                //LOGS LIVRO
                System.out.println();
                try {
                    FileInputStream fileIn = new FileInputStream("logLivro.ser");
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                
                    List<Log> logs = (List<Log>) objectIn.readObject();
                
                    System.out.println("Logs de Livro:");
                    for (Log log : logs) {
                        if (log instanceof LogLivro) {
                            LogLivro logLivro = (LogLivro) log;
                            System.out.println(logLivro);
                            // Acesse os atributos específicos de LogLivro se necessário
                        }
                    }
                    
                    objectIn.close();
                    fileIn.close();
                } catch (Exception e) {
                    System.out.println("Erro ao ler os logs: " + e.getMessage());
                }
                
            } else if(opcao == 8){
                //LOGS USER
                try {
                    FileInputStream fileIn = new FileInputStream("logUser.ser");
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                
                    List<Log> logs = (List<Log>) objectIn.readObject();
                
                    System.out.println("Logs de Usuário:");
                    for (Log log : logs) {
                        if (log instanceof LogUsuario) {
                            LogUsuario logUsuario = (LogUsuario) log;
                            System.out.println(logUsuario);
                            // Acesse os atributos específicos de LogLivro se necessário
                        }
                    }
                    objectIn.close();
                    fileIn.close();
                } catch (Exception e) {
                    System.out.println("Erro ao ler os logs: " + e.getMessage());
                }
            } else if(opcao == 9){
                //LOGS EMPRESTIMO
                try {
                    FileInputStream fileIn = new FileInputStream("logEmprestimo.ser");
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                
                    List<Log> logs = (List<Log>) objectIn.readObject();
                
                    System.out.println("Logs de Empréstimo:");
                    for (Log log : logs) {
                        LogEmprestimo logEmprestimo = (LogEmprestimo) log;
                        System.out.println(logEmprestimo.getMensagem() + logEmprestimo.getEmprestimo());
                    }
                    objectIn.close();
                    fileIn.close();
                } catch (Exception e) {
                    System.out.println("Erro ao ler os logs: " + e.getMessage());
                }
            } else if(opcao == 200){
                System.out.println("\n200- Listar livros \n");
                biblioteca.listarTodosLivros();
            } else if(opcao == 201){
                System.out.println("\n201- Listar usuários \n");
                biblioteca.listarTodosUsuarios();
            } else if(opcao == 202){
                System.out.println("\n202- Listar empréstimos \n");
                biblioteca.listarTodosEmprestimos();
            }
            else {
                System.out.println("\n----------------------- \nOpção inválida!!\n -----------------------");
            }   
        }
    }
}
