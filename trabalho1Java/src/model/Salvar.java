package model;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import controller.Emprestimo;
import controller.Livro;
import controller.Usuario;

public class Salvar implements Serializable {
    private List<LogUsuario> logUsuarios;
    private List<LogLivro> logLivros;
    private List<LogEmprestimo> logEmprestimos;

    public Salvar() {
        logUsuarios = new ArrayList<>();
        logLivros = new ArrayList<>();
        logEmprestimos = new ArrayList<>();
    }

    public void adicionarLogUsuario(String mensagem, LocalDateTime data, Usuario usuario) {
        LogUsuario log = new LogUsuario(mensagem, data, usuario);
        logUsuarios.add(log);
    }

    public void adicionarLogLivro(String mensagem, LocalDateTime data, Livro livro ) {
        LogLivro log = new LogLivro(mensagem, data, livro);
        logLivros.add(log);
    }

    public void adicionarLogEmprestimo(String mensagem, LocalDateTime data, Emprestimo emprestimo ) {
        LogEmprestimo log = new LogEmprestimo(mensagem, data, emprestimo);
        logEmprestimos.add(log);
    }

    public void salvarLogLivro(String nomeArquivo) {
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
    
            List<Log> logs = new ArrayList<>(logLivros);
    
            objectOut.writeObject(logs);
            System.out.println("Logs de livro salvos com sucesso em " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar os logs de livro: " + e.getMessage());
        }
    }

    public void salvarLogUsuario(String nomeArquivo) {
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
    
            List<Log> logs = new ArrayList<>(logUsuarios);
    
            objectOut.writeObject(logs);
            System.out.println("Logs de usuários salvos com sucesso em " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar os logs de usuário: " + e.getMessage());
        }
    }

    public void salvarLogEmprestimo(String nomeArquivo) {
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
    
            List<Log> logs = new ArrayList<>(logEmprestimos);
    
            objectOut.writeObject(logs);
            System.out.println("Logs de Empréstimos salvos com sucesso em " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar os logs de Empréstimos: " + e.getMessage());
        }
    }
}