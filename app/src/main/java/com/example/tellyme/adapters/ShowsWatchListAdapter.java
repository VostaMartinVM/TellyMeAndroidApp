package com.example.tellyme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tellyme.R;
import com.example.tellyme.model.Show;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowsWatchListAdapter extends RecyclerView.Adapter<ShowsWatchListAdapter.ViewHolder>{

    private ArrayList<Show> shows;
    private RecyclerViewOnClickListener listener;


    public ShowsWatchListAdapter(ArrayList<Show> shows, RecyclerViewOnClickListener listener) {
        this.shows = shows;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.shows_watch_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowsWatchListAdapter.ViewHolder holder, int position) {
        holder.showTitle.setText(shows.get(position).getName());
        holder.episodeTitle.setText(shows.get(position).getName());
        if (shows.get(position).getBackdropPath() != null)
        {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + shows.get(position).getBackdropPath()).fit().centerCrop().into(holder.showImage);
        }
        else {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + shows.get(position).getPosterPath()).fit().centerCrop().into(holder.showImage);
        }
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
        private final TextView episodeTitle;
        private final FloatingActionButton showButton;
        private final ImageView showImage;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.showTitle = itemView.findViewById(R.id.show_watchlist_title);
            this.episodeTitle = itemView.findViewById(R.id.episode_watchlist_title);
            this.showImage = itemView.findViewById(R.id.show_watchlist_image);
            this.showButton = itemView.findViewById(R.id.button_show_watchlist);
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

