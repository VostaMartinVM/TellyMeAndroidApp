package com.example.tellyme.view.fragment.homeFragments;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tellyme.view.activity.MessageSystemActivity;
import com.example.tellyme.utils.FragmentUtils;
import com.example.tellyme.utils.ToolBarUtils;
import com.example.tellyme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

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

    @Override
    public void onResume() {
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);
        ToolBarUtils.changeUserIcon(-1, toolbar);
        super.onResume();
    }

    private void startMessageActivity()
    {
        Intent i = new Intent(getActivity(), MessageSystemActivity.class);
        startActivity(i);
    }

    private void messageButtonFunctionality(View view)
    {
        FloatingActionButton messageButton = view.findViewById(R.id.home_message_button);
        messageButton.setOnClickListener((tempView) -> startMessageActivity());
    }

    private void postButtonFunctionality(View view)
    {
        Button openPostWindow = view.findViewById(R.id.openPostWindow);
        openPostWindow.setOnClickListener((tempView) -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            CreatePostFragment createPostFragment = CreatePostFragment.newInstance();
            FragmentUtils.changeFragmentWithAnimation(createPostFragment, R.id.post_dummy_fragment, "pf", fragmentManager, 0, 0
                    , R.anim.post_enter_from_bottom, R.anim.post_enter_from_top);
        });
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}