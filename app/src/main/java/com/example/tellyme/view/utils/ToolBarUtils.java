package com.example.tellyme.view.utils;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.Toolbar;

public class ToolBarUtils {
    public static void changeUserIcon(int color, Toolbar toolbar)
    {
        Drawable drawable = toolbar.getMenu().getItem(0).getIcon();
        drawable.mutate();
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }
}
