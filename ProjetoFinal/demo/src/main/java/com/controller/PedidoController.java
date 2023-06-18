package com.controller;

import java.util.List;

import com.dal.PedidoDao;
import com.model.*;

public abstract class PedidoController {

    public static void cadastrarPedido(Pedido pedido) throws Exception {
        try {
            List<Produto> produtos = pedido.getProdutos();

            ProdutoController.alterarProdutos(produtos);
            PedidoDao.cadastrarPedido(pedido);
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar pedido, CAUSA:" + e.getMessage());
        }
    }

    public static void adicionarNoCarrinho(Pedido pedido, Produto produto, int quantidade) throws Exception {
        if (produto.getQuantidadeEstoque() < quantidade)
            throw new Exception("Erro, não há estoque disponível para o produto \"" + produto.getNomeProduto() + "\".");
        try {
            if (!pedido.addProduto(produto, quantidade))
                throw new Exception("Produto já consta no carrinho");
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar produto, CAUSA:" + e.getMessage());
        }
    }

    public static void alterarCarrinho(Pedido pedido, Produto produto, int quantidade) throws Exception {
        if (produto.getQuantidadeEstoque() < quantidade)
            throw new Exception("Erro, não há estoque disponível para o produto \"" + produto.getNomeProduto() + "\".");
        try {
            if (!pedido.alteraProduto(produto, quantidade))
                throw new Exception("Produto não consta no carrinho");
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar produto, CAUSA:" + e.getMessage());
        }
    }

    public static void removerDoCarrinho(Pedido pedido, Produto produto) throws Exception {
        try {
            int qnt = pedido.getQuantidade(produto);
            if (!pedido.removerProduto(produto))
                throw new Exception("Produto não consta no carrinho");
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + qnt);
        } catch (Exception e) {
            throw new Exception("Erro ao remover produto, CAUSA:" + e.getMessage());
        }
    }

    public static void alterarPedido(int id, Pedido pedido) throws Exception {
        try {
            Pedido prevPedido = PedidoController.buscarPedidoPorId(id);
            if (prevPedido == null) {
                return;
            }
            prevPedido = pedido;
            prevPedido.setId(id);
            if (PedidoDao.buscarPedido(pedido.getId()) != null) {
                PedidoDao.alteraPedido(pedido);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao alterar pedido, CAUSA:" + e.getMessage());
        }
    }

    public static void deletarPedido(int id) throws Exception {
        try {
            if (PedidoDao.buscarPedido(id) != null) {
                PedidoDao.deletaPedido(PedidoDao.buscarPedido(id));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao deletar pedido, CAUSA:" + e.getMessage());
        }
    }

    public static List<Pedido> listarPedidos() throws Exception {
        try {
            return PedidoDao.listarPedidos();
        } catch (Exception e) {
            throw new Exception("Erro ao listar pedido, CAUSA:" + e.getMessage());
        }
    }

    public static Pedido buscarPedidoPorId(int id) throws Exception {
        try {
            if (id == 0)
                throw new Exception();
            return PedidoDao.buscarPedido(id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar pedido, CAUSA:" + e.getMessage());
        }
    }

    public static List<Pedido> buscarPedidoPorNome(String nome) throws Exception {
        try {
            return PedidoDao.buscarPedidoPorNome(nome);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar pedido, CAUSA:" + e.getMessage());
        }
    }
}
