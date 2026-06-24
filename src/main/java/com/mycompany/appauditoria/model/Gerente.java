/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.model;

/**
 *
 * @author victo
 */
public class Gerente {
    private String cpf;
    private String nome;
    private String telefone;
    private String area;
    
    public Gerente() {}
    
    public Gerente(String cpf, String nome, String telefone, String area) {
        setCpf(cpf);
        setNome(nome);
        setTelefone(telefone);
        setArea(area);
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

    public String getArea() {
        return area;
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

    public final void setArea(String area) {
        if (area == null || area.isBlank()) throw new IllegalArgumentException("Área é obrigatória.");
        this.area = area;
    }
}
