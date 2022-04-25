package com.example.watchtracker.viewModel.SeachViewModel;

import android.content.pm.ApplicationInfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.watchtracker.BuildConfig;
import com.example.watchtracker.model.Show;

import java.util.ArrayList;

public class SearchShowsViewModel extends ViewModel {
   MutableLiveData<ArrayList<Show>> showLiveData;
   ArrayList<Show> showArrayList;
   public SearchShowsViewModel (ApplicationInfo applicationInfo)
   {
       showLiveData = new MutableLiveData<>();
       init();
   }

   public void init ()
   {

       populateList();
       showLiveData.setValue(showArrayList);
   }

   public void populateList()
   {
       showArrayList = new ArrayList<>();
   }
}