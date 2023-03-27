package br.edu.ifsp.dmos5.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    }

    private void findById(){
        userEditText = findViewById(R.id.edittext_userM);
        passwEditText = findViewById(R.id.edittext_senhaM);
        loginButton = findViewById(R.id.button_loginM);
        newUserButton = findViewById(R.id.button_novoUsuarioM);
    }

    @Override
    public void onClick(View v) {
        if (v== newUserButton){
            openNewUser();
        }
    }

    private String readEditText(EditText et){
        String etS = et.getText().toString();

        return etS;
    }

    private void openNewUser(){
        String user = readEditText(userEditText);
        String passw = readEditText(passwEditText);

        Bundle bundle = new Bundle();
        bundle.putString("user", user);
        bundle.putString("passw", passw);

        Intent intent = new Intent(this, NewUserActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}