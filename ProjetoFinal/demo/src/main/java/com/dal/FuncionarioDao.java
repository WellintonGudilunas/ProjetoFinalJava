package com.dal;

import java.util.List;
import javax.persistence.*;
import com.model.Funcionario;
public abstract class FuncionarioDao{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    private static EntityManager em = emf.createEntityManager();
  
    public static void cadastrarFuncionario(Funcionario funcionario) {
        try{
            //Inicia a transação no banco de dados
            em.getTransaction().begin();
            //Realiza o cadastro do objeto no banco
            em.persist(funcionario);
            //Confirma a transação caso tudo esteja correto
            em.getTransaction().commit();     
        } catch (Exception e) {
            //desfaz a transação em caso de erro
            em.getTransaction().rollback();
        }
    }
    
    public static void alteraFuncionario(Funcionario funcionario) {
        try{
            em.getTransaction().begin();
            //Altera o objeto no banco de dados
            em.merge(funcionario);
            em.getTransaction().commit();     
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static List<Funcionario> listarFuncionarios() {
        try{
            em.getTransaction().begin();
            //Seleciona todos os registros no banco
            Query sql = em.createQuery("SELECT p FROM Funcionario p");
            //Adiciona todos os registros em uma lista
            List<Funcionario> funcionarios = sql.getResultList();
            em.getTransaction().commit();    
            return funcionarios;
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public static Funcionario buscarFuncionario(int id) {
        try {
            em.getTransaction().begin();
            //Busca um registro por ID, o atributo Funcionario.class serve pra identificar que é uma classe do tipo Funcionario
            Funcionario funcionario = em.find(Funcionario.class, id);
            em.getTransaction().commit();
            return funcionario; 
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public static List<Funcionario> buscarFuncionarioPorNome(String nome) {
        try {
            em.getTransaction().begin();
            //Cria uma Query para buscar por nome no banco de dados 
            Query sql = em.createQuery("SELECT p FROM Funcionario p WHERE p.nome LIKE :nome");
            //Seta o parametro criado na quero ":nome" para seguir os critérios abaixo ("nome_da_tabela", String_de_consulta) os simbolos de % servem para indicar que pode ser qualquer coisa antes ou qualquer coisa depois
            sql.setParameter("nome", "%" + nome + "%");
            //Adiciona o resultado em uma lista
            List<Funcionario> funcionarios = sql.getResultList();
            em.getTransaction().commit();    
            return funcionarios;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    public static void deletaFuncionario(Funcionario funcionario) {
        try {
            em.getTransaction().begin();
            //Pega o objeto funcionario e gera uma instância do banco de dados do objeto
            funcionario = em.merge(funcionario);
            //Remove o objeto instanciado
            em.remove(funcionario);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }      
    }
}
