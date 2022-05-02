package com.example.tellyme.viewModel.ListsViewModels;

import androidx.lifecycle.ViewModel;

import com.example.tellyme.R;
import com.example.tellyme.model.List;

import java.util.ArrayList;

public class MyListViewModel extends ViewModel {

    ArrayList<List> lists;

    public MyListViewModel()
    {
        List MyMovies = new List("MyMovies", R.mipmap.lists_background);
        List MyShows = new List("MyShows", R.mipmap.lists_background);
        List Favorites = new List("Favorites", R.mipmap.lists_background);
        lists = new ArrayList<>();
        lists.add(MyMovies);
        lists.add(MyShows);
        lists.add(Favorites);
    }



    // TODO: Implement the ViewModel
}