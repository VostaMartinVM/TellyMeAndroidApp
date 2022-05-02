package com.example.tellyme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tellyme.R;
import com.example.tellyme.model.DummyData;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SpecificListAdapter extends RecyclerView.Adapter<SpecificListAdapter.ViewHolder> {

    private ArrayList<DummyData> dummyData;

    public SpecificListAdapter (ArrayList<DummyData> dummyData){
        this.dummyData = dummyData;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lists_item, parent, false);
        int height = parent.getHeight()/8;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.listTitle.setText(dummyData.get(position).getName());
        Picasso.get().load(dummyData.get(position).getImage()).fit().centerCrop().into(holder.listImage);
    }

    @Override
    public int getItemCount() {
        return dummyData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView listTitle;
        private final ShapeableImageView listImage;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.listTitle = itemView.findViewById(R.id.lists_text);
            this.listImage = itemView.findViewById(R.id.lists_image);
        }
    }
}