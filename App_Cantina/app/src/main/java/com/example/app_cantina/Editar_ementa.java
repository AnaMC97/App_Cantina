package com.example.app_cantina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Editar_ementa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ementa);
    }
    public void voltar(View v){
        Intent it = new Intent(Editar_ementa.this,Tela_1_Cantina.class);
        startActivity(it);
        finish();
    }
}