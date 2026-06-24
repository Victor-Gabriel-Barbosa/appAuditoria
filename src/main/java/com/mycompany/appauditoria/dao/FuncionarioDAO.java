/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.dao;

import static com.mycompany.appauditoria.database.Conexao.getConnection;
import com.mycompany.appauditoria.model.Funcionario;
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
public class FuncionarioDAO {
    private static final int PERFIL_ID = 3;
    
    public FuncionarioDAO() {}
    
    // Cadastra um novo funcionário no banco de dados
    public void cadastrarFuncionario(Funcionario funcionario, Connection conn, String usuarioCpfLogado) throws SQLException {
        try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
            psSession.setString(1, usuarioCpfLogado);
            psSession.execute();
        }

        String sql = "INSERT INTO funcionario (cpf, nome, telefone, funcao, perfilId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getCpf());
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getTelefone());
            ps.setString(4, funcionario.getFuncao());
            ps.setInt(5, PERFIL_ID);
            
            ps.executeUpdate();
        }
    }
    
    // Busca todos os funcionários cadastrados no banco de dados
    public List<Funcionario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("funcao")
                );
                
                listaFuncionarios.add(funcionario);
            }
        }
        return listaFuncionarios;
    }
    
    // Atualiza os dados de um funcionário no banco de dados
    public boolean atualizarFuncionario(Funcionario funcionario, String usuarioCpfLogado) throws SQLException {
        String sql = "UPDATE funcionario SET nome = ?, telefone = ?, funcao = ? WHERE cpf = ?";

        try (Connection conn = getConnection()) {
            try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
                psSession.setString(1, usuarioCpfLogado);
                psSession.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, funcionario.getNome());
                ps.setString(2, funcionario.getTelefone());
                ps.setString(3, funcionario.getFuncao());
                ps.setString(4, funcionario.getCpf());

                return ps.executeUpdate() != 0;
            }
        }
    }
    
    // Remove um funcionário do banco de dados pelo CPF
    public boolean deletarFuncionario(String cpf, String usuarioCpfLogado) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE cpf = ?";

        try (Connection conn = getConnection()) {
            try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
                psSession.setString(1, usuarioCpfLogado);
                psSession.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cpf);
                return ps.executeUpdate() != 0;
            }
        }
    }
}
