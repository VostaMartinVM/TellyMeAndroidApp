package com.example.watchtracker.view.fragment.searchFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.viewModel.SeachViewModel.SearchShowsViewModel;

public class SearchShowsFragment extends Fragment {

    private SearchShowsViewModel mViewModel;

    public static SearchShowsFragment newInstance() {
        return new SearchShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_shows_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchShowsViewModel.class);
        // TODO: Use the ViewModel
    }

}