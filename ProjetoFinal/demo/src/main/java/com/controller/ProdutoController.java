package com.controller;

import java.util.List;

import com.dal.ProdutoDao;
import com.model.Produto;

public abstract class ProdutoController {

    private static boolean produtoExiste(Produto produto) throws Exception {
        String nome = produto.getNomeProduto();
        return buscarProdutoPorNome(nome) != null;
    }

    public static void cadastrarProduto(Produto produto) throws Exception {
        try {
            if(produtoExiste(produto)){
                throw new Exception("Produto já tem um cadastrado");
            }
            ProdutoDao.cadastrarProduto(produto);
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar produto, CAUSA:" + e.getMessage());
        }
    }

    public static void alterarProduto(int id, Produto produto) throws Exception {
        Produto prevProduto = buscarProdutoPorId(id);
        if (prevProduto == null) {
            return;
        }
        prevProduto = produto;
        prevProduto.setId(id);
        try {

            ProdutoDao.alteraProduto(produto);

        } catch (Exception e) {
            throw new Exception("Erro ao alterar produto, CAUSA:" + e.getMessage());
        }

    }

    public static void alterarProdutos(List<Produto> produtos) throws Exception {
        try {
            ProdutoDao.alteraProduto(produtos);
        } catch (Exception e) {
            throw new Exception("Erro ao alterar produto, CAUSA:" + e.getMessage());
        }

    }

    public static void deletarProduto(int id) throws Exception {
        try {
            if (ProdutoDao.buscarProduto(id) != null) {
                ProdutoDao.deletaProduto(ProdutoDao.buscarProduto(id));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao deletar produto, CAUSA:" + e.getMessage());
        }

    }

    public static List<Produto> listarProdutos() throws Exception {
        try {
            return ProdutoDao.listarProdutos();
        } catch (Exception e) {
            throw new Exception("Erro ao listar produto, CAUSA:" + e.getMessage());
        }
    }

    public static Produto buscarProdutoPorId(int id) throws Exception {
        try {
            if (id == 0)
                throw new Exception();
            return ProdutoDao.buscarProduto(id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar produto, CAUSA:" + e.getMessage());
        }
    }

    public static Produto buscarProdutoPorNome(String nomeProduto) throws Exception {
        try {
            return ProdutoDao.buscarProdutoPorNome(nomeProduto.toLowerCase());
        } catch (Exception e) {
            throw new Exception("Erro ao buscar produto, CAUSA:" + e.getMessage());
        }

    }

}
