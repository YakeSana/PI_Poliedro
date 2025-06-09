/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

public class Pergunta {
    private String pergunta;
    private List<Alternativa> alternativas = new ArrayList<Alternativa>();
    private int id;
    private int id_disciplina;

    public Pergunta(int id,String pergunta,List<Alternativa> alternativas,int id_disciplina){
        this.id = id;
        this.pergunta = pergunta;
        this.alternativas = alternativas;
        this.id_disciplina = id_disciplina;
    }
    
    public Pergunta(int id, String pergunta){
        this.id = id;
        this.pergunta = pergunta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public int getId() {
        return id;
    }
    public Pergunta() {
   
}

public void setId(int id) {
    this.id = id;
}
    
}
