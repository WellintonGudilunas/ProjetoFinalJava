package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double valorTotal;
    private LocalDateTime dataPedido;
    private Double desconto = 0.00;
    
    List<Produto> produtos = new ArrayList<Produto>();
    List<String> QtdPedido = new ArrayList<String>();
   
    public Pedido(double valorTotal, LocalDateTime dataPedido, Double desconto) {
        this.valorTotal = valorTotal;
        this.dataPedido = dataPedido;
        this.desconto = desconto;

    }
    public Pedido() {
    }

    public int getId() {
        return id;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public LocalDateTime getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    public Double getDesconto() {
        return desconto;
    }
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    public List<String> getQtdPedido() {
        return QtdPedido;
    }
    public void setQtdPedido(List<String> qtdPedido) {
        QtdPedido = qtdPedido;
    }

    @Override
    public String toString() {
        return "\tPedidos \n\nid: " + id + "\nvalorTotal: " + valorTotal + "\ndataPedido: " + dataPedido + "\nprodutos: "
                + produtos + "\nQtdPedido: " + QtdPedido + "\n\n";
    }

}
