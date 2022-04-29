package com.example.watchtracker.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.watchtracker.R;

public class MovieWatchListItem extends RecyclerView.ViewHolder {

    private final TextView movieTitle;
    private final ImageView movieImage;

    public MovieWatchListItem(@NonNull View itemView) {
        super(itemView);
        this.movieImage = itemView.findViewById(R.id.movie_image);
        this.movieTitle = itemView.findViewById(R.id.movie_title);
    }

}
