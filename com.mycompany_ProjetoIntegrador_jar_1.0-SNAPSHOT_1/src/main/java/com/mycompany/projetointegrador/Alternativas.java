/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetointegrador;

    public class Alternativas {
        private int id;
        private int id_perg;
        private String texto;
        private boolean correta;
    
        public Alternativas() {}
    
        public Alternativas(int id, int id_perg, String texto, boolean correta) {
            this.id = id;
            this.id_perg = id_perg;
            this.texto = texto;
            this.correta = correta;
        }
        public Alternativas(int id, int id_perg, String texto) {
            this.id = id;
            this.id_perg = id_perg;
            this.texto = texto;
        }
    
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public int getPergunta() {
            return id_perg;
        }
    
        public void setPergunta(int id_perg) {
            this.id_perg = id_perg;
        }
    
        public String getTexto() {
            return texto;
        }
    
        public void setTexto(String texto) {
            this.texto = texto;
        }
    
        public boolean isCorreta() {
            return correta;
        }
    
        public void setCorreta(boolean correta) {
            this.correta = correta;
        }
    }
    
    

