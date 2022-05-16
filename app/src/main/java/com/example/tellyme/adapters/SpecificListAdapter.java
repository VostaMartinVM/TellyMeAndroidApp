package com.example.tellyme.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tellyme.R;
import com.example.tellyme.model.Movie;
import com.example.tellyme.model.Show;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SpecificListAdapter extends RecyclerView.Adapter<SpecificListAdapter.ViewHolder> {

    private ArrayList<Object> tvPrograms;
    private final RecyclerViewOnClickListener listener;


    public SpecificListAdapter (ArrayList<Object> tvPrograms, RecyclerViewOnClickListener listener){
        this.tvPrograms = tvPrograms;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lists_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (tvPrograms.get(position) instanceof Show)
        {
            Show show = (Show) tvPrograms.get(position);
            holder.listTitle.setText(show.getName());
            if (show.getBackdropPath() != null)
            {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + show.getBackdropPath()).fit().centerCrop().into(holder.listImage);
            }
            else {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + show.getPosterPath()).fit().centerCrop().into(holder.listImage);
            }
        }
        else {
            Movie movie = (Movie) tvPrograms.get(position);
            holder.listTitle.setText(movie.getTitle());
            if (movie.getBackdropPath() != null)
            {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getBackdropPath()).fit().centerCrop().into(holder.listImage);
            }
            else {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getPosterPath()).fit().centerCrop().into(holder.listImage);
            }
        }
    }

    @Override
    public int getItemCount() {
        return tvPrograms.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateTVPrograms(ArrayList<Object> tvPrograms){
        this.tvPrograms = tvPrograms;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView listTitle;
        private final ShapeableImageView listImage;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.listTitle = itemView.findViewById(R.id.lists_text);
            this.listImage = itemView.findViewById(R.id.lists_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onCLick(view, getAbsoluteAdapterPosition());
        }
    }

    public interface RecyclerViewOnClickListener{
        void onCLick(View view, int position);
    }
}