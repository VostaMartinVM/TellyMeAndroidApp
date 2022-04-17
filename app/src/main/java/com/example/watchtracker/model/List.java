package com.example.watchtracker.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.example.watchtracker.R;

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
