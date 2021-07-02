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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

    public class PrimeiroAcesso extends AppCompatActivity {

        private FirebaseAuth mAuth;
        private DatabaseReference mDatabase;


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            Context context = this;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mAuth = FirebaseAuth.getInstance();

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_primeiro_acesso);

            final EditText editTextName = this.findViewById(R.id.edit_TextName);
            final EditText editTextTelefone =  this.findViewById(R.id.edit_TextTelefone);
            final EditText editTextMatricula = this.findViewById(R.id.edit_TextMatricula);
            final EditText editTextoab = this.findViewById(R.id.edit_Textoab);
            final EditText editTextrg = this.findViewById(R.id.edit_Textrg);
            final EditText editTextEndereco = this.findViewById(R.id.edit_TextEndereco);
            final EditText editTextcep = this.findViewById(R.id.edit_Textcep);
            final EditText editTextCidade = this.findViewById(R.id.edit_TextCidade);
            final EditText editTextEstado = this.findViewById(R.id.edit_TextEstado);
            final EditText editTextEmail = this.findViewById(R.id.edit_TextEmail);
            final EditText editTextSenha = this.findViewById(R.id.edit_TextSenha);
            final Button btCadastrar =  this.findViewById(R.id.btCadastrar);
            final Button btVoltar = this.findViewById(R.id.btVoltar);
            final ListView listView = this.findViewById(R.id.listNomeTelefoneMatriculaOabrgEnderecocepCidadeEstadoEmail);

            btVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainActivity = new Intent(context, MainActivity.class);
                    startActivity(mainActivity);
                }
            });

            ArrayList contatos = new ArrayList<String>();

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, contatos);

            btCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String nome, email, telefone, senha, matricula, oab, rg, endereco, cep, cidade, estado;
                    nome = editTextName.getText().toString();
                    telefone = editTextTelefone.getText().toString();
                    email = editTextEmail.getText().toString();
                    senha = editTextSenha.getText().toString();
                    matricula = editTextMatricula.getText().toString();
                    oab = editTextoab.getText().toString();
                    rg = editTextrg.getText().toString();
                    endereco = editTextEndereco.getText().toString();
                    cep = editTextcep.getText().toString();
                    cidade = editTextCidade.getText().toString();
                    estado = editTextEstado.getText().toString();
                    Log.i("nome", nome);
                    primeiroAcesso(nome, email, telefone, senha, matricula, oab, rg, endereco, cep, cidade, estado);

                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!",
                            Toast.LENGTH_LONG);

                }
            });

        }

                private void primeiroAcesso (String nome, String email, String telefone, String senha,
                                             String matricula, String oab, String rg, String endereco, String cep, String cidade, String estado){
                    mAuth.createUserWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("UsuarioCadastrado", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        System.out.println(nome +email + telefone + senha + matricula + oab + rg + endereco + cep + cidade + estado);
                                        writeNewUser(nome, email, telefone, senha, matricula, oab, rg, endereco, cep, cidade, estado);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("UsuarioCadastrado", "createUserWithEmail:failure", task.getException());

                                    }
                                }
                            });
                }

                                    public void writeNewUser(String nome, String email,
                                    String telefone, String senha, String matricula, String oab, String rg,
                                                             String endereco, String cep, String cidade, String estado) {
                                    FirebaseUser usuarios = mAuth.getCurrentUser();
                                    User user = new User(nome, email, telefone, senha, matricula, oab, rg, endereco,cep, cidade, estado);
                                    //System.out.println(user.email);
                                    mDatabase.child("users").child(usuarios.getUid()).setValue(user);
        }

        @Override
        protected void onPause() {
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



