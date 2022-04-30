package com.example.watchtracker.view.fragment.moviesFragments;

import androidx.fragment.app.FragmentManager;
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
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.watchtracker.R;
import com.example.watchtracker.adapters.MovieWatchListListAdapter;
import com.example.watchtracker.model.Movie;
import com.example.watchtracker.view.fragment.showsFragments.SpecificShowFragment;
import com.example.watchtracker.view.utils.FragmentUtils;
import com.example.watchtracker.viewModel.MoviesViewModels.WatchListMoviesViewModel;

import java.util.ArrayList;

public class WatchListMoviesFragment extends Fragment {

    private WatchListMoviesViewModel mViewModel;



    public static WatchListMoviesFragment newInstance() {
        return new WatchListMoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.watch_list_movies_fragment, container, false);
        RecyclerView movieWatchList;
        MovieWatchListListAdapter movieWatchListListAdapter;

        movieWatchList = view.findViewById(R.id.watch_list_movies);
        movieWatchList.setHasFixedSize(true);
        movieWatchList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("TrashTaste", R.mipmap.lists_background));
        movieWatchListListAdapter = new MovieWatchListListAdapter(movies);
        movieWatchList.setAdapter(movieWatchListListAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WatchListMoviesViewModel.class);
        // TODO: Use the ViewModel
    }

}