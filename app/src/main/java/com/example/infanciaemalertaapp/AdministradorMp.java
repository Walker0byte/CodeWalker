package com.example.infanciaemalertaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AdministradorMp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_mp);

        //Toast.makeText(this, "Login Efetuado com Sucesso!", Toast.LENGTH_LONG).show();
        mAuth = FirebaseAuth.getInstance();

       context = this;

        final EditText editTextSenha = this.findViewById(R.id.edit_TextSenha);
        final EditText editTextEmail = this.findViewById(R.id.edit_TextEmail);
        final Button btAcessar = this.findViewById(R.id.btAcessar);
        final Button btSair = this.findViewById(R.id.btSair);
        final ListView listView = this.findViewById(R.id.listNomeTelefoneMatriculaOabrgEnderecocepCidadeEstadoEmail);
        final Button btPrimeiroAcesso = findViewById(R.id.btPrimeiroAcesso);

        btPrimeiroAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent acesso = new Intent(context, PrimeiroAcesso.class);
                startActivity(acesso);

            }
        });

        btAcessar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        String email, senha;
        email = editTextEmail.getText().toString();
        senha = editTextSenha.getText().toString();
        Log.i("email", email);
        administradorMp(email,senha);

            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent acesso = new Intent(context, MainActivity.class);
                startActivity(acesso);

            }
        });
    }
    private void administradorMp (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LoginUsuario", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                           Intent denunciasCadastradas = new Intent(context, DenunciasCadastradas.class);
                           startActivity(denunciasCadastradas);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LoginUsuario", "signInWithEmail:failure", task.getException());

                        }
                    }
                });

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
