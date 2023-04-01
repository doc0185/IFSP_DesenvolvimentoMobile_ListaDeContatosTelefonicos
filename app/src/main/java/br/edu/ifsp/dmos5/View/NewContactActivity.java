package br.edu.ifsp.dmos5.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.dmos5.DAO.UsuarioDAO;
import br.edu.ifsp.dmos5.DAO.UsuarioDAOImpl;
import br.edu.ifsp.dmos5.Model.Contato;
import br.edu.ifsp.dmos5.R;

public class NewContactActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText apelidoEditText;
    private EditText nomeEditText;
    private EditText telefoneEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findById();
        setClickListener();
    }

    private void findById(){
        apelidoEditText = findViewById(R.id.textview_apelidoNC);
        nomeEditText = findViewById(R.id.textview_nomeNC);
        telefoneEditText = findViewById(R.id.textview_telefoneNC);
        saveButton = findViewById(R.id.button_SalvarNC);
    }

    private String readEditText(EditText et){
        String etS = et.getText().toString();

        return etS;
    }

    private void setClickListener() {
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == saveButton){
            saveNewContact(UsuarioDAOImpl.getInstance());
        }
    }

    public void saveNewContact(UsuarioDAO uDAO){
        Contato contato;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String user = bundle.getString("user");
            String apelido = readEditText(apelidoEditText);
            String nome = readEditText(nomeEditText);
            String telefone = readEditText(telefoneEditText);

            contato = new Contato(apelido, nome, telefone);

            uDAO.addContato(uDAO.findByUsername(user), contato);

            Toast.makeText(this, R.string.contactSaved, Toast.LENGTH_LONG).show();
            //Log.d("msg", uDAO.findByUsername(user).getContato());
        }
    }
}