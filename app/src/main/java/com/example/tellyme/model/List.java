package com.example.tellyme.model;

import com.example.tellyme.R;

public class List {

    String ListText;
    int ListImage;


    public List(String ListText, int ListImage)
    {
       this.ListImage = ListImage;
       this.ListText = ListText;
    }

    public int getListImage() {
        return ListImage;
    }

    public String getListText() {
        return ListText;
    }

    public void setListImage(int listImage) {
        ListImage = listImage;
    }

    public void setListText(String listText) {
        ListText = listText;
    }
}
