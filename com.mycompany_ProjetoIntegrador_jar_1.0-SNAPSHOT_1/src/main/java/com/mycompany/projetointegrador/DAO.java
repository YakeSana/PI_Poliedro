package com.mycompany.projetointegrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DAO {
    
    
    public boolean existe(Usuario usuario) throws Exception {
    String sql = "SELECT nome_real, tipo_usuario FROM usuario WHERE nome_usuario = ? AND senha = ?";
    try (Connection conn = ConexaoBD.obterConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, usuario.getNomeUsuario());
        ps.setString(2, usuario.getSenha());

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Preenche o nome real e o tipo do usuário após o login com sucesso
                usuario.setNomeReal(rs.getString("nome_real"));
                usuario.setTipo(rs.getString("tipo_usuario"));
                return true;
            } else {
                return false;
            }
        }
    }
}

public boolean nomeUsuarioJaExiste(String nomeUsuario) throws Exception {
    String sql = "SELECT 1 FROM usuario WHERE nome_usuario = ?";
    try (Connection conn = ConexaoBD.obterConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, nomeUsuario);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // true se já existir
        }
    }
}

public boolean cadastrarUsuario(Usuario usuario) throws Exception {
    String sql = "INSERT INTO usuario (nome_real, nome_usuario, senha, tipo_usuario) VALUES (?, ?, ?, ?)";

    try (Connection conn = ConexaoBD.obterConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, usuario.getNomeReal());
        ps.setString(2, usuario.getNomeUsuario());
        ps.setString(3, usuario.getSenha());
        ps.setString(4, usuario.getTipo());

        int linhasAfetadas = ps.executeUpdate();
        return linhasAfetadas > 0; // retorna true se o cadastro foi bem-sucedido
    }
}













}


