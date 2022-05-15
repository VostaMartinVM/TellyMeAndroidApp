package com.example.tellyme.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tellyme.R;
import com.example.tellyme.view.fragment.homeFragments.HomeFragment;
import com.example.tellyme.utils.DelayUtils;
import com.example.tellyme.view.fragment.showsFragments.ShowsFragment;
import com.example.tellyme.view.fragment.moviesFragments.MoviesFragment;
import com.example.tellyme.view.fragment.listsFragments.MyListsFragment;
import com.example.tellyme.view.fragment.searchFragments.SearchFragment;
import com.example.tellyme.utils.FragmentUtils;
import com.example.tellyme.utils.ToolBarUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity{

    private HomeFragment homeFragment;
    private MyListsFragment myListFragment;
    private ShowsFragment showsFragment;
    private MoviesFragment moviesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            bottomNavigationCreate();
            toolbarCreate();
        }
        else {
            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
        }
    }


    @Override
    public void onBackPressed() {
        Fragment searchFragment = getSupportFragmentManager().findFragmentByTag("sf");
        Fragment postFragment = getSupportFragmentManager().findFragmentByTag("pf");
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (searchFragment != null && searchFragment.isResumed())
        {
            if (fragmentManager.findFragmentByTag("sldf") != null)
            {
                do {
                    super.onBackPressed();
                }
                while (fragmentManager.findFragmentByTag("sldf") != null);
            }
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
            Fragment searchFragmentOpened = getSupportFragmentManager().findFragmentById(R.id.search_fragment_layout);
            if (!(searchFragmentOpened instanceof SearchFragment) )
            {
                FragmentUtils.changeFragmentWithAnimationAndArgs(searchFragment, R.id.search_fragment_layout, "sf", fragmentManager, R.anim.enter_from_bottom
                        , R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom, "enteredFrom", "");
            }
            else {
                onBackPressed();
            }
        });
    }

    private void bottomNavigationButtonFun(FragmentManager fragmentManager)
    {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment searchFragmentOpened = getSupportFragmentManager().findFragmentById(R.id.search_fragment_layout);
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
                case R.id.shows_item:
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
                Intent intent = new Intent(this, UserPageActivity.class);
                startActivity(intent);
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