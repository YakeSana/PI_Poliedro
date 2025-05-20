package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Pergunta;
import java.util.ArrayList;
import java.util.List;

public class PerguntasDAO {
    public Pergunta getPerguntacomAlternativa(int idPergunta,int id_disciplina,int dificuldade){
        Pergunta pergunta = null;
        try (Connection conexao = new ConnectionFactory().obterConexao();){
            String sqlString = "SELECT * from pergunta where id_pergunta = ? and id_disciplina = ? and id_dificuldade = ?";
            PreparedStatement stmt = conexao.prepareStatement(sqlString);
            stmt.setInt(1, idPergunta);
            stmt.setInt(2, id_disciplina);
            stmt.setInt(3, dificuldade);
            ResultSet data = stmt.executeQuery();
            if(data.next()){
                pergunta = new Pergunta(idPergunta, data.getString("texto"), null,data.getInt("id_disciplina"));
            }
            if (pergunta != null) {
                AlternativaDAO alternativasDAO = new AlternativaDAO();
                pergunta.setAlternativas(alternativasDAO.getlistaAlternativas(idPergunta));
            }
            else{
                new RuntimeException("Query de Pergunta Vazia");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pergunta;
    }

    public List<Integer> ids_disponiveis(int dificuldade,int id_disciplina){
        List<Integer> lista = new ArrayList<>();
        try(Connection conexao = new ConnectionFactory().obterConexao()) {
            String sqlString = "SELECT * from pergunta where id_disciplina = ? and id_dificuldade = ?";
            PreparedStatement stmt = conexao.prepareStatement(sqlString);
            stmt.setInt(1, id_disciplina);
            stmt.setInt(2, dificuldade);
            ResultSet data = stmt.executeQuery();
            while(data.next()){
                lista.add(data.getInt("id_pergunta"));
            }
            System.out.println("ids_disponíveis" + lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
