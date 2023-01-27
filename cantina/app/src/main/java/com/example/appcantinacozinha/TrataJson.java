package com.example.appcantinacozinha;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrataJson {

    public static List<Ementa> GetEmentas(String conteudo) {
        List<Ementa> ementas = new ArrayList<Ementa>();

        try {
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            jsonArray = new JSONArray(conteudo);
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                Ementa ementa = new Ementa();
                ementa.ementaCarne = jsonObject.getString("carne");
                ementa.ementaPeixe = jsonObject.getString("peixe");
                ementa.ementaVeg = jsonObject.getString("veg");
                ementa.ementaSopa = jsonObject.getString("sopa");
                ementa.ementaSobremesa = jsonObject.getString("sobremesa");
                //ementa.ementaJantar = jsonObject.getString("jantar");
                ementas.add(ementa);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ementas;
    }


}