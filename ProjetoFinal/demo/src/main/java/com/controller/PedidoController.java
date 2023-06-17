package com.controller;

import java.util.List;

import com.dal.PedidoDao;
import com.model.Pedido;

public abstract class PedidoController {

    public static void cadastrarPedido(Pedido pedido) {
        try {
            PedidoDao.cadastrarPedido(pedido);
        } catch (Exception e) {
            System.out.println("Erro ao realizar o cadastro");
        }
    }

    public static void alterarPedido(Pedido pedido) {
        try {
            if(PedidoDao.buscarPedido(pedido.getId()) != null){
                PedidoDao.alteraPedido(pedido);
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar o cadastro");
        }
    }

    public static void deletarPedido(int id) {
        try {
            if(PedidoDao.buscarPedido(id) != null){
                PedidoDao.deletaPedido(PedidoDao.buscarPedido(id));
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar o cadastro");
        }
    }

    public static List<Pedido> listarPedidos() {
        try {
            return PedidoDao.listarPedidos();
        } catch (Exception e) {
            System.out.println("Erro ao listar o cadastro");
            return null;
        }
    }

    public static Pedido buscarPedidoPorId(int id) {
        try {
            if(id == 0) throw new Exception();
            return PedidoDao.buscarPedido(id);      
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cadastro");
            return null;
        }
    }

    public static List<Pedido> buscarPedidoPorNome(String nome) {
        try {
            return PedidoDao.buscarPedidoPorNome(nome);      
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cadastro");
            return null;
        }
    }
}
