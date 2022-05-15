package com.example.tellyme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tellyme.R;
import com.example.tellyme.model.Show;
import com.example.tellyme.viewModel.SeachViewModels.SearchShowsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchShowsListAdapter extends RecyclerView.Adapter<SearchShowsListAdapter.ViewHolder> {

    private ArrayList<Show> shows;
    private RecyclerViewOnClickListener listener;
    private SearchShowsViewModel searchShowsViewModel;
    private Context context;
    private String args;

    public SearchShowsListAdapter (ArrayList<Show> shows, SearchShowsViewModel searchShowsViewModel,
                                   Context context, RecyclerViewOnClickListener listener, String args){
        this.shows = shows;
        this.listener = listener;
        this.context = context;
        this.searchShowsViewModel = searchShowsViewModel;
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
        holder.showTitle.setText(shows.get(position).getName());
        if (shows.get(position).getBackdropPath() != null)
        {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + shows.get(position).getBackdropPath()).fit().centerCrop().into(holder.showImage);
        }
        else {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + shows.get(position).getPosterPath()).fit().centerCrop().into(holder.showImage);
        }
        holder.addButton.setOnClickListener(view -> {
                if (args == null || args.isEmpty())
                {
                    searchShowsViewModel.addShowsToSpecificList("Shows", shows.get(position).getId());
                }
                else {
                    if (!args.matches("Movies"))
                    {
                        searchShowsViewModel.addShowsToSpecificList(args, shows.get(position).getId());
                    }
                }
        });
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public void updateShows(ArrayList<Show> shows) {
        this.shows = shows;
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
