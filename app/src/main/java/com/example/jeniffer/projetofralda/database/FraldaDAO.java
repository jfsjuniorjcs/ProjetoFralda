package com.example.jeniffer.projetofralda.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jeniffer.projetofralda.model.Fralda;

import java.util.ArrayList;
import java.util.List;

public class FraldaDAO {

   private Context mContext;

    public FraldaDAO(Context context){
        this.mContext = context;

    }

    public long inserir(Fralda fralda){

        FraldaDbHelper helper = new FraldaDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromFralda(fralda);

        long id = db.insert(LivroContract.TABLE_NAME,null,values);
        fralda.setId(id);
        db.close();

        return id;

   }
    /*public int atualizar(Fralda fralda){

        FraldaDbHelper helper = new FraldaDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromFralda(fralda);

        int rowsAffected = db.update(LivroContract.TABLE_NAME, values,
                LivroContract._ID + "?",
                new String[]{String.valueOf(fralda.getId())});
        db.close();

        return rowsAffected;

    }*/
    public int excluir(Fralda fralda){
        FraldaDbHelper helper = new FraldaDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        int rowsAffected =db.delete(LivroContract.TABLE_NAME,
                LivroContract._ID + "?",
                new String[]{String.valueOf(fralda.getId())});

        db.close();

        return rowsAffected;

    }
    public List<Fralda> listar(){

        FraldaDbHelper helper = new FraldaDbHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor =db.rawQuery("SELECT * FROM " + LivroContract.TABLE_NAME, null);
        List<Fralda> fraldas = new ArrayList<>();
        while (cursor.moveToNext()){
            Fralda fralda = new Fralda();
            fralda.setId(cursor.getLong(cursor.getColumnIndex(LivroContract._ID)));
            fralda.setMarca(cursor.getString(cursor.getColumnIndex(LivroContract.MARCA)));
            fralda.setModelo(cursor.getString(cursor.getColumnIndex(LivroContract.MODELO)));
            fralda.setTamanho(cursor.getString(cursor.getColumnIndex(LivroContract.TAMANHO)));
            fralda.setQuantidade(cursor.getInt(cursor.getColumnIndex(LivroContract.QUANTIDADE)));
            fralda.setImagemFralda(cursor.getString(cursor.getColumnIndex(LivroContract.IMAGEM)));
            fraldas.add(fralda);
        }

        cursor.close();
        db.close();

        return fraldas;
    }
    public boolean isFavorito(Fralda fralda){
        FraldaDbHelper helper = new FraldaDbHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor =db.rawQuery(
                "SELECT count(*) FROM " + LivroContract.TABLE_NAME +
                        "WHERE " + LivroContract.MARCA + " = ?",
                new String[]{fralda.getMarca()});
        boolean existe =false;
        if (cursor  != null) {
            existe = cursor.getInt(0) > 0;

            cursor.close();

        }
        db.close();

        return existe;

    }

    private ContentValues valuesFromFralda(Fralda fralda){

        ContentValues values = new ContentValues();
        values.put(LivroContract.MARCA,fralda.getMarca());
        values.put(LivroContract.MODELO,fralda.getMarca());
        values.put(LivroContract.TAMANHO,fralda.getMarca());
        values.put(LivroContract.QUANTIDADE,fralda.getMarca());
        values.put(LivroContract.IMAGEM,fralda.getMarca());

        return values;

    }
}
