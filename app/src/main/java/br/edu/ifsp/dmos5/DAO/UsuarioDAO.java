package br.edu.ifsp.dmos5.DAO;

import java.util.List;

import br.edu.ifsp.dmos5.Model.Usuario;


public interface UsuarioDAO {
    void addUsuario(Usuario usuario);
    Usuario findByUsername(String nome);
    List<Usuario> findAll();
}
