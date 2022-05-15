package com.example.tellyme.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tellyme.R;
import com.example.tellyme.model.Movie;
import com.example.tellyme.model.Show;
import com.squareup.picasso.Picasso;

public class SpecificMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_movie);

        Bundle args = getIntent().getExtras();
        if (args != null)
        {
            Movie movie = (Movie) args.get("loadedMovie");
            ImageView showImage = findViewById(R.id.specific_movie_image);
            TextView movieTitle = findViewById(R.id.specific_movie_title);
            movieTitle.setText(movie.getTitle());
            if (movie.getBackdropPath() != null)
            {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getBackdropPath()).fit().centerCrop().into(showImage);
            }
            else {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getPosterPath()).fit().centerCrop().into(showImage);
            }
        }
    }
}