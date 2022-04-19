package com.example.watchtracker.view.activity;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.watchtracker.R;
import com.example.watchtracker.view.fragment.ShowsFragments.ShowsFragment;
import com.example.watchtracker.view.fragment.HomeFragments.HomeFragment;
import com.example.watchtracker.view.fragment.MoviesFragments.MoviesFragment;
import com.example.watchtracker.view.fragment.ListsFragments.MyListsFragment;
import com.example.watchtracker.view.fragment.SearchFragments.SearchFragment;
import com.example.watchtracker.view.fragment.UserFragments.UserFragment;
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

    private void bottomNavigationCreate()
    {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        homeFragment = new HomeFragment();
        myListFragment = new MyListsFragment();
        showsFragment = new ShowsFragment();
        moviesFragment = new MoviesFragment();

        FloatingActionButton searchActionButton = findViewById(R.id.searchButton);
        searchActionButton.setCompatElevation(0);
        searchActionButton.setOnClickListener(view -> {
            if (!searchOpened)
            {
                SearchFragment searchFragment = new SearchFragment();
                searchOpened = true;
                changeFragment(searchFragment);
            }
            else {
                FrameLayout searchFrame = findViewById(R.id.search_frame);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchFrame.setScaleX(1);
                        searchFrame.setScaleY(1);
                    }
                }, 5);
                searchOpened = false;
                onBackPressed();
            }
        });

        changeFragment(homeFragment);

        //noinspection deprecation
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
                Toolbar toolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.toolbar);
                ImageView logoIcon = findViewById(R.id.logoIcon);
                toolbar.setVisibility(View.VISIBLE);
                logoIcon.setVisibility(View.VISIBLE);
            switch(menuItem.getItemId()){
                case R.id.home_item:
                    searchOpened = false;
                    changeFragment(homeFragment);
                    break;
                case R.id.my_list_item:
                    searchOpened = false;
                    changeFragment(myListFragment);
                    break;
                case R.id.tv_series_item:
                    searchOpened = false;
                    changeFragment(showsFragment);
                    break;
                case R.id.movies_item:
                    searchOpened = false;
                    changeFragment(moviesFragment);
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Pressed on nonexistent button", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    private void toolbarCreate()
    {
        Toolbar toolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.user_button:
                    searchOpened = false;
                    userFragment = new UserFragment();
                    changeFragment(userFragment);
            }
            return true;
        });
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment instanceof SearchFragment)
        {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom);
            fragmentTransaction.replace(R.id.searchFragmentLayout, fragment, "f");
        }
        else
        {
            fragmentTransaction.replace(R.id.searchFragmentLayout, new Fragment(), "f");
            fragmentTransaction.replace(R.id.fragmentLayout, fragment, "f");
        }
        fragmentTransaction.addToBackStack("f");
        fragmentTransaction.commit();
    }



}