package com.example.watchtracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.watchtracker.R;

public class ListsBaseAdapter extends BaseAdapter {

    Context context;
    String listItems[];
    int listImages[];
    LayoutInflater inflater;


    public ListsBaseAdapter(Context context, String[] listItems, int[] listImages) {
        this.listImages = listImages;
        this.listItems = listItems;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return listItems.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.lists_item, null);
        TextView textView = (TextView) view.findViewById(R.id.ListsText);
        ImageView imageView = (ImageView) view.findViewById(R.id.ListIcon);
        textView.setText(listItems[i]);
        imageView.setImageResource(listImages[i]);
        return view;
    }
}
