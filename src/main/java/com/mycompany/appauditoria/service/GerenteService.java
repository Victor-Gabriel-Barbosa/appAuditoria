/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appauditoria.service;

import com.mycompany.appauditoria.dao.GerenteDAO;
import com.mycompany.appauditoria.dao.UsuarioDAO;
import com.mycompany.appauditoria.database.Conexao;
import com.mycompany.appauditoria.model.Gerente;
import com.mycompany.appauditoria.model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author victo
 */
public class GerenteService {
    private final GerenteDAO gerenteDAO = new GerenteDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final int PERFIL_ID = 2;

    public void registrarGerente(Gerente gerente, String senha, String usuarioCpfLogado) throws SQLException {
        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false);
            try {
                gerenteDAO.cadastrarGerente(gerente, conn, usuarioCpfLogado);
                usuarioDAO.cadastrarUsuario(new Usuario(gerente.getCpf(), senha, PERFIL_ID), conn, usuarioCpfLogado);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
