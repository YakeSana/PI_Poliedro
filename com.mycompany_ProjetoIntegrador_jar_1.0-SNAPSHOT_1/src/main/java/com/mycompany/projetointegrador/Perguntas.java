/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetointegrador;

/**
 *
 * @author 25.00768-3
 */
public class Perguntas {
    private int id;
    private String texto;
    private int dificuldade;
    private int disciplina;

    public Perguntas() {}

    public Perguntas(int id, String texto, int dificuldade, int disciplina) {
        this.id = id;
        this.texto = texto;
        this.dificuldade = dificuldade;
        this.disciplina = disciplina;
    }
    public Perguntas(int id) {
        this.id = id;
    }
    
    public Perguntas(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }
    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getDificuldadeId() {
        return dificuldade;
    }

    public void setDificuldadeId(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getDisciplinaId() {
        return disciplina;
    }

    public void setDisciplinaId(int disciplina) {
        this.disciplina = disciplina;
    }
   
}
