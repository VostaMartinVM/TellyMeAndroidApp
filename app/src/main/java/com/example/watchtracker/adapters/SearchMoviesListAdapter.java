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
import com.example.watchtracker.model.Show;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchMoviesListAdapter extends RecyclerView.Adapter<SearchMoviesListAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private View view;

    public SearchMoviesListAdapter (ArrayList<Movie> movies){
        this.movies = movies;
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
        holder.showTitle.setText(movies.get(position).getTitle());
        if (movies.get(position).getBackdropPath() != null)
        {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + movies.get(position).getBackdropPath()).fit().centerCrop().into(holder.showImage);
        }
        else {
            Picasso.get().load("dummy path" + movies.get(position).getPosterPath()).into(holder.showImage);
        }
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
        private final TextView showTitle;
        private final ImageView showImage;
        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.showTitle = itemView.findViewById(R.id.search_title);
            this.showImage = itemView.findViewById(R.id.search_image);
        }
    }
}
