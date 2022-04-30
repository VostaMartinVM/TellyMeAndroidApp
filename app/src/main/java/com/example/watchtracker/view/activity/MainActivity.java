package com.example.watchtracker.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

    private HomeFragment homeFragment;
    private MyListsFragment myListFragment;
    private ShowsFragment showsFragment;
    private MoviesFragment moviesFragment;
    private UserFragment userFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationCreate();
        toolbarCreate();
    }

    @Override
    public void onBackPressed() {
        Fragment searchFragment = getSupportFragmentManager().findFragmentByTag("sf");
        Fragment postFragment = getSupportFragmentManager().findFragmentByTag("pf");
        if (searchFragment != null && searchFragment.isResumed())
        {
            super.onBackPressed();
        }
        else if (postFragment != null && postFragment.isResumed())
        {
            new AlertDialog.Builder(this).setTitle("Delete post").setMessage("Are you sure " +
                    "you want to delete this post?").setPositiveButton("Yes",
                    (dialogInterface, i) -> {
                        DelayUtils.delay(300, () ->{
                            super.onBackPressed();
                        });
                    }).setNegativeButton("no",
                    null).show();
        }
        else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void bottomNavigationCreate()
    {
        //setting initial visualization for bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        FragmentManager fragmentManager = getSupportFragmentManager();

        //Search floating action button functionality
        searchActionButton(fragmentManager);

        //functionality for specific items in bottom navigation view
        bottomNavigationButtonFun(fragmentManager);

    }

    private void searchActionButton(FragmentManager fragmentManager)
    {
        FloatingActionButton searchActionButton = findViewById(R.id.searchButton);
        searchActionButton.setCompatElevation(0);
        SearchFragment searchFragment = SearchFragment.newInstance();
        searchActionButton.setOnClickListener(view -> {
            Fragment searchFragmentOpened = getSupportFragmentManager().findFragmentById(R.id.searchFragmentLayout);
            if (!(searchFragmentOpened instanceof SearchFragment) )
            {
                FragmentUtils.changeFragmentWithAnimation(searchFragment, R.id.searchFragmentLayout, "sf", fragmentManager, R.anim.enter_from_bottom
                        , R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom);
            }
            else {
                if (fragmentManager.findFragmentByTag("sldf") != null)
                {
                    do {
                        onBackPressed();
                    }
                    while (fragmentManager.findFragmentByTag("sldf") != null);
                }
                onBackPressed();
            }
        });
    }

    private void bottomNavigationButtonFun(FragmentManager fragmentManager)
    {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment searchFragmentOpened = getSupportFragmentManager().findFragmentById(R.id.searchFragmentLayout);
            if (searchFragmentOpened instanceof SearchFragment)
            {
                onBackPressed();
            }
            switch(menuItem.getItemId()){
                case R.id.home_item:
                    homeFragment = HomeFragment.newInstance();
                    checkIfPostOpened();
                    FragmentUtils.changeFragment(homeFragment, R.id.fragmentLayout, "mf", fragmentManager);
                    break;
                case R.id.my_list_item:
                    myListFragment = MyListsFragment.newInstance();
                    checkIfPostOpened();
                    FragmentUtils.changeFragment(myListFragment, R.id.fragmentLayout, "mf", fragmentManager);
                    break;
                case R.id.tv_series_item:
                    showsFragment = ShowsFragment.newInstance();
                    checkIfPostOpened();
                    FragmentUtils.changeFragment(showsFragment, R.id.fragmentLayout, "mf", fragmentManager);
                    break;
                case R.id.movies_item:
                    moviesFragment = MoviesFragment.newInstance();
                    checkIfPostOpened();
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
            if (menuItem.getItemId() == R.id.user_button) {
                userFragment = UserFragment.newInstance();
                checkIfPostOpened();
                FragmentUtils.changeFragment(userFragment, R.id.fragmentLayout, "mf", fragmentManager);
            }
            return true;
        });
    }

    private void checkIfPostOpened()
    {
        int size = getSupportFragmentManager().getBackStackEntryCount();
        if (size > 0)
        {
            String tag = getSupportFragmentManager().getBackStackEntryAt(size-1).getName();
            if (tag.equals("pf"))
            {
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}