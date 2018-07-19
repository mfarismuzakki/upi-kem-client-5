package com.upi.bahasaindonesia.kem.models;

import java.io.Serializable;

public class BukuTeks implements Serializable {
    private int kode;
    private String judul;
    private String teks;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTeks() {
        return teks;
    }

    public void setTeks(String teks) {
        this.teks = teks;
    }
}
