package com.example.tellyme.model;

public class DummyData {

    public String name;
    public int image;

    public DummyData(String name,int image) {
        this.name = name;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
