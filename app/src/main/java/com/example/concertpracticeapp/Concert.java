package com.example.concertpracticeapp;

import java.io.Serializable;

public class Concert implements Serializable {
    private String numeTrupa;
    private String locatie;
    public enum Gen {
        Rock, Pop
    }

    private Gen gen;

    private int numarBilete;

    public Concert(String numeTrupa, String locatie, int numarBilete, Gen gen) {
        this.numeTrupa = numeTrupa;
        this.locatie = locatie;
        this.numarBilete = numarBilete;
        this.gen = gen;
    }

    public String getNumeTrupa() {
        return numeTrupa;
    }

    public void setNumeTrupa(String numeTrupa) {
        this.numeTrupa = numeTrupa;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Gen getGen() {
        return gen;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }

    public int getNumarBilete() {
        return numarBilete;
    }

    public void setNumarBilete(int numarBilete) {
        this.numarBilete = numarBilete;
    }

    @Override
    public String toString() {
        return "numeTrupa='" + numeTrupa + '\'' +
                ", locatie='" + locatie + '\'' +
                ", gen=" + gen +
                ", numarBilete=" + numarBilete;
    }
}
