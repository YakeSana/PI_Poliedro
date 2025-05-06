package model;

public class Alternativa {
    private String texto;
    private int idPergunta;
    private boolean correta;
    private int id;

    Alternativa(int id,String texto,int idPergunta,boolean correta){
        this.id = id;
        this.texto = texto;
        this.idPergunta = idPergunta;
        this.correta = correta;
    }

    @Override
    public String toString(){
        return texto;
    }

    public boolean isCorreta() {
        return correta;
    }

    public int getId() {
        return id;
    }

    
    
}
