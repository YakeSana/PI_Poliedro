package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Pergunta;
import java.util.ArrayList;
import java.util.List;

public class PerguntasDAO {
    public Pergunta getPerguntacomAlternativa(int idPergunta){
        Pergunta pergunta = null;
        try (Connection conexao = new ConnectionFactory().obterConexao();){
            String sqlString = "SELECT * from pergunta where id_pergunta = ?";
            PreparedStatement stmt = conexao.prepareStatement(sqlString);
            stmt.setInt(1, idPergunta);
            ResultSet data = stmt.executeQuery();
            if(data.next()){
                pergunta = new Pergunta(idPergunta, data.getString("texto"), null,data.getInt("id_disciplina"));
            }
            else{
                new RuntimeException("Query de Pergunta Vazia");
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


    public List<Integer> ids_disponiveis(int dificuldade, List<Integer> idsDisciplinas) {
        List<Integer> lista = new ArrayList<>();
    
        if (idsDisciplinas == null || idsDisciplinas.isEmpty()) {
            throw new IllegalArgumentException("Lista de disciplinas não pode ser nula ou vazia.");
        }
    
        try (Connection conexao = new ConnectionFactory().obterConexao()) {
            // Construindo SQL com cláusula IN para os IDs das disciplinas
            StringBuilder sqlBuilder = new StringBuilder("SELECT id_pergunta FROM pergunta WHERE id_dificuldade = ? AND id_disciplina IN (");
            for (int i = 0; i < idsDisciplinas.size(); i++) {
                sqlBuilder.append("?");
                if (i < idsDisciplinas.size() - 1) {
                    sqlBuilder.append(", ");
                }
            }
            sqlBuilder.append(")");
    
            PreparedStatement stmt = conexao.prepareStatement(sqlBuilder.toString());
            stmt.setInt(1, dificuldade);
    
            // Setando os IDs de disciplina nos parâmetros
            for (int i = 0; i < idsDisciplinas.size(); i++) {
                stmt.setInt(i + 2, idsDisciplinas.get(i)); // +2 pois o primeiro parâmetro é dificuldade
            }
    
            ResultSet data = stmt.executeQuery();
            while (data.next()) {
                lista.add(data.getInt("id_pergunta"));
            }
    
            System.out.println("ids_disponiveis: " + lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return lista;
    }
    
    
}
