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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

 */
public class telaAlmoco extends AppCompatActivity implements View.OnClickListener{

    String JSON_STRING;
    ImageView imagemcarne, imagempeixe, imagemveg, imagemsopa, imagemsobre;
    Button menucarne, menupeixe, menuveg, menucarne1, menupeixe1, menuveg1, ajudar2;
    String VerdeClaro = "#ADE792";
    String CorCarne = "#CCA6A6";
    String CorPeixe = "#90BEE3";
    String CorVeg = "#3cb371";
    String Cinza = "#8C8C8C";
    String CinzentoClaro = "#d8d8d8";
    TextView textoAjudar2,ementaSopa, ementaSobremesa;
    int s = 1;

    int selecionadoTudoCarne = 0;
    int selecionadoTudoPeixe = 0;
    int selecionadoTudoVeg = 0;

    String [] EmentaCarne = new String[5];
    String [] EmentaPeixe = new String[5];
    String [] EmentaVegetariano = new String[5];
    String [] EmentaSopa = new String[5];
    String [] EmentaSobremesa = new String[5];

    int [] EscolhasEmenta = new int[]{
            0, 0, 0, 0, 0
    };

    int [] EscolhasEmentaCafe = new int[5];
    int [] EscolhasEmentaJantar = new int[5];

