package br.edu.ifsp.dmos5.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifsp.dmos5.DAO.UsuarioDAO;
import br.edu.ifsp.dmos5.DAO.UsuarioDAOImpl;
import br.edu.ifsp.dmos5.Model.Contato;
import br.edu.ifsp.dmos5.Model.Usuario;
import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.View.Adapter.ContactsSpinnerAdapter;

public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

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
        setOnItemSelectedListener();
        setClickListener();

        populateSpinner(UsuarioDAOImpl.getInstance());


    }

    @Override
    protected void onResume() {
        super.onResume();
        contatosSpinner.setSelection(0);
        populateSpinner(UsuarioDAOImpl.getInstance());
        setOnItemSelectedListener();

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

    private void setOnItemSelectedListener(){
        contatosSpinner.setOnItemSelectedListener(this);
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

    private void populateSpinner(UsuarioDAO uDAO){
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("user");
        List<Contato> dataset = uDAO.findByUsername(user).findAll();
        dataset.add(0, null);

        ContactsSpinnerAdapter adapter = new ContactsSpinnerAdapter(this, android.R.layout.simple_spinner_item, dataset);
        contatosSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position==0){
            nomeContatoTextView.setText("");
            telefoneTextView.setText("");
        } else{
            showContactsDetails((Contato) contatosSpinner.getItemAtPosition(position));
        }


        //nomeContatoTextView.setText(((Contato) contatosSpinner.getItemAtPosition(position)).getNome());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        nomeContatoTextView.setVisibility(View.GONE);
        telefoneTextView.setVisibility(View.GONE);
    }

    private void showContactsDetails (Contato contato){
        nomeContatoTextView.setVisibility(View.VISIBLE);
        telefoneTextView.setVisibility(View.VISIBLE);
        nomeContatoTextView.setText(contato.getNome());
        telefoneTextView.setText(contato.getTelefone());
    }
}