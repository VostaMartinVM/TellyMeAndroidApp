package com.example.watchtracker.view.fragment.showsFragments;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.view.utils.ToolBarUtils;
import com.example.watchtracker.viewModel.ShowsFragments.ShowsViewModel;
import com.example.watchtracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShowsFragment extends Fragment {

    private ShowsViewModel mViewModel;

    public static ShowsFragment newInstance() {
        return new ShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shows_fragment, container, false);
        return view;
    }

    @Override
    public void onResume() {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ToolBarUtils.changeUserIcon(-1, toolbar);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShowsViewModel.class);
        // TODO: Use the ViewModel
    }

}