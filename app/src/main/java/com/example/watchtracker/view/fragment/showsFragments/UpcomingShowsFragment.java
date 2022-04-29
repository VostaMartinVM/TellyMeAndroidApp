package com.example.watchtracker.view.fragment.showsFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.viewModel.ShowsViewModels.UpcomingShowsViewModel;

public class UpcomingShowsFragment extends Fragment {

    private UpcomingShowsViewModel mViewModel;

    public static UpcomingShowsFragment newInstance() {
        return new UpcomingShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upcoming_shows_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UpcomingShowsViewModel.class);
        // TODO: Use the ViewModel
    }

}