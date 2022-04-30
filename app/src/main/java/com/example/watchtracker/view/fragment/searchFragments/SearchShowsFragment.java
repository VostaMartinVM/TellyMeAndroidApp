package com.example.watchtracker.view.fragment.searchFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.adapters.SearchShowsListAdapter;
import com.example.watchtracker.model.Show;
import com.example.watchtracker.viewModel.SeachViewModels.SearchShowsViewModel;

import java.util.ArrayList;

public class SearchShowsFragment extends Fragment {

    private SearchShowsViewModel mViewModel;
    private ArrayList<Show> shows = new ArrayList<>();
    private SearchShowsListAdapter showsListAdapter;
    private View view;

    public static SearchShowsFragment newInstance() {
        return new SearchShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_shows_fragment, container, false);

        //show all shows
        RecyclerView recyclerView = view.findViewById(R.id.search_shows_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        showsListAdapter = new SearchShowsListAdapter(shows, getContext());
        recyclerView.setAdapter(showsListAdapter);

        mViewModel = new ViewModelProvider(this).get(SearchShowsViewModel.class);
        mViewModel.getShows().observe(getViewLifecycleOwner(), showList -> {
            if(showList != null) {
                shows = showList;
                showsListAdapter.setShows(showList);
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchShowsViewModel.class);
        // TODO: Use the ViewModel
    }

}