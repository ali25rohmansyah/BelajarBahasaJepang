package com.example.quizjepang.model;

public class HiraganaModel {

    private String Gambar, Nama, Tipe;

    public HiraganaModel() {
    }

    public HiraganaModel(String gambar, String nama, String tipe) {
        Gambar = gambar;
        Nama = nama;
        Tipe = tipe;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getTipe() {
        return Tipe;
    }

    public void setTipe(String tipe) {
        Tipe = tipe;
    }
}
