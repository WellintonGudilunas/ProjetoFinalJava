package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private int idPedido;

    private double valorTotal;
    private LocalDateTime dataPedido;
    private Double desconto = 0.00;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedidoId")
    private List<PedidoProduto> produtos = new ArrayList<>();

    public Pedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public boolean addProduto(Produto produto, int quantidade) {
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setPedido(this);
        pedidoProduto.setProduto(produto);
        pedidoProduto.setQuantidade(quantidade);
        for (PedidoProduto pp : produtos) {
            if (pp.getProduto().getNomeProduto().equals(produto.getNomeProduto())) {
                return false;
            }
        }
        return produtos.add(pedidoProduto);
    }

    public boolean alteraProduto(Produto produto, int quantidade) {
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setPedido(this);
        pedidoProduto.setProduto(produto);
        pedidoProduto.setQuantidade(quantidade);
        for (PedidoProduto pp : produtos) {
            if (pp.getProduto().getNomeProduto().equals(produto.getNomeProduto())) {
                produtos.remove(pp);
                produtos.add(pedidoProduto);
                return true;
            }
        }
        return false;
    }

    public boolean removerProduto(Produto produto) {
        for (PedidoProduto pp : produtos) {
            if (pp.getProduto().getNomeProduto().equals(produto.getNomeProduto())) {
                return produtos.remove(pp);
            }
        }
        return false;
    }

    public int getQuantidade(Produto produto) {
        for (PedidoProduto pp : produtos) {
            if (pp.getProduto().getNomeProduto().equals(produto.getNomeProduto())) {
                return pp.getQuantidade();
            }
        }
        return 0;
    }

    public Pedido() {
    }

    public List<Produto> getProdutos() {
        List<Produto> lista = new ArrayList<>();
        for (PedidoProduto pp : produtos) {
            lista.add(pp.getProduto());
        }
        return lista;
    }

    public void setId(int id) {
        this.idPedido = id;
    }

    public int getId() {
        return idPedido;
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

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido [idPedido=" + idPedido + ", valorTotal=" + valorTotal + ", dataPedido=" + dataPedido
                + ", desconto=" + desconto + ", cliente=" + cliente + ", produtos=" + produtos + "]";
    }

}

@Entity
class PedidoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produtoId")
    private Produto produto;

    private int quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "PedidoProduto [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade
                + "]";
    }

}