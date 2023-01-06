package com.example.appcantina;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

/* TODO

 */
public class telaAlmoco extends AppCompatActivity implements View.OnClickListener{

    ImageView imagemcarne, imagempeixe, imagemveg;
    Button segundafeira, tercafeira, quartafeira, quintafeira, sextafeira, menucarne, menupeixe, menuveg, menucarne1, menupeixe1, menuveg1;
    String VerdeClaro = "#ADE792";
    String CorCarne = "#CCA6A6";
    String CorPeixe = "#90BEE3";
    String CorVeg = "#96C698";
    String Cinza = "#8C8C8C";
    String CinzentoClaro = "#d8d8d8";
    int s = 1;

    int selecionadoTudoCarne = 0;
    int selecionadoTudoPeixe = 0;
    int selecionadoTudoVeg = 0;

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

    int [] EscolhasEmentaCafe = new int[5];
    int [] EscolhasEmentaJantar = new int[5];

    Button [] DiasComRefeicaoMarcada = new Button[5];

    int diaSemana;
    Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_almoco);

        Intent it = getIntent();
        if(it.getIntArrayExtra("EmentaEscolhida") != null)
            EscolhasEmenta = it.getIntArrayExtra("EmentaEscolhidaAlmoco");

        if(it.getIntArrayExtra("EmentaEscolhidaPA") != null)
            EscolhasEmentaCafe = it.getIntArrayExtra("EmentaEscolhidaPA");

        if(it.getIntArrayExtra("EmentaEscolhidaJantar") != null)
            EscolhasEmentaJantar = it.getIntArrayExtra("EmentaEscolhidaJantar");

        if(s==1){
            Button voltar = findViewById(R.id.voltar);
            voltar.setText("Voltar");
        }


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

        DiasComRefeicaoMarcada[0] = (Button)findViewById(R.id.segfeira);
        DiasComRefeicaoMarcada[1] = (Button)findViewById(R.id.terfeira);
        DiasComRefeicaoMarcada[2] = (Button)findViewById(R.id.quafeira);
        DiasComRefeicaoMarcada[3] = (Button)findViewById(R.id.quifeira);
        DiasComRefeicaoMarcada[4] = (Button)findViewById(R.id.sexfeira);

        calendario = Calendar.getInstance();
        diaSemana = calendario.get(Calendar.DAY_OF_WEEK)-2;

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
                    menupeixe1.setBackgroundColor(Color.parseColor(CorPeixe));
                    menuveg1.setBackgroundColor(Color.parseColor(CorVeg));
                    menucarne.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menupeixe.setBackgroundColor(Color.parseColor(CorPeixe));
                    menuveg.setBackgroundColor(Color.parseColor(CorVeg));
                    selecionadoTudoCarne = 1;
                }else{
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                            EscolhasEmenta[i] = 0;
                        }
                    }
                    menucarne1.setBackgroundColor(Color.parseColor(CorCarne));
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
                    menucarne1.setBackgroundColor(Color.parseColor(CorCarne));
                    menuveg1.setBackgroundColor(Color.parseColor(CorVeg));
                    menupeixe.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menucarne.setBackgroundColor(Color.parseColor(CorCarne));
                    menuveg.setBackgroundColor(Color.parseColor(CorVeg));
                    selecionadoTudoPeixe = 2;
                }else{
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                            EscolhasEmenta[i] = 0;
                        }
                    }
                    menupeixe1.setBackgroundColor(Color.parseColor(CorPeixe));
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
                    menucarne1.setBackgroundColor(Color.parseColor(CorCarne));
                    menupeixe1.setBackgroundColor(Color.parseColor(CorPeixe));
                    menuveg.setBackgroundColor(Color.parseColor(VerdeClaro));
                    menucarne.setBackgroundColor(Color.parseColor(CorCarne));
                    menupeixe.setBackgroundColor(Color.parseColor(CorPeixe));
                    selecionadoTudoVeg = 3;
                }else{
                    for (int i = 0; i < EscolhasEmenta.length; i++) {
                        if (DiasComRefeicaoMarcada[i].isEnabled()){
                            DiasComRefeicaoMarcada[i].setBackgroundColor(Color.parseColor(Cinza));
                            EscolhasEmenta[i] = 0;
                        }
                    }
                    menucarne1.setBackgroundColor(Color.parseColor(CorCarne));
                    menucarne.setBackgroundColor(Color.parseColor(CorVeg));
                    selecionadoTudoVeg = 0;
                }
            }
        });

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
                CorDiaSemanaSelecionado(i);
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
            case R.id.terfeira:
                i = 1;
                CorDiaSemanaSelecionado(i);
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
            case R.id.quafeira:
                i = 2;
                CorDiaSemanaSelecionado(i);
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
            case R.id.quifeira:
                i = 3;
                CorDiaSemanaSelecionado(i);
                MostraEmenta(i, EscolhasEmenta[i]);
                break;
            case R.id.sexfeira:
                i = 4;
                CorDiaSemanaSelecionado(i);
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
}