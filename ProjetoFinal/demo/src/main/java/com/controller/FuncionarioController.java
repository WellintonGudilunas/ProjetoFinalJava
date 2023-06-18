package com.controller;

import java.util.List;

import com.dal.FuncionarioDao;
import com.model.Cliente;
import com.model.Funcionario;

public abstract class FuncionarioController {

    public static void cadastrarFuncionario(Funcionario funcionario) throws Exception {
        try {
            FuncionarioDao.cadastrarFuncionario(funcionario);
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar funcionario, CAUSA:" + e.getMessage());
        }
    }

    public static void alterarFuncionario(Funcionario funcionario) throws Exception {
        try {
            if (FuncionarioDao.buscarFuncionario(funcionario.getId()) != null) {
                FuncionarioDao.alteraFuncionario(funcionario);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao alterar funcionario, CAUSA:" + e.getMessage());
        }

    }

    public static void deletarFuncionario(int id) throws Exception {
        try {
            if (FuncionarioDao.buscarFuncionario(id) != null) {
                FuncionarioDao.deletaFuncionario(FuncionarioDao.buscarFuncionario(id));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao deletar funcionario, CAUSA:" + e.getMessage());
        }

    }

    public static List<Funcionario> listarFuncionarios() throws Exception {
        try {
            List<Funcionario> lista = FuncionarioDao.listarFuncionarios();
            if (lista.size() > 0) {
                return lista;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao listar funcionarios, CAUSA:" + e.getMessage());
        }
    }

    public static Funcionario buscarFuncionarioPorId(int id) throws Exception {
        try {
            if (id == 0)
                throw new Exception("id = 0");
            return FuncionarioDao.buscarFuncionario(id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar funcionarios, CAUSA:" + e.getMessage());
        }
    }

    public static List<Funcionario> buscarFuncionarioPorNome(String nome) throws Exception {
        try {
            if (nome != null) {
                return FuncionarioDao.buscarFuncionarioPorNome(nome);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar funcionario, CAUSA:" + e.getMessage());
        }

    }

}
