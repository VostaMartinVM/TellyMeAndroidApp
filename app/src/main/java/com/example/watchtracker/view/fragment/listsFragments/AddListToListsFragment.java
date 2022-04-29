package com.example.watchtracker.view.fragment.listsFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.viewModel.ListsViewModels.AddListToListsViewModel;

public class AddListToListsFragment extends Fragment {

    private AddListToListsViewModel mViewModel;

    public static AddListToListsFragment newInstance() {
        return new AddListToListsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_list_to_lists_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddListToListsViewModel.class);
        // TODO: Use the ViewModel
    }

}