/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.service;

import com.mycompany.appauditoria.dao.FuncionarioDAO;
import com.mycompany.appauditoria.dao.UsuarioDAO;
import com.mycompany.appauditoria.database.Conexao;
import com.mycompany.appauditoria.model.Funcionario;
import com.mycompany.appauditoria.model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author victo
 */
public class FuncionarioService {
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final int PERFIL_ID = 3;

    public void registrarFuncionario(Funcionario funcionario, String senha, String usuarioCpfLogado) throws SQLException {
        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false);
            try {
                funcionarioDAO.cadastrarFuncionario(funcionario, conn, usuarioCpfLogado);
                usuarioDAO.cadastrarUsuario(new Usuario(funcionario.getCpf(), senha, PERFIL_ID), conn, usuarioCpfLogado);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
