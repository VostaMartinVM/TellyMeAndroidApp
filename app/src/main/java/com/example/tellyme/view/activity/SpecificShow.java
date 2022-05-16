package com.example.tellyme.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.tellyme.R;
import com.example.tellyme.model.Show;
import com.example.tellyme.utils.FragmentUtils;
import com.example.tellyme.view.fragment.showsFragments.AboutShowFragment;
import com.squareup.picasso.Picasso;

public class SpecificShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_show);

        Bundle args = getIntent().getExtras();
        if (args != null) {
            Show show = (Show) args.get("loadedShow");
            ImageView showImage = findViewById(R.id.specific_show_image);
            if (show.getBackdropPath() != null) {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + show.getBackdropPath()).fit().centerCrop().into(showImage);
            } else {
                Picasso.get().load("https://image.tmdb.org/t/p/original" + show.getPosterPath()).fit().centerCrop().into(showImage);
            }
            AboutShowFragment aboutShowFragment = new AboutShowFragment();
            FragmentUtils.passArgument(aboutShowFragment, R.id.show_info_fragment, getSupportFragmentManager(),
                    "loadedShow", show);
        }
    }
}