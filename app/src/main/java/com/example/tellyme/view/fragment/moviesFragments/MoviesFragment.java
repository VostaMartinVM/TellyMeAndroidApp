package com.example.tellyme.view.fragment.moviesFragments;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tellyme.utils.ToolBarUtils;
import com.example.tellyme.viewModel.MoviesViewModels.MoviesViewModel;
import com.example.tellyme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoviesFragment extends Fragment {

    private MoviesViewModel mViewModel;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movies_fragment, container, false);

        return view;
    }

    @Override
    public void onResume() {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(4).setChecked(true);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ToolBarUtils.changeUserIcon(-1, toolbar);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        // TODO: Use the ViewModel
    }

}