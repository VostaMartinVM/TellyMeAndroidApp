package com.example.tellyme.view.fragment.searchFragments;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import com.example.tellyme.adapters.SearchShowsListAdapter;
import com.example.tellyme.model.Show;
import com.example.tellyme.view.activity.MessageSystemActivity;
import com.example.tellyme.view.activity.SpecificMovie;
import com.example.tellyme.view.activity.SpecificShow;
import com.example.tellyme.view.utils.DelayUtils;
import com.example.tellyme.viewModel.SeachViewModels.SearchShowsViewModel;

import java.util.ArrayList;

public class SearchShowsFragment extends Fragment {

    private SearchShowsViewModel mViewModel;
    private ArrayList<Show> shows = new ArrayList<>();
    private SearchShowsListAdapter showsListAdapter;
    private SearchShowsListAdapter.RecyclerViewOnClickListener listener;
    private View view;

    public static SearchShowsFragment newInstance() {
        return new SearchShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_shows_fragment, container, false);
        setOnClickListener();
        //show all shows
        RecyclerView recyclerView = view.findViewById(R.id.search_shows_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        showsListAdapter = new SearchShowsListAdapter(shows, getContext(), listener);
        recyclerView.setAdapter(showsListAdapter);

        mViewModel = new ViewModelProvider(this).get(SearchShowsViewModel.class);
        mViewModel.getShows().observe(getViewLifecycleOwner(), showList -> {
            if(showList != null) {
                shows = showList;
                showsListAdapter.setShows(showList);
            }
        });
        return view;
    }
    private void setOnClickListener()
    {
        listener = new SearchShowsListAdapter.RecyclerViewOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                FragmentActivity activity = getActivity();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                Intent i = new Intent(getActivity(), SpecificShow.class);
                startActivity(i);
                fragmentManager.popBackStackImmediate();
                fragmentManager.popBackStackImmediate();
            }
        };
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchShowsViewModel.class);
        // TODO: Use the ViewModel
    }

}