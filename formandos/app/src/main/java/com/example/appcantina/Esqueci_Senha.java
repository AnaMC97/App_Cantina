package com.example.appcantina;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.PrecomputedText;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.microedition.khronos.egl.EGLSurface;
import javax.xml.transform.Result;

/* TODO
ver se o codigo de ajuda esta a funcionar
 */

public class Esqueci_Senha extends AppCompatActivity {

    private TextView codigo;
    private TextView nif;
    TextView textoajudar, txtdigitenif, txtdigitecodigo, digitenif, digitecodigo;
    Button ajudar, validar, voltar, sair;

   String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);
        //---------------------------------------------------
        //criação da var com a "senha"
        String varcod;
        varcod = "000000";
        //---------------------------------------------------------------------------------------------
        //Execução Automática do código
        codigo = findViewById(R.id.digitecodigo);
        nif = findViewById(R.id.digitenif);
        codigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String conteudo = new String();
                conteudo = codigo.getText().toString();
                if (conteudo.length() == 6) {
                    if (conteudo.equals(varcod)) {
                        Toast.makeText(Esqueci_Senha.this, "VALIDADO", Toast.LENGTH_SHORT).show();
                        //DBHelper.GeraCodigo(conteudo);
                        Intent it = new Intent(Esqueci_Senha.this, telaAlmoco.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        });

        ajudar = findViewById(R.id.ajuda3);
        textoajudar = findViewById(R.id.textoajuda3);
        txtdigitenif = findViewById(R.id.txtdigitenif);
        digitenif = findViewById(R.id.digitenif);
        txtdigitecodigo = findViewById(R.id.txtdigitecodigo);
        digitecodigo = findViewById(R.id.digitecodigo);
        validar = findViewById(R.id.btnvalidar);
        voltar = findViewById(R.id.btnvoltar);
        sair = findViewById(R.id.sair);

        ajudar.setVisibility(View.VISIBLE);
        textoajudar.setVisibility(View.INVISIBLE);
        txtdigitenif.setVisibility(View.VISIBLE);
        digitenif.setVisibility(View.VISIBLE);
        txtdigitecodigo.setVisibility(View.VISIBLE);
        digitecodigo.setVisibility(View.VISIBLE);
        validar.setVisibility(View.VISIBLE);
        voltar.setVisibility(View.VISIBLE);
        sair.setVisibility(View.INVISIBLE);


        ajudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajudar.setVisibility(View.VISIBLE);
                textoajudar.setVisibility(View.VISIBLE);
                txtdigitenif.setVisibility(View.INVISIBLE);
                digitenif.setVisibility(View.INVISIBLE);
                txtdigitecodigo.setVisibility(View.INVISIBLE);
                digitecodigo.setVisibility(View.INVISIBLE);
                validar.setVisibility(View.INVISIBLE);
                voltar.setVisibility(View.INVISIBLE);
                sair.setVisibility(View.VISIBLE);
            }
        });
    }
        public void Voltar (View v){
            Intent it = new Intent(Esqueci_Senha.this, TelaInicial.class);
            startActivity(it);
            finish();
        }


        public void getDados (View v){
            new BackgroundTask().execute();
        }

        class BackgroundTask extends AsyncTask<Void, Void, String> {

            int x = new Random().nextInt(999999 - 000000) + 999999;
            String json_url;
            String codigo = x + "";
            EditText nif2 = findViewById(R.id.digitenif);
            int nif = Integer.parseInt(nif2.getText().toString());

            @Override
            protected void onPreExecute() {
                //url = "http://androidtut.comli.com/json_get_data.php";
                //  json_url = "http://192.168.50.116/CencalCantina/validaFormando.php";
                json_url = "http://10.18.128.140/CencalCantina/enviaEmailCodigoValidacao.php?nif=" + nif + "&codigo=" + codigo;
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
                TextView tv = findViewById(R.id.dados);
                tv.setText(result);
            }
        }

    public void sair(View v){
        Intent it = new Intent(Esqueci_Senha.this, Esqueci_Senha.class);
        startActivity(it);
        finish();
    }
    }
