package com.example.watchtracker.view.fragment.MoviesFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.viewModel.MoviesFragments.UpcomingMoviesViewModel;

public class UpcomingMoviesFragment extends Fragment {

    private UpcomingMoviesViewModel mViewModel;

    public static UpcomingMoviesFragment newInstance() {
        return new UpcomingMoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upcoming_movies_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UpcomingMoviesViewModel.class);
        // TODO: Use the ViewModel
    }

}