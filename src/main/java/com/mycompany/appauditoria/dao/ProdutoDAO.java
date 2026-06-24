/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.dao;

import static com.mycompany.appauditoria.database.Conexao.getConnection;
import com.mycompany.appauditoria.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class ProdutoDAO {
    public ProdutoDAO() {}
    
    // Cadastra um novo produto no banco de dados
    public void cadastrarProduto(Produto produto, String usuarioCpfLogado) throws SQLException {
        String sql = "INSERT INTO produto (codigo, descricao, valor, estoque) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
                psSession.setString(1, usuarioCpfLogado);
                psSession.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, produto.getCodigo());
                ps.setString(2, produto.getDescricao());
                ps.setBigDecimal(3, produto.getValor());
                ps.setInt(4, produto.getEstoque());
                
                ps.executeUpdate();
            }
        }
    }
    
    // Busca todos os produtos cadastrados no banco de dados
    public List<Produto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM produto";
        List<Produto> listaProdutos = new ArrayList<>();

        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getString("codigo"),
                    rs.getString("descricao"),
                    rs.getBigDecimal("valor"),
                    rs.getInt("estoque")
                );
                
                listaProdutos.add(produto);
            }
        }
        return listaProdutos;
    }
    
    // Atualiza os dados de um produto no banco de dados
    public boolean atualizarProduto(Produto produto, String usuarioCpfLogado) throws SQLException {
        String sql = "UPDATE produto SET descricao = ?, valor = ?, estoque = ? WHERE codigo = ?";

        try (Connection conn = getConnection()) {
            try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
                psSession.setString(1, usuarioCpfLogado);
                psSession.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, produto.getDescricao());
                ps.setBigDecimal(2, produto.getValor());
                ps.setInt(3, produto.getEstoque());
                ps.setString(4, produto.getCodigo());

                return ps.executeUpdate() != 0;
            }
        }
    }
    
    // Remove um produto do banco de dados pelo código
    public boolean deletarProduto(String codigo, String usuarioCpfLogado) throws SQLException {
        String sql = "DELETE FROM produto WHERE codigo = ?";

        try (Connection conn = getConnection()) {
            try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
                psSession.setString(1, usuarioCpfLogado);
                psSession.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, codigo);
                return ps.executeUpdate() != 0;
            }
        }
    }
}
