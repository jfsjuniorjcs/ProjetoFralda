package com.example.jeniffer.projetofralda.model;

import org.parceler.Parcel;

@Parcel
public class Fralda {

    public String marca;
    public String modelo;
    public String tamanho;
    public int quantidade;
    public String imagemFralda;
    private long id;

    public Fralda() {

    }

    public String getMarca(){

        return marca;
    }
    public void setMarca(String marca){
        this.marca = marca;

    }
    public String getModelo(){

        return modelo;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;

    }
    public String getTamanho(){

        return tamanho;
    }
    public void setTamanho(String tamanho){
        this.tamanho = tamanho;

    }
    public int getQuantidade(){

        return quantidade;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;

    }

    public String getImagemFralda(){

        return imagemFralda;
    }
    public void setImagemFralda(String imagemFralda){
        this.imagemFralda = imagemFralda;

    }

    @Override
    public String toString() {

        return marca+"-"+modelo+"-"+tamanho+"-"+quantidade;
    }

    public Fralda(String marca, String modelo, String tamanho, int quantidade, String imagemFralda) {
        this.marca = marca;
        this.modelo = modelo;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.imagemFralda = imagemFralda;
        this.id = id;
    }


    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }
}
