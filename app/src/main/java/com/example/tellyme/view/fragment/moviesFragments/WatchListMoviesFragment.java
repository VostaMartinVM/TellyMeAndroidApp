package com.example.tellyme.view.fragment.moviesFragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tellyme.R;
import com.example.tellyme.adapters.MovieWatchListListAdapter;
import com.example.tellyme.model.Movie;
import com.example.tellyme.view.activity.SpecificMovie;
import com.example.tellyme.viewModel.MoviesViewModels.WatchListMoviesViewModel;

import java.util.ArrayList;

public class WatchListMoviesFragment extends Fragment {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieWatchListListAdapter movieWatchListListAdapter;
    private MovieWatchListListAdapter.RecyclerViewOnClickListener listener;
    private View view;


    public static WatchListMoviesFragment newInstance() {
        return new WatchListMoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.watch_list_movies_fragment, container, false);
        setOnClickListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = view.findViewById(R.id.watch_list_movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        movieWatchListListAdapter = new MovieWatchListListAdapter(movies, listener);
        recyclerView.setAdapter(movieWatchListListAdapter);

        WatchListMoviesViewModel watchListMoviesViewModel = new ViewModelProvider(this).get(WatchListMoviesViewModel.class);
        watchListMoviesViewModel.setContext(getContext());
        watchListMoviesViewModel.getMovies().observe(getViewLifecycleOwner(), movieList -> {
            if(movieList != null)
            {
                movies = movieList;
                movieWatchListListAdapter.updateMovies(movieList);
            }
        });
    }

    private void setOnClickListener(){
        listener = (view, position) -> {
            Intent i = new Intent(getActivity(), SpecificMovie.class);
            i.putExtra("loadedMovie", movies.get(position));
            startActivity(i);
        };
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}