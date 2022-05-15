package com.example.tellyme.view.fragment.searchFragments;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tellyme.R;
import com.example.tellyme.utils.DelayUtils;
import com.example.tellyme.utils.FragmentUtils;
import com.example.tellyme.utils.KeyboardUtils;
import com.example.tellyme.viewModel.SeachViewModels.SearchViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;

    private View view;
    private EditText searchText;
    private ImageView searchImage;
    private ImageButton showsButton;
    private ImageButton moviesButton;
    private ImageButton peopleButton;
    private String argsString = "";

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);
        searchText = view.findViewById(R.id.search_edit_text);
        searchImage = view.findViewById(R.id.search_image_view);
        showsButton = view.findViewById(R.id.search_shows_button);
        moviesButton = view.findViewById(R.id.search_movies_button);
        peopleButton = view.findViewById(R.id.search_people_button);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        if (args != null)
        {
            if (!((String)args.get("enteredFrom")).isEmpty())
            {
                argsString = (String) args.get("enteredFrom");
            }
            else {
                argsString = "";
            }
        }

        searchBox();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentUtils.hideAndShowFragment(fragmentManager.findFragmentById(R.id.fragmentLayout),
                fragmentManager.findFragmentById(R.id.search_fragment_layout), fragmentManager);
        searchTextFunctionality();
    }

    private void searchBox(){
        FragmentManager fragmentManager = getParentFragmentManager();
        Fragment fragmentLayout = fragmentManager.findFragmentById(R.id.fragmentLayout);
        FrameLayout searchLayout = view.findViewById(R.id.search_frame);
        view.post(() -> {
            searchText.setText("");
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
                FragmentUtils.hideFragment(fragmentLayout, fragmentManager);
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
            KeyboardUtils.hideSoftKeyboard(getContext(), view1);
            SearchShowsFragment searchShowsFragment = SearchShowsFragment.newInstance();
            FragmentUtils.changeFragmentWithArgument(searchShowsFragment, R.id.search_list_dummy_frame, "sldf",getParentFragmentManager(),
                    "enteredFrom", argsString);
        });

        moviesButton.setOnClickListener(view1 -> {
            KeyboardUtils.hideSoftKeyboard(getContext(), view1);
            SearchMoviesFragment searchMoviesFragment = SearchMoviesFragment.newInstance();
            FragmentUtils.changeFragmentWithArgument(searchMoviesFragment, R.id.search_list_dummy_frame, "sldf", getParentFragmentManager(),
                    "enteredFrom", argsString);
        });

        peopleButton.setOnClickListener(view1 -> {
            KeyboardUtils.hideSoftKeyboard(getContext(), view1);
            SearchPeopleFragment searchPeopleFragment = SearchPeopleFragment.newInstance();
            FragmentUtils.changeFragmentWithArgument(searchPeopleFragment, R.id.search_list_dummy_frame, "sldf", getParentFragmentManager(),
                    "enteredFrom", argsString);
        });

    }

    private void searchTextFunctionality()
    {
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SearchedPrograms searchedPrograms = new SearchedPrograms();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentUtils.changeFragmentWithArgument(searchedPrograms, R.id.search_list_dummy_frame, "sldf"
                        , fragmentManager, "enteredFrom", argsString);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

        super.onAttach(activity);
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

            FragmentManager fragmentManager = getParentFragmentManager();

            Fragment fragmentLayout = fragmentManager.findFragmentById(R.id.fragmentLayout);
            FragmentUtils.showFragment(fragmentLayout, fragmentManager);
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