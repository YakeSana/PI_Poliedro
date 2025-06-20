/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author conta
 */
public class Usuario {
    private String nomeReal;
    private String nomeUsuario;
    private String senha;
    private String tipo;
    private int id;

    public Usuario(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }
    public Usuario(int id) {
    this.id = id;
}
    public int getId() {
        return id;
    }
     public Usuario(int id, String NovoNome, String NovoNomeDeUsuario, String senha) {
        this.id = id;
        this.nomeReal = NovoNome;
        this.nomeUsuario = NovoNomeDeUsuario;
        this.senha = senha;
        
    }
    
    


    public Usuario(String nomeReal, String nomeUsuario, String senha, String tipo) {
        this.nomeReal = nomeReal;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipo = tipo;
        
    }

    public String getNomeReal() {
        return nomeReal;
    }

    public void setNomeReal(String nomeReal) {
        this.nomeReal = nomeReal;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    @Override
    public String toString(){
        return "id: "+id+", nome: "+nomeUsuario+", tipo: "+tipo;
    }
}


