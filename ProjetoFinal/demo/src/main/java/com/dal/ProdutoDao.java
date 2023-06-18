package com.dal;

import java.util.List;
import javax.persistence.*;
import com.model.Produto;

public abstract class ProdutoDao {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    private static EntityManager em = emf.createEntityManager();

    public static void cadastrarProduto(Produto produto) {
        try {
            // Inicia a transação no banco de dados
            em.getTransaction().begin();
            // Realiza o cadastro do objeto no banco
            em.persist(produto);
            // Confirma a transação caso tudo esteja correto
            em.getTransaction().commit();
        } catch (Exception e) {
            // desfaz a transação em caso de erro
            em.getTransaction().rollback();
        }
    }

    public static void alteraProduto(Produto produto) {
        try {
            em.getTransaction().begin();
            // Altera o objeto no banco de dados
            em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void alteraProduto(List<Produto> produtos) {
        try {
            em.getTransaction().begin();
            for (Produto produto : produtos) {
                // Altera o objeto no banco de dados
                em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static List<Produto> listarProdutos() {
        try {
            em.getTransaction().begin();
            // Seleciona todos os registros no banco
            Query sql = em.createQuery("SELECT p FROM Produto p");
            // Adiciona todos os registros em uma lista
            List<Produto> produtos = sql.getResultList();
            em.getTransaction().commit();
            return produtos;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public static Produto buscarProduto(int id) {
        try {
            em.getTransaction().begin();
            // Busca um registro por ID, o atributo Produto.class serve pra identificar que
            // é uma classe do tipo Produto
            Produto produto = em.find(Produto.class, id);
            em.getTransaction().commit();
            return produto;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public static Produto buscarProdutoPorNome(String nomeProduto) {
        try {
            em.getTransaction().begin();
            // Cria uma Query para buscar por nome no banco de dados
            Query sql = em.createQuery("SELECT p FROM Produto p WHERE p.nomeProduto LIKE :nomeProduto");
            // Seta o parametro criado na quero ":nome" para seguir os critérios abaixo
            // ("nome_da_tabela", String_de_consulta) os simbolos de % servem para indicar
            // que pode ser qualquer coisa antes ou qualquer coisa depois
            sql.setParameter("nomeProduto", "%" + nomeProduto + "%");
            // Adiciona o resultado em uma lista
            Produto produto = (Produto) sql.getSingleResult();
            em.getTransaction().commit();
            return produto;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    public static void deletaProduto(Produto produto) {
        try {
            em.getTransaction().begin();
            // Pega o objeto produto e gera uma instância do banco de dados do objeto
            produto = em.merge(produto);
            // Remove o objeto instanciado
            em.remove(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }
    }
}
