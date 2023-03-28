package br.edu.ifsp.dmos5.Model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String senha;
    private List<Contato> contatos;

    public Usuario(String username, String senha){
        contatos = new ArrayList<>();
        this.username = username;
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void addContato(Contato contato){
        contatos.add(contato);
    }


}
