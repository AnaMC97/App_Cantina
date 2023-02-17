package com.example.appcantina;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrataJson {

    public static Ementa GetEmentas(String conteudo) {
        try {
            JSONArray jsonArray;
            JSONObject jsonObject;
            jsonArray = new JSONArray(conteudo);
            Ementa ementa = new Ementa();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                ementa.EmentaCarne[i] = jsonObject.getString("carne");
                ementa.EmentaPeixe[i] = jsonObject.getString("peixe");
                ementa.EmentaVegetariano[i] = jsonObject.getString("veg");
                ementa.EmentaSopa[i] = jsonObject.getString("sopa");
                ementa.EmentaSobremesa[i] = jsonObject.getString("sobremesa");
            }
            return ementa;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String DevolveIntRefeicaoMarcada(String conteudo) {
        try {
            JSONArray jsonArray;
            JSONObject jsonObject;
            jsonArray = new JSONArray(conteudo);
            String CodigoRefeicao;
            jsonObject = jsonArray.getJSONObject(0);
            CodigoRefeicao = jsonObject.getString("Tipo_Refeicao_idTipo_Refeicao");
            return CodigoRefeicao;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}