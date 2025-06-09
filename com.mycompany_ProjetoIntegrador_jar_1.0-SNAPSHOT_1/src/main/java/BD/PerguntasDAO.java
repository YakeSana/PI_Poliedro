/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

/**
 *
 * @author mitch
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Pergunta;
import java.sql.SQLException;
import java.sql.Statement;
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
    public boolean atualizarPerguntaEAlternativas(int idPergunta, String novoTextoPergunta, List<String> novasAlternativas) throws Exception {
    Connection con = null;
    PreparedStatement psPergunta = null;
    PreparedStatement psAlternativa = null;
    try {
        con = ConnectionFactory.obterConexao();
        con.setAutoCommit(false); // Garantir atomicidade

        // Atualiza o texto da pergunta
        String sqlPergunta = "UPDATE pergunta SET texto = ? WHERE id_pergunta = ?";
        psPergunta = con.prepareStatement(sqlPergunta);
        psPergunta.setString(1, novoTextoPergunta);
        psPergunta.setInt(2, idPergunta);
        psPergunta.executeUpdate();

        // Busca os IDs das alternativas associadas à pergunta
        String sqlBuscaAlt = "SELECT id_alternativa FROM alternativa WHERE id_pergunta = ? ORDER BY id_alternativa";
        PreparedStatement psBuscaAlt = con.prepareStatement(sqlBuscaAlt);
        psBuscaAlt.setInt(1, idPergunta);
        ResultSet rs = psBuscaAlt.executeQuery();

        List<Integer> idsAlternativas = new ArrayList<>();
        while (rs.next()) {
            idsAlternativas.add(rs.getInt("id_alternativa"));
        }
        rs.close();
        psBuscaAlt.close();

        // Atualiza texto e campo 'correta' das alternativas
        String sqlAlt = "UPDATE alternativa SET texto = ?, correta = ? WHERE id_alternativa = ?";
        psAlternativa = con.prepareStatement(sqlAlt);

        for (int i = 0; i < idsAlternativas.size() && i < novasAlternativas.size(); i++) {
            psAlternativa.setString(1, novasAlternativas.get(i));
            psAlternativa.setBoolean(2, i == 0); // true apenas para a primeira alternativa
            psAlternativa.setInt(3, idsAlternativas.get(i));
            psAlternativa.executeUpdate();
        }

        con.commit();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    } finally {
        try {
            if (psPergunta != null) psPergunta.close();
            if (psAlternativa != null) psAlternativa.close();
            if (con != null) con.setAutoCommit(true);
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public void excluirPerguntas(Pergunta pergunta) throws Exception{
    
    String sql = "DELETE FROM pergunta WHERE id_pergunta = ?";
    
    try(Connection conn = ConnectionFactory.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql)){
        
        ps.setInt(1, pergunta.getId());
        ps.execute();
    }
}
    public boolean adicionarPerguntaEAlternativas(String textoPergunta, List<String> alternativas) throws Exception {
    Connection con = null;
    PreparedStatement psPergunta = null;
    PreparedStatement psAlternativa = null;
    ResultSet rs = null;

    try {
        con = ConnectionFactory.obterConexao();
        con.setAutoCommit(false); // Garantir atomicidade

        // Inserir a nova pergunta
        String sqlInserirPergunta = "INSERT INTO pergunta (texto) VALUES (?)";
        psPergunta = con.prepareStatement(sqlInserirPergunta, Statement.RETURN_GENERATED_KEYS);
        psPergunta.setString(1, textoPergunta);
        psPergunta.executeUpdate();

        // Obter o ID gerado da pergunta
        rs = psPergunta.getGeneratedKeys();
        int idPergunta = -1;
        if (rs.next()) {
            idPergunta = rs.getInt(1);
        } else {
            throw new SQLException("Falha ao obter o ID da nova pergunta.");
        }

        rs.close();
        psPergunta.close();

        // Inserir alternativas associadas à pergunta
        String sqlInserirAlternativa = "INSERT INTO alternativa (texto, correta, id_pergunta) VALUES (?, ?, ?)";
        psAlternativa = con.prepareStatement(sqlInserirAlternativa);

        for (int i = 0; i < alternativas.size(); i++) {
            psAlternativa.setString(1, alternativas.get(i));
            psAlternativa.setBoolean(2, i == 0); // Marca a primeira como correta
            psAlternativa.setInt(3, idPergunta);
            psAlternativa.executeUpdate();
        }

        con.commit();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (psPergunta != null) psPergunta.close();
            if (psAlternativa != null) psAlternativa.close();
            if (con != null) con.setAutoCommit(true);
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


  
}


