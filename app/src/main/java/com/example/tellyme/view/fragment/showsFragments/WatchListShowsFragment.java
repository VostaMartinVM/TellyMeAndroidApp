package com.example.tellyme.view.fragment.showsFragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tellyme.R;
import com.example.tellyme.adapters.ShowsWatchListAdapter;
import com.example.tellyme.model.DummyData2;
import com.example.tellyme.model.Show;
import com.example.tellyme.view.activity.MessageSystemActivity;
import com.example.tellyme.view.activity.SpecificShow;
import com.example.tellyme.viewModel.ShowsViewModels.WatchListShowsViewModel;

import java.util.ArrayList;

public class WatchListShowsFragment extends Fragment {

    private WatchListShowsViewModel watchListShowsViewModel;
    private ArrayList<Show> shows = new ArrayList<>();
    private ShowsWatchListAdapter showsWatchListAdapter;
    private ShowsWatchListAdapter.RecyclerViewOnClickListener listener;
    private View view;

    public static WatchListShowsFragment newInstance() {
        return new WatchListShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.watch_list_shows_fragment, container, false);
        setOnClickListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = view.findViewById(R.id.watch_list_shows_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        showsWatchListAdapter = new ShowsWatchListAdapter(shows, listener);
        recyclerView.setAdapter(showsWatchListAdapter);

        watchListShowsViewModel = new ViewModelProvider(this).get(WatchListShowsViewModel.class);
        watchListShowsViewModel.getShows().observe(getViewLifecycleOwner(), showList -> {
            if (showList != null)
            {
                shows = showList;
                showsWatchListAdapter.updateShows(showList);
            }
        });
    }

    private void setOnClickListener(){
        listener = new ShowsWatchListAdapter.RecyclerViewOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getActivity(), SpecificShow.class);
                i.putExtra("loadedShow", shows.get(position));
                startActivity(i);
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}