package com.controller;

import java.util.List;

import com.dal.ClienteDao;
import com.model.Cliente;

public abstract class ClienteController {

    public static void cadastrarCliente(Cliente cliente) throws Exception {
        try {
            if (cliente.getNome() == null) {
                System.out.println("Você não pode cadastrar um cliente sem nome!!!");
                return;
            }
            ClienteDao.cadastrarCliente(cliente);
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar cliente, CAUSA:" + e.getMessage());
        }
    }

    public static void alterarCliente(int id, Cliente cliente) throws Exception {
        try {
            Cliente prevCliente = ClienteController.buscarClientePorId(id);
            if (prevCliente == null) {
                return;
            }
            prevCliente = cliente;
            prevCliente.setId(id);

            if (ClienteDao.buscarCliente(prevCliente.getId()) != null) {
                ClienteDao.alteraCliente(prevCliente);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao alterar cliente, CAUSA:" + e.getMessage());
        }

    }

    public static void deletarCliente(int id) throws Exception {
        try {
            if (ClienteDao.buscarCliente(id) != null) {
                ClienteDao.deletaCliente(ClienteDao.buscarCliente(id));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao deletar cliente, CAUSA:" + e.getMessage());
        }

    }

    public static List<Cliente> listarClientes() throws Exception {
        try {
            List<Cliente> lista = ClienteDao.listarClientes();
            if (lista.size() > 0) {
                return lista;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao listar cliente, CAUSA:" + e.getMessage());
        }
    }

    public static Cliente buscarClientePorId(int id) throws Exception {
        try {
            if (id == 0)
                throw new Exception();
            return ClienteDao.buscarCliente(id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar cliente, CAUSA:" + e.getMessage());
        }
    }

    public static List<Cliente> buscarClientePorNome(String nome) throws Exception {
        try {
            if (nome != null) {
                return ClienteDao.buscarClientePorNome(nome);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar cliente, CAUSA:" + e.getMessage());
        }

    }
}
