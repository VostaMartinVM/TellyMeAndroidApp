package com.example.watchtracker.model;

public class Show {
    private String name;
    private String backdrop_path;

    public Show (String name, String backdrop_path)
    {
        this.name = name;
        this.backdrop_path = backdrop_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
