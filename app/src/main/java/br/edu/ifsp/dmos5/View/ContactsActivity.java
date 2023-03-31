package br.edu.ifsp.dmos5.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmos5.DAO.UsuarioDAO;
import br.edu.ifsp.dmos5.DAO.UsuarioDAOImpl;
import br.edu.ifsp.dmos5.Model.Usuario;
import br.edu.ifsp.dmos5.R;

public class ContactsActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner contatosSpinner;
    private TextView nomeContatoTextView;
    private TextView telefoneTextView;
    private Button novoContatoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UsuarioDAOImpl.getInstance();
        checkPassw(UsuarioDAOImpl.getInstance());

        findById();
        setClickListener();

    }

    private void findById(){
        contatosSpinner = findViewById(R.id.spinner_contatosC);
        nomeContatoTextView = findViewById(R.id.textview_nomeContatoC);
        telefoneTextView = findViewById(R.id.textview_telefoneC);
        novoContatoButton = findViewById(R.id.button_novoContatoC);
    }

    private void setClickListener() {
        novoContatoButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == novoContatoButton){
            saveNewContato(UsuarioDAOImpl.getInstance());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            /*
             * Usar o método finish() finaliza a acticity, contudo, não é a forma correta
             * de navegar entre as activities de nosso aplicativo. Futuramente iremos
             * entender melhor sobre navegação entre activities e também o ciclo de vida
             * de uma activity.
             */
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveNewContato(UsuarioDAO uDAO){
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("user");

        Bundle bundle2 = new Bundle();
        bundle2.putString("user", user);

        Intent intent = new Intent(this, NewContactActivity.class);
        intent.putExtras(bundle2);
        startActivity(intent);
    }

    private Usuario checkPassw(UsuarioDAO uDAO){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String user = bundle.getString("user");
            String passw = bundle.getString("passw");

            if (uDAO.checkUserPassw(user, passw) == null){
                Toast.makeText(this, R.string.userNotFound, Toast.LENGTH_LONG).show();
                finish();
            }
            return uDAO.checkUserPassw(user, passw);
        }
        return null;
    }

}