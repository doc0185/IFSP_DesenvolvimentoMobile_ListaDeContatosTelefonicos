package br.edu.ifsp.dmos5.Model;

public class Contato {
    private String apelido;
    private String nome;
    private String telefone;

    public Contato(String apelido, String nome, String telefone){
        this.apelido = apelido;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
