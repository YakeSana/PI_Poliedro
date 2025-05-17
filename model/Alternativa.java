package model;

public class Alternativa {
    private String texto;
    private boolean correta;
    private int id;

    public Alternativa(int id,String texto,boolean correta){
        this.id = id;
        this.texto = texto;
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
