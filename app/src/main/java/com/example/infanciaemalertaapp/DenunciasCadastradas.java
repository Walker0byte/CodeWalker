package com.example.infanciaemalertaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DenunciasCadastradas <Post, listView> extends AppCompatActivity {

    public static String detalhes;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> userDList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncias_cadastradas);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase = mDatabase.child("denuncia");

        Context context = this;

        final Button btInicio = (Button) this.findViewById(R.id.btInicio);
        ListView listView = (ListView) this.findViewById(R.id.listMenorIdadeSexoMaePaiEscolaEnderecoMEstadoMCidadeM);


        btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivity = new Intent(context, MainActivity.class);
                startActivity(mainActivity);
            }
        });
        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, userDList);
        listView.setAdapter(arrayAdapter);


        getData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalhesDenunciaIntent = new Intent(context, DetalhesDenuncia.class);
                detalhesDenunciaIntent.putExtra(detalhes, userDList.get(position).toString());
                startActivity(detalhesDenunciaIntent);


            }
        });


    }

    private void getData(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    UserD userD = ds.getValue(UserD.class);
                    System.out.println("Denuncias = " + userD.email);
                    String userString = String.valueOf(userD);
                    arrayAdapter.add(userString);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}