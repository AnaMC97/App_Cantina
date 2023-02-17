package com.example.appcantina;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* TODO
    Alterar cor das refeições marcadas
    Alterar background color do verificado
*/

public class Residentes extends AppCompatActivity implements View.OnClickListener {

    ImageButton imagemcafe, imagemsun, imagemlua;
    Button menucafe, menualmoco, menujantar, ajudar, tudoPA, tudoJantar, atualizarRefeicao;
    TextView textoajudar;

    String VerdeClaro = "#ADE792";
    String VerdeEscuro = "#6ECCAF";
    String AmareloClaro = "#F3ECB0";
    String AmareloEscuro = "#fabf7c";
    String Azul = "#98d8f8";
    String Cinzento = "#8C8C8C";
    String CinzentoClaro = "#d8d8d8";

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

    Button [] DiasComRefeicaoMarcada = new Button[5];

    int tudoSelecionadoPA = 0;
    int tudoSelecionadoJantar = 0;
    int alunoRFID = 1;
    int diaSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residentes);

        // São devolvidas as escolhas no menu de almoços, bem como as escolhas de jantar e PA
        Intent it = getIntent();
        if(it.getIntArrayExtra("EmentaEscolhidaAlmoco") != null)
            EscolhasEmentaAlmoco = it.getIntArrayExtra("EmentaEscolhidaAlmoco");

        if(it.getIntArrayExtra("EmentaEscolhidaPA") != null)
            EscolhasEmentaCafe = it.getIntArrayExtra("EmentaEscolhidaPA");

        if(it.getIntArrayExtra("EmentaEscolhidaJantar") != null)
            EscolhasEmentaJantar = it.getIntArrayExtra("EmentaEscolhidaJantar");

        DiasComRefeicaoMarcada[0] = (Button)findViewById(R.id.segfeira);
        DiasComRefeicaoMarcada[1] = (Button)findViewById(R.id.terfeira);
        DiasComRefeicaoMarcada[2] = (Button)findViewById(R.id.quafeira);
        DiasComRefeicaoMarcada[3] = (Button)findViewById(R.id.quifeira);
        DiasComRefeicaoMarcada[4] = (Button)findViewById(R.id.sexfeira);
        for(int i = 0; i < DiasComRefeicaoMarcada.length; i++)
            DiasComRefeicaoMarcada[i].setOnClickListener(this);

        menucafe = findViewById(R.id.ementapequenoalmoco);
        menucafe.setOnClickListener(this);
        menualmoco = findViewById(R.id.ementaalmoco);
        menualmoco.setOnClickListener(this);
        menujantar = findViewById(R.id.ementajantar);
        menujantar.setOnClickListener(this);

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

        for (int i = 0; i < EscolhasEmentaCafe.length; i++)
            if(EscolhasEmentaCafe[i] != 0 || EscolhasEmentaJantar[i] != 0)
                DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));

        Calendar calendario = Calendar.getInstance();
        diaSemana = calendario.get(Calendar.DAY_OF_WEEK)-2;

        MudaCorDiasPassados(diaSemana);

        atualizarRefeicao = findViewById(R.id.atualizarrefeicoes);

        tudoPA = findViewById(R.id.TudoPA);
        tudoPA.setOnClickListener(this);
        tudoPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tudoSelecionadoPA == 0){
                    for(int i=0; i< EscolhasEmentaCafe.length; i++){
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            EscolhasEmentaCafe[i] = 1;
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                            menucafe.setBackgroundColor(Color.parseColor(VerdeClaro));
                        }
                    }
                    tudoSelecionadoPA = 1;
                    tudoPA.setBackgroundColor(Color.parseColor(VerdeClaro));
                }else{
                    for(int i=0; i< EscolhasEmentaCafe.length; i++){
                        if (DiasComRefeicaoMarcada[i].isEnabled()) {
                            EscolhasEmentaCafe[i] = 0;
                            menucafe.setBackgroundColor(Color.parseColor(AmareloClaro));
                            if (tudoSelecionadoJantar == 0)
                                DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinzento));
                        }

                    }
                    tudoSelecionadoPA = 0;
                    tudoPA.setBackgroundColor(Color.parseColor(Cinzento));
                }
            }
        });

        tudoJantar = findViewById(R.id.TudoJantar);
        tudoJantar.setOnClickListener(this);
        tudoJantar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tudoSelecionadoJantar == 0){
                    for(int i=0; i< EscolhasEmentaJantar.length; i++){
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            EscolhasEmentaJantar[i] = 1;
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                            menujantar.setBackgroundColor(Color.parseColor(VerdeClaro));
                        }
                    }
                    tudoSelecionadoJantar = 1;
                    tudoJantar.setBackgroundColor(Color.parseColor(VerdeClaro));
                } else {
                    for(int i=0; i< EscolhasEmentaJantar.length; i++){
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            EscolhasEmentaJantar[i] = 0;
                            menujantar.setBackgroundColor(Color.parseColor(Azul));
                            if (tudoSelecionadoPA == 0)
                                DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinzento));
                        }
                    }
                    tudoSelecionadoJantar = 0;
                    tudoJantar.setBackgroundColor(Color.parseColor(Cinzento));
                }
            }
        });

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
        CorDiaSemanaSelecionado(i);
        ClickEmenta(i);
    }

    public void MostraEmenta(int i, int escolhaPA, int escolhaJantar){
        NadaSelecionado();
        if(escolhaPA == 1) {
            menucafe.setBackgroundColor(Color.parseColor(VerdeClaro));
            imagemcafe.setImageResource(R.drawable.escolhido);
        }
        if(escolhaJantar == 1) {
            menujantar.setBackgroundColor(Color.parseColor(VerdeClaro));
            imagemlua.setImageResource(R.drawable.escolhido);
        }
        menujantar.setText(EmentaJantar[i]);
    }

    // Botão para as ementas do PA
    public void ClickEmenta(int i){
        menucafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmentaCafe[i] == 1){
                    menucafe.setBackgroundColor(Color.parseColor(AmareloClaro));
                    imagemcafe.setImageResource(R.drawable.cafe);
                    EscolhasEmentaCafe[i] = 0;
                    imagemcafe.setImageResource(R.drawable.cafe);
                    if (EscolhasEmentaJantar[i] == 0 && EscolhasEmentaCafe[i] == 0)
                        DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#8C8C8C"));
                }else{
                    imagemcafe.setImageResource(R.drawable.escolhido);
                    menucafe.setBackgroundColor(Color.parseColor(VerdeClaro));
                    EscolhasEmentaCafe[i] = 1;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                }
            }
        });


        // Botão para as ementas do almoço
        menualmoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Residentes.this, telaAlmoco.class);
                it.putExtra("EmentaEscolhidaAlmoco", EscolhasEmentaAlmoco);
                it.putExtra("EmentaEscolhidaPA", EscolhasEmentaCafe);
                it.putExtra("EmentaEscolhidaJantar", EscolhasEmentaJantar);
                startActivity(it);
                finish();
            }
        });


        // Botão para as ementas do jantar
        menujantar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmentaJantar[i] == 1){
                    menujantar.setBackgroundColor(Color.parseColor(Azul));
                    imagemlua.setImageResource(R.drawable.lua);
                    EscolhasEmentaJantar[i] = 0;
                    imagemlua.setImageResource(R.drawable.lua);
                    if (EscolhasEmentaJantar[i] == 0 && EscolhasEmentaCafe[i] == 0)
                        DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor("#8C8C8C"));
                }else{
                    menujantar.setBackgroundColor(Color.parseColor(VerdeClaro));
                    imagemlua.setImageResource(R.drawable.escolhido);
                    EscolhasEmentaJantar[i] = 1;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                }
            }
        });
    }

    public void CorDiaSemanaSelecionado(int i){
        for(int j = 0; j<DiasComRefeicaoMarcada.length; j++){
            if (j == i)
                DiasComRefeicaoMarcada[j].setTextColor(Color.parseColor("#000000"));
            else if (DiasComRefeicaoMarcada[j].isEnabled())
                DiasComRefeicaoMarcada[j].setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void NadaSelecionado(){
        menucafe.setBackgroundColor(Color.parseColor(AmareloClaro));
        menualmoco.setBackgroundColor(Color.parseColor(AmareloEscuro));
        menujantar.setBackgroundColor(Color.parseColor(Azul));
    }

    public void MudaCorDiasPassados(int i){
        for(int j = 0; j < i; j++){
            DiasComRefeicaoMarcada[j].setEnabled(false);
            DiasComRefeicaoMarcada[j].setBackgroundColor(Color.parseColor(CinzentoClaro));
            DiasComRefeicaoMarcada[j].setTextColor(Color.parseColor("#ffffff"));
        }
    }
}