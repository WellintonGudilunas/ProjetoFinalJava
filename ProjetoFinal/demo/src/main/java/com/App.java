package com;

import java.time.LocalDateTime;

import com.controller.*;
import com.model.*;

public class App {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Produto produto1 = new Produto();
        Produto produto2 = new Produto("mouse", 50, 60.0, "eletronico");
        Produto produto3 = new Produto("notebook", 20, 1500.0, "eletronico");
        Produto produto4 = new Produto("teclado", 60, 101.0, "eletronico");
        Pedido pedido = new Pedido();
        try {

            //cliente = new Cliente("Tiago", "André", 22, "123456789", "Rua", "8000000", "@gmail.com", "419999999", "Rua");
            //ClienteController.cadastrarCliente(cliente);
            cliente = ClienteController.buscarClientePorCPF("123456789");

            //produto1 = new Produto("tv", 100, 30.0, "eletronico");

            //ProdutoController.cadastrarProduto(produto1);

            produto1 = ProdutoController.buscarProdutoPorNome("tv");

            // ProdutoController.cadastrarProduto(produto2);
            // ProdutoController.cadastrarProduto(produto3);
            // ProdutoController.cadastrarProduto(produto4);

            produto2 = ProdutoController.buscarProdutoPorNome("mouse");
            produto3 = ProdutoController.buscarProdutoPorNome("notebook");
            produto4 = ProdutoController.buscarProdutoPorNome("teclado");

            // pedido = new Pedido(LocalDateTime.now());
            // pedido.setCliente(cliente);

            // PedidoController.adicionarNoCarrinho(pedido, produto1, 1);
            // PedidoController.adicionarNoCarrinho(pedido, produto2, 1);
            // PedidoController.adicionarNoCarrinho(pedido, produto3, 1);
            // PedidoController.adicionarNoCarrinho(pedido, produto4, 1);

            // PedidoController.cadastrarPedido(pedido);

            

            pedido = PedidoController.buscarPedidoPorId(2);
            
            // PedidoController.alterarCarrinho(pedido, produto4, 10);

            System.out.println("\n\nQuantidade\n\n"+pedido.getProdutos().get(3).getQuantidadeEstoque());
            //System.out.println(pedido.getProdutos());
            PedidoController.alterarPedido(pedido.getId(), pedido);

            pedido = PedidoController.buscarPedidoPorId(2);

            System.out.println(pedido);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
