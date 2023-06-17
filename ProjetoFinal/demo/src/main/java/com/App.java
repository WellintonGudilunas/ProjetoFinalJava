package com;

import com.controller.ClienteController;
import com.controller.FuncionarioController;
import com.model.Cliente;
import com.model.Funcionario;


public class App {
    public static void main( String[] args ) {
        Cliente cliente = new Cliente("tiago","asd",101,"101", "asd", "asd", "@asd", 123, "asd");
        Funcionario funcionario = new Funcionario("asd","asd",101,"101", "asd", "asd", "@asd", 123, 123,"asd");

        try {  
            Cliente cli = new Cliente("viadao alterador","asd",101,"101", "asd", "asd", "@asd", 123, "asd");
            ClienteController.deletarCliente(5);
            //FuncionarioController.cadastrarFuncionario(funcionario);
            
            //FuncionarioController.deletarFuncionario(2);
            //System.out.println("Removeru");
            
        } catch (Exception e) {
            System.out.println("erro ao salvar " + e.getMessage());
        }
    }
}
