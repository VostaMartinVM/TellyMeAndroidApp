package com.example.watchtracker.view.fragment.listsFragments;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.watchtracker.R;
import com.example.watchtracker.view.fragment.searchFragments.SearchFragment;
import com.example.watchtracker.view.fragment.showsFragments.SpecificShowFragment;
import com.example.watchtracker.view.utils.FragmentUtils;
import com.example.watchtracker.adapters.ListsBaseAdapter;
import com.example.watchtracker.viewModel.ListsViewModels.SpecificListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SpecificListFragment extends Fragment {

    private SpecificListViewModel mViewModel;
    private Boolean searchOpened = false;

    public static SpecificListFragment newInstance() {
        return new SpecificListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.specific_list_fragment, container, false);
        listsItems(view);
        addItemToListButtonFunctionality(view);
        return view;
    }

    private void listsItems(View view)
    {
        String[] listItems = {"Toaru Majutsu no Index", "Toaru Kagaku no Accelerator", "Toaru Kagaku no Railgun"};
        int [] listImages = {R.mipmap.lists_background, R.mipmap.lists_background, R.mipmap.lists_background};
        ListView listView = (ListView) view.findViewById(R.id.specific_list);
        ListsBaseAdapter listsBaseAdapter = new ListsBaseAdapter(getActivity().getApplicationContext(), listItems, listImages);
        listView.setAdapter(listsBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getParentFragmentManager();
                SpecificShowFragment specificShowFragment = SpecificShowFragment.newInstance();
                FragmentUtils.changeFragment(specificShowFragment, R.id.list_item_dummy_fragment, "lidf", fragmentManager);
            }
        });

    }

    private void addItemToListButtonFunctionality(View view)
    {
        FloatingActionButton addItemToListButton = view.findViewById(R.id.add_item_to_list_button);
        addItemToListButton.setOnClickListener((tempView) -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            SearchFragment searchFragment = SearchFragment.newInstance();
            FragmentUtils.changeFragmentWithAnimation(searchFragment, R.id.searchFragmentLayout, "mf", fragmentManager, R.anim.enter_from_bottom
                    , R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom);
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SpecificListViewModel.class);
        // TODO: Use the ViewModel
    }

}