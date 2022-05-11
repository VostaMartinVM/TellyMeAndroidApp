package com.example.tellyme.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.tellyme.R;

public class UserPage extends AppCompatActivity {

    private static int PICK_PROFILE_IMAGE = 100;
    private static int PICK_AVATAR_IMAGE = 101;
    private Uri profileImageUri;
    private ImageView profileCover;
    private Uri avatarImageUri;
    private ImageView avatarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        setImages();
    }

    private void setImages()
    {
        profileCover = findViewById(R.id.profile_cover);
        avatarImage = findViewById(R.id.avatar_image);

        profileCover.setOnClickListener(view -> {
            openGalleryForProfile();
        });

        avatarImage.setOnClickListener(view -> {
            openGalleryForAvatar();
        });
    }

    private void openGalleryForProfile()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_PROFILE_IMAGE);
    }

    private void openGalleryForAvatar()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_AVATAR_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_PROFILE_IMAGE)
        {
            profileImageUri = data.getData();
            profileCover.setImageURI(profileImageUri);
        }
        if (resultCode == RESULT_OK && requestCode == PICK_AVATAR_IMAGE)
        {
            avatarImageUri = data.getData();
            avatarImage.setImageURI(avatarImageUri);
        }
    }
}