package com.example.watchtracker.view.fragment.searchFragments;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.watchtracker.R;
import com.example.watchtracker.view.utils.DelayUtils;
import com.example.watchtracker.view.utils.FragmentUtils;
import com.example.watchtracker.view.utils.KeyboardUtils;
import com.example.watchtracker.viewModel.SeachViewModel.SearchShowsViewModel;
import com.example.watchtracker.viewModel.SeachViewModel.SearchViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;

    private EditText searchText;
    private ImageView searchImage;
    private ImageButton showsButton;
    private ImageButton moviesButton;
    private ImageButton peopleButton;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        FrameLayout searchLayout = view.findViewById(R.id.search_frame);
        searchText = view.findViewById(R.id.search_edit_text);
        searchImage = view.findViewById(R.id.search_image_view);
        showsButton = view.findViewById(R.id.search_shows_button);
        moviesButton = view.findViewById(R.id.search_movies_button);
        peopleButton = view.findViewById(R.id.search_people_button);
        view.post(() -> {
            searchText.setVisibility(View.GONE);
            searchImage.setVisibility(View.GONE);
            showsButton.setVisibility(View.GONE);
            moviesButton.setVisibility(View.GONE);
            peopleButton.setVisibility(View.GONE);
            searchText.setAlpha(0f);
            searchImage.setAlpha(0f);
            searchText.setTranslationY(500);
            searchImage.setTranslationY(500);
            searchText.animate().translationY(0).setDuration(500).start();
            searchImage.animate().translationY(0).setDuration(500).start();
            DelayUtils.delay(400, () -> {
                searchText.setVisibility(View.VISIBLE);
                searchImage.setVisibility(View.VISIBLE);
            });
            searchText.animate().alpha(1.0f).setDuration(800).start();
            searchImage.animate().alpha(1.0f).setDuration(800).start();
        });
        DelayUtils.delay(50, () ->{
            searchText.setVisibility(View.VISIBLE);
            searchImage.setVisibility(View.VISIBLE);
            showsButton.setVisibility(View.VISIBLE);
            moviesButton.setVisibility(View.VISIBLE);
            peopleButton.setVisibility(View.VISIBLE);
        });
        DelayUtils.delay(395, () -> {
            searchLayout.setScaleX(20);
            searchLayout.setScaleY(20);

        });


        showsButton.setOnClickListener(view1 -> {
            SearchShowsFragment searchShowsFragment = SearchShowsFragment.newInstance();
            FragmentUtils.changeFragment(searchShowsFragment, R.id.search_list_dummy_frame, "sldf",getParentFragmentManager() );
        });

        moviesButton.setOnClickListener(view1 -> {
            SearchMoviesFragment searchMoviesFragment = SearchMoviesFragment.newInstance();
            FragmentUtils.changeFragment(searchMoviesFragment, R.id.search_list_dummy_frame, "sldf", getParentFragmentManager());
        });

        peopleButton.setOnClickListener(view1 -> {
            SearchPeopleFragment searchPeopleFragment = SearchPeopleFragment.newInstance();
            FragmentUtils.changeFragment(searchPeopleFragment, R.id.search_list_dummy_frame, "sldf", getParentFragmentManager());
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        FloatingActionButton searchActionButton = activity.findViewById(R.id.searchButton);
        searchActionButton.setClickable(false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.getMenu().getItem(0).setEnabled(false);
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottomNavigationView);
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            bottomNavigationView.getMenu().getItem(i).setEnabled(false);
        }
        DelayUtils.delay(250, new DelayUtils.DelayCallback() {
            @Override
            public void afterDelay() {
                searchActionButton.setClickable(true);
                for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
                    if (i != 2)
                    {
                        bottomNavigationView.getMenu().getItem(i).setEnabled(true);
                    }
                }
            }
        });
        FrameLayout searchFrame = activity.findViewById(R.id.search_frame);
        DelayUtils.delay(10, () -> {
            if (searchFrame != null)
            {
                searchFrame.setScaleX(1);
                searchFrame.setScaleY(1);
            }
        });
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentUtils.hideAndShowFragment(fragmentManager.findFragmentById(R.id.fragmentLayout),
                fragmentManager.findFragmentById(R.id.searchFragmentLayout), fragmentManager);

        super.onAttach(activity);
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    @Override
    public void onPause() {
        searchText.setAlpha(0);
        searchImage.setAlpha(0);
        searchText.setVisibility(View.GONE);
        searchImage.setVisibility(View.GONE);
        moviesButton.setVisibility(View.GONE);
        showsButton.setVisibility(View.GONE);
        peopleButton.setVisibility(View.GONE);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.getMenu().getItem(0).setEnabled(true);
        FrameLayout searchFrame = getActivity().findViewById(R.id.search_frame);
        DelayUtils.delay(10, () -> {
            if (searchFrame != null) {
                searchFrame.setScaleX(1);
                searchFrame.setScaleY(1);
            }
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentLayout));
            fragmentTransaction.commit();
        });
        super.onPause();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        // TODO: Use the ViewModel
    }

}