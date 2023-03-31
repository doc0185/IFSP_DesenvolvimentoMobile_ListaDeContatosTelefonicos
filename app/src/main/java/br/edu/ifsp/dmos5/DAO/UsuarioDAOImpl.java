package br.edu.ifsp.dmos5.DAO;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifsp.dmos5.Model.Contato;
import br.edu.ifsp.dmos5.Model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{

    private static UsuarioDAOImpl instance = null;
    private List<Usuario> database;
    private UsuarioDAOImpl(){};

    public static UsuarioDAOImpl getInstance(){
        if (instance == null){
            instance = new UsuarioDAOImpl();
        }
        return instance;
    }

    /*public UsuarioDAOImpl() {
        database = new ArrayList<>();
    }*/

    @Override
    public int addUsuario(Usuario usuario) {
        if (database == null){
            database = new ArrayList<>();
        }
        if(usuario != null){
            for (Usuario user : database) {
                if (Objects.equals(usuario.getUsername(), user.getUsername())) {
                    return 0;
                }
            }

            database.add(usuario);
            int i = 0;
            Log.d("myTag", "usario" + database.get(i).getUsername());
            i++;
            return 1;
        }
        return 0;
    }

    public void addContato(Usuario user, Contato contato){
            for (Usuario usuario: database){
                if (usuario == user){
                    user.addContato(contato);
                }
            }


    }

    @Override
    public Usuario findByUsername(String username) {
        if (database == null){
            database = new ArrayList<>();
        }
        for (Usuario user : database){
            if (Objects.equals(username, user.getUsername())){
                return user;
            }
        }
        return null;

    }

    public Usuario checkUserPassw(String username, String passw){
        if (database == null){
            database = new ArrayList<>();
        }
        for (Usuario user: database){
            if (Objects.equals(username, user.getUsername()) && Objects.equals(passw, user.getSenha())){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return database;
    }
}
