package com.model;

import javax.persistence.*;

@Entity
@Table(name = "Funcionario")
public class Funcionario extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int salario;
    private String cargo;

    public Funcionario(String nome, String sobrenome, int idade, String cpf, String endereco, String cep, String email,
            int telefone, int salario, String cargo) {
        super(nome, sobrenome, idade, cpf, endereco, cep, email, telefone);
        this.salario = salario;
        this.cargo = cargo;
    }

    public Funcionario() {
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    

    @Override
    public String toString() {
        return "\tFucionario \n\nsalario: " + salario + "\ncargo: " + cargo + "\n\n";
    }

}
