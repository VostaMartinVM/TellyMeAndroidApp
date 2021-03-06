package com.example.tellyme.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tellyme.R;
import com.example.tellyme.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieWatchListListAdapter extends RecyclerView.Adapter<MovieWatchListListAdapter.ViewHolder> {


    private ArrayList<Movie> movies;
    private final RecyclerViewOnClickListener listener;


    public MovieWatchListListAdapter(ArrayList<Movie> movies, RecyclerViewOnClickListener listener){
        this.movies = movies;
        this.listener = listener;
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
        holder.movieTitle.setText(movies.get(position).getTitle());
        if (movies.get(position).getBackdropPath() != null)
        {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + movies.get(position).getBackdropPath()).fit().centerCrop().into(holder.movieImage);
        }
        else {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + movies.get(position).getPosterPath())
                    .fit().centerCrop().into(holder.movieImage);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateMovies(ArrayList<Movie> movies)
    {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView movieTitle;
        private final ImageView movieImage;

        ViewHolder(View itemView)
        {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title_watchlist);
            movieImage = itemView.findViewById(R.id.movie_image_watchlist);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAbsoluteAdapterPosition());
        }
    }

    public interface RecyclerViewOnClickListener{
        void onClick(View view, int position);
    }
}
