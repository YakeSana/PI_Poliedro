package BD;


import Model.Alternativa;
import Model.Pergunta;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsuarioDAO {
    
    
    public boolean existe(Usuario usuario) throws Exception {
    String sql = "SELECT id_usuario, nome_real, tipo_usuario FROM usuario WHERE nome_usuario = ? AND senha = ?";
    try (Connection conn = ConnectionFactory.obterConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, usuario.getNomeUsuario());
        ps.setString(2, usuario.getSenha());

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Preenche o nome real e o tipo do usuário após o login com sucesso
                usuario.setId(rs.getInt("id_usuario"));
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
    try (Connection conn = ConnectionFactory.obterConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, nomeUsuario);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // true se já existir
        }
    }
}

public boolean cadastrarUsuario(Usuario usuario) throws Exception {
    String sql = "INSERT INTO usuario (nome_real, nome_usuario, senha, tipo_usuario) VALUES (?, ?, ?, ?)";

    try (Connection conn = ConnectionFactory.obterConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, usuario.getNomeReal());
        ps.setString(2, usuario.getNomeUsuario());
        ps.setString(3, usuario.getSenha());
        ps.setString(4, usuario.getTipo());

        int linhasAfetadas = ps.executeUpdate();
        return linhasAfetadas > 0; // retorna true se o cadastro foi bem-sucedido
    }
}
public void editar(Usuario usuario)throws Exception{
    String sql = "UPDATE usuario SET nome_real = ?, senha = ?, nome_usuario = ? WHERE id_usuario = ?";
    try(Connection conn = ConnectionFactory.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,usuario.getNomeReal());
            ps.setString(2,usuario.getSenha());
            ps.setString(3,usuario.getNomeUsuario());
            ps.setInt(4,usuario.getId());
            ps.execute();
        }
    }
public void excluirUsuario(Usuario usuario) throws Exception{
    
    String sql = "DELETE FROM usuario WHERE id_usuario = ?";
    
    try(Connection conn = ConnectionFactory.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql)){
        
        ps.setInt(1, usuario.getId());
        ps.execute();
    }
}
/*public void editarPerguntas(Pergunta pergunta)throws Exception{
    String sql = "UPDATE pergunta SET texto = ? WHERE id_pergunta = ?";
    try(Connection conn = ConnectionFactory.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,pergunta.getPergunta());
            ps.setInt(2,pergunta.getId());
            ps.execute();
        }
    }
public void editarAlternativas(Alternativa alternativa)throws Exception{
    String sql = "UPDATE alternativa SET texto = ? WHERE id_alternativa = ?";
    try(Connection conn = ConnectionFactory.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,alternativa.getTexto());
            ps.setInt(2,alternativa.getId());
            ps.execute();
        }
    }*/

}
















