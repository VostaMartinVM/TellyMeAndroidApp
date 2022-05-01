package com.example.watchtracker.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.watchtracker.R;
import com.example.watchtracker.model.Movie;
import com.example.watchtracker.model.Show;
import com.example.watchtracker.repository.MovieRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchMoviesListAdapter extends RecyclerView.Adapter<SearchMoviesListAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private View view;
    private MovieRepository movieRepository;

    public SearchMoviesListAdapter (ArrayList<Movie> movies, Context context){
        this.movies = movies;
        movieRepository = MovieRepository.getInstance();
        movieRepository.setContext(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int height = parent.getHeight()/6;
        int width = parent.getWidth();
        view = layoutInflater.inflate(R.layout.search_list_item, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
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
            Picasso.get().load("dummy path" + movies.get(position).getPosterPath()).into(holder.movieImage);
        }
        holder.movieButton.setOnClickListener(view -> {
            movieRepository.addMovieToList(movies.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView movieTitle;
        private final ImageView movieImage;
        private final FloatingActionButton movieButton;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.movieTitle = itemView.findViewById(R.id.search_title);
            this.movieImage = itemView.findViewById(R.id.search_image);
            this.movieButton = itemView.findViewById(R.id.search_add_button);
        }
    }
}
