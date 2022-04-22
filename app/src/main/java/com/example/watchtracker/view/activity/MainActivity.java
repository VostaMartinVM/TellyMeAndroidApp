package com.example.watchtracker.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.watchtracker.R;
import com.example.watchtracker.view.fragment.homeFragments.HomeFragment;
import com.example.watchtracker.view.utils.DelayUtils;
import com.example.watchtracker.view.fragment.showsFragments.ShowsFragment;
import com.example.watchtracker.view.fragment.moviesFragments.MoviesFragment;
import com.example.watchtracker.view.fragment.listsFragments.MyListsFragment;
import com.example.watchtracker.view.fragment.searchFragments.SearchFragment;
import com.example.watchtracker.view.fragment.userFragments.UserFragment;
import com.example.watchtracker.view.utils.FragmentUtils;
import com.example.watchtracker.view.utils.ToolBarUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity{

    HomeFragment homeFragment;
    MyListsFragment myListFragment;
    ShowsFragment showsFragment;
    MoviesFragment moviesFragment;
    UserFragment userFragment;

    boolean searchOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationCreate();
        toolbarCreate();
    }

    @Override
    public void onBackPressed() {
        if (searchOpened)
        {
            searchOpened = false;
        }
        super.onBackPressed();
    }

    private void bottomNavigationCreate()
    {
        //setting initial visualization for bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        //setting home page shown at the start
        homeFragment = HomeFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentUtils.changeFragment(homeFragment, R.id.fragmentLayout, "mf", fragmentManager);

        //Search floating action button functionality
        FloatingActionButton searchActionButton = findViewById(R.id.searchButton);
        searchActionButton.setCompatElevation(0);
        searchActionButton.setOnClickListener(view -> {
            if (!searchOpened)
            {
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchOpened = true;
                FragmentUtils.changeFragmentWithAnimation(searchFragment, R.id.searchFragmentLayout, "sf", fragmentManager, R.anim.enter_from_bottom
                , R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom);
            }
            else {
                FrameLayout searchFrame = findViewById(R.id.search_frame);
                DelayUtils.delay(10, () -> {
                    if (searchFrame != null)
                    {
                        searchFrame.setScaleX(1);
                        searchFrame.setScaleY(1);
                    }
                });
                searchOpened = false;
                onBackPressed();
            }
        });

        //functionality for specific items in bottom navigation view
        //noinspection deprecation
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            if (searchOpened)
            {
                onBackPressed();
            }
            switch(menuItem.getItemId()){
                case R.id.home_item:
                    searchOpened = false;
                    homeFragment = HomeFragment.newInstance();
                    FragmentUtils.changeFragment(homeFragment, R.id.fragmentLayout, "mf", fragmentManager);
                    break;
                case R.id.my_list_item:
                    searchOpened = false;
                    if (myListFragment == null)
                    {
                        myListFragment = MyListsFragment.newInstance();
                    }
                    FragmentUtils.changeFragment(myListFragment, R.id.fragmentLayout, "mf", fragmentManager);
                    break;
                case R.id.tv_series_item:
                    searchOpened = false;
                    if (showsFragment == null)
                    {
                        showsFragment = ShowsFragment.newInstance();
                    }
                    FragmentUtils.changeFragment(showsFragment, R.id.fragmentLayout, "mf", fragmentManager);
                    break;
                case R.id.movies_item:
                    searchOpened = false;
                    if (moviesFragment == null)
                    {
                        moviesFragment = MoviesFragment.newInstance();
                    }
                    FragmentUtils.changeFragment(moviesFragment, R.id.fragmentLayout, "mf", fragmentManager);
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Pressed on nonexistent button", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    //toolbar functionality
    private void toolbarCreate()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        ToolBarUtils.changeUserIcon(-1, toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        toolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.user_button:
                    if (userFragment == null)
                    {
                        userFragment = UserFragment.newInstance();
                    }
                    FragmentUtils.changeFragment(userFragment, R.id.fragmentLayout, "mf", fragmentManager);
            }
            return true;
        });
    }
}