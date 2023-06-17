package com.controller;

import java.util.List;

import com.dal.FuncionarioDao;
import com.model.Funcionario;

public abstract class FuncionarioController {

    public static void cadastrarFuncionario(Funcionario funcionario) {
        try {
            FuncionarioDao.cadastrarFuncionario(funcionario);
        } catch (Exception e) {
            System.out.println("Erro ao realizar o cadastro");
        }
    }
    public static void alterarFuncionario(Funcionario funcionario) {
        try {
            if(FuncionarioDao.buscarFuncionario(funcionario.getId()) != null){
                FuncionarioDao.alteraFuncionario(funcionario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar o cadastro");
        }
        
    }
    public static void deletarFuncionario(int id) {
        try {
            if(FuncionarioDao.buscarFuncionario(id) != null){
                FuncionarioDao.deletaFuncionario(FuncionarioDao.buscarFuncionario(id));
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar o cadastro");
        }
        
    }
    public static List<Funcionario> listarFuncionarios() {
        try {
            return FuncionarioDao.listarFuncionarios();
        } catch (Exception e) {
            System.out.println("Erro ao listar o cadastro");
            return null;
        }
    }
    public static Funcionario buscarFuncionarioPorId(int id) {
        try {
            return FuncionarioDao.buscarFuncionario(id);      
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cadastro");
            return null;
        }
    }
    public static List<Funcionario> buscarFuncionarioPorNome(String nome) {
        try {
            return FuncionarioDao.buscarFuncionarioPorNome(nome);      
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cadastro");
            return null;
        }
        
    }
    
}
