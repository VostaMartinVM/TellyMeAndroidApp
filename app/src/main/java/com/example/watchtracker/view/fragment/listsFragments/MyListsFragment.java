package com.example.watchtracker.view.fragment.listsFragments;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
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
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.watchtracker.model.DummyData;
import com.example.watchtracker.view.utils.FragmentUtils;
import com.example.watchtracker.view.utils.ToolBarUtils;
import com.example.watchtracker.adapters.ListsAdapter;
import com.example.watchtracker.viewModel.ListsViewModels.MyListViewModel;
import com.example.watchtracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MyListsFragment extends Fragment {

    private ArrayList<DummyData> dummyData = new ArrayList<>();
    private ListsAdapter listsAdapter;

    public static MyListsFragment newInstance() {
        return new MyListsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        dummyData.add(new DummyData("Shows", R.mipmap.lists_background));
        dummyData.add(new DummyData("Movies", R.mipmap.lists_background));
        dummyData.add(new DummyData("Favorites", R.mipmap.lists_background));

        View view = inflater.inflate(R.layout.list_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.lists_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);




        listsAdapter = new ListsAdapter(dummyData);
        recyclerView.setAdapter(listsAdapter);

        return view;

    }

    @Override
    public void onResume() {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ToolBarUtils.changeUserIcon(-1, toolbar);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}