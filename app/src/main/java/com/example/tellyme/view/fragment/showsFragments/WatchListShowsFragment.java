package com.example.tellyme.view.fragment.showsFragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tellyme.R;
import com.example.tellyme.viewModel.ShowsViewModels.WatchListShowsViewModel;

public class WatchListShowsFragment extends Fragment {

    private WatchListShowsViewModel mViewModel;

    public static WatchListShowsFragment newInstance() {
        return new WatchListShowsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.watch_list_shows_fragment, container, false);
        watchListShowItems(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WatchListShowsViewModel.class);
        // TODO: Use the ViewModel
    }

    private void watchListShowItems(View view)
    {
        String[] showName = {"kaguya", "batman", "moonknight"};
        String[] episodeName = {"ep1", "ep2", "ep3"};
        int [] showPicture = {R.mipmap.lists_background, R.mipmap.lists_background, R.mipmap.lists_background};
        RecyclerView recyclerView = view.findViewById(R.id.watch_list_shows_recycler_view);
    }

}