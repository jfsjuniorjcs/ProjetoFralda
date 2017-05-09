package com.example.jeniffer.projetofralda.database;

import android.provider.BaseColumns;

/**
 * Created by Jeniffer on 19/05/2016.
 */
public interface LivroContract extends BaseColumns {

    String TABLE_NAME = "fraldas";

    String MARCA ="marca";
    String MODELO ="modelo";
    String TAMANHO ="tamanho";
    String QUANTIDADE ="quantidade";
    String IMAGEM ="imagemFralda";
}
