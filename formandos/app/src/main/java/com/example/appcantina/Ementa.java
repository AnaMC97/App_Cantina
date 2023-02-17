package com.example.appcantina;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Ementa {
    String [] EmentaCarne = new String[5];
    String [] EmentaPeixe = new String[5];
    String [] EmentaVegetariano = new String[5];
    String [] EmentaSopa = new String[5];
    String [] EmentaSobremesa = new String[5];
    private Context context;
    private int i;
    private int diaSemana;
    private int diferencaData = 4 - diaSemana;
    private String escolhaEmentaDia;
    private String aluno;
    private String url = "http://192.168.12.120/CencalCantina/executaQuery.php";
    int [] ementaEstrangeira = new int[5];

    public Ementa () {}
    //public Ementa (String id, int diferenca) {aluno = id; diferencaData = diferenca;}
    public Ementa (Context context, String id, int dia){
        aluno = id;
        diaSemana = dia;
        this.context=context;
    }

    public void SendEmentasResidentes1(int[] ementa){
        ementaEstrangeira = ementa;
        StringRequest stringRequest;
        diferencaData = 4 - diaSemana;
        i = 4;

        // Vai percorrer o array das escolhas igual aos dias que faltam até a semana terminar
        Log.e("teste","diaSemana - "+ (diaSemana));
        for(int j = 4; j>=diaSemana; j--) {
            Log.e("teste","j - "+j);
            stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("Response", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) { }
            }) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Log.e("teste","Diferença Data - "+diferencaData);
                    GetRefeicaoMarcada();

                    if(escolhaEmentaDia == "0"){
                        params.put("query", "INSERT INTO reg_marcacao values (DATE_ADD(CURDATE(), INTERVAL "+ diferencaData +" DAY)," + aluno + "," + 0 + "," + ementa[i] + ");");
                    }
                    i--;
                    diferencaData--;
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }
    }

    public void SendEmentasResidentes(int[] ementa){
        ementaEstrangeira = ementa;
        StringRequest stringRequest;
        diferencaData = 4 - diaSemana;
        i = 4;

        // Vai percorrer o array das escolhas igual aos dias que faltam até a semana terminar

        for(int j = 4; j>=diaSemana; j--) {
            Log.e("teste","j - "+j);
            stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("Response", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) { }
            }) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Log.e("teste","Diferença Data - "+diferencaData);
                    GetRefeicaoMarcada();

                    if(escolhaEmentaDia == "0"){
                        params.put("query", "INSERT INTO reg_marcacao values (DATE_ADD(CURDATE(), INTERVAL "+ diferencaData +" DAY)," + aluno + "," + 0 + "," + ementa[i] + ");");
                    }
                    i--;
                    diferencaData--;
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }
    }



    public String GetRefeicaoMarcada(){
        String valor = "SELECT Tipo_Refeicao_idTipo_Refeicao FROM reg_marcacao WHERE data = (DATE_ADD(CURDATE(), INTERVAL "+ diferencaData +" DAY) AND Cartao_idCartao = "+ aluno;
        String json_url = "http://192.168.12.120/CencalCantina/ementasEscolhida.php?query="+ valor;
        try{
            URL url = new URL(json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream input = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            StringBuilder stringBuilder = new StringBuilder();
            String JSON_STRING;
            while ((JSON_STRING = br.readLine()) != null) {
                stringBuilder.append(JSON_STRING + "\n");
            }
            br.close();
            input.close();
            httpURLConnection.disconnect();
            TrataJson tj = new TrataJson();
            return tj.DevolveIntRefeicaoMarcada(stringBuilder.toString().trim());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        /*
    public void GetRefeicaoMarcada1() { new Ementa.BackgroundTask().execute();}

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        public BackgroundTask() { }
            String json_url;

        @Override
        protected void onPreExecute() {
            String valor = "SELECT Tipo_Refeicao_idTipo_Refeicao FROM reg_marcacao WHERE data = (DATE_ADD(CURDATE(), INTERVAL "+ diferencaData +" DAY) AND Cartao_idCartao = "+ aluno;
            Log.e("teste", valor);
            json_url = "http://192.168.12.120/CencalCantina/ementasEscolhida.php?query="+ valor;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                Log.e("teste", json_url);
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(input));
                StringBuilder stringBuilder = new StringBuilder();
                String JSON_STRING;
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
            TrataJson tj = new TrataJson();
            escolhaEmentaDia = tj.DevolveIntRefeicaoMarcada(result);
        }

         */



}
