package com.example.tellyme.view.fragment.searchFragments;

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
import android.widget.EditText;

import com.example.tellyme.R;
import com.example.tellyme.adapters.SearchedProgramsAdapter;
import com.example.tellyme.adapters.SpecificListAdapter;
import com.example.tellyme.model.Movie;
import com.example.tellyme.model.Show;
import com.example.tellyme.view.activity.SpecificMovie;
import com.example.tellyme.view.activity.SpecificShow;
import com.example.tellyme.viewModel.ListsViewModels.SpecificListViewModel;
import com.example.tellyme.viewModel.SeachViewModels.SearchedProgramsViewModel;

import java.util.ArrayList;

public class SearchedPrograms extends Fragment {

    private SearchedProgramsViewModel mViewModel;
    private View view;
    private Bundle args;
    private ArrayList<Object> tvPrograms;
    private SearchedProgramsAdapter searchedProgramsAdapter;
    private SearchedProgramsAdapter.RecyclerViewOnClickListener listener;

    public static SearchedPrograms newInstance() {
        return new SearchedPrograms();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.searched_programs_fragment, container, false);
        setOnClickListener();

        args = getArguments();
        RecyclerView recyclerView = view.findViewById(R.id.searched_programs_recycled_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        tvPrograms = new ArrayList<>();

        EditText searchText = getActivity().findViewById(R.id.search_edit_text);

        mViewModel = new ViewModelProvider(this).get(SearchedProgramsViewModel.class);
        mViewModel.getSearchedPrograms(searchText.getText().toString()).observe(getViewLifecycleOwner(), programList ->{
            for (int i = 0; i < programList.size(); i++) {
                if (!tvPrograms.contains(programList.get(i)))
                {
                    tvPrograms.add(programList.get(i));
                }
            }
            searchedProgramsAdapter.updateTvPrograms(tvPrograms);
        });

        searchedProgramsAdapter = new SearchedProgramsAdapter(tvPrograms, listener);
        recyclerView.setAdapter(searchedProgramsAdapter);

        return view;
    }

    private void setOnClickListener(){
        listener = new SearchedProgramsAdapter.RecyclerViewOnClickListener() {
            @Override
            public void onClick(View view, int position) {
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
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchedProgramsViewModel.class);
        // TODO: Use the ViewModel
    }

}