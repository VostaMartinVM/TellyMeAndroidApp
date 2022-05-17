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
import com.example.tellyme.viewModel.SeachViewModels.SearchMoviesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchMoviesListAdapter extends RecyclerView.Adapter<SearchMoviesListAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private final SearchMoviesViewModel searchMoviesViewModel;
    private final RecyclerViewOnClickListener listener;
    private final String args;

    public SearchMoviesListAdapter (ArrayList<Movie> movies, SearchMoviesViewModel searchMoviesViewModel,
                                    RecyclerViewOnClickListener listener, String args){
        this.movies = movies;
        this.listener = listener;
        this.searchMoviesViewModel = searchMoviesViewModel;
        this.args = args;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_list_item, parent, false);
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
            Picasso.get().load("https://image.tmdb.org/t/p/original" + movies.get(position).getPosterPath()).fit().centerCrop().into(holder.movieImage);
        }
        holder.addButton.setOnClickListener(view -> {
            if (args.isEmpty()){
                searchMoviesViewModel.addMovieToSpecificList("Movies", movies.get(position).getId());
            }
            else {
                if (!args.matches("Shows"))
                {
                    searchMoviesViewModel.addMovieToSpecificList(args, movies.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView movieTitle;
        private final ImageView movieImage;
        private final FloatingActionButton addButton;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.movieTitle = itemView.findViewById(R.id.search_title);
            this.movieImage = itemView.findViewById(R.id.program_image);
            this.addButton = itemView.findViewById(R.id.search_add_button);
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
