package com.controller;

import java.util.List;

import com.dal.ClienteDao;
import com.model.Cliente;

public abstract class ClienteController {

    public static void cadastrarCliente(Cliente cliente) {
        try {
            if(cliente.getNome() == null){
                System.out.println("Você não pode cadastrar um cliente sem nome!!!");    
                return;
            }
            
            ClienteDao.cadastrarCliente(cliente);
        } catch (Exception e) {
            System.out.println("Erro ao realizar o cadastro");
        }
    }

    public static void alterarCliente(int id, Cliente cliente) {
        try {
        Cliente prevCliente = ClienteController.buscarClientePorId(id);
        if(prevCliente == null){
            return;
        }
        prevCliente = cliente;
        prevCliente.setId(id);

            if(ClienteDao.buscarCliente(prevCliente.getId()) != null){
                ClienteDao.alteraCliente(prevCliente);
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar o cadastro");
        }
        
    }

    public static void deletarCliente(int id) {
        try {
            if(ClienteDao.buscarCliente(id) != null){
                ClienteDao.deletaCliente(ClienteDao.buscarCliente(id));
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar o cadastro");
        }
        
    }

    public static List<Cliente> listarClientes() {
        try {
            if(ClienteDao.listarClientes().size() > 0){
                return ClienteDao.listarClientes();
            } else {
                System.out.println("Não há nenhum cliente cadastrado");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar os cadastros");
            return null;
        }
    }

    public static Cliente buscarClientePorId(int id) {
        try {
            if(id == 0) throw new Exception();
            return ClienteDao.buscarCliente(id);      
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cadastro");
            return null;
        }
    }

    public static List<Cliente> buscarClientePorNome(String nome) {
        try {
            if(nome != null){
                return ClienteDao.buscarClientePorNome(nome);      
            } else {
                System.out.println("Num tem");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cadastro");
            return null;
        }
        
    }
}
