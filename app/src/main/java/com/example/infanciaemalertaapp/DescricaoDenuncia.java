package com.example.infanciaemalertaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class DescricaoDenuncia extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private FirebaseStorage mFirebasestorage;
    private StorageReference mdenunciaphotosStorageReference;
    private static final int AnexarImagem = 1;
    private ImageView imageView;
    private Uri mImageUri;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_denuncia);

        Context context = this;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mFirebasestorage = FirebaseStorage.getInstance();
        mdenunciaphotosStorageReference = mFirebasestorage.getReference().child("denuncias");


        imageView = findViewById(R.id.imageView);
        final EditText editTextDescricao =  this.findViewById(R.id.edit_TextDescricao);
        final Button btEnviar =  this.findViewById(R.id.btEnviar);
        final Button btAnexar = this.findViewById(R.id.btAnexar);
        final Button btRetornar =  this.findViewById(R.id.btRetornar);
        final Button btEnviado = this.findViewById(R.id.btEnviado);

        final TextView textViewNMenor = this.findViewById(R.id.tvMenor);
        final TextView textViewIdadeM = this.findViewById(R.id.tvIdade);
        final TextView textViewSexoM = this.findViewById(R.id.tvSexoM);
        //final TextView textViewSexoFem = this.findViewById(R.id.tvSexoFem);
        final TextView textViewMaeM = this.findViewById(R.id.tvMae);
        final TextView textViewPaiM = this.findViewById(R.id.tvPai);
        final TextView textViewEnderecoM = this.findViewById(R.id.tvEndereco);
        final TextView textViewEscolaM = this.findViewById(R.id.tvEscola);
        final TextView textViewCidadeM = this.findViewById(R.id.tvCidade);
        final TextView textViewEstadoM = this.findViewById(R.id.tvEstado);


        String NMenor = getIntent().getStringExtra("Menor");
        String IdadeM = getIntent().getStringExtra("Idade");
        String SexoM = getIntent().getStringExtra("SexoM");
        String MaeM = getIntent().getStringExtra("Mae");
        String PaiM = getIntent().getStringExtra("Pai");
        String EscolaM = getIntent().getStringExtra("Escola");
        String EnderecoM = getIntent().getStringExtra("Endereco");
        String CidadeM = getIntent().getStringExtra("Cidade");
        String EstadoM = getIntent().getStringExtra("Estado");


        textViewNMenor.setText(NMenor);
        textViewIdadeM.setText(IdadeM);
        textViewSexoM.setText(SexoM);
        textViewMaeM.setText(MaeM);
        textViewPaiM.setText(PaiM);
        textViewEscolaM.setText(EscolaM);
        textViewEnderecoM.setText(EnderecoM);
        textViewCidadeM.setText(CidadeM);
        textViewEstadoM.setText(EstadoM);


        btAnexar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

            }
            private void openFileChooser(){
                Intent intent = new Intent();
                intent.setType("image/*");
                //intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, AnexarImagem);

         }

         });


            btRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent denuncieAqui = new Intent(context, DenuncieAqui.class);
                startActivity(denuncieAqui);
            }
        });

            btEnviado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainActivity = new Intent(context, MainActivity.class);
                    startActivity(mainActivity);
                }
            });
        ArrayList descricaoD = new ArrayList<String>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, descricaoD);

       btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NMenor, IdadeM, SexoM, MaeM, PaiM, EscolaM, EnderecoM, CidadeM, EstadoM, descricao;
                NMenor = textViewNMenor.getText().toString();
                IdadeM = textViewIdadeM.getText().toString();
                SexoM = textViewSexoM.getText().toString();
                MaeM = textViewMaeM.getText().toString();
                PaiM = textViewPaiM.getText().toString();
                EscolaM = textViewEscolaM.getText().toString();
                EnderecoM = textViewEnderecoM.getText().toString();
                CidadeM = textViewCidadeM.getText().toString();
                EstadoM = textViewEstadoM.getText().toString();
                descricao = editTextDescricao.getText().toString();
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap =  drawable.getBitmap();
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                Log.i("descricao", descricao);
                descricaoDenuncia (NMenor, IdadeM, SexoM, MaeM, PaiM, EscolaM, EnderecoM, CidadeM, EstadoM, descricao);

                textViewNMenor.setText("");
                textViewIdadeM.setText("");
                textViewSexoM.setText("");
                textViewMaeM.setText("");
                textViewPaiM.setText("");
                textViewEscolaM.setText("");
                textViewEnderecoM.setText("");
                textViewCidadeM.setText("");
                textViewEstadoM.setText("");
                editTextDescricao.setText("");
                imageView.setImageResource(android.R.color.transparent);

                Toast.makeText(getApplicationContext(), "Denúncia enviada com sucesso!",
                        Toast.LENGTH_LONG);

          }
        });

    }

    private void descricaoDenuncia(String NMenor, String IdadeM, String SexoM, String MaeM,                                    String PaiM, String EscolaM, String EnderecoM, String CidadeM, String EstadoM, String descricao) {


        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Denúncia realizada", "createUserWithId:success");
                            FirebaseUser useD = mAuth.getCurrentUser();
                            System.out.println (NMenor + IdadeM + SexoM + MaeM + PaiM + EscolaM +
                                    EnderecoM + CidadeM + EstadoM + descricao);
                            writeNewUserD  (NMenor, IdadeM, SexoM, MaeM, PaiM, EscolaM, EnderecoM, CidadeM, EstadoM, descricao );


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Denúncia realizada", "createUserWithId:failure", task.getException());

                        }
                    }
                });
    }

    private void writeNewUserD(String NMenor, String IdadeM, String SexoM, String MaeM,
                               String PaiM, String EscolaM, String EnderecoM, String CidadeM, String EstadoM, String descricao){
        FirebaseUser descricaoD = mAuth.getCurrentUser();
        UserD userD = new UserD(NMenor, IdadeM, SexoM, MaeM, PaiM, EscolaM, EnderecoM, CidadeM, EstadoM, descricao);
        mDatabase.child("denuncia").child(descricaoD.getUid()).setValue(userD);


   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AnexarImagem) {
            Log.i("INFO","Passou");
            final Uri uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();

                Uri selectdImageUri = data.getData();
                StorageReference photoRef =
                        mdenunciaphotosStorageReference.child(selectdImageUri.getLastPathSegment());
                photoRef.putFile(selectdImageUri).addOnSuccessListener(
                        this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Uri uploadSessionUri = taskSnapshot.getUploadSessionUri();
                            }
                        });
                }
            //use the bitmap as you like
            imageView.setImageBitmap(bitmap);


        }

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

  