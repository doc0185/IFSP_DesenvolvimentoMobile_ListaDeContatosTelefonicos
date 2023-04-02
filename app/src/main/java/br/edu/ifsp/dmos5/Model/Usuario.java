package br.edu.ifsp.dmos5.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String username;
    private String senha;
    private List<Contato> contatos;

    public Usuario(String username, String senha){
        this.contatos = new ArrayList<>();
        this.contatos.add(null);
        this.username = username;
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }


    public String getSenha() {
        return senha;
    }


    public int addContato(Contato contato){

        if(contatos.size() == 2){
            contatos.add(contato);
            return 1;
        } else{
            for(Contato contact : contatos){
                if(contact != null){
                    if(Objects.equals(contact.getApelido(), contato.getApelido())){
                        return 0;
                    }
                }
            }
            contatos.add(contato);
        }
        return 1;
    }



    public List<Contato> findAll(){
        return contatos;
    }
}
