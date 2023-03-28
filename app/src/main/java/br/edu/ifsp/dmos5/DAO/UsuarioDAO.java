package br.edu.ifsp.dmos5.DAO;

import java.util.List;

import br.edu.ifsp.dmos5.Model.Contato;
import br.edu.ifsp.dmos5.Model.Usuario;


public interface UsuarioDAO {
    int addUsuario(Usuario usuario);

    void addContato(Usuario usuario, Contato contato);
    Usuario findByUsername(String nome);
    List<Usuario> findAll();
}
