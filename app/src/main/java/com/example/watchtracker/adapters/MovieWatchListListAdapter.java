package com.example.watchtracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchtracker.R;
import com.example.watchtracker.model.Movie;

import java.util.ArrayList;

public class MovieWatchListListAdapter extends RecyclerView.Adapter<MovieWatchListListAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private View.OnClickListener onClickListener;

    MovieWatchListListAdapter (ArrayList<Movie> movies){
        this.movies = movies;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_movie_watch_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.movieImage.setImageResource(movies.get(position).getBackdrop_path());
        holder.movieTitle.setText(movies.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        ImageView movieImage;

        ViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieImage = itemView.findViewById(R.id.movie_image);
        }
    }
}
