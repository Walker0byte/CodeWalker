package com.example.infanciaemalertaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        final Button btDenuncie = (Button) this.findViewById(R.id.btDenuncie);
        final Button btAcessar = (Button) this.findViewById(R.id.btAcessar);

        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent administradorMp = new Intent(context, AdministradorMp.class);
                startActivity(administradorMp);
            }
        });

        btDenuncie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent denuncieAqui = new Intent(context, DenuncieAqui.class);
                startActivity(denuncieAqui);
            }
        });

    }
}