package com.example.watchtracker.view.fragment.moviesFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.viewModel.MoviesViewModels.SpecificMovieViewModel;

public class SpecificMovieFragment extends Fragment {

    private SpecificMovieViewModel mViewModel;

    public static SpecificMovieFragment newInstance() {
        return new SpecificMovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.specific_movie_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SpecificMovieViewModel.class);
        // TODO: Use the ViewModel
    }

}