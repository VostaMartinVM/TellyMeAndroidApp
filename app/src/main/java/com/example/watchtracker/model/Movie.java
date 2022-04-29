package com.example.watchtracker.model;

public class Movie {

    private String name;
    private int backdrop_path;

    public Movie(String name, int backdrop_path) {
        this.name = name;
        this.backdrop_path = backdrop_path;
    }

    public int getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(int backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
