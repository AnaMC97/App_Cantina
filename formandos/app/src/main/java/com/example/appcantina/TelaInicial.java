package com.example.appcantina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class TelaInicial extends AppCompatActivity {
    private EditText RFID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //---------------------------------------------------------------------
        //tirar bordas
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //---------------------------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        //-------------------------------------------------------

        RFID = findViewById(R.id.TagID);
        RFID.setInputType(0);
        RFID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               String conteudo = RFID.getText().toString();
                if (conteudo.length() == 10) {
                    if (RFID.equals(RFID)) {
                        Intent it = new Intent(TelaInicial.this, telaAlmoco.class);
                        startActivity(it);
                        finish();
                        Toast.makeText(TelaInicial.this, "Bem vindo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TelaInicial.this, "Erro na validação", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
        public void EsqueciSenha (View v){
            Intent it = new Intent(TelaInicial.this, Esqueci_Senha.class);
            startActivity(it);
            finish();
        }

    }


