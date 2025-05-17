package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Pergunta;

public class PerguntasDAO {
    public Pergunta getPerguntacomAlternativa(int idPergunta,int id_disciplina){
        Pergunta pergunta = null;
        try (Connection conexao = ConnectionFactory.obterConexao();){
            String sqlString = "SELECT * from pergunta where id == ? and id_disciplina == ?";
            PreparedStatement stmt = conexao.prepareStatement(sqlString);
            stmt.setInt(1, idPergunta);
            stmt.setInt(2, id_disciplina);
            ResultSet data = stmt.executeQuery();
            if(data.next()){
                pergunta = new Pergunta(idPergunta, data.getString("texto"), null,data.getInt("id_disciplina"));
            }
            if (pergunta != null) {
                AlternativaDAO alternativasDAO = new AlternativaDAO();
                pergunta.setAlternativas(alternativasDAO.getlistaAlternativas(idPergunta));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pergunta;
    }
}
