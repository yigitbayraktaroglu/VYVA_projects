package com.example.projetaslaks;

public class Arac {
    private int durum;
    private String plaka;
    private String vites;
    private int km;
    private String renk;
    private String marka;
    private String model;

    public Arac(int durum, String plaka, String vites, int km, String renk, String marka, String model) {
        this.durum = durum;
        this.plaka = plaka;
        this.vites = vites;
        this.km = km;
        this.renk = renk;
        this.marka = marka;
        this.model = model;
    }

    public String getPlaka() {
        return plaka;
    }

    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getVites() {
        return vites;
    }

    public void setVites(String vites) {
        this.vites = vites;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
