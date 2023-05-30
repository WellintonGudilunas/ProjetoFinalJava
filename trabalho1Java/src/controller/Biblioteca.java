package controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Log;
import model.LogLivro;
import model.LogUsuario;
import model.Salvar;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<Livro>();
    private List<Usuario> usuarios = new ArrayList<>();
    public List<Emprestimo> emprestimos = new ArrayList<>();

    private Salvar salvar;
    
    public Biblioteca() {
        this.salvar = new Salvar();
    }

    // EMPRESTAR LIVRO ---------------------------------------
    public boolean emprestarLivro(Emprestimo emprestimo){
        Usuario usuario = emprestimo.getUsuario();
        Livro livroEmprestar = emprestimo.getLivro();

       if(livroEmprestar == null){
            System.out.println("Livro não encontrado");
            return false;
       }
       if(usuario == null){
            System.out.println("Usuário não encontrado");
            return false;
       }
       if(usuario.temLivroEmprestado() == true){
            System.out.println("O usuário já tem um livro emprestado");
            return false;
       }
       if(livroEmprestar.getQuantidadeDisponivel() > 0){
            LocalDateTime dataEmprestimo = LocalDateTime.now();
            LocalDateTime dataDevolucao = dataEmprestimo.plusDays(7);
            livroEmprestar.setQuantidadeDisponivel(livroEmprestar.getQuantidadeDisponivel() - 1);
            livroEmprestar.setQuantidadeVezesEmprestado(livroEmprestar.getQuantidadeVezesEmprestado() + 1);
            usuario.setLivroEmprestado(true);
            this.emprestimos.add(emprestimo);
        } else {
            System.out.println("Não há livro em estoq");
            return false;
        }
        return true;
    }

    //----------------------------------------------------------

    // DEVOLVER LIVRO 
    public boolean devolverLivro(int codigoEmprestimo){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            boolean encontrouEmprestimo = false;
            for (Emprestimo emprestimo : this.emprestimos) {
                if(emprestimo.getCodigoEmprestimo() == codigoEmprestimo){
                    // emprestimo.setDataDevolucao(LocalDateTime.now());
                    emprestimo.getUsuario().setLivroEmprestado(false);
                    emprestimo.getLivro().setQuantidadeDisponivel(emprestimo.getLivro().getQuantidadeDisponivel() + 1);
                    emprestimo.setDataDevolucaoEfetiva(LocalDateTime.now());
                    this.emprestimos.remove(emprestimo);
                    encontrouEmprestimo = true;
                    return true;
                }
            }
            if(!encontrouEmprestimo){
                System.out.println("Não encontrou empréstimo");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
        return false;

    // PODE SER FEITO DAS DUAS MANEIRAS *************************
    // A MANEIRA DE CIMA É MAIS COMPLEXA POREM MAIS ENXUTA

    /*Emprestimo emprestimo = pesquisarEmprestimoCodigo2(codigoEmprestimo);
    Usuario usuario = emprestimo.getUsuario();
    Livro livro = emprestimo.getLivro();
    if(livro == null){
        System.out.println("Livro não encontrado");
        return false;
    }
    if(usuario == null){
        System.out.println("Usuário não encontrado");
        return false;
    }
    livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
    usuario.setLivroEmprestado(false);
    emprestimo.setDataDevolucao(LocalDateTime.now());
    this.emprestimos.remove(emprestimo);
    return true;*/
    }

    //--------------------------------------------------------------------

    // CADASTRAR LIVRO / LIVROS --------------------------------------------

    public void cadastrarLivro (Livro livro){
        this.livros.add(livro);
    }

    public void cadastrarLivro (List<Livro> livrosParaCadastrar){
        for (Livro livro: livrosParaCadastrar) {
            this.livros.add(livro);
        }
    }

    //----------------------------------------------------------------------------

    // CADASTRAR USUARIO / USUARIOS --------------------------------------------
    public void cadastrarUsuario (Usuario user){
        this.usuarios.add(user);
    }

    public void cadastrarUsuarios (List<Usuario> usuariosParaCadastrar){
        for (Usuario user: usuariosParaCadastrar) {
            this.usuarios.add(user);
        }
    }
    // -------------------------------------------------------------------------

    // PESQUISAR EMPRESTIMO POR -------------------------------------------------
    public void pesquisarEmprestimoCodigo(int codigo){ // TRY - PRINTA UM EMPRESTIMO
        try {
            boolean emprestimoEncontrado = false;
            if(codigo > 0){
                for (Emprestimo emprestimo : this.emprestimos) {
                    if(emprestimo.getCodigoEmprestimo() == codigo){
                        System.out.println("\n Empréstimo Encontrado!!!");
                        System.out.println(emprestimo);
                        emprestimoEncontrado = true;
                    }
                    
                }
            }
            if (!emprestimoEncontrado) {
                System.out.println("\nEmpréstimo não encontrado!!!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public Emprestimo pesquisarEmprestimoCodigo2(int codigo){ // TRY CATCH - RETORNA UM EMPRESTIMO ENVES DE PRINTA
        try {   
            if(codigo > 0){
                for (Emprestimo emprestimo : this.emprestimos) {
                    if(emprestimo.getCodigoEmprestimo() == codigo){
                        return emprestimo;
                    }
                    
                }
            } 
            return null;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }    
    }
    // ------------------------------------------------------------------------------


    // PESQUISAR LIVRO POR ------------------------------------------------------

    public void pesquisarLivroCodigoLog(int codigo) {
        try {
            FileInputStream fileIn = new FileInputStream("logLivro.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
        
            boolean livroEncontrado = false;

            for (Log log : logs) {
                    LogLivro logLivro = (LogLivro) log;
                    if(logLivro.getLivro().getCodigo() == codigo){
                        System.out.println(logLivro.getLivro());
                        livroEncontrado = true;
                    }
            }

            if (!livroEncontrado) {
                System.out.println("\nLivro não encontrado!!!");
            }

            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os logs: " + e.getMessage());
        }
    }

    public void pesquisarLivroCodigo(int codigo){
        boolean livroEncontrado = false;

        if(codigo > 0){
            for (Livro livro : this.livros) {
                if(livro.getCodigo() == codigo){
                    System.out.println("\nLivro Encontrado!!!");
                    System.out.println(livro);
                    livroEncontrado = true;
                }
                
            }
        }
        if (!livroEncontrado) {
            System.out.println("\nLivro não encontrado!!!");
        }
    }
    
    public void pesquisarLivroTitulo(String titulo){ // JA TEM TRY CATCH

        try {
            boolean livroEncontrado = false;

            if(titulo != null){
                for (Livro livro : this.livros) {
                    if(livro.getTitulo().toUpperCase().equals(titulo.toUpperCase())){
                        System.out.println("\nLivro Encontrado!!!");
                        System.out.println(livro);
                        livroEncontrado = true;
                    }
                    
                }
            }
            if (!livroEncontrado) {
                System.out.println("\nLivro não encontrado!!!");
            }
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }

    public void pesquisarLivroCategoria(String categoria){ // JA TEM TRY CATCH

        try {
            boolean livroEncontrado = false;

            if(categoria != null){
                for (Livro livro : this.livros) {
                    if(livro.getCategoria().toUpperCase().equals(categoria.toUpperCase())){
                        System.out.println("\nLivro Encontrado!!!");
                        System.out.println(livro);
                        livroEncontrado = true;
                    }
                    
                }
            }
            if (!livroEncontrado) {
                System.out.println("\nLivro não encontrado!!!");
            }
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
   
    public Livro pesquisarLivroCodigo2(int codigo) { // TRYCATCH - RETORNA UM LIVRO ENVÉS DE PRINTAR UM LIVRO
        try {   
            if(codigo > 0){
                for (Livro livro : this.livros) {
                    if(livro.getCodigo() == codigo){
                        return livro;
                    }
                    
                }
            } 
            return null;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }    
    }
        // ------------------------------------------------------------------------------
        
        //PESQUISAR USUARIO POR -----------------------------------------------------
    public void pesquisarUsuarioCodigoLog(int codigo){

        try {
            FileInputStream fileIn = new FileInputStream("logUser.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
        
            boolean usuarioEncontrado = false;

            for (Log log : logs) {
                    LogUsuario logUsuario = (LogUsuario) log;
                    if(logUsuario.getUsuario().getCodigo() == codigo){
                        System.out.println(logUsuario.getUsuario());
                        usuarioEncontrado = true;
                        break;
                    }
            }

            if (!usuarioEncontrado) {
                System.out.println("\nUsuário não encontrado!!!");
            }

            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os logs: " + e.getMessage());
        }
    }

    public void pesquisarUsuarioCodigo(int codigo){
        boolean usuarioEncontrado = false;

        if(codigo > 0){
            for (Usuario usuario : this.usuarios) {
                if(usuario.getCodigo() == codigo){
                    System.out.println("\nUsuario Encontrado!!!");
                    System.out.println(usuario);
                    usuarioEncontrado = true;
                }
                
            }
        }

        if (!usuarioEncontrado) {
            System.out.println("\nUsuário não encontrado!!!");
        }
    }

    public void pesquisarUsuarioNome(String nome){
        boolean usuarioEncontrado = false;

        if(nome != null){
            for (Usuario usuario : this.usuarios) {
                if(usuario.getNome().equals(nome)){
                    System.out.println("\nUsuario Encontrado!!!");
                    System.out.println(usuario);
                    usuarioEncontrado = true;
                }
                
            }
        }

        if (!usuarioEncontrado) {
            System.out.println("\nLivro não encontrado!!!");
        }
    }
    
    public Usuario pesquisarUsuarioCodigo2(int codigo) { // TRYCATCH - RETORNA UM USER ENVÉS DE PRINTAR UM USER
        try {
            if(codigo > 0){
                for (Usuario usuario : this.usuarios) {
                    if(usuario.getCodigo() == codigo){
                        return usuario;
                    }
                }
            }
            return null;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
    
    //---------------------------------------------------------------------

    // LISTAR TODOS ------------------------------------------------------------
    
    public void listarTodosLivros(){
        for (Livro livro : this.livros) {
            System.out.println(livro);
        }
    }

    public void listarTodosUsuarios(){
        for(Usuario usuario : this.usuarios){
            System.out.println(usuario);
        }
    }

    public void listarTodosEmprestimos(){
        if(this.emprestimos.size() > 0){
            for(Emprestimo emprestimo : this.emprestimos){
                System.out.println(emprestimo);
            }
        } else {
            System.out.println("Não há nenhum Empréstimo cadastrado (tempo de execução)");
        }

    }
    
    //---------------------------------------------------------------------

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    @Override
    public String toString() {
        return "Biblioteca [livros=" + this.livros + "]";
    }

}
