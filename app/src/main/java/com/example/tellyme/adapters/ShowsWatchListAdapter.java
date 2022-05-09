package com.example.tellyme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tellyme.R;
import com.example.tellyme.model.DummyData2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ShowsWatchListAdapter extends RecyclerView.Adapter<ShowsWatchListAdapter.ViewHolder>{

    private ArrayList<DummyData2> dummyData2;
    private RecyclerViewOnClickListener listener;


    public ShowsWatchListAdapter(ArrayList<DummyData2> dummyData2, RecyclerViewOnClickListener listener) {
        this.dummyData2 = dummyData2;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.shows_watch_list_item, parent, false);
        int height = parent.getHeight()/8;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowsWatchListAdapter.ViewHolder holder, int position) {
        holder.showTitle.setText(dummyData2.get(position).getName());
        holder.episodeTitle.setText(dummyData2.get(position).getName2());
        holder.showImage.setImageResource(dummyData2.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return dummyData2.size();
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

