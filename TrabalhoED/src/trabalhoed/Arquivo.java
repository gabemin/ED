/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed;

/**
 *
 * @author gsmin
 */
public class Arquivo {
    private String nome;
    private String path;
    private Arquivo proximo;
    private Arquivo filho;
    String tipo;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public Arquivo getProximo() {
        return proximo;
    }

    public void setProximo(Arquivo proximo) {
        this.proximo = proximo;
    }

    public Arquivo getFilho() {
        return filho;
    }

    public void setFilho(Arquivo filho) {
        this.filho = filho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Arquivo(String nome, Arquivo proximo, String tipo) {
        this.nome = nome;
        this.proximo = proximo;
        this.tipo = tipo;
    }
    
}