    Button [] DiasComRefeicaoMarcada = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_almoco);
        // Se for residente
        if(s==1){
            Button voltar = findViewById(R.id.voltar);
            voltar.setText("Voltar");
            Intent it = getIntent();
            if(it.getIntArrayExtra("EmentaEscolhidaAlmoco") != null)
                EscolhasEmenta = it.getIntArrayExtra("EmentaEscolhidaAlmoco");

            if(it.getIntArrayExtra("EmentaEscolhidaPA") != null)
                EscolhasEmentaCafe = it.getIntArrayExtra("EmentaEscolhidaPA");

            if(it.getIntArrayExtra("EmentaEscolhidaJantar") != null)
                EscolhasEmentaJantar = it.getIntArrayExtra("EmentaEscolhidaJantar");
        }

        // Faz pedido das ementas à base de dados. É devolvido as ementas para os 5 dias da semana.


        menucarne = findViewById(R.id.ementacarne);
        menucarne.setOnClickListener(this);
        menupeixe = findViewById(R.id.ementapeixe);
        menupeixe.setOnClickListener(this);
        menuveg = findViewById(R.id.ementaveg);
        menuveg.setOnClickListener(this);

        //botoes para selecionar todos os dias
        menupeixe1 = findViewById(R.id.ementapeixe1);
        menupeixe1.setOnClickListener(this);
        menuveg1 = findViewById(R.id.ementaveg1);
        menuveg1.setOnClickListener(this);

        imagemcarne = findViewById(R.id.imgcarne);
        imagempeixe = findViewById(R.id.imgpeixe);
        imagemveg = findViewById(R.id.imgveg);
        imagemsopa = findViewById(R.id.imgsopa);
        imagemsobre = findViewById(R.id.imgsobre);
        ementaSopa = findViewById(R.id.ementasopa);
        ementaSobremesa = findViewById(R.id.ementasobremesa);

        imagemcarne.setVisibility(View.INVISIBLE);
        imagempeixe.setVisibility(View.INVISIBLE);
        imagemveg.setVisibility(View.INVISIBLE);
        imagemsopa.setVisibility(View.INVISIBLE);
        imagemsobre.setVisibility(View.INVISIBLE);
        menucarne.setVisibility(View.INVISIBLE);
        menupeixe.setVisibility(View.INVISIBLE);
        menuveg.setVisibility(View.INVISIBLE);
        ementaSopa.setVisibility(View.INVISIBLE);
        ementaSobremesa.setVisibility(View.INVISIBLE);

        DiasComRefeicaoMarcada[0] = (Button)findViewById(R.id.segfeira);
        DiasComRefeicaoMarcada[1] = (Button)findViewById(R.id.terfeira);
        DiasComRefeicaoMarcada[2] = (Button)findViewById(R.id.quafeira);
        DiasComRefeicaoMarcada[3] = (Button)findViewById(R.id.quifeira);
        DiasComRefeicaoMarcada[4] = (Button)findViewById(R.id.sexfeira);
        for(int i = 0; i < DiasComRefeicaoMarcada.length; i++)
            DiasComRefeicaoMarcada[i].setOnClickListener(this);

        Calendar calendario = Calendar.getInstance();
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK)-2;

        MudaCorDiasPassados(diaSemana);


        // Altera a cor dos dias da semana para verde quando o residente entra para marcar almoço e já tem algo escolhido
        for (int i = 0; i < EscolhasEmenta.length; i++)
            if(EscolhasEmenta[i] != 0)
                DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));

        menucarne1 = findViewById(R.id.ementacarne1);
        menucarne1.setOnClickListener(this);
        menucarne1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecionadoTudoCarne == 0){
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                            EscolhasEmenta[i] = 1;
                        }
                    }
                    menucarne1.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menupeixe1.setBackgroundColor(Color.parseColor(Cinza));
                    menuveg1.setBackgroundColor(Color.parseColor(Cinza));
                    menucarne.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menupeixe.setBackgroundColor(Color.parseColor(CorPeixe));
                    menuveg.setBackgroundColor(Color.parseColor(CorVeg));
                    selecionadoTudoCarne = 1;
                    selecionadoTudoPeixe = 0;
                    selecionadoTudoVeg = 0;
                }else{
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                            EscolhasEmenta[i] = 0;
                        }
                    }
                    menucarne1.setBackgroundColor(Color.parseColor(Cinza));
                    menucarne.setBackgroundColor(Color.parseColor(CorCarne));
                    selecionadoTudoCarne = 0;
                }
            }
        });

        menupeixe1 = findViewById(R.id.ementapeixe1);
        menupeixe1.setOnClickListener(this);
        menupeixe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecionadoTudoPeixe == 0) {
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                            EscolhasEmenta[i] = 2;
                        }
                    }
                    menupeixe1.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menucarne1.setBackgroundColor(Color.parseColor(Cinza));
                    menuveg1.setBackgroundColor(Color.parseColor(Cinza));
                    menupeixe.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menucarne.setBackgroundColor(Color.parseColor(CorCarne));
                    menuveg.setBackgroundColor(Color.parseColor(CorVeg));
                    selecionadoTudoCarne = 0;
                    selecionadoTudoPeixe = 2;
                    selecionadoTudoVeg = 0;
                }else{
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                            EscolhasEmenta[i] = 0;
                        }
                    }
                    menupeixe1.setBackgroundColor(Color.parseColor(Cinza));
                    menupeixe.setBackgroundColor(Color.parseColor(CorPeixe));
                    selecionadoTudoPeixe = 0;
                }
            }
        });

        menuveg1 = findViewById(R.id.ementaveg1);
        menuveg1.setOnClickListener(this);
        menuveg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selecionadoTudoVeg == 0 ) {
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                            EscolhasEmenta[i] = 3;
                        }
                    }
                    menuveg1.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menucarne1.setBackgroundColor(Color.parseColor(Cinza));
                    menupeixe1.setBackgroundColor(Color.parseColor(Cinza));
                    menuveg.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menucarne.setBackgroundColor(Color.parseColor(CorCarne));
                    menupeixe.setBackgroundColor(Color.parseColor(CorPeixe));
                    selecionadoTudoCarne = 0;
                    selecionadoTudoPeixe = 0;
                    selecionadoTudoVeg = 3;
                }else{
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                            EscolhasEmenta[i] = 0;
                        }
                    }
                    menuveg1.setBackgroundColor(Color.parseColor(Cinza));
                    menuveg.setBackgroundColor(Color.parseColor(CorVeg));
                    selecionadoTudoVeg = 0;
                }
            }
        });

        ajudar2 = findViewById(R.id.ajuda2);
        textoAjudar2 = findViewById(R.id.textoajudar2);
        ajudar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagemcarne.setVisibility(View.INVISIBLE);
                imagempeixe.setVisibility(View.INVISIBLE);
                imagemveg.setVisibility(View.INVISIBLE);
                imagemsopa.setVisibility(View.INVISIBLE);
                imagemsobre.setVisibility(View.INVISIBLE);
                ementaSopa.setVisibility(View.INVISIBLE);
                ementaSobremesa.setVisibility(View.INVISIBLE);
                menucarne.setVisibility(View.INVISIBLE);
                menupeixe.setVisibility(View.INVISIBLE);
                menuveg.setVisibility(View.INVISIBLE);
                textoAjudar2.setVisibility(View.VISIBLE);
            }
        });

        getDados();
    }

    @Override
    public void onClick(View v) {
        int i = -1;
        imagemcarne.setVisibility(View.VISIBLE);
        imagempeixe.setVisibility(View.VISIBLE);
        imagemveg.setVisibility(View.VISIBLE);
        imagemsopa.setVisibility(View.VISIBLE);
        imagemsobre.setVisibility(View.VISIBLE);
        ementaSopa.setVisibility(View.VISIBLE);
        ementaSobremesa.setVisibility(View.VISIBLE);
        menucarne.setVisibility(View.VISIBLE);
        menupeixe.setVisibility(View.VISIBLE);
        menuveg.setVisibility(View.VISIBLE);
        textoAjudar2.setVisibility(View.INVISIBLE);
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
        CorDiaSemanaSelecionado(i);
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
        ementaSopa.setText(EmentaSopa[i]);
        ementaSobremesa.setText(EmentaSobremesa[i]);

    }

    public void ClickEmenta(int i){

        menucarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmenta[i] == 1){
                    NadaSelecionado();
                    EscolhasEmenta[i] = 0;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                }else{
                    CarneSelecionada();
                    EscolhasEmenta[i] = 1;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                }
            }
        });

        menupeixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmenta[i] == 2){
                    NadaSelecionado();
                    EscolhasEmenta[i] = 0;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                }else{
                    PeixeSelecionado();
                    EscolhasEmenta[i] = 2;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                }
            }
        });

        menuveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EscolhasEmenta[i] == 3){
                    NadaSelecionado();
                    EscolhasEmenta[i] = 0;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                }else{
                    VegSelecionado();
                    EscolhasEmenta[i] = 3;
                    DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(VerdeClaro));
                }
            }
        });
    }

    private void CarneSelecionada(){
        menucarne.setBackgroundColor(Color.parseColor(VerdeClaro));
        menupeixe.setBackgroundColor(Color.parseColor(CorPeixe));
        menuveg.setBackgroundColor(Color.parseColor(CorVeg));
    }

    private void PeixeSelecionado(){
        menucarne.setBackgroundColor(Color.parseColor(CorCarne));
        menupeixe.setBackgroundColor(Color.parseColor(VerdeClaro));
        menuveg.setBackgroundColor(Color.parseColor(CorVeg));
    }

    private void VegSelecionado(){
        menucarne.setBackgroundColor(Color.parseColor(CorCarne));
        menupeixe.setBackgroundColor(Color.parseColor(CorPeixe));
        menuveg.setBackgroundColor(Color.parseColor(VerdeClaro));
    }

    private void NadaSelecionado(){
        menucarne.setBackgroundColor(Color.parseColor(CorCarne));
        menupeixe.setBackgroundColor(Color.parseColor(CorPeixe));
        menuveg.setBackgroundColor(Color.parseColor(CorVeg));
    }

    public void CorDiaSemanaSelecionado(int i){
        for(int j = 0; j<DiasComRefeicaoMarcada.length; j++){
            if (j == i)
                DiasComRefeicaoMarcada[j].setTextColor(Color.parseColor("#000000"));
            else if (DiasComRefeicaoMarcada[j].isEnabled())
                DiasComRefeicaoMarcada[j].setTextColor(Color.parseColor("#ffffff"));
        }
    }

    public void VoltarAtualizar (View v) {
        if(s == 0){

            //comunica c base de dados
        }else {
            Intent it = new Intent(telaAlmoco.this, Residentes.class);
            it.putExtra("EmentaEscolhidaAlmoco", EscolhasEmenta);
            it.putExtra("EmentaEscolhidaPA", EscolhasEmentaCafe);
            it.putExtra("EmentaEscolhidaJantar", EscolhasEmentaJantar);
            startActivity(it);
            finish();
        }
    }

    public void MudaCorDiasPassados(int i){
        for(int j = 0; j < i; j++){
            DiasComRefeicaoMarcada[j].setEnabled(false);
            DiasComRefeicaoMarcada[j].setBackgroundColor(Color.parseColor(CinzentoClaro));
            DiasComRefeicaoMarcada[j].setTextColor(Color.parseColor("#ffffff"));
        }
    }

    public void getDados() {
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected void onPreExecute() {
            //json_url = "http://192.168.12.176/CencalCantina/ementasSemana2.php";
            //json_url = "http://10.0.2.2:8080/CencalCantina/ementasSemana2.php";
            json_url = "http://192.168.12.120/CencalCantina/ementasSemana2.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                Log.e("teste","teste1");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.e("teste","teste2");
                InputStream input = httpURLConnection.getInputStream();
                Log.e("teste","teste3");
                BufferedReader br = new BufferedReader(new InputStreamReader(input));
                Log.e("teste","teste4");
                StringBuilder stringBuilder = new StringBuilder();
                Log.e("teste","teste5");

                while ((JSON_STRING =br.readLine())!=null) {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                br.close();
                input.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                Log.e("teste","fail1");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("teste","fail2");
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

            TrataJson tj = new TrataJson();
            List<Ementa> ementas = tj.GetEmentas(result);
            int i=0;

            for(Ementa Ementas: ementas){
                EmentaCarne[i] = Ementas.ementaCarne;
                EmentaPeixe[i] = Ementas.ementaPeixe;
                EmentaVegetariano[i] = Ementas.ementaVeg;
                EmentaSopa[i] = Ementas.ementaSopa;
                EmentaSobremesa[i] = Ementas.ementaSobremesa;
                i++;
            }
        }
    }
}