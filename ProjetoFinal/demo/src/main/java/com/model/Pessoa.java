package com.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    protected String nome, sobrenome, endereco, email, cpf, cep;
    protected int idade, telefone;

    public Pessoa(String nome, String sobrenome, int idade, String cpf, String endereco, String cep, String email,
            int telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
    }

    public Pessoa() {

    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    

    @Override
    public String toString() {
        return "Pessoas [nome=" + nome + ", sobrenome=" + sobrenome + ", endereco=" + endereco + ", email=" + email
                + ", idade=" + idade + ", cpf=" + cpf + ", cep=" + cep + ", telefone=" + telefone + "]";
    }

}
