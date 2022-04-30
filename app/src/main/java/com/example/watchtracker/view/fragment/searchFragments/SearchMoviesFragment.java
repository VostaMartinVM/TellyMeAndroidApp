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
import com.example.watchtracker.adapters.SearchMoviesListAdapter;
import com.example.watchtracker.adapters.SearchShowsListAdapter;
import com.example.watchtracker.model.Movie;
import com.example.watchtracker.viewModel.SeachViewModels.SearchMoviesViewModel;
import com.example.watchtracker.viewModel.SeachViewModels.SearchShowsViewModel;

import java.util.ArrayList;

public class SearchMoviesFragment extends Fragment {

    private SearchMoviesViewModel mViewModel;
    private SearchMoviesListAdapter moviesListAdapter;
    private ArrayList<Movie> movies = new ArrayList<>();
    private View view;


    public static SearchMoviesFragment newInstance() {
        return new SearchMoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_movies_fragment, container, false);
        //show all shows
        RecyclerView recyclerView = view.findViewById(R.id.search_movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        moviesListAdapter = new SearchMoviesListAdapter(movies);
        recyclerView.setAdapter(moviesListAdapter);

        mViewModel = new ViewModelProvider(this).get(SearchMoviesViewModel.class);
        mViewModel.getMovies().observe(getViewLifecycleOwner(), movieList -> {
            if(movieList != null) {
                movies = movieList;
                moviesListAdapter.setMovies(movieList);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchMoviesViewModel.class);
        // TODO: Use the ViewModel
    }

}