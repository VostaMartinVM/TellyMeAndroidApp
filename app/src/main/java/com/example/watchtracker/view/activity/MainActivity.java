package com.example.watchtracker.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.watchtracker.R;
import com.example.watchtracker.view.fragment.TVShowsFragment;
import com.example.watchtracker.view.fragment.HomeFragment;
import com.example.watchtracker.view.fragment.MoviesFragment;
import com.example.watchtracker.view.fragment.MyListFragment;
import com.example.watchtracker.view.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

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

        HomeFragment homeFragment = new HomeFragment();
        changeFragment(homeFragment);

        //noinspection deprecation
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId()){
                case R.id.home_item:
                    changeFragment(homeFragment); // change fragmentFirst
                    break;
                case R.id.my_list_item:
                    MyListFragment myListFragment = new MyListFragment();
                    changeFragment(myListFragment); // change fragmentSecond
                    break;
                case R.id.tv_series_item:
                    TVShowsFragment tvShowsFragment = new TVShowsFragment();
                    changeFragment(tvShowsFragment);
                    break;
                case R.id.movies_item:
                    MoviesFragment moviesFragment = new MoviesFragment();
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
                    UserFragment userFragment = new UserFragment();
                    changeFragment(userFragment);
            }
            return true;
        });
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
    }


}