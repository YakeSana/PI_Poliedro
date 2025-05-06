package model;
import java.util.ArrayList;
import java.util.List;

public class Pergunta {
    private String pergunta;
    private List<Alternativa> alternativas = new ArrayList<Alternativa>();
    private int id;

    public Pergunta(int id){
        setPergunta("Pergunta " + id);
        setId(id);
        for(int i=0;i<5;i++){
            boolean correta = false;
            if(i==0) correta = true;
            Alternativa alt = new Alternativa(i,"Alternativa "+i , id, correta);
            alternativas.add(alt);
        }
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
