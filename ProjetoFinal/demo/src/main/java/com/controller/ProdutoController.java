package com.controller;

import java.util.List;

import com.dal.ProdutoDao;
import com.model.Produto;

public abstract class ProdutoController {

    public static void cadastrarProduto(Produto produto) {
        try {
            ProdutoDao.cadastrarProduto(produto);
        } catch (Exception e) {
            System.out.println("Erro ao realizar o cadastro");
        }
    }
    public static void alterarProduto(Produto produto) {
        try {
            if(ProdutoDao.buscarProduto(produto.getId()) != null){
                ProdutoDao.alteraProduto(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar o cadastro");
        }
        
    }
    public static void deletarProduto(int id) {
        try {
            if(ProdutoDao.buscarProduto(id) != null){
                ProdutoDao.deletaProduto(ProdutoDao.buscarProduto(id));
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar o cadastro");
        }
        
    }
    public static List<Produto> listarProdutos() {
        try {
            return ProdutoDao.listarProdutos();
        } catch (Exception e) {
            System.out.println("Erro ao listar o cadastro");
            return null;
        }
    }
    public static Produto buscarProdutoPorId(int id) {
        try {
            if(id == 0) throw new Exception();
            return ProdutoDao.buscarProduto(id);      
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cadastro");
            return null;
        }
    }
    public static List<Produto> buscarProdutoPorNome(String nome) {
        try {
            return ProdutoDao.buscarProdutoPorNome(nome);      
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cadastro");
            return null;
        }
        
    }
    
}
