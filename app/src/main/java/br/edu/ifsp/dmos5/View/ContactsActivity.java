package br.edu.ifsp.dmos5.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import br.edu.ifsp.dmos5.R;

public class ContactsActivity extends AppCompatActivity {

    private Spinner contatosSpinner;
    private TextView nomeContatoTextView;
    private TextView telefoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        findById();
    }

    private void findById(){
        contatosSpinner = findViewById(R.id.spinner_contatosC);
        nomeContatoTextView = findViewById(R.id.textview_nomeContatoC);
        telefoneTextView = findViewById(R.id.textview_telefoneC);
    }
}