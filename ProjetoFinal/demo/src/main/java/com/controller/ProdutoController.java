package com.controller;

import java.util.List;

import com.dal.ProdutoDao;
import com.model.Produto;

public abstract class ProdutoController {

    public static void cadastrarProduto(Produto produto) throws Exception {
        try {
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
            throw new Exception("Erro ao alterar pedido, CAUSA:" + e.getMessage());
        }

    }

    public static void alterarProdutos(List<Produto> produtos) throws Exception {
        try {
            ProdutoDao.alteraProduto(produtos);
        } catch (Exception e) {
            throw new Exception("Erro ao alterar pedido, CAUSA:" + e.getMessage());
        }

    }

    public static void deletarProduto(int id) throws Exception {
        try {
            if (ProdutoDao.buscarProduto(id) != null) {
                ProdutoDao.deletaProduto(ProdutoDao.buscarProduto(id));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao deletar pedido, CAUSA:" + e.getMessage());
        }

    }

    public static List<Produto> listarProdutos() throws Exception {
        try {
            return ProdutoDao.listarProdutos();
        } catch (Exception e) {
            throw new Exception("Erro ao listar pedido, CAUSA:" + e.getMessage());
        }
    }

    public static Produto buscarProdutoPorId(int id) throws Exception {
        try {
            if (id == 0)
                throw new Exception();
            return ProdutoDao.buscarProduto(id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar pedido, CAUSA:" + e.getMessage());
        }
    }

    public static Produto buscarProdutoPorNome(String nome) throws Exception {
        try {
            return ProdutoDao.buscarProdutoPorNome(nome);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar pedido, CAUSA:" + e.getMessage());
        }

    }

}
