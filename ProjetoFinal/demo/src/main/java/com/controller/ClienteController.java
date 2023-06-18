package com.controller;

import java.util.List;

import com.dal.ClienteDao;
import com.model.Cliente;

public abstract class ClienteController {

    private static boolean clienteExiste(Cliente cliente) throws Exception {
        String cpf = cliente.getCpf();
        return buscarClientePorCPF(cpf) != null;
    }

    public static void cadastrarCliente(Cliente cliente) throws Exception {
        try {
            if (cliente.getNome() == null) {
                throw new Exception("Não pode cadastrar um cliente sem nome!");
            }
            if (clienteExiste(cliente)) {
                throw new Exception("Cliente já existe");
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
            if (id <= 0)
                throw new Exception("Id inválido");
            return ClienteDao.buscarCliente(id);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar cliente, CAUSA:" + e.getMessage());
        }
    }

    public static Cliente buscarClientePorCPF(String cpf) throws Exception {
        try {
            if (cpf != null) {
                return ClienteDao.buscarClientePorCPF(cpf);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar cliente, CAUSA:" + e.getMessage());
        }

    }
}
