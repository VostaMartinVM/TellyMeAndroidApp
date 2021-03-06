package com.example.tellyme.viewModel.ListsViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.tellyme.repository.ListRepository;

import java.util.ArrayList;

public class MyListViewModel extends AndroidViewModel {

    private final ListRepository listRepository;

    public MyListViewModel(@NonNull Application application) {
        super(application);
        listRepository = ListRepository.getInstance();
    }

    public LiveData<ArrayList<String>> getLists() {
        return listRepository.getLists();
    }

    public void addList(String listName){
        listRepository.newList(listName);
    }
}