package com.example.appcantinacozinha;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tela_1_Cantina extends AppCompatActivity {
//----------------------------------------------------------


//--------------------------------------------------------------------------
    ImageView img;
    Button ajudar, editarEmenta;
    TextView textoajudar, txtquantref, txtcarne, txtpeixe, txtveg, txtrefname, txtnome, txtcod, txtcodigo, sair;

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

        img = findViewById(R.id.img);
        ajudar = findViewById(R.id.ajuda);
        editarEmenta = findViewById(R.id.editarementa);
        textoajudar = findViewById(R.id.textoajuda);
        txtquantref = findViewById(R.id.txtquantref);
        txtcarne = findViewById(R.id.txtcarne);
        txtpeixe = findViewById(R.id.txtpeixe);
        txtveg = findViewById(R.id.txtvegetariano);
        txtrefname = findViewById(R.id.txtref_name);
        txtnome = findViewById(R.id.txtnome);
        txtcod = findViewById(R.id.txtcod);
        txtcodigo = findViewById(R.id.txtcodigo);
        sair = findViewById(R.id.sair);

        img.setVisibility(View.VISIBLE);
        ajudar.setVisibility(View.VISIBLE);
        editarEmenta.setVisibility(View.VISIBLE);
        textoajudar.setVisibility(View.INVISIBLE);
        txtquantref.setVisibility(View.VISIBLE);
        txtcarne.setVisibility(View.VISIBLE);
        txtpeixe.setVisibility(View.VISIBLE);
        txtveg.setVisibility(View.VISIBLE);
        txtrefname.setVisibility(View.VISIBLE);
        txtnome.setVisibility(View.VISIBLE);
        txtcod.setVisibility(View.VISIBLE);
        txtcodigo.setVisibility(View.VISIBLE);
        sair.setVisibility(View.INVISIBLE);

        ajudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setVisibility(View.INVISIBLE);
                ajudar.setVisibility(View.VISIBLE);
                editarEmenta.setVisibility(View.INVISIBLE);
                txtquantref.setVisibility(View.INVISIBLE);
                txtcarne.setVisibility(View.INVISIBLE);
                txtpeixe.setVisibility(View.INVISIBLE);
                txtveg.setVisibility(View.INVISIBLE);
                txtrefname.setVisibility(View.INVISIBLE);
                txtnome.setVisibility(View.INVISIBLE);
                txtcod.setVisibility(View.INVISIBLE);
                txtcodigo.setVisibility(View.INVISIBLE);
                textoajudar.setVisibility(View.VISIBLE);
                sair.setVisibility(View.VISIBLE);
            }
        });
        //---------------------------------------------------
        //criação da var com a "senha"
        String varcod;
        varcod="000000";
        //--------------------------------------------------
        //validação auto  codigo
        txtcodigo = findViewById(R.id.txtcodigo);
        txtcodigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String texto=new String();
                texto = txtcodigo.getText().toString();
                if (texto.length() == 6) {
                    if (texto.equals(varcod)){
                        Toast.makeText(Tela_1_Cantina.this, "VALIDADO", Toast.LENGTH_SHORT).show();
                        //------------------------------------------------------------------------------------
                        TextView textView = (TextView) findViewById(R.id.txtnome);
                        String nomeal="Manuel de Carreira Lopes e Marques Neves";
                        textView.setText(nomeal);
                        }
                        ImageView imagemcarne=(ImageView) findViewById(R.id.img);
                         imagemcarne.setImageResource(R.drawable.carne_m);
                        }
                    }
                });
            }

    public void Editarementa(View v){
        Intent it = new Intent(Tela_1_Cantina.this,Tela_2_Cantina.class);
        startActivity(it);
        finish();
    }
    public void sair(View v){
        Intent it = new Intent(Tela_1_Cantina.this, Tela_1_Cantina.class);
        startActivity(it);
        finish();
    }

}