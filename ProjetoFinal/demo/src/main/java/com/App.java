package com;

import java.time.LocalDateTime;

import com.controller.*;
import com.model.*;

public class App {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Produto produto = new Produto();
        Produto produto1 = new Produto();
        Produto produto2 = new Produto();
        Produto produto3 = new Produto();
        Produto produto4 = new Produto();
        Pedido pedido = new Pedido();
        
        try {

            cliente = new Cliente("Tiago", "Nigro", 22, "123456789", "Rua", "8000000", "@gmail.com", "419999999",
                    "Rua");
           ClienteController.cadastrarCliente(cliente);
            cliente = ClienteController.buscarClientePorCPF("123456789");

            produto1 = new Produto("tv", 100, 30.0, "eletronico");
            produto2 = new Produto("mouse", 100, 30.0, "eletronico");
            produto3 = new Produto("notebook", 100, 30.0, "eletronico");
            produto4 = new Produto("teclado", 100, 30.0, "eletronico");

            
            
            ProdutoController.cadastrarProduto(produto1);
            ProdutoController.cadastrarProduto(produto2);
            ProdutoController.cadastrarProduto(produto3);
            ProdutoController.cadastrarProduto(produto4);
            
            produto1 = ProdutoController.buscarProdutoPorNome("tv");
            produto2 = ProdutoController.buscarProdutoPorNome("mouse");
            produto3 = ProdutoController.buscarProdutoPorNome("notebook");
            produto4 = ProdutoController.buscarProdutoPorNome("teclado");

            pedido = new Pedido(LocalDateTime.now());
            pedido.setCliente(cliente);

            PedidoController.adicionarNoCarrinho(pedido, produto1, 1);
            PedidoController.adicionarNoCarrinho(pedido, produto2, 4);
            PedidoController.adicionarNoCarrinho(pedido, produto3, 2);
            PedidoController.adicionarNoCarrinho(pedido, produto4, 1);

            PedidoController.cadastrarPedido(pedido);

            pedido = PedidoController.buscarPedidoPorId(1);

            PedidoController.alterarCarrinho(pedido, produto4, 0);

            System.out.println("\n\nQuantidade\n\n" + pedido.getProdutos().get(3).getQuantidadeEstoque());
            System.out.println(pedido.getProdutos());
            PedidoController.alterarPedido(pedido.getId(), pedido);

            pedido = PedidoController.buscarPedidoPorId(1);

            System.out.println(pedido);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
