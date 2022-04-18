package com.example.watchtracker.viewModel.ListsViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.watchtracker.R;
import com.example.watchtracker.model.List;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<List> List;
    LayoutInflater inflater;


    public CustomBaseAdapter(Context context, ArrayList List)
    {
        this.List = List;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return List.size();
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
        view = inflater.inflate(R.layout.activity_lists_item, null);
        TextView textView = (TextView) view.findViewById(R.id.ListsText);
        ImageView imageView = (ImageView) view.findViewById(R.id.ListIcon);
        textView.setText(List.get(i).getListText());
        imageView.setImageResource(List.get(i).getListImage());
        return view;
    }
}
