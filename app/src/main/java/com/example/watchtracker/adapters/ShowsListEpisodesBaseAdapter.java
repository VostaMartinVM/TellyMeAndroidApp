package com.example.watchtracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.watchtracker.R;

public class ShowsListEpisodesBaseAdapter extends BaseAdapter{


    Context context;
    String showName[];
    String episodeName[];
    int showPicture[];
    LayoutInflater inflater;


    public ShowsListEpisodesBaseAdapter(Context context, String[] showName, String[] episodeName, int[] showPicture) {
        this.showName = showName;
        this.episodeName = episodeName;
        this.showPicture = showPicture;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return showName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_upcoming_watch_list_shows_item, null);
        TextView showNameTextView = (TextView) view.findViewById(R.id.show_name);
        TextView episodeNameTextView = (TextView) view.findViewById(R.id.episode_name);
        ImageView imageView = (ImageView) view.findViewById(R.id.show_image);

        showNameTextView.setText(showName[i]);
        episodeNameTextView.setText(episodeName[i]);
        imageView.setImageResource(showPicture[i]);
        return view;
    }
}

