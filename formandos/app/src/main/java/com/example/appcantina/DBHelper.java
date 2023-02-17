package com.example.appcantina;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
private  static  int versao = 1;
private static  String nomeDB = "dbcantina.db";

    public DBHelper(@Nullable Context context,  int version) {
        super(context, nomeDB, null,versao );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int iD) {

        }
    }

