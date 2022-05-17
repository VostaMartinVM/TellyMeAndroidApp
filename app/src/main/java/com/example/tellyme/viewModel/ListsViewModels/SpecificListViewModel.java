package com.example.tellyme.viewModel.ListsViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.tellyme.repository.ListRepository;

import java.util.ArrayList;

public class SpecificListViewModel extends AndroidViewModel {

    private final ListRepository listRepository;
    private Context context;

    public SpecificListViewModel(@NonNull Application application) {
        super(application);
        listRepository = ListRepository.getInstance();
    }

    @SuppressWarnings("rawtypes")
    public LiveData<ArrayList> getTVPrograms(String listName)
    {
        listRepository.setContext(context);
        return listRepository.getTVProgramsForList(listName);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}