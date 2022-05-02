package com.example.tellyme.view.fragment.showsFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tellyme.R;
import com.example.tellyme.viewModel.ShowsViewModels.EpisodesShowViewModel;

public class EpisodesShowFragment extends Fragment {

    private EpisodesShowViewModel mViewModel;

    public static EpisodesShowFragment newInstance() {
        return new EpisodesShowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.episodes_show_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EpisodesShowViewModel.class);
        // TODO: Use the ViewModel
    }

}