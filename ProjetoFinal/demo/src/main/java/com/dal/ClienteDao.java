package com.dal;

import java.util.List;
import javax.persistence.*;
import com.model.Cliente;
public abstract class ClienteDao{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    private static EntityManager em = emf.createEntityManager();
  
    public static void cadastrarCliente(Cliente cliente) {
        try{
            //Inicia a transação no banco de dados
            em.getTransaction().begin();
            //Realiza o cadastro do objeto no banco
            em.persist(cliente);
            //Confirma a transação caso tudo esteja correto
            em.getTransaction().commit();     
        } catch (Exception e) {
            //desfaz a transação em caso de erro
            em.getTransaction().rollback();
        }
    }
    
    public static void alteraCliente(Cliente cliente) {
        try{
            em.getTransaction().begin();
            //Altera o objeto no banco de dados
            em.merge(cliente);
            em.getTransaction().commit();     
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static List<Cliente> listarClientes() {
        try{
            em.getTransaction().begin();
            //Seleciona todos os registros no banco
            Query sql = em.createQuery("SELECT p FROM Cliente p");
            //Adiciona todos os registros em uma lista
            List<Cliente> clientes = sql.getResultList();
            em.getTransaction().commit();    
            return clientes;
            
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public static Cliente buscarCliente(int id) {
        try {
            em.getTransaction().begin();
            //Busca um registro por ID, o atributo Cliente.class serve pra identificar que é uma classe do tipo Cliente
            Cliente cliente = em.find(Cliente.class, id);
            em.getTransaction().commit();
            return cliente; 
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public static List<Cliente> buscarClientePorNome(String nome) {
        try {
            em.getTransaction().begin();
            //Cria uma Query para buscar por nome no banco de dados 
            Query sql = em.createQuery("SELECT p FROM Cliente p WHERE p.nome LIKE :nome");
            //Seta o parametro criado na quero ":nome" para seguir os critérios abaixo ("nome_da_tabela", String_de_consulta) os simbolos de % servem para indicar que pode ser qualquer coisa antes ou qualquer coisa depois
            sql.setParameter("nome", "%" + nome + "%");
            //Adiciona o resultado em uma lista
            List<Cliente> clientes = sql.getResultList();
            em.getTransaction().commit();    
            return clientes;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    public static void deletaCliente(Cliente cliente) {
        try {
            em.getTransaction().begin();
            //Pega o objeto cliente e gera uma instância do banco de dados do objeto
            cliente = em.merge(cliente);
            //Remove o objeto instanciado
            em.remove(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }      
    }
}
