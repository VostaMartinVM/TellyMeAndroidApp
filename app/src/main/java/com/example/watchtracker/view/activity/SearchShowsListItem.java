package com.example.watchtracker.view.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.watchtracker.R;

public class SearchShowsListItem extends RecyclerView.ViewHolder {

    private final TextView showTitle;
    private final ImageView showImage;

    public SearchShowsListItem(@NonNull View itemView) {
        super(itemView);
        this.showTitle = itemView.findViewById(R.id.show_title);
        this.showImage = itemView.findViewById(R.id.show_image);
    }
}