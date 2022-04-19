package com.example.watchtracker.view.fragment.SearchFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.watchtracker.R;
import com.example.watchtracker.view.activity.MainActivity;
import com.example.watchtracker.viewModel.SeachViewModel.SearchViewModel;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        FrameLayout searchLayout = view.findViewById(R.id.search_frame);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                searchLayout.setScaleX(20);
                searchLayout.setScaleY(20);
            }
        }, 395);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        // TODO: Use the ViewModel
    }

}