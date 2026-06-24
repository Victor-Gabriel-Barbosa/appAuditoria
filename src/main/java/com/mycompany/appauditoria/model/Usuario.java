/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.model;

/**
 *
 * @author victo
 */
public class Usuario {
    private String cpf;
    private String senha;
    private Integer perfilId;

    public Usuario(String cpf, String senha, Integer perfilId) {
        setCpf(cpf);
        setSenha(senha);
        setPerfilId(perfilId);
    }

    public String getCpf() {
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF é obrigatório.");
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) throw new IllegalArgumentException("CPF deve possuir 11 dígitos.");
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public Integer getPerfilId() {
        return perfilId;
    }

    public final void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public final void setSenha(String senha) {
        if (senha == null || senha.isBlank()) throw new IllegalArgumentException("A senha não pode estar vazia.");
        if (senha.length() < 8) throw new IllegalArgumentException("A senha deve possuir pelo menos 8 caracteres.");
        this.senha = senha;
    }

    public final void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }
}
