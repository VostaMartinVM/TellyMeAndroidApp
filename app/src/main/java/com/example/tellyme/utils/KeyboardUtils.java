package com.example.tellyme.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardUtils {

    public static void hideSoftKeyboard(final Context context, View view) {
        InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftKeyboard(final Context context, EditText editText) {
        try {
            editText.requestFocus();
            editText.postDelayed(
                    () -> {
                        InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        keyboard.showSoftInput(editText, 0);
                    }
                    , 200);
        } catch (Exception npe) {
            npe.printStackTrace();
        }
    }
}
