/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.model;

/**
 *
 * @author victo
 */
public class Funcionario {
    private String cpf;
    private String nome;
    private String telefone;
    private String funcao;
    
    public Funcionario() {}
    
    public Funcionario(String cpf, String nome, String telefone, String funcao) {
        setCpf(cpf);
        setNome(nome);
        setTelefone(telefone);
        setFuncao(funcao);
    }
    
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getFuncao() {
        return funcao;
    }

    public final void setCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF é obrigatório.");
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) throw new IllegalArgumentException("CPF deve possuir 11 dígitos.");
        this.cpf = cpf;
    }

    public final void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        this.nome = nome;
    }

    public final void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public final void setFuncao(String funcao) {
        if (funcao == null || funcao.isBlank()) throw new IllegalArgumentException("Função é obrigatória.");
        this.funcao = funcao;
    }
}
