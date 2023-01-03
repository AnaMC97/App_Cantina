package com.example.testeprojeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Residentes extends AppCompatActivity implements View.OnClickListener {

    ImageButton imagemcafe, imagemsun, imagemlua;
    Button segundafeira, tercafeira, quartafeira, quintafeira, sextafeira, menucafe, menualmoco, menujantar, ajudar;
    TextView textoajudar;

    String VerdeClaro = "#ADE792";
    String VerdeEscuro = "#6ECCAF";
    String AmareloClaro = "#F3ECB0";
    String AmareloEscuro = "#fabf7c";
    String Azul = "#98d8f8";

    String EmentaPequenoAlmoco = "Pequeno almoço";

    String [] EmentaJantar = new String[]{
            "Ementa segunda Jantar",
            "Ementa terça Jantar",
            "Ementa quarta Jantar",
            "Ementa quinta Jantar",
            "Ementa sexta Jantar"
    };

    int [] EscolhasEmentaAlmoco = new int[]{
            0, 0, 0, 0, 0
    };

    int [] EscolhasEmentaCafe = new int[]{
            0, 0, 0, 0, 0
    };

    int [] EscolhasEmentaJantar = new int[]{
            0, 0, 0, 0, 0
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residentes);

        menucafe = findViewById(R.id.ementapequenoalmoco);
        menucafe.setOnClickListener(this);
        menualmoco = findViewById(R.id.ementaalmoco);
        menualmoco.setOnClickListener(this);
        menujantar = findViewById(R.id.ementajantar);
        menujantar.setOnClickListener(this);

        segundafeira = findViewById(R.id.segfeira);
        segundafeira.setOnClickListener(this);
        tercafeira = findViewById(R.id.terfeira);
        tercafeira.setOnClickListener(this);
        quartafeira = findViewById(R.id.quafeira);
        quartafeira.setOnClickListener(this);
        quintafeira = findViewById(R.id.quifeira);
        quintafeira.setOnClickListener(this);
        sextafeira = findViewById(R.id.sexfeira);
        sextafeira.setOnClickListener(this);

        imagemcafe = findViewById(R.id.imgcafe);
        imagemsun = findViewById(R.id.imgsun);
        imagemlua = findViewById(R.id.imglua);

        imagemcafe.setVisibility(View.INVISIBLE);
        imagemsun.setVisibility(View.INVISIBLE);
        imagemlua.setVisibility(View.INVISIBLE);
        menucafe.setVisibility(View.INVISIBLE);
        menualmoco.setVisibility(View.INVISIBLE);
        menujantar.setVisibility(View.INVISIBLE);
        menucafe.setText(EmentaPequenoAlmoco);
        menualmoco.setText("Ver opções de almoço");

        ajudar = findViewById(R.id.ajuda);
        textoajudar = findViewById(R.id.textoajuda);
        ajudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagemcafe.setVisibility(View.INVISIBLE);
                imagemsun.setVisibility(View.INVISIBLE);
                imagemlua.setVisibility(View.INVISIBLE);
                menucafe.setVisibility(View.INVISIBLE);
                menualmoco.setVisibility(View.INVISIBLE);
                menujantar.setVisibility(View.INVISIBLE);
                textoajudar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = -1;
        imagemcafe.setVisibility(View.VISIBLE);
        imagemsun.setVisibility(View.VISIBLE);
        imagemlua.setVisibility(View.VISIBLE);
        menucafe.setVisibility(View.VISIBLE);
        menualmoco.setVisibility(View.VISIBLE);
        menujantar.setVisibility(View.VISIBLE);
        textoajudar.setVisibility(View.INVISIBLE);
        switch (v.getId()) {
            case R.id.segfeira:
                i = 0;
                MostraEmenta(i, EscolhasEmentaCafe[i], EscolhasEmentaJantar[i]);
                break;
            case R.id.terfeira:
                i = 1;
                MostraEmenta(i, EscolhasEmentaCafe[i], EscolhasEmentaJantar[i]);
                break;
            case R.id.quafeira:
                i = 2;
                MostraEmenta(i, EscolhasEmentaCafe[i], EscolhasEmentaJantar[i]);
                break;
            case R.id.quifeira:
                i = 3;
                MostraEmenta(i, EscolhasEmentaCafe[i], EscolhasEmentaJantar[i]);
                break;
            case R.id.sexfeira:
                i = 4;
                MostraEmenta(i, EscolhasEmentaCafe[i], EscolhasEmentaJantar[i]);
                break;
        }
        ClickEmenta(i);
    }

    public void MostraEmenta(int i, int escolhaPA, int escolhaJantar){
        NadaSelecionado();
        if(escolhaPA == 1)
            menucafe.setBackgroundColor(Color.parseColor(VerdeClaro));
        if(escolhaJantar == 1)
            menujantar.setBackgroundColor(Color.parseColor(VerdeClaro));
        menujantar.setText(EmentaJantar[i]);
    }

    public void ClickEmenta(int i){
        Button [] DiasComRefeicaoMarcada = new Button[]{
                (Button)findViewById(R.id.segfeira),
                (Button)findViewById(R.id.terfeira),
                (Button)findViewById(R.id.quafeira),
                (Button)findViewById(R.id.quifeira),
                (Button)findViewById(R.id.sexfeira)
        };

        menucafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmentaCafe[i] == 1){
                    menucafe.setBackgroundColor(Color.parseColor(AmareloClaro));
                    EscolhasEmentaCafe[i] = 0;
                    if (EscolhasEmentaJantar[i] == 0 && EscolhasEmentaCafe[i] == 0)
                        DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#8C8C8C"));
                }else{
                    menucafe.setBackgroundColor(Color.parseColor(VerdeClaro));
                    EscolhasEmentaCafe[i] = 1;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                }
            }
        });

        menualmoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Residentes.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        menujantar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmentaJantar[i] == 1){
                    menujantar.setBackgroundColor(Color.parseColor(Azul));
                    EscolhasEmentaJantar[i] = 0;
                    if (EscolhasEmentaJantar[i] == 0 && EscolhasEmentaCafe[i] == 0)
                        DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#8C8C8C"));
                }else{
                    menujantar.setBackgroundColor(Color.parseColor(VerdeClaro));
                    EscolhasEmentaJantar[i] = 1;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                }
            }
        });
    }





    private void CafeSelecionado(){
        menucafe.setBackgroundColor(Color.parseColor(VerdeClaro));
        menualmoco.setBackgroundColor(Color.parseColor(AmareloEscuro));
        menujantar.setBackgroundColor(Color.parseColor(Azul));
    }

    private void AlmocoSelecionado(){
        menucafe.setBackgroundColor(Color.parseColor(AmareloClaro));
        menualmoco.setBackgroundColor(Color.parseColor(VerdeClaro));
        menujantar.setBackgroundColor(Color.parseColor(Azul));
    }

    private void JantarSelecionado(){
        menucafe.setBackgroundColor(Color.parseColor(AmareloClaro));
        menualmoco.setBackgroundColor(Color.parseColor(AmareloEscuro));
        menujantar.setBackgroundColor(Color.parseColor(VerdeClaro));
    }

    private void NadaSelecionado(){
        menucafe.setBackgroundColor(Color.parseColor(AmareloClaro));
        menualmoco.setBackgroundColor(Color.parseColor(AmareloEscuro));
        menujantar.setBackgroundColor(Color.parseColor(Azul));
    }
}