package com.example.appcantinacozinha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

/* TODO
    Implementar botão de ajuda
    Ligar base de dados
    Iniciar no dia corrente
 */

public class Tela_2_Cantina extends AppCompatActivity implements View.OnClickListener{

    String corCarne = "#CCA6A6";
    String corPeixe = "#90BEE3";
    String corVeg = "#96C698";
    String corSopa = "#FFDAB9";
    String corSobremesa = "#FFC0CB";
    String corJantar = "#98d8f8";

    EditText ementaSopaSobremesa, ementaCarne, ementaPeixe, ementaVeg, ementaJantar;
    Button atualizarEmenta;
    ImageButton sopa, sobremesa, sopaSobremesa;

    String [] ementaJantarSemana = new String[]{
            "Ementa segunda Jantar",
            "Ementa terça Jantar",
            "Ementa quarta Jantar",
            "Ementa quinta Jantar",
            "Ementa sexta Jantar"
    };

    String [] ementaCarneSemana = new String[]{
            "Ementa segunda Carne",
            "Ementa terça Carne",
            "Ementa quarta Carne",
            "Ementa quinta Carne",
            "Ementa sexta Carne"
    };

    String [] ementaPeixeSemana = new String[]{
            "Ementa segunda Peixe",
            "Ementa terça Peixe",
            "Ementa quarta Peixe",
            "Ementa quinta Peixe",
            "Ementa sexta Peixe"
    };

    String [] ementaVegSemana = new String[]{
            "Ementa segunda Veg",
            "Ementa terça Veg",
            "Ementa quarta Veg",
            "Ementa quinta Veg",
            "Ementa sexta Veg"
    };

    String [] ementaSopaSemana = new String[]{
            "Ementa segunda Sopa",
            "Ementa terça Sopa",
            "Ementa quarta Sopa",
            "Ementa quinta Sopa",
            "Ementa sexta Sopa"
    };

    String [] ementaSobremesaSemana = new String[]{
            "Ementa segunda Sobremesa",
            "Ementa terça Sobremesa",
            "Ementa quarta Sobremesa",
            "Ementa quinta Sobremesa",
            "Ementa sexta Sobremesa"
    };

    Button [] DiasSemana = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2_cantina);

        DiasSemana[0] = (Button)findViewById(R.id.segfeira);
        DiasSemana[1] = (Button)findViewById(R.id.terfeira);
        DiasSemana[2] = (Button)findViewById(R.id.quafeira);
        DiasSemana[3] = (Button)findViewById(R.id.quifeira);
        DiasSemana[4] = (Button)findViewById(R.id.sexfeira);
        for(int i = 0; i < DiasSemana.length; i++)
            DiasSemana[i].setOnClickListener(this);

        ementaSopaSobremesa = findViewById(R.id.ementasopasobremesa);
        ementaSopaSobremesa.setOnClickListener(this);
        ementaCarne = findViewById(R.id.ementacarne);
        ementaCarne.setOnClickListener(this);
        ementaPeixe = findViewById(R.id.ementapeixe);
        ementaPeixe.setOnClickListener(this);
        ementaVeg = findViewById(R.id.ementaveg);
        ementaVeg.setOnClickListener(this);
        ementaJantar = findViewById(R.id.ementajantar);
        ementaJantar.setOnClickListener(this);

        Calendar calendario = Calendar.getInstance();
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK)-2;

        switch (diaSemana) {
            case Calendar.TUESDAY:
                MudaCorDiasPassados(diaSemana);
                break;
            case Calendar.WEDNESDAY:
                MudaCorDiasPassados(diaSemana);
                break;
            case Calendar.THURSDAY:
                MudaCorDiasPassados(diaSemana);
                break;
            case Calendar.FRIDAY:
                MudaCorDiasPassados(diaSemana);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        int i = -1;
        switch (v.getId()) {
            case R.id.segfeira:
                i = 0;
                MostraEmenta(i);
                break;
            case R.id.terfeira:
                i = 1;
                MostraEmenta(i);
                break;
            case R.id.quafeira:
                i = 2;
                MostraEmenta(i);
                break;
            case R.id.quifeira:
                i = 3;
                MostraEmenta(i);
                break;
            case R.id.sexfeira:
                i = 4;
                MostraEmenta(i);
                break;
        }
        CorDiaSemanaSelecionado(i);
    }

    private void MostraEmenta(int i){
        ementaCarne.setText(ementaCarneSemana[i]);
        ementaPeixe.setText(ementaPeixeSemana[i]);
        ementaVeg.setText(ementaVegSemana[i]);
        ementaJantar.setText(ementaJantarSemana[i]);
        ementaSopaSobremesa.setText(ementaSopaSemana[i]);
        sopaSobremesa = findViewById(R.id.imgsopasobremesa);
        sopaSobremesa.setImageResource(R.drawable.sopa);
        sopaSobremesa.setBackgroundColor(Color.parseColor(corSopa));
        ementaSopaSobremesa.setBackgroundColor(Color.parseColor(corSopa));


        sopa = findViewById(R.id.atualizaSopa);
        sopa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ementaSopaSobremesa.setText(ementaSopaSemana[i]);
                sopaSobremesa.setImageResource(R.drawable.sopa);
                sopaSobremesa.setBackgroundColor(Color.parseColor(corSopa));
                ementaSopaSobremesa.setBackgroundColor(Color.parseColor(corSopa));
            }
        });

        sobremesa = findViewById(R.id.atualizaSobremesa);
        sobremesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ementaSopaSobremesa.setText(ementaSobremesaSemana[i]);
                sopaSobremesa.setImageResource(R.drawable.sobremesa);
                sopaSobremesa.setBackgroundColor(Color.parseColor(corSobremesa));
                ementaSopaSobremesa.setBackgroundColor(Color.parseColor(corSobremesa));
            }
        });
    }

    public void Voltar (View v){
        Intent it = new Intent(Tela_2_Cantina.this, Tela_1_Cantina.class);
        startActivity(it);
        finish();

    }

    public void AtualizarEmenta (View v){
        // Envio à base de dados
    }

    public void CorDiaSemanaSelecionado(int i){
        for(int j = 0; j < DiasSemana.length; j++){
            if (j == i)
                DiasSemana[j].setTextColor(Color.parseColor("#000000"));
            else if (DiasSemana[j].isEnabled())
                DiasSemana[j].setTextColor(Color.parseColor("#ffffff"));
        }
    }

    public void MudaCorDiasPassados(int i){
        for(int j = 0; j < i; j++){
            DiasSemana[j].setEnabled(false);
            DiasSemana[j].setBackgroundColor(Color.parseColor("#d8d8d8"));
            DiasSemana[j].setTextColor(Color.parseColor("#ffffff"));
        }
    }
}