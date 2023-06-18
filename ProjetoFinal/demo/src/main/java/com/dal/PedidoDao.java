package com.dal;

import java.util.List;
import javax.persistence.*;
import com.model.Pedido;
public abstract class PedidoDao{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    private static EntityManager em = emf.createEntityManager();
  
    public static void cadastrarPedido(Pedido pedido) {
        try{
            //Inicia a transação no banco de dados
            em.getTransaction().begin();
            //Realiza o cadastro do objeto no banco
            em.persist(pedido);
            //Confirma a transação caso tudo esteja correto
            em.getTransaction().commit();     
        } catch (Exception e) {
            //desfaz a transação em caso de erro
            em.getTransaction().rollback();
        }
    }
    
    public static void alteraPedido(Pedido pedido) {
        try{
            em.getTransaction().begin();
            //Altera o objeto no banco de dados
            em.merge(pedido);
            em.getTransaction().commit();     
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static List<Pedido> listarPedidos() {
        try{
            em.getTransaction().begin();
            //Seleciona todos os registros no banco
            Query sql = em.createQuery("SELECT p FROM Pedido p");
            //Adiciona todos os registros em uma lista
            List<Pedido> pedidos = sql.getResultList();
            em.getTransaction().commit();    
            return pedidos;
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public static Pedido buscarPedido(int id) {
        try {
            em.getTransaction().begin();
            //Busca um registro por ID, o atributo Pedido.class serve pra identificar que é uma classe do tipo Pedido
            Pedido pedido = em.find(Pedido.class, id);
            em.getTransaction().commit();
            return pedido;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    public static List<Pedido> buscarPedidoPorNome(String nome) {
        try {
            em.getTransaction().begin();
            //Cria uma Query para buscar por nome no banco de dados 
            Query sql = em.createQuery("SELECT p FROM Pedido p WHERE p.nome LIKE :nome");
            //Seta o parametro criado na quero ":nome" para seguir os critérios abaixo ("nome_da_tabela", String_de_consulta) os simbolos de % servem para indicar que pode ser qualquer coisa antes ou qualquer coisa depois
            sql.setParameter("nome", "%" + nome + "%");
            //Adiciona o resultado em uma lista
            List<Pedido> pedidos = sql.getResultList();
            em.getTransaction().commit();    
            return pedidos;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    public static void deletaPedido(Pedido pedido) {
        try {
            em.getTransaction().begin();
            //Pega o objeto pedido e gera uma instância do banco de dados do objeto
            pedido = em.merge(pedido);
            //Remove o objeto instanciado
            em.remove(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }      
    }
}
