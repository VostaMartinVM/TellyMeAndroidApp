package com.example.watchtracker.view.fragment.ListsFragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.watchtracker.view.fragment.HomeFragments.CreatePostFragment;
import com.example.watchtracker.viewModel.ListsViewModel.MyListViewModel;
import com.example.watchtracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyListsFragment extends Fragment {

    private MyListViewModel mViewModel;

    ListView listView;

    public static MyListsFragment newInstance() {
        return new MyListsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_list_fragment, container, false);
        addListButtonFunctionality(view);
        return view;

    }

    private void addListButtonFunctionality(View view)
    {
        FloatingActionButton addListButton = view.findViewById(R.id.add_list_button);
        addListButton.setOnClickListener((tempView) -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AddListToListsFragment addListToListsFragment = AddListToListsFragment.newInstance();
            fragmentTransaction.replace(R.id.list_dummy_fragment, addListToListsFragment, "cl");
            fragmentTransaction.addToBackStack("cl");
            fragmentTransaction.commit();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyListViewModel.class);
        // TODO: Use the ViewModel
    }

}