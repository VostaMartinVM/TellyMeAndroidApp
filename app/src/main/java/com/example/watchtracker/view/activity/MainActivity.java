package com.example.watchtracker.view.activity;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
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
import com.example.watchtracker.model.Utils;
import com.example.watchtracker.view.fragment.ShowsFragments.ShowsFragment;
import com.example.watchtracker.view.fragment.HomeFragments.HomeFragment;
import com.example.watchtracker.view.fragment.MoviesFragments.MoviesFragment;
import com.example.watchtracker.view.fragment.ListsFragments.MyListsFragment;
import com.example.watchtracker.view.fragment.SearchFragments.SearchFragment;
import com.example.watchtracker.view.fragment.UserFragments.UserFragment;
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
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
        FrameLayout searchFrame = findViewById(R.id.search_frame);
        FrameLayout fragmentLayout = findViewById(R.id.fragmentLayout);
        if (searchOpened) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.getMenu().getItem(0).setEnabled(true);
            Utils.delay(10, () -> {
                if (searchFrame != null) {
                    searchFrame.setScaleX(1);
                    searchFrame.setScaleY(1);
                }
                if (fragmentLayout != null) {
                    fragmentLayout.setVisibility(View.VISIBLE);
                }
            });
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
        changeFragment(homeFragment);

        //Search floating action button functionality
        FloatingActionButton searchActionButton = findViewById(R.id.searchButton);
        searchActionButton.setCompatElevation(0);
        searchActionButton.setOnClickListener(view -> {
            if (!searchOpened)
            {
                SearchFragment searchFragment = new SearchFragment();
                searchOpened = true;
                searchActionButton.setClickable(false);
                Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.getMenu().getItem(0).setEnabled(false);
                changeFragment(searchFragment);
                FrameLayout fragmentLayout = findViewById(R.id.fragmentLayout);
                for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
                    bottomNavigationView.getMenu().getItem(i).setEnabled(false);
                }
                Utils.delay(250, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        searchActionButton.setClickable(true);
                        fragmentLayout.setVisibility(View.GONE);
                        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
                            if (i != 2)
                            {
                                bottomNavigationView.getMenu().getItem(i).setEnabled(true);
                            }
                        }
                    }
                });
            }
            else {
                FrameLayout searchFrame = findViewById(R.id.search_frame);
                Utils.delay(10, () -> {
                        searchFrame.setScaleX(1);
                        searchFrame.setScaleY(1);
                });
                FrameLayout fragmentLayout = findViewById(R.id.fragmentLayout);
                fragmentLayout.setVisibility(View.VISIBLE);
                Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.getMenu().getItem(0).setEnabled(true);
                searchOpened = false;
                onBackPressed();
            }
        });

        //functionality for specific items in bottom navigation view
        //noinspection deprecation
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            FrameLayout searchFrame = findViewById(R.id.search_frame);
            FrameLayout fragmentLayout = findViewById(R.id.fragmentLayout);
            changeUserColorIcon(-1);
            if (searchOpened)
            {
                Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.getMenu().getItem(0).setEnabled(true);
                Utils.delay(10, () -> {
                    if (searchFrame != null)
                    {
                        searchFrame.setScaleX(1);
                        searchFrame.setScaleY(1);
                    }
                    if (fragmentLayout != null)
                    {
                    fragmentLayout.setVisibility(View.VISIBLE);
                    }
                });
                onBackPressed();
            }

            switch(menuItem.getItemId()){
                case R.id.home_item:
                    searchOpened = false;
                    homeFragment = HomeFragment.newInstance();
                    changeFragment(homeFragment);
                    break;
                case R.id.my_list_item:
                    searchOpened = false;
                    if (myListFragment == null)
                    {
                        myListFragment = MyListsFragment.newInstance();
                    }
                    changeFragment(myListFragment);
                    break;
                case R.id.tv_series_item:
                    searchOpened = false;
                    if (showsFragment == null)
                    {
                        showsFragment = ShowsFragment.newInstance();
                    }
                    changeFragment(showsFragment);
                    break;
                case R.id.movies_item:
                    searchOpened = false;
                    if (moviesFragment == null)
                    {
                        moviesFragment = MoviesFragment.newInstance();
                    }
                    changeFragment(moviesFragment);
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

        toolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.user_button:
                    searchOpened = false;
                    changeUserColorIcon(-8945409);
                    if (userFragment == null)
                    {
                        userFragment = UserFragment.newInstance();
                    }
                    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
                    bottomNavigationView.getMenu().getItem(2).setChecked(true);
                    changeFragment(userFragment);
            }
            return true;
        });
    }

    private void changeUserColorIcon(int color)
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        Drawable drawable = toolbar.getMenu().getItem(0).getIcon();
        drawable.mutate();
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    //navigating between pages
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