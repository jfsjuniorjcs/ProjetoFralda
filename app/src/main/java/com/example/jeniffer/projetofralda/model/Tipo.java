package com.example.jeniffer.projetofralda.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Tipo {

    String nome;
    @SerializedName("fralda")
    List<Fralda> fraldas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Fralda> getFraldas() {
        return fraldas;
    }

    public void setFraldas(List<Fralda> fraldas) {
        this.fraldas = fraldas;
    }
}
