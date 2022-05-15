package com.example.tellyme.viewModel.ListsViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tellyme.model.Show;
import com.example.tellyme.repository.ListRepository;

import java.util.ArrayList;

public class SpecificListViewModel extends AndroidViewModel {

    private ListRepository listRepository;

    public SpecificListViewModel(@NonNull Application application) {
        super(application);
        listRepository = ListRepository.getInstance();
    }

    public LiveData<ArrayList> getTVPrograms(String listName)
    {

        return listRepository.getTVProgramsForList(listName);
    }


}