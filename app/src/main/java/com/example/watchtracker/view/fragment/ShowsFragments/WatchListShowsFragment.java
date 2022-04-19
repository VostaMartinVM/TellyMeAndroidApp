package com.example.watchtracker.view.fragment.ShowsFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.viewModel.ShowsFragments.WatchListShowsViewModel;

public class WatchListShowsFragment extends Fragment {

    private WatchListShowsViewModel mViewModel;

    public static WatchListShowsFragment newInstance() {
        return new WatchListShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.watch_list_shows_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WatchListShowsViewModel.class);
        // TODO: Use the ViewModel
    }

}