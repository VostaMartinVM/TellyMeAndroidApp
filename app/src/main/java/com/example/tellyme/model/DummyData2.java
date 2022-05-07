package com.example.tellyme.model;

public class DummyData2 {

    public String name;
    public String name2;
    public int image;

    public DummyData2(String name, String name2, int image) {
        this.name = name;
        this.name2 = name2;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}
