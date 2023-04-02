package br.edu.ifsp.dmos5.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import br.edu.ifsp.dmos5.DAO.UsuarioDAOImpl;
import br.edu.ifsp.dmos5.Model.MD5;
import br.edu.ifsp.dmos5.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userEditText;
    private EditText passwEditText;
    private Button loginButton;
    private Button newUserButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsuarioDAOImpl.getInstance();

        findById();
        setClickListener();
    }

    private void findById(){
        userEditText = findViewById(R.id.edittext_userM);
        passwEditText = findViewById(R.id.edittext_senhaM);
        loginButton = findViewById(R.id.button_loginM);
        newUserButton = findViewById(R.id.button_novoUsuarioM);
    }

    private void setClickListener() {
        loginButton.setOnClickListener(this);
        newUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v== newUserButton){
            openNewUser();
        }
        if (v== loginButton){
            openContacts();
        }
    }

    private String readEditText(EditText et){

        String etS = et.getText().toString();

        return etS;
    }


    private void openContacts(){
        String user = readEditText(userEditText);
        String passw = readEditText(passwEditText);

        if(user.matches("") || passw.matches("")){
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_LONG).show();
            return;
        }

        try{
            String passwMD5 = MD5.getHashMd5(passw);
            Bundle bundle = new Bundle();
            bundle.putString("user", user);
            bundle.putString("passw", passwMD5);

            Intent intent = new Intent(this, ContactsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(this, R.string.NoSuchAlgorithmException, Toast.LENGTH_SHORT).show();
        }
        /*
        Bundle bundle = new Bundle();
        bundle.putString("user", user);
        bundle.putString("passw", passw);

        Intent intent = new Intent(this, ContactsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);*/
    }

    private void openNewUser(){

        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }
}