package com.example.watchtracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchtracker.R;
import com.example.watchtracker.model.DummyData;
import com.example.watchtracker.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieWatchListListAdapter extends RecyclerView.Adapter<MovieWatchListListAdapter.ViewHolder> {


    private ArrayList<DummyData> dummyData;

    public MovieWatchListListAdapter(ArrayList<DummyData> dummyData){
        this.dummyData = dummyData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_watch_list_item, parent, false);
        int height = parent.getHeight()/6;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.movieTitle.setText(dummyData.get(position).getName());
        Picasso.get().load(dummyData.get(position).getImage()).fit().centerCrop().into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return dummyData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView movieTitle;
        private final ImageView movieImage;
        private final FloatingActionButton checkButtonMovieWatchList;

        ViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title_watchlist);
            movieImage = itemView.findViewById(R.id.movie_image_watchlist);
            checkButtonMovieWatchList = itemView.findViewById(R.id.check_button_movie_watch_list);
        }
    }
}
