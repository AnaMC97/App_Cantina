package com.example.appcantina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //---------------------------------------------------------------------
        //tirar bordas
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //---------------------------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_btn);
    }
    public void EsqueciSenha(View v){
        Intent it = new Intent(TelaInicial.this,Esqueci_Senha.class);
        startActivity(it);
        finish();
    }

}