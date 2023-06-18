package com;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.controller.*;
import com.model.Cliente;
import com.model.Funcionario;
import com.model.Pedido;
import com.model.Produto;


public class App {
    public static void main( String[] args ) {
        Cliente cliente = new Cliente("tiago","asd",101,"101", "asd", "asd", "@asd", 123, "asd");
        Funcionario funcionario = new Funcionario("asd","asd",101,"101", "asd", "asd", "@asd", 123, 123,"asd");
        Produto teclado = new Produto("teclado", 1, 2.0, "asd");
        Produto mouse = new Produto("mouse", 2, 2.0, "asd");
        Produto tv = new Produto("tv", 2, 2.0, "asd");
        Pedido pedido = new Pedido();
        try {
            mouse = ProdutoController.buscarProdutoPorNome("mouse");
            tv = ProdutoController.buscarProdutoPorNome("tv");
            teclado = ProdutoController.buscarProdutoPorNome("teclado");
            

            pedido.setCliente(cliente);
            PedidoController.adicionarNoCarrinho(pedido, tv, 50);
            PedidoController.adicionarNoCarrinho(pedido, teclado, 1);
            PedidoController.adicionarNoCarrinho(pedido, mouse, 1);

            PedidoController.cadastrarPedido(pedido);
            
            
            


            //PedidoController.adicionarNoCarrinho(pedido, mouse, 5);

            //PedidoController.alterarPedido(pedido.getId(), pedido);
            //pedido = PedidoController.buscarPedidoPorId(2);


            System.out.println("DADOS\n\n\n");
            System.out.println(pedido.getProdutos());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
