package com.example.tellyme.view.fragment.listsFragments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;

import com.example.tellyme.utils.FragmentUtils;
import com.example.tellyme.utils.ToolBarUtils;
import com.example.tellyme.adapters.ListsAdapter;
import com.example.tellyme.R;
import com.example.tellyme.viewModel.ListsViewModels.MyListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MyListsFragment extends Fragment {

    private View view;
    private ArrayList<String> listsNames = new ArrayList<>();
    private ListsAdapter listsAdapter;
    private ListsAdapter.RecyclerViewOnClickListener listener;
    private MyListViewModel viewModel;
    private String newListName = "";

    public static MyListsFragment newInstance() {
        return new MyListsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.list_fragment, container, false);
        setOnClickListener();

        RecyclerView recyclerView = view.findViewById(R.id.lists_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        viewModel = new ViewModelProvider(this).get(MyListViewModel.class);

        viewModel.getLists().observe(getViewLifecycleOwner(), lists ->{
            listsNames = lists;
            listsAdapter.updateLists(lists);
        });
        listsAdapter = new ListsAdapter(listsNames, viewModel ,listener);
        recyclerView.setAdapter(listsAdapter);

        addList();

        return view;

    }
    private void setOnClickListener(){
        listener = new ListsAdapter.RecyclerViewOnClickListener() {
            @Override
            public void onCLick(View view, int position) {
                FragmentManager fragmentManager = getParentFragmentManager();
                SpecificListFragment specificListFrag = new SpecificListFragment();
                FragmentUtils.changeFragmentWithArgument(specificListFrag, R.id.list_dummy_fragment, "ldf", fragmentManager,
                        "loadedList",   listsNames.get(position));
            }
        };
    }

    private void addList()
    {
        FloatingActionButton addListButton = view.findViewById(R.id.add_list_button);
        addListButton.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("List Name");

            final EditText input = new EditText(getContext());
            builder.setView(input);

            builder.setPositiveButton("Done", null);
            builder.setNegativeButton("Cancel", null);

            final AlertDialog alertDialog = builder.create();
            alertDialog.setOnShowListener(dialogInterface -> {
                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(view -> {
                    if (input.getText().toString().trim().matches(""))
                    {
                        input.setError("Cannot be empty");
                    }
                    else if (listsNames.contains(input.getText().toString())){
                        input.setError("Cannot be named " + input.getText().toString());
                    }
                    else {
                        newListName = input.getText().toString();
                        dialogInterface.dismiss();
                        listsAdapter.addList(newListName);
                    }
                });
                Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeButton.setOnClickListener(view -> {
                    dialogInterface.dismiss();
                });
            });
            alertDialog.show();
        });
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