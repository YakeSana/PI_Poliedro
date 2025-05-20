package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Alternativa;

public class AlternativaDAO {
    public List<Alternativa> getlistaAlternativas(int id_pergunta){
        List<Alternativa> alternativas = new ArrayList<Alternativa>();
        try (Connection conexao = new ConnectionFactory().obterConexao()){
            String sql = "SELECT * from alternativa where id_pergunta = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_pergunta);
            ResultSet data = stmt.executeQuery();

            while(data.next()){
                int id = data.getInt("id_alternativa");
                String texto = data.getString("texto");
                boolean correta = data.getBoolean("correta");
                Alternativa alternativa = new Alternativa(id,texto,correta);
                alternativas.add(alternativa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alternativas;
    }
}
