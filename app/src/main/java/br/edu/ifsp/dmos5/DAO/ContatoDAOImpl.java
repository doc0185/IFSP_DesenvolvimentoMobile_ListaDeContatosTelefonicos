package br.edu.ifsp.dmos5.DAO;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.Model.Contato;

public class ContatoDAOImpl implements ContatoDAO{

    private List<Contato> database;

    public ContatoDAOImpl() {
        database = new ArrayList<>();
    }

    @Override
    public void addContato(Contato contato) {
        if (contato != null){
            database.add(contato);
        }
    }

    @Override
    public Contato findByNome(String nome) {
        return database.stream().filter(contato1 -> contato1.getNome() == nome).findAny().orElse(null);
    }

    @Override
    public List<Contato> findAll() {
        return database;
    }
}
