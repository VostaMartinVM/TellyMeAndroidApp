package com.example.watchtracker.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.watchtracker.R;
import com.example.watchtracker.view.fragment.TVShowsFragment;
import com.example.watchtracker.view.fragment.HomeFragment;
import com.example.watchtracker.view.fragment.MoviesFragment;
import com.example.watchtracker.view.fragment.MyListFragment;
import com.example.watchtracker.view.fragment.SearchFragment;
import com.example.watchtracker.view.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity{

    HomeFragment homeFragment;
    MyListFragment myListFragment;
    TVShowsFragment tvShowsFragment;
    MoviesFragment moviesFragment;
    UserFragment userFragment;

    Fragment lastFragment;


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
        myListFragment = new MyListFragment();
        tvShowsFragment = new TVShowsFragment();
        moviesFragment = new MoviesFragment();

        FloatingActionButton searchActionButton = findViewById(R.id.searchButton);
        searchActionButton.setOnClickListener(view -> {
            Toolbar toolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.toolbar);
            ImageView logoIcon = findViewById(R.id.logoIcon);
            if (!(getCurrentFragment() instanceof SearchFragment))
            {
                SearchFragment searchFragment = new SearchFragment();
                lastFragment = getCurrentFragment();
                changeFragment(searchFragment);
                toolbar.setVisibility(View.GONE);
                logoIcon.setVisibility(View.GONE);
            }
            else {
                toolbar.setVisibility(View.VISIBLE);
                logoIcon.setVisibility(View.VISIBLE);
                goToLastFragment();
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
                    changeFragment(homeFragment); // change fragmentFirst
                    break;
                case R.id.my_list_item:
                    changeFragment(myListFragment); // change fragmentSecond
                    break;
                case R.id.tv_series_item:
                    changeFragment(tvShowsFragment);
                    break;
                case R.id.movies_item:
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
                    userFragment = new UserFragment();
                    changeFragment(userFragment);
            }
            return true;
        });
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
    }

    public void goToLastFragment() {
       changeFragment(lastFragment);
    }

    private Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int stackCount = fragmentManager.getBackStackEntryCount();
        if( fragmentManager.getFragments() != null ) return fragmentManager.getFragments().get( stackCount > 0 ? stackCount-1 : stackCount );
        else return null;
    }

}