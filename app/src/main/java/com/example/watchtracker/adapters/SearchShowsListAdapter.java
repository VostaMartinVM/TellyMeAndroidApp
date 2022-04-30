package com.example.watchtracker.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchtracker.R;
import com.example.watchtracker.model.Show;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchShowsListAdapter extends RecyclerView.Adapter<SearchShowsListAdapter.ViewHolder> {

    private ArrayList<Show> shows;
    public SearchShowsListAdapter (ArrayList<Show> shows){
        this.shows = shows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_search_shows_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.showTitle.setText(shows.get(position).getName());
        if (shows.get(position).getBackdropPath() != null)
        {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + shows.get(position).getBackdropPath()).into(holder.showImage);
        }
        else {
            Picasso.get().load("https://image.tmdb.org/t/p/original" + shows.get(position).getPosterPath()).into(holder.showImage);
        }
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public void setShows(ArrayList<Show> shows) {
        this.shows = shows;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView showTitle;
        private final ImageView showImage;
        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.showTitle = itemView.findViewById(R.id.show_title);
            this.showImage = itemView.findViewById(R.id.show_image);
        }
    }
}
