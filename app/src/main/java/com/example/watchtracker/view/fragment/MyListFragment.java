package com.example.watchtracker.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.watchtracker.R;
import com.example.watchtracker.viewModel.ListsViewModels.MyListViewModel;

public class MyListFragment extends Fragment {

    private MyListViewModel mViewModel;

    ListView listView;

    public static MyListFragment newInstance() {
        return new MyListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_list_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyListViewModel.class);
        // TODO: Use the ViewModel
    }

}