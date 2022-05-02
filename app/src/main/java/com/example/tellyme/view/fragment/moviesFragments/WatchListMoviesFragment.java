package com.example.tellyme.view.fragment.moviesFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tellyme.R;
import com.example.tellyme.model.DummyData;
import com.example.tellyme.viewModel.MoviesViewModels.WatchListMoviesViewModel;

import java.util.ArrayList;

public class WatchListMoviesFragment extends Fragment {

    private ArrayList<DummyData> dummyData = new ArrayList<>();
    private WatchListMoviesViewModel mViewModel;



    public static WatchListMoviesFragment newInstance() {
        return new WatchListMoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        dummyData.add(new DummyData("StarWars", R.mipmap.lists_background));
        dummyData.add(new DummyData("F&F", R.mipmap.lists_background));
        dummyData.add(new DummyData("fakt uz nevim", R.mipmap.lists_background));

        View view = inflater.inflate(R.layout.watch_list_movies_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.watch_list_movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WatchListMoviesViewModel.class);
        // TODO: Use the ViewModel
    }

}