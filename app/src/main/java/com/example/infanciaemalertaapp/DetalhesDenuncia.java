package com.example.infanciaemalertaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class DetalhesDenuncia<Menor, Pessoa> extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> userList = new ArrayList<>();
    private List<Menor> pessoaList = new ArrayList<>();
    private ArrayAdapter<Menor>pessoaArrayAdapter;


    ListView listViewdados;

    Menor menorSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_denuncia);

        Context context = this;

        final Button btInicial = this.findViewById(R.id.btInicial);
        final Button btVoltar = this.findViewById(R.id.btVoltar);
        final TextView detalhesDenuncia = findViewById(R.id.detalhesDenuncia);
        ListView listViewdados =   (ListView) findViewById(R.id.listViewdados);

        Intent intent = getIntent();
        String detalhes = intent.getStringExtra(DenunciasCadastradas.detalhes);
        detalhesDenuncia.setText(detalhes);

        inicializarFirebase();
        eventoDatabase();


        listViewdados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           menorSelecionado =  (Menor) parent.getItemAtPosition(position);
           detalhesDenuncia.setText(menorSelecionado.toString());
            }
        });

        btInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent (context, MainActivity.class);
                startActivity(mainActivity);
            }
        });


        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent denunciasCadastradas = new Intent (context, DenunciasCadastradas.class);
                startActivity(denunciasCadastradas);
            }
        });



    }

    private void eventoDatabase() {
    }

    private void inicializarFirebase() {
    }
}