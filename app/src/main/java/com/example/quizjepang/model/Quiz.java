package com.example.quizjepang.model;

public class Quiz {

    private String Image, Kata, Soal;

    public Quiz() {
    }

    public Quiz(String image, String kata, String soal) {
        Image = image;
        Kata = kata;
        Soal = soal;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getKata() {
        return Kata;
    }

    public void setKata(String kata) {
        Kata = kata;
    }

    public String getSoal() {
        return Soal;
    }

    public void setSoal(String soal) {
        Soal = soal;
    }
}
