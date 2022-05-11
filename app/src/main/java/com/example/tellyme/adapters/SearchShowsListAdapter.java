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
import com.example.tellyme.repository.ShowRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchShowsListAdapter extends RecyclerView.Adapter<SearchShowsListAdapter.ViewHolder> {

    private ArrayList<Show> shows;
    private ShowRepository showRepository;
    private RecyclerViewOnClickListener listener;

    public SearchShowsListAdapter (ArrayList<Show> shows, Context context, RecyclerViewOnClickListener listener){
        this.shows = shows;
        this.listener = listener;
        showRepository = ShowRepository.getInstance();
        showRepository.setContext(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_list_item, parent, false);
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
        holder.showButton.setOnClickListener(view -> {
            showRepository.addShowToList("Watch list", shows.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public void setShows(ArrayList<Show> shows) {
        this.shows = shows;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView showTitle;
        private final ShapeableImageView showImage;
        private final FloatingActionButton showButton;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.showTitle = itemView.findViewById(R.id.search_title);
            this.showImage = itemView.findViewById(R.id.user_image);
            this.showButton = itemView.findViewById(R.id.search_add_button);
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
