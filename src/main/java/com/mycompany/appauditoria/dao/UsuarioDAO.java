/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.dao;

import static com.mycompany.appauditoria.database.Conexao.getConnection;
import com.mycompany.appauditoria.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author victo
 */
public class UsuarioDAO {
    public UsuarioDAO() {}
    
    // Autentica um usuário utilizando CPF e senha
    public Integer autenticar(String cpf, String senha) throws SQLException {
        String sql = "SELECT perfilId, senha FROM usuario WHERE cpf = ?";

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                String hash = rs.getString("senha");
                int perfilId = rs.getInt("perfilId");

                return (BCrypt.checkpw(senha, hash)) ? perfilId : -1;
            }
        }
    }

    public void cadastrarUsuario(Usuario usuario, Connection conn, String usuarioCpfLogado) throws SQLException {
        try (PreparedStatement psSession = conn.prepareStatement("SET @usuario_logado_cpf = ?")) {
            psSession.setString(1, usuarioCpfLogado);
            psSession.execute();
        }

        String sql = "INSERT INTO usuario (cpf, senha, perfilId) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String senhaHasheada = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            
            ps.setString(1, usuario.getCpf());
            ps.setString(2, senhaHasheada);
            ps.setInt(3, usuario.getPerfilId());
            ps.executeUpdate();
        }
    }
}
