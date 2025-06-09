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
    /*public List<RankingEntry> obterRanking() {
    List<RankingEntry> ranking = new ArrayList<>();
    String sql = "SELECT pontuacao_total, nome_real " +
                 "FROM partida " +
                 "JOIN usuario ON partida.id_usuario = usuario.id " +
                 "ORDER BY pontuacao_total DESC";

    try (Connection conexao = new ConnectionFactory().obterConexao()) {
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int pontuacao = rs.getInt("pontuacao_total");
            String nome = rs.getString("nome_real");
            ranking.add(new RankingEntry(nome, pontuacao));
        }

    } catch (SQLException e) {
        e.printStackTrace(); // ou logar erro
    }

    return ranking;
}

}
    List<RankingEntry> ranking = dao.obterRanking();
DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "Pontuação"}, 0);

for (RankingEntry entry : ranking) {
    model.addRow(new Object[]{entry.getNomeReal(), entry.getPontuacaoTotal()});
}

tabelaRanking.setModel(model); // tabelaRanking é sua JTable*/
}
