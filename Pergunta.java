import java.util.ArrayList;
import java.util.List;

public class Pergunta {
    private String pergunta;
    private List<String> respostas = new ArrayList<String>();
    private int id;

    Pergunta(int id){
        setPergunta("Pergunta " + id);
        setRespostas(List.of("a", "b", "c","d","e"));
        setId(id);
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
