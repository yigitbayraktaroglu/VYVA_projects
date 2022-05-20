package com.example.projetaslaks;

public class Musteri {
    private String isim;
    private String soyisim;
    private String tcNo;
    private String telNo;
    private String sifre;
    private Arac arac ;

    public Musteri(String isim, String soyisim, String tcNo, String sifre,String telNo,Arac arac ) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.tcNo = tcNo;
        this.sifre = sifre;
        this.telNo=telNo;
        this.arac=arac;
    }

    public Musteri() {

    }


    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public Arac getArac() {
        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }


}