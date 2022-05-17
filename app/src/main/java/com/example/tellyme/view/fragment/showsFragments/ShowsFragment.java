package com.example.tellyme.view.fragment.showsFragments;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tellyme.utils.ToolBarUtils;
import com.example.tellyme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ShowsFragment extends Fragment {

    public static ShowsFragment newInstance() {
        return new ShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shows_fragment, container, false);
    }

    @Override
    public void onResume() {
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);
        ToolBarUtils.changeUserIcon(-1, toolbar);
        super.onResume();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}