/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Alternativa {

    private String texto;
    private boolean correta;
    private int id;

    public Alternativa(int id, String texto, boolean correta) {
        this.id = id;
        this.texto = texto;
        this.correta = correta;
    }

    @Override
    public String toString() {
        return texto;
    }

    public boolean isCorreta() {
        return correta;
    }

    public int getId() {
        return id;
    }
    
    public String getTexto(){
        return texto;
    }
    
    public void setTexto(String texto){
        this.texto = texto;
    }
}
