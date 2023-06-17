package com.model;

import javax.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente extends Pessoa {

    private String enderecoEntregas;

    public Cliente(String nome, String sobrenome, int idade, String cpf, String endereco, String cep, String email,
            int telefone, String enderecoEntregas) {
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

    protected Double calcularDesconto(Double Valor) {
        return 0.0;
    }

    @Override
    public String toString() {
        return "\nCliente [id=" + ", enderecoEntregas=" + enderecoEntregas + "] " + super.toString();
    }


}
