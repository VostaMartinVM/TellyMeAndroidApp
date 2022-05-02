package com.example.watchtracker.view.fragment.listsFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchtracker.R;
import com.example.watchtracker.adapters.SpecificListAdapter;
import com.example.watchtracker.model.DummyData;


import java.util.ArrayList;

public class SpecificListFragment extends Fragment {

    private ArrayList<DummyData> dummyData = new ArrayList<>();
    private SpecificListAdapter specificListAdapter;

    public static SpecificListFragment newInstance() {
        return new SpecificListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.specific_list_fragment, container, false);

        dummyData.add(new DummyData("Toaru tagaku no Railgun", R.mipmap.lists_background));
        dummyData.add(new DummyData("Toaru tagaku no Accelerator", R.mipmap.lists_background));
        dummyData.add(new DummyData("Toaru majutsu no Index", R.mipmap.lists_background));


        RecyclerView recyclerView = view.findViewById(R.id.specific_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        specificListAdapter = new SpecificListAdapter(dummyData);
        recyclerView.setAdapter(specificListAdapter);

        return view;


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}