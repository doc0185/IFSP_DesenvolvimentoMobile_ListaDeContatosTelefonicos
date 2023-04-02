package br.edu.ifsp.dmos5.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import br.edu.ifsp.dmos5.DAO.UsuarioDAO;
import br.edu.ifsp.dmos5.DAO.UsuarioDAOImpl;
import br.edu.ifsp.dmos5.Model.MD5;
import br.edu.ifsp.dmos5.Model.Usuario;
import br.edu.ifsp.dmos5.R;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userEditText;
    private EditText passwEditText;
    private EditText passwCEditText;
    private Button saveButton;
    private Usuario user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findById();
        setClickListener();
    }

    private void findById(){
        userEditText = findViewById(R.id.edittext_userNU);
        passwEditText = findViewById(R.id.edittext_senhaNU);
        passwCEditText = findViewById(R.id.edittext_senhaConfNU);
        saveButton = findViewById(R.id.button_SalvarNU);
    }

    private void setClickListener() {
        saveButton.setOnClickListener(this);
    }

    private String readEditText(EditText et){
        String etS = et.getText().toString();

        return etS;
    }

    @Override
    public void onClick(View v) {
        if (v== saveButton){
            saveNewUser(UsuarioDAOImpl.getInstance());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveNewUser(UsuarioDAOImpl uDAO){
        String username = readEditText(userEditText);
        String passw = readEditText(passwEditText);
        String passwC = readEditText(passwCEditText);

        if(username.matches("") || passw.matches("") || passwC.matches("")){
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_LONG).show();
            return;
        }

        Log.d("myTag", username);
        Log.d("myTag", passw);
        Log.d("myTag", passwC);

        if (!passw.equals(passwC)){
            Toast.makeText(this, R.string.passwNotEqual, Toast.LENGTH_LONG).show();
        } else{
            try{
                String passwMD5 = MD5.getHashMd5(passw);
                user = new Usuario(username, passwMD5);
                if (uDAO.addUsuario(user) == 1){
                    Toast.makeText(this, R.string.userCreated, Toast.LENGTH_LONG).show();
                    finish();
                } else{
                    Toast.makeText(this, R.string.userAlreadyCreated, Toast.LENGTH_LONG).show();
                }

            } catch (NoSuchAlgorithmException e) {
                Toast.makeText(this, R.string.NoSuchAlgorithmException, Toast.LENGTH_SHORT).show();
            }
            /*
            user = new Usuario(username, passw);
            if (uDAO.addUsuario(user) == 1){
                Toast.makeText(this, R.string.userCreated, Toast.LENGTH_LONG).show();
                finish();
            } else{
                Toast.makeText(this, R.string.userAlreadyCreated, Toast.LENGTH_LONG).show();
            }*/
        }
    }


}