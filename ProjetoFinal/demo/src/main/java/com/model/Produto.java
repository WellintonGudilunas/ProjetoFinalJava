package com.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeProduto;
    private int quantidadeEstoque;
    private Double preço;
    private String categoria;

    public Produto(String nomeProduto, int quantidadeEstoque, Double preço, String categoria) {
        this.nomeProduto = nomeProduto;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preço = preço;
        this.categoria = categoria;
    }

    public Produto() {

    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getId() {
        return id;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidaEstoque) {
        this.quantidadeEstoque = quantidaEstoque;
    }

    public Double getPreço() {
        return preço;
    }

    public void setPreço(Double preço) {
        this.preço = preço;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "\tProduto \n\nnomeProduto: " + nomeProduto + "\nid: " + id + "\nquantidadeEstoque: " + quantidadeEstoque
                + "\npreço: " + preço + "\ncategoria: " + categoria + "\n\n";
    }

}
