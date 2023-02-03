package com.example.appcantinacozinha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

/* TODO
    Implementar botão de ajuda
    Ligar base de dados
    Iniciar no dia corrente
 */

public class Tela_2_Cantina extends AppCompatActivity implements View.OnClickListener{

    String JSON_STRING;

    String corCarne = "#CCA6A6";
    String corPeixe = "#90BEE3";
    String corVeg = "#96C698";
    String corSopa = "#FFDAB9";
    String corSobremesa = "#FFC0CB";
    String corJantar = "#98d8f8";

    EditText ementaSopaSobremesa, ementaCarne, ementaPeixe, ementaVeg;
    Button ajudar, sair;
    TextView textoajudar;
    ImageButton  sopa, sobremesa, sopaSobremesa,imgsopasobremesa, imgcarne, imgpeixe, imgveg;

    //String [] ementaJantarSemana = new String[5];
    String [] ementaCarneSemana = new String[5];
    String [] ementaPeixeSemana = new String[5];
    String [] ementaVegSemana = new String[5];
    String [] ementaSopaSemana = new String[5];
    String [] ementaSobremesaSemana = new String[5];

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

        imgsopasobremesa = findViewById(R.id.imgsopasobremesa);
        ementaSopaSobremesa = findViewById(R.id.ementasopasobremesa);
        ementaSopaSobremesa.setOnClickListener(this);
        imgcarne = findViewById(R.id.imgcarne);
        ementaCarne = findViewById(R.id.ementacarne);
        ementaCarne.setOnClickListener(this);
        imgpeixe = findViewById(R.id.imgpeixe);
        ementaPeixe = findViewById(R.id.ementapeixe);
        ementaPeixe.setOnClickListener(this);
        imgveg = findViewById(R.id.imgveg);
        ementaVeg = findViewById(R.id.ementaveg);
        ementaVeg.setOnClickListener(this);
        ajudar = findViewById(R.id.ajuda2);
        textoajudar = findViewById(R.id.textoajudar2);
        sair = findViewById(R.id.sair3);

        imgsopasobremesa.setVisibility(View.VISIBLE);
        ementaSopaSobremesa.setVisibility(View.VISIBLE);
        imgcarne.setVisibility(View.VISIBLE);
        ementaCarne.setVisibility(View.VISIBLE);
        imgpeixe.setVisibility(View.VISIBLE);
        ementaPeixe.setVisibility(View.VISIBLE);
        imgveg.setVisibility(View.VISIBLE);
        ementaVeg.setVisibility(View.VISIBLE);
        ajudar.setVisibility(View.VISIBLE);
        textoajudar = findViewById(R.id.textoajudar2);
        textoajudar.setVisibility(View.INVISIBLE);
        sair.setVisibility(View.INVISIBLE);

        ajudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajudar.setVisibility(View.VISIBLE);
                imgsopasobremesa.setVisibility(View.INVISIBLE);
                ementaSopaSobremesa.setVisibility(View.INVISIBLE);
                imgcarne.setVisibility(View.INVISIBLE);
                ementaCarne.setVisibility(View.INVISIBLE);
                imgpeixe.setVisibility(View.INVISIBLE);
                ementaPeixe.setVisibility(View.INVISIBLE);
                imgveg.setVisibility(View.INVISIBLE);
                ementaVeg.setVisibility(View.INVISIBLE);
                textoajudar.setVisibility(View.VISIBLE);
                sair.setVisibility(View.VISIBLE);
            }
        });


        Calendar calendario = Calendar.getInstance();
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK)-2;

        MudaCorDiasPassados(diaSemana);
        getDados(1);

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



    public void getDados(int query) { new BackgroundTask(query).execute(); }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        int query;

        public BackgroundTask(int get) {
            query = get;
        }

        String json_url;

        @Override
        protected void onPreExecute() {
            switch (query) {
                case 1:
                    json_url = "http://192.168.12.120/CencalCantina/ementasSemana2.php";
                    break;
                case 2:
                    json_url = "http://192.168.12.120/CencalCantina/ementasSemana2.php";
            }

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(input));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = br.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                br.close();
                input.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            switch (query) {
                case 1:
                    TrataJson tj = new TrataJson();
                    List<Ementa> ementas = tj.GetEmentas(result);
                    int i = 0;
                    for (Ementa Ementas : ementas) {
                        ementaCarneSemana[i] = Ementas.ementaCarne;
                        ementaPeixeSemana[i] = Ementas.ementaPeixe;
                        ementaVegSemana[i] = Ementas.ementaVeg;
                        ementaSopaSemana[i] = Ementas.ementaSopa;
                        ementaSobremesaSemana[i] = Ementas.ementaSobremesa;
                        //ementaJantarSemana[i] = Ementas.ementaJantar;
                        i++;
                    }
                    break;
                case 2:
            }

        }

    }
    public void sair(View v){
        Intent it = new Intent(Tela_2_Cantina.this, Tela_2_Cantina.class);
        startActivity(it);
        finish();
    }
}

