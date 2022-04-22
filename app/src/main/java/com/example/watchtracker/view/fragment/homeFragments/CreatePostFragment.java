package com.example.watchtracker.view.fragment.homeFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.viewModel.HomeViewModels.CreatePostViewModel;

public class CreatePostFragment extends Fragment {

    private CreatePostViewModel mViewModel;

    public static CreatePostFragment newInstance() {
        return new CreatePostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_post_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);
        // TODO: Use the ViewModel
    }

}