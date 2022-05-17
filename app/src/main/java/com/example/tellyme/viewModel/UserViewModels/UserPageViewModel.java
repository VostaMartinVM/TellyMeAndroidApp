package com.example.tellyme.viewModel.UserViewModels;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tellyme.repository.ImageRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserPageViewModel extends AndroidViewModel {
    private final ImageRepository imageRepository;
    private final FirebaseUser user;

    public UserPageViewModel(@NonNull Application application) {
        super(application);
        imageRepository = ImageRepository.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void uploadImage(Context context, Uri imageUri, String imageName)
    {
        imageRepository.uploadImage(context, imageUri, imageName);
    }

    public String getUsername()
    {
        return user.getDisplayName();
    }
    public Bitmap getImage(String imageName, ImageView imageView)
    {
        return imageRepository.getImage(imageName, imageView);
    }

}
