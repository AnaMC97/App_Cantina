package com.example.app_cantina;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_1_Cantina extends AppCompatActivity {

private TextView codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //------------------------------------------------
        // retirar barra criada auto
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1_cantina);
        //---------------------------------------------------
        //criação da var com a "senha"
        String varcod;
        varcod="000000";
        //--------------------------------------------------
        //validação auto  codigo
        codigo = findViewById(R.id.txtcodigo);
        codigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String texto=new String();
                texto = codigo.getText().toString();
                if (texto.length() == 6) {
                    if (texto.equals(varcod)){
                        Toast.makeText(Tela_1_Cantina.this, "VALIDADO", Toast.LENGTH_SHORT).show();
                        //------------------------------------------------------------------------------------
                        TextView textView = (TextView) findViewById(R.id.txtnome);
                        String nomeal="Manuel de Carreira Lopes e Marques Neves";
                        textView.setText(nomeal);
                        }
                        ImageView imagemcarne=(ImageView) findViewById(R.id.imageView3);
                         imagemcarne.setImageResource(R.drawable.carne_m);
                        }
                    }
                });
            }

    public void Editarementa(View v){
        Intent it = new Intent(Tela_1_Cantina.this,Editar_ementa.class);
        startActivity(it);
        finish();
    }
}