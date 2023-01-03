package com.example.testeprojeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imagemcarne, imagempeixe, imagemveg;
    Button segundafeira, tercafeira, quartafeira, quintafeira, sextafeira, menucarne, menupeixe, menuveg;

    String [] EmentaCarne = new String[]{
            "Ementa segunda carne",
            "Ementa terça carne",
            "Ementa quarta carne",
            "Ementa quinta carne",
            "Ementa sexta carne"
    };

    String [] EmentaPeixe = new String[]{
            "Ementa segunda peixe",
            "Ementa terça peixe",
            "Ementa quarta peixe",
            "Ementa quinta peixe",
            "Ementa sexta peixe"
    };

    String [] EmentaVegetariano = new String[]{
            "Ementa segunda vegetariano",
            "Ementa terça vegetariano",
            "Ementa quarta vegetariano",
            "Ementa quinta vegetariano",
            "Ementa sexta vegetariano"
    };

    int [] EscolhasEmenta = new int[]{
            0, 0, 0, 0, 0
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = new Intent(MainActivity.this, Residentes.class);
        startActivity(it);
        finish();

        menucarne = findViewById(R.id.ementacarne);
        menucarne.setOnClickListener(this);
        menupeixe = findViewById(R.id.ementapeixe);
        menupeixe.setOnClickListener(this);
        menuveg = findViewById(R.id.ementaveg);
        menuveg.setOnClickListener(this);

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

        imagemcarne = findViewById(R.id.imgcarne);
        imagempeixe = findViewById(R.id.imgpeixe);
        imagemveg = findViewById(R.id.imgveg);

        imagemcarne.setVisibility(View.INVISIBLE);
        imagempeixe.setVisibility(View.INVISIBLE);
        imagemveg.setVisibility(View.INVISIBLE);
        menucarne.setVisibility(View.INVISIBLE);
        menupeixe.setVisibility(View.INVISIBLE);
        menuveg.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        int i = -1;
        imagemcarne.setVisibility(View.VISIBLE);
        imagempeixe.setVisibility(View.VISIBLE);
        imagemveg.setVisibility(View.VISIBLE);
        menucarne.setVisibility(View.VISIBLE);
        menupeixe.setVisibility(View.VISIBLE);
        menuveg.setVisibility(View.VISIBLE);
        switch (v.getId()) {
            case R.id.segfeira:
                i = 0;
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
            case R.id.terfeira:
                i = 1;
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
            case R.id.quafeira:
                i = 2;
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
            case R.id.quifeira:
                i = 3;
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
            case R.id.sexfeira:
                i = 4;
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
        }
        ClickEmenta(i);
    }

    public void MostraEmenta(int i, int escolha){
        switch (escolha){
            case 1:
                CarneSelecionada();
                break;
            case 2:
                PeixeSelecionado();
                break;
            case 3:
                VegSelecionado();
                break;
            case 0:
                NadaSelecionado();
                break;
        }
        menucarne.setText(EmentaCarne[i]);
        menupeixe.setText(EmentaPeixe[i]);
        menuveg.setText(EmentaVegetariano[i]);
    }

    public void ClickEmenta(int i){
        Button [] DiasComRefeicaoMarcada = new Button[]{
                (Button)findViewById(R.id.segfeira),
                (Button)findViewById(R.id.terfeira),
                (Button)findViewById(R.id.quafeira),
                (Button)findViewById(R.id.quifeira),
                (Button)findViewById(R.id.sexfeira)
        };

        menucarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmenta[i] == 1){
                    NadaSelecionado();
                    EscolhasEmenta[i] = 0;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#000000"));
                }else{
                    CarneSelecionada();
                    EscolhasEmenta[i] = 1;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#00FF00"));
                }
            }
        });

        menupeixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmenta[i] == 2){
                    NadaSelecionado();
                    EscolhasEmenta[i] = 0;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#000000"));
                }else{
                    PeixeSelecionado();
                    EscolhasEmenta[i] = 2;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#00FF00"));
                }
            }
        });

        menuveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmenta[i] == 3){
                    NadaSelecionado();
                    EscolhasEmenta[i] = 0;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#000000"));
                }else{
                    VegSelecionado();
                    EscolhasEmenta[i] = 3;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#00FF00"));
                }
            }
        });
    }

    private void CarneSelecionada(){
        menucarne.setBackgroundColor(Color.parseColor("#00FF00"));
        menupeixe.setBackgroundColor(Color.parseColor("#0000FF"));
        menuveg.setBackgroundColor(Color.parseColor("#013220"));
    }

    private void PeixeSelecionado(){
        menucarne.setBackgroundColor(Color.parseColor("#FF0000"));
        menupeixe.setBackgroundColor(Color.parseColor("#00FF00"));
        menuveg.setBackgroundColor(Color.parseColor("#013220"));
    }

    private void VegSelecionado(){
        menucarne.setBackgroundColor(Color.parseColor("#FF0000"));
        menupeixe.setBackgroundColor(Color.parseColor("#0000FF"));
        menuveg.setBackgroundColor(Color.parseColor("#00FF00"));
    }

    private void NadaSelecionado(){
        menucarne.setBackgroundColor(Color.parseColor("#FF0000"));
        menupeixe.setBackgroundColor(Color.parseColor("#0000FF"));
        menuveg.setBackgroundColor(Color.parseColor("#013220"));
    }
}