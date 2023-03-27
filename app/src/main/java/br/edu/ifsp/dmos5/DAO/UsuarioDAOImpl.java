package br.edu.ifsp.dmos5.DAO;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.Model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{
    private List<Usuario> database;

    public UsuarioDAOImpl() {
        database = new ArrayList<>();
    }

    @Override
    public void addUsuario(Usuario usuario) {
        if(usuario != null){
            database.add(usuario);
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        return database.stream().filter(username1 -> username1.getUsername() == username).findAny().orElse(null);
    }

    @Override
    public List<Usuario> findAll() {
        return database;
    }
}
