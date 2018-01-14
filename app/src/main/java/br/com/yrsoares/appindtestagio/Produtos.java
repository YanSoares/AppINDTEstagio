package br.com.yrsoares.appindtestagio;

import android.graphics.Bitmap;

/**
 * Created by yrsoa on 10/03/2017.
 */

public class Produtos {

    private int id;
    private String name;
    private double rate;
    private Bitmap imagem;

    public Produtos(int id, String name, double rate, Bitmap imagem) {

        this.id = id;
        this.name = name;
        this.rate = rate;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return name;
    }
}
