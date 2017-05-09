package com.example.jeniffer.projetofralda.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FraldaDbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION=1;
    public static final String DB_NAME="fraldas_db";


    public FraldaDbHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ LivroContract.TABLE_NAME +" ("+
                        LivroContract._ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        LivroContract.MARCA      + " TEXT NOT NULL, " +
                        LivroContract.MODELO     + " TEXT NOT NULL, " +
                        LivroContract.TAMANHO    + " TEXT NOT NULL, " +
                        LivroContract.QUANTIDADE + " TEXT NOT NULL, " +
                        LivroContract.IMAGEM     + " TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

