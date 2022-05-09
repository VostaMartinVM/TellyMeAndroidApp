package com.example.tellyme.view.fragment.searchFragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
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
import com.example.tellyme.adapters.SearchMoviesListAdapter;
import com.example.tellyme.model.Movie;
import com.example.tellyme.view.activity.MessageSystemActivity;
import com.example.tellyme.view.activity.SpecificMovie;
import com.example.tellyme.view.utils.DelayUtils;
import com.example.tellyme.viewModel.SeachViewModels.SearchMoviesViewModel;

import java.util.ArrayList;

public class SearchMoviesFragment extends Fragment {

    private SearchMoviesViewModel mViewModel;
    private SearchMoviesListAdapter moviesListAdapter;
    private ArrayList<Movie> movies = new ArrayList<>();
    private SearchMoviesListAdapter.RecyclerViewOnClickListener listener;
    private View view;


    public static SearchMoviesFragment newInstance() {
        return new SearchMoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_movies_fragment, container, false);
        setOnClickListener();
        //show all movies
        RecyclerView recyclerView = view.findViewById(R.id.search_movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        moviesListAdapter = new SearchMoviesListAdapter(movies, getContext(), listener);
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

    private void setOnClickListener(){
        listener = new SearchMoviesListAdapter.RecyclerViewOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                DelayUtils.delay(10, () -> getActivity().onBackPressed());
                Intent i = new Intent(getActivity(), SpecificMovie.class);
                startActivity(i);
            }
        };
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchMoviesViewModel.class);
        // TODO: Use the ViewModel
    }

}