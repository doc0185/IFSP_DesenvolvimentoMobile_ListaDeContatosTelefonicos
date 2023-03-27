package br.edu.ifsp.dmos5.DAO;

import java.util.List;

import br.edu.ifsp.dmos5.Model.Contato;

public interface ContatoDAO {
    void addContato(Contato contato);
    Contato findByNome(String nome);
    List<Contato> findAll();
}
