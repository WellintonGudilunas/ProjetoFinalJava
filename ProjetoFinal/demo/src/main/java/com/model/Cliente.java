package com.model;

import javax.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente extends Pessoa {

    private String enderecoEntregas;

    public Cliente(String nome, String sobrenome, int idade, String cpf, String endereco, String cep, String email,
            String telefone, String enderecoEntregas) {
        super(nome, sobrenome, idade, cpf, endereco, cep, email, telefone);
        this.enderecoEntregas = enderecoEntregas;
    }

    public Cliente() {

    }

    public String getEnderecoEntregas() {
        return enderecoEntregas;
    }

    public void setEnderecoEntregas(String enderecoEntregas) {
        this.enderecoEntregas = enderecoEntregas;
    }

    @Override
    public String toString() {
        return "Cliente [enderecoEntregas=" + enderecoEntregas + ", " + super.toString() + "]";
    }
}
