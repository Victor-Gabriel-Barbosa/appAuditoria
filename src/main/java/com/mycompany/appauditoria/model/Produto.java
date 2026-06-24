/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.model;

import java.math.BigDecimal;

/**
 *
 * @author victo
 */
public class Produto {
    private String codigo;
    private String descricao;
    private BigDecimal valor;
    private Integer estoque;
    
    protected Produto() {}
    
    public Produto(String codigo, String descricao, BigDecimal valor, Integer estoque) {
        setCodigo(codigo);
        setDescricao(descricao);
        setValor(valor);
        setEstoque(estoque);
    }
    
    public String getCodigo() { 
        return codigo; 
    }
    
    public String getDescricao() { 
        return descricao; 
    }
    
    public BigDecimal getValor() { 
        return valor; 
    }
    
    public Integer getEstoque() { 
        return estoque; 
    }

    public final void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("Código é obrigatório.");
        this.codigo = codigo;
    }

    public final void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("Descrição é obrigatória.");
        this.descricao = descricao;
    }

    public final void setValor(BigDecimal valor) {
        if (valor == null) throw new IllegalArgumentException("Valor é obrigatório.");
        if (valor.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Valor não pode ser negativo.");
        this.valor = valor;
    }

    public final void setEstoque(Integer estoque) {
        if (estoque == null) throw new IllegalArgumentException("Estoque é obrigatório.");
        if (estoque < 0) throw new IllegalArgumentException("Estoque não pode ser negativo.");
        this.estoque = estoque;
    }
}
