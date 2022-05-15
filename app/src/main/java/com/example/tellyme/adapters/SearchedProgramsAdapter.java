package com.example.tellyme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tellyme.R;
import com.example.tellyme.model.Movie;
import com.example.tellyme.model.Show;
import com.example.tellyme.viewModel.SeachViewModels.SearchedProgramsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchedProgramsAdapter extends RecyclerView.Adapter<SearchedProgramsAdapter.ViewHolder> {

    private ArrayList<Object> tvPrograms;
    private RecyclerViewOnClickListener listener;
    private SearchedProgramsViewModel searchedProgramsViewModel;
    private String args = "";

    public SearchedProgramsAdapter(ArrayList<Object> tvPrograms, RecyclerViewOnClickListener listener,
                                   SearchedProgramsViewModel searchedProgramsViewModel, String args)
    {
        this.tvPrograms = tvPrograms;
        this.listener = listener;
        this.args = args;
        this.searchedProgramsViewModel = searchedProgramsViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_list_item, parent, false);
        return new SearchedProgramsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (tvPrograms.get(position) instanceof Show)
        {
            Show show = (Show) tvPrograms.get(position);
            holder.showTitle.setText(show.getName());
            if (show.getBackdropPath() != null)
            {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + show.getBackdropPath()).fit().centerCrop().into(holder.showImage);
            }
            else {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + show.getPosterPath()).fit().centerCrop().into(holder.showImage);
            }

            holder.addButton.setOnClickListener(view -> {
                if (args == null || args.isEmpty())
                {
                    searchedProgramsViewModel.addShowToSpecificList("Shows", show.getId());
                }
                else {
                    if (!args.matches("Movies"))
                    {
                        searchedProgramsViewModel.addShowToSpecificList(args, show.getId());
                    }
                }
            });
        }
        else {
            Movie movie = (Movie) tvPrograms.get(position);
            holder.showTitle.setText(movie.getTitle());
            if (movie.getBackdropPath() != null)
            {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getBackdropPath()).fit().centerCrop().into(holder.showImage);
            }
            else {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getPosterPath()).fit().centerCrop().into(holder.showImage);
            }

            holder.addButton.setOnClickListener(view -> {
                if (args == null || args.isEmpty())
                {
                    searchedProgramsViewModel.addMovieToSpecificList("Movies", movie.getId());
                }
                else {
                    if (!args.matches("Shows"))
                    {
                        searchedProgramsViewModel.addMovieToSpecificList(args, movie.getId());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return tvPrograms.size();
    }

    public void updateTvPrograms(ArrayList<Object> tvPrograms)
    {
        this.tvPrograms = tvPrograms;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView showTitle;
        private final ShapeableImageView showImage;
        private final FloatingActionButton addButton;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.showTitle = itemView.findViewById(R.id.search_title);
            this.showImage = itemView.findViewById(R.id.program_image);
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
