package com.example.tellyme.utils;

import android.util.Property;
import android.view.View;

public class HeightProperty extends Property<View, Integer> {

    public HeightProperty() {
        super(Integer.class, "height");
    }

    @Override public Integer get(View view) {
        return view.getHeight();
    }

    @Override public void set(View view, Integer value) {
        view.getLayoutParams().height = value;
        view.setLayoutParams(view.getLayoutParams());
    }
}

