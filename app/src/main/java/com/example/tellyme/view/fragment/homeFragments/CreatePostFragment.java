package com.example.tellyme.view.fragment.homeFragments;

import androidx.fragment.app.FragmentManager;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.tellyme.R;
import com.example.tellyme.view.fragment.searchFragments.SearchFragment;
import com.example.tellyme.utils.DelayUtils;
import com.example.tellyme.utils.FragmentUtils;
import com.example.tellyme.utils.HeightProperty;
import com.example.tellyme.utils.KeyboardUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreatePostFragment extends Fragment {

    private View view;

    public static CreatePostFragment newInstance() {
        return new CreatePostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_post_fragment, container, false);
        return view;
    }

    @Override
    public void onResume() {
        //focusing on edit text
        EditText postEditText = view.findViewById(R.id.post_edit_text);
        postEditText.setVisibility(View.GONE);
        DelayUtils.delay(10, () -> {
            postEditText.setVisibility(View.VISIBLE);
            postEditText.requestFocus();
        });

        //hide keyboard
        FrameLayout homeLayout = requireActivity().findViewById(R.id.home_frame_layout);
        homeLayout.setOnClickListener(view1 -> {
            if (getContext() != null)
            {
                KeyboardUtils.hideSoftKeyboard(getContext(), view);
            }
        });

        //bringing up keyboard
        KeyboardUtils.showSoftKeyboard(getActivity(), postEditText);
        //animation
        int postDefaultHeight = postEditText.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofInt(postEditText, new HeightProperty(), postDefaultHeight, 1000);
        animator.setDuration(500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

        //delete functionality
        ImageButton deleteButton = view.findViewById(R.id.delete_post_button);
        deleteButton.setOnClickListener(view -> requireActivity().onBackPressed());

        //adding button functionality
        FloatingActionButton addToPostButton = view.findViewById(R.id.add_movie_to_post_button);
        addToPostButton.setOnClickListener(view -> {
            SearchFragment searchFragment = SearchFragment.newInstance();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentUtils.changeFragmentWithAnimation(searchFragment, R.id.search_fragment_layout, "sf", fragmentManager, R.anim.enter_from_bottom
                    , R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom);
        });
        super.onResume();
    }

    @Override
    public void onPause() {
        ImageButton deleteButton = view.findViewById(R.id.delete_post_button);
        FloatingActionButton addToPostButton = view.findViewById(R.id.add_movie_to_post_button);
        FloatingActionButton sendPostButton = view.findViewById(R.id.send_post_button);
        deleteButton.setVisibility(View.GONE);
        addToPostButton.setVisibility(View.GONE);
        sendPostButton.setVisibility(View.GONE);
        super.onPause();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }
}