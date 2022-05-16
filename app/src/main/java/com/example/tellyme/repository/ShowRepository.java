package com.example.tellyme.repository;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.tellyme.R;
import com.example.tellyme.model.Show;
import com.example.tellyme.model.ShowRequest;
import com.example.tellyme.network.ShowServiceInterface;
import com.example.tellyme.network.TMDBServiceGenerator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRepository {
    @SuppressLint("StaticFieldLeak")
    private static ShowRepository instance;
    private final ShowServiceInterface apiService;
    private MutableLiveData<ArrayList<Show>> shows;
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;
    private Context context;

    private ArrayList<Long> showIds;
    private ArrayList<Show> showListHelper;


    public ShowRepository() {
        shows = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        showIds = new ArrayList<>();
        showListHelper = new ArrayList<>();
        apiService = TMDBServiceGenerator.getRetrofitInstance().create(ShowServiceInterface.class);
    }

    public static ShowRepository getInstance() {
        if (instance == null)
        {
            instance = new ShowRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Show>> getShows() {
        shows = new MutableLiveData<>();
        Call<ShowRequest> call = apiService.getAllShows(context.getString(R.string.tmdb_api_key));
        call.enqueue(new Callback<ShowRequest>() {
            @Override
            public void onResponse(@NonNull Call<ShowRequest> call, @NonNull Response<ShowRequest> response) {
                shows.setValue(Objects.requireNonNull(response.body()).getResults());
            }

            @Override
            public void onFailure(@NonNull Call<ShowRequest> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
        return shows;
    }

    public MutableLiveData<ArrayList<Show>> getShowsBySearchedText(String searchedText){
        shows = new MutableLiveData<>();
        Call<ShowRequest> call = apiService.getShowsBySearchedText(context.getString(R.string.tmdb_api_key), searchedText);
        call.enqueue(new Callback<ShowRequest>() {
            @Override
            public void onResponse(@NonNull Call<ShowRequest> call, @NonNull Response<ShowRequest> response) {
                if (response.body() != null)
                {
                    shows.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ShowRequest> call, @NonNull Throwable t) {

            }
        });
        return shows;
    }

    public void addShowToList(String listName, int showID){
        Map<String, Object> show = new HashMap<>();
        Map<String, Object> id = new HashMap<>();
        id.put("showID", FieldValue.arrayUnion(showID));
        show.put(listName, id);
        db.collection(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                .document("Lists")
                .set(show, SetOptions.merge());
    }

    @SuppressWarnings("unchecked")
    public MutableLiveData<ArrayList<Show>> getShowsForSpecificList(String listName)
    {
        shows = new MutableLiveData<>();
        DocumentReference docRef = db.collection(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                .document("Lists");
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    if (document.get(listName) instanceof Map)
                    {
                        Map<String, Object> temp = (Map<String, Object>) document.get(listName);
                        if (temp != null)
                        {
                            showIds = (ArrayList<Long>) temp.get("showID");
                            if (showIds != null)
                            {
                                Integer[] showIdsArray = new Integer[showIds.size()];
                                for (int i = 0; i < showIds.size(); i++) {
                                    showIdsArray[i] = ((Long) showIds.get(i)).intValue();
                                }
                                showListHelper = new ArrayList<>();

                                for (Integer integer : showIdsArray) {
                                    getSpecificShowsHelper(integer);
                                }
                            }
                        }
                    }
                }
            }
        });
        return shows;
    }

    private void getSpecificShowsHelper(int id) {
        Call<Show> call = apiService.getSpecificShow(id, context.getString(R.string.tmdb_api_key));
        call.enqueue(new Callback<Show>() {
            @Override
            public void onResponse(@NonNull Call<Show> call, @NonNull Response<Show> response) {
                showListHelper.add(response.body());
                shows.setValue(showListHelper);
            }

            @Override
            public void onFailure(@NonNull Call<Show> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void setContext(Context context) {
        this.context = context;
    }

}
