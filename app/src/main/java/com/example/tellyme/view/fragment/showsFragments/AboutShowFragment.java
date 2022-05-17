package com.example.tellyme.view.fragment.showsFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tellyme.R;
import com.example.tellyme.model.Show;
import com.example.tellyme.utils.ArgumentUtils;

public class AboutShowFragment extends Fragment {

    public static AboutShowFragment newInstance() {
        return new AboutShowFragment();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.about_show_fragment, container, false);

        Bundle args = getArguments();

        if (args != null)
        {
            String personJsonString = args.getString("loadedShow");
            Show show = ArgumentUtils.getGsonParser().fromJson(personJsonString, Show.class);

            TextView title = view.findViewById(R.id.show_title);
            TextView ratingNumber = view.findViewById(R.id.show_rating_number);
            ProgressBar ratingCircle = view.findViewById(R.id.show_rating_circle);
            TextView description = view.findViewById(R.id.show_description);

            title.setText(show.getName());
            ratingNumber.setText(show.getVoteAverage().toString());
            ratingCircle.setProgress(show.getVoteAverage().intValue());
            description.setText(show.getOverview());

        }

        return view;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}