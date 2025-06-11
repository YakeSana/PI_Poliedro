/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 *
 * @author mitch
 */
public class RankingDAO {

    public void atualizaRanking(Usuario usuario, int pontuacao) {
        if (usuario != null) {
            String sql = "INSERT INTO partida (id_usuario, data_hora, pontuacao_total) VALUES (?, ?, ?)";

            try (Connection conexao = new ConnectionFactory().obterConexao()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, usuario.getId());
                stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                stmt.setInt(3, pontuacao);

                stmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
