package com.example.watchtracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchtracker.R;
import com.example.watchtracker.model.Show;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchShowsListAdapter extends RecyclerView.Adapter<SearchShowsListAdapter.ViewHolder> {

    private ArrayList<Show> shows;
    private Context context;

    public SearchShowsListAdapter (ArrayList<Show> shows, Context context){
        this.shows = shows;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_shows_list_item, parent, false);
        int height = parent.getHeight()/6;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
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
            Picasso.get().load("dummy path").into(holder.showImage);
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
        private final ShapeableImageView showImage;
        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.showTitle = itemView.findViewById(R.id.search_show_title);
            this.showImage = itemView.findViewById(R.id.search_show_image);
        }
    }
}
