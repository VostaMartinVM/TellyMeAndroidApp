package com.example.watchtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
    HomeFragment firstFragment;
    MyListFragment secondFragment;
    TvShowsFragment thirdFragment;
    MoviesFragment fourthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        firstFragment = new HomeFragment();
        changeFragment(firstFragment);

        //noinspection deprecation
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId()){
                case R.id.home_item:
                    changeFragment(firstFragment); // change fragmentFirst
                    break;
                case R.id.my_list_item:
                    secondFragment = new MyListFragment();
                    changeFragment(secondFragment); // change fragmentSecond
                    break;
                case R.id.tv_series_item:
                    thirdFragment = new TvShowsFragment();
                    changeFragment(thirdFragment);
                    break;
                case R.id.movies_item:
                    fourthFragment = new MoviesFragment();
                    changeFragment(fourthFragment);
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Pressed on nonexistent button", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
    }

}