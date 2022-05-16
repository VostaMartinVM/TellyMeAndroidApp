package com.example.tellyme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tellyme.R;
import com.example.tellyme.viewModel.ListsViewModels.MyListViewModel;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {

    private ArrayList<String> listsNames;
    private RecyclerViewOnClickListener listener;
    private MyListViewModel myListViewModel;

    public ListsAdapter (ArrayList<String> listsNames, MyListViewModel myListViewModel, RecyclerViewOnClickListener listener){
        this.listsNames = listsNames;
        this.listener = listener;
        this.myListViewModel = myListViewModel;
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
        holder.listTitle.setText(listsNames.get(position));
    }

    @Override
    public int getItemCount() {
        return listsNames.size();
    }

    public void updateLists(ArrayList<String> listsNames){
        this.listsNames = listsNames;
        notifyDataSetChanged();
    }


    public void addList(String listName)
    {
        listsNames.add(listName);
        notifyDataSetChanged();
        myListViewModel.addList(listName);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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