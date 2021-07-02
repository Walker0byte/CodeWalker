package com.example.infanciaemalertaapp;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

import javax.security.auth.AuthPermission;


public class DenuncieAqui extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String FemOrMasc;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Context context = this;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncie_aqui);

        final EditText editTextMenor = this.findViewById(R.id.edit_TextMenor);
        final EditText editTextIdade = this.findViewById(R.id.edit_TextIdade);
        final TextView textViewSexo = this.findViewById(R.id.TextSexo);
        final EditText editTextMae =  this.findViewById(R.id.edit_TextMae);
        final EditText editTextPai = this.findViewById(R.id.edit_TextPai);
        final EditText editTextEnderecoM = this.findViewById(R.id.edit_TextEnderecoM);
        final EditText editTextEstadoM = this.findViewById(R.id.edit_TextEstadoM);
        final EditText editTextCidadeM = this.findViewById(R.id.edit_TextCidadeM);
        final EditText editTextEscola =  this.findViewById(R.id.edit_TextEscola);
        final Button btDesistir = this.findViewById(R.id.btDesistir);
        final Button btProximo = this.findViewById(R.id.btProximo);
        final ListView listView = this.findViewById(R.id.listMenorIdadeSexoMaePaiEscolaEnderecoMEstadoMCidadeM);
        final RadioGroup radioGroupFemOrMasc = findViewById(R.id.radioGroupFemorMasc);
        final RadioButton radioButtonFem =  findViewById(R.id.radioButtonFem);
        final RadioButton radioButtonMasc = findViewById(R.id.radioButtonMasc);

        radioGroupFemOrMasc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                FemOrMasc = (String) radioButton.getText();
                Log.d ("Seleção enviada", FemOrMasc);

            }
            });

        btDesistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(context, MainActivity.class);
                startActivity(mainActivity);
            }
        });

       ArrayList denuncie = new ArrayList<String>();

       final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_2, denuncie);


        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent  = new Intent(DenuncieAqui.this, DescricaoDenuncia.class);
              intent.putExtra ("Menor", editTextMenor.getText().toString());
              intent.putExtra("Idade", editTextIdade.getText().toString());
              intent.putExtra("SexoM", FemOrMasc);
              intent.putExtra("Mae", editTextMae.getText().toString());
              intent.putExtra("Pai", editTextPai.getText().toString());
              intent.putExtra("Escola", editTextEscola.getText().toString());
              intent.putExtra("Endereco", editTextEnderecoM.getText().toString());
              intent.putExtra("Cidade", editTextCidadeM.getText().toString());
              intent.putExtra("Estado", editTextEstadoM.getText().toString());

              startActivity(intent);



            }
        });

    }


    @Override
    protected void onPause(){
        Log.i("curso", "onPause chamado");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i("curso", "onResume chamado");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i("curso", "onDestroy chamado");
        super.onDestroy();
    }
}


