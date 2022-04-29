package com.example.watchtracker.viewModel.Adapters;

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
        View view = layoutInflater.inflate(R.layout.search_shows_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.showTitle.setText(shows.get(position).getName());
        Uri imageUri = Uri.parse("https://image.tmdb.org/t/p/original" + shows.get(position).getBackdrop_path());
        holder.showImage.setImageURI(imageUri);
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView showTitle;
        private final ImageView showImage;
        ViewHolder (View itemView)
        {
            super(itemView);
            this.showTitle = itemView.findViewById(R.id.show_title);
            this.showImage = itemView.findViewById(R.id.show_image);
        }
    }
}
