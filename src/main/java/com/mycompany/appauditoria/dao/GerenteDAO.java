/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.dao;

import static com.mycompany.appauditoria.database.Conexao.getConnection;
import com.mycompany.appauditoria.model.Gerente;
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
public class GerenteDAO {
    private static final int PERFIL_ID = 2;
    
    public GerenteDAO() {}
    
    // Cadastra um novo gerente no banco de dados
    public void cadastrarGerente(Gerente gerente, Connection conn, String usuarioCpfLogado) throws SQLException {
        try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
            psSession.setString(1, usuarioCpfLogado);
            psSession.execute();
        }

        String sql = "INSERT INTO gerente (cpf, nome, telefone, area, perfilId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, gerente.getCpf());
            ps.setString(2, gerente.getNome());
            ps.setString(3, gerente.getTelefone());
            ps.setString(4, gerente.getArea());
            ps.setInt(5, PERFIL_ID);

            ps.executeUpdate();
        }
    }

    // Busca todos os gerentes cadastrados no banco de dados
    public List<Gerente> listarTodos() throws SQLException {
        String sql = "SELECT * FROM gerente";
        List<Gerente> listaGerentes = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Gerente gerente = new Gerente(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("area")
                );

                listaGerentes.add(gerente);
            }
        }
        return listaGerentes;
    }

    // Atualiza os dados de um gerente no banco de dados
    public boolean atualizarGerente(Gerente gerente, String usuarioCpfLogado) throws SQLException {
        String sql = "UPDATE gerente SET nome = ?, telefone = ?, area = ? WHERE cpf = ?";

        try (Connection conn = getConnection()) {
            try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
                psSession.setString(1, usuarioCpfLogado);
                psSession.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, gerente.getNome());
                ps.setString(2, gerente.getTelefone());
                ps.setString(3, gerente.getArea());
                ps.setString(4, gerente.getCpf());

                return ps.executeUpdate() != 0;
            }
        }
    }
    
    // Remove um gerente do banco de dados pelo CPF
    public boolean deletarGerente(String cpf, String usuarioCpfLogado) throws SQLException {
        String sql = "DELETE FROM gerente WHERE cpf = ?";

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
