package com.example.watchtracker.view.fragment.userFragments;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.view.utils.ToolBarUtils;
import com.example.watchtracker.viewModel.UserFragments.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserFragment extends Fragment {

    private UserViewModel mViewModel;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment, container, false);
    }

    @Override
    public void onResume() {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ToolBarUtils.changeUserIcon(-8945409, toolbar);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        // TODO: Use the ViewModel
    }

}