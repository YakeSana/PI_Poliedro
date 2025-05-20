package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DisciplinasDAO {
    public List<Integer> getListaDisciplinas(){
        List<Integer> lista = new ArrayList<>();
        try (Connection conexao = new ConnectionFactory().obterConexao()){
            String sql = "SELECT * from disciplina";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet data = stmt.executeQuery();

            while(data.next()){
                lista.add(data.getInt("id_disciplina"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Lista de Disciplinas: "+ lista);
        return lista;
    }
}
