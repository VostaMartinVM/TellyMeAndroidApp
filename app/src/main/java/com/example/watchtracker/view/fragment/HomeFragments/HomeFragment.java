package com.example.watchtracker.view.fragment.HomeFragments;

import androidx.annotation.ColorInt;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.watchtracker.view.activity.MainActivity;
import com.example.watchtracker.view.activity.MessageSystem;
import com.example.watchtracker.view.fragment.ListsFragments.AddListToListsFragment;
import com.example.watchtracker.viewModel.HomeViewModels.HomeViewModel;
import com.example.watchtracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        postButtonFunctionality(view);
        messageButtonFunctionality(view);
        return view;
    }

    private void startMessageActivity()
    {
        Intent i = new Intent(getActivity(), MessageSystem.class);
        startActivity(i);
    }

    private void messageButtonFunctionality(View view)
    {
        FloatingActionButton messageButton = view.findViewById(R.id.home_message_button);
        messageButton.setOnClickListener((tempView) -> {
            startMessageActivity();
        });
    }

    private void postButtonFunctionality(View view)
    {
        Button openPostWindow = view.findViewById(R.id.openPostWindow);
        openPostWindow.setOnClickListener((tempView) -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CreatePostFragment createPostFragment = CreatePostFragment.newInstance();
            fragmentTransaction.replace(R.id.post_dummy_fragment, createPostFragment, "hf");
            fragmentTransaction.addToBackStack("hf");
            fragmentTransaction.commit();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}