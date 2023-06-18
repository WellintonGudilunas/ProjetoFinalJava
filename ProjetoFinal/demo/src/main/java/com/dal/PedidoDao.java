package com.dal;

import java.util.List;
import javax.persistence.*;
import com.model.Pedido;

public abstract class PedidoDao {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    private static EntityManager em = emf.createEntityManager();

    public static void cadastrarPedido(Pedido pedido) throws Exception {
        try {
            // Inicia a transação no banco de dados
            em.getTransaction().begin();
            // Realiza o cadastro do objeto no banco
            em.persist(pedido);
            // Confirma a transação caso tudo esteja correto
            em.getTransaction().commit();
        } catch (Exception e) {
            // desfaz a transação em caso de erro
            em.getTransaction().rollback();
            throw new Exception("erro ao cadastrar pedido, dados Pedido:" + pedido);
        }
    }

    public static void alteraPedido(Pedido pedido) {
        try {
            em.getTransaction().begin();
            // Altera o objeto no banco de dados
            em.merge(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static List<Pedido> listarPedidos() {
        try {
            em.getTransaction().begin();
            // Seleciona todos os registros no banco
            Query sql = em.createQuery("SELECT p FROM Pedido p");
            // Adiciona todos os registros em uma lista
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
            // Busca um registro por ID, o atributo Pedido.class serve pra identificar que é
            // uma classe do tipo Pedido
            Pedido pedido = em.find(Pedido.class, id);
            em.getTransaction().commit();
            return pedido;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    public static void deletaPedido(Pedido pedido) {
        try {
            em.getTransaction().begin();
            // Pega o objeto pedido e gera uma instância do banco de dados do objeto
            pedido = em.merge(pedido);
            // Remove o objeto instanciado
            em.remove(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }
    }
}
