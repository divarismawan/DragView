package com.example.dragview;

import android.graphics.Bitmap;

public class ModelFilm {

    private String title;
    private String genre;
    private int thubnail;

    public ModelFilm(String title, String genre, int thubnail){
        this.title    = title;
        this.genre    = genre;
        this.thubnail = thubnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getThubnail() {
        return thubnail;
    }

    public void setThubnail(int thubnail) {
        this.thubnail = thubnail;
    }
}
