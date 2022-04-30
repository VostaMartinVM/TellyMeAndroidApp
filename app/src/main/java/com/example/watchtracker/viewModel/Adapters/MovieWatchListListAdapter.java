package com.example.watchtracker.viewModel.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchtracker.R;
import com.example.watchtracker.model.Movie;

import java.util.ArrayList;

public class MovieWatchListListAdapter extends RecyclerView.Adapter<MovieWatchListListAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private AdapterView.OnItemClickListener onClickListener;

    public MovieWatchListListAdapter(ArrayList<Movie> movies){
        this.movies = movies;
    }

    public void setOnClickListener(AdapterView.OnItemClickListener listener) {
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_watch_list_item, parent, false);
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
        private final TextView movieTitle;
        private final ImageView movieImage;

        ViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title_watchlist);
            movieImage = itemView.findViewById(R.id.movie_image_watchlist);
        }
    }
}
