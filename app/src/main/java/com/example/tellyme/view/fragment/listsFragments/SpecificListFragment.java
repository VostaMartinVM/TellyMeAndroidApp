package com.example.tellyme.view.fragment.listsFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tellyme.R;
import com.example.tellyme.adapters.SpecificListAdapter;
import com.example.tellyme.model.Movie;
import com.example.tellyme.model.Show;
import com.example.tellyme.utils.FragmentUtils;
import com.example.tellyme.view.activity.SpecificMovie;
import com.example.tellyme.view.activity.SpecificShow;
import com.example.tellyme.view.fragment.searchFragments.SearchFragment;
import com.example.tellyme.viewModel.ListsViewModels.SpecificListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class SpecificListFragment extends Fragment {

    private View view;

    private ArrayList<Object> tvPrograms;
    private SpecificListAdapter specificListAdapter;
    private SpecificListAdapter.RecyclerViewOnClickListener listener;
    private Bundle args;

    public static SpecificListFragment newInstance() {
        return new SpecificListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.specific_list_fragment, container, false);
        setOnClickListener();

        args = getArguments();
        RecyclerView recyclerView = view.findViewById(R.id.specific_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        tvPrograms = new ArrayList<>();

        SpecificListViewModel viewModel = new ViewModelProvider(this).get(SpecificListViewModel.class);
        viewModel.setContext(getContext());
        viewModel.getTVPrograms(args.getString("loadedList")).observe(getViewLifecycleOwner(), programList ->{
            for (int i = 0; i < programList.size(); i++) {
                if (!tvPrograms.contains(programList.get(i)))
                {
                    tvPrograms.add(programList.get(i));
                }
            }
            specificListAdapter.updateTVPrograms(tvPrograms);
        });

        specificListAdapter = new SpecificListAdapter(tvPrograms, listener);
        recyclerView.setAdapter(specificListAdapter);

        addTvProgramToList();

        return view;

    }

    private void setOnClickListener(){
        listener = (view, position) -> {
            Intent i;
            if(tvPrograms.get(position) instanceof Show)
            {
                i = new Intent(getActivity(), SpecificShow.class);
                i.putExtra("loadedShow", (Show)tvPrograms.get(position));
            }
            else {
                i = new Intent(getActivity(), SpecificMovie.class);
                i.putExtra("loadedMovie", (Movie)tvPrograms.get(position));
            }
            startActivity(i);
        };
    }

    private void addTvProgramToList()
    {
        FloatingActionButton addTvProgram = view.findViewById(R.id.add_tv_program_to_list_button);
        addTvProgram.setOnClickListener(view -> {
            SearchFragment searchFragment = new SearchFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentUtils.changeFragmentWithAnimationAndArgs(searchFragment, R.id.search_fragment_layout, "sf",
                    fragmentManager, R.anim.enter_from_bottom, R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom,
                    "enteredFrom", args.get("loadedList"));
        });
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use. the ViewModel
    }

}