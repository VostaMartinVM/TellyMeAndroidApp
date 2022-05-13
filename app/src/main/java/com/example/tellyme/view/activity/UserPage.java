package com.example.tellyme.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;

import com.example.tellyme.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

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
        toolbarCreate();
        setImages();
    }

    private void toolbarCreate()
    {
        Toolbar toolbar = findViewById(R.id.toolbar_user_menu);
            toolbar.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.log_out){
                    LoginManager.getInstance().logOut();
                    GoogleSignIn.getClient(this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                            .signOut();
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(this, SignInActivity.class);
                    startActivity(i);
                }
                if (menuItem.getItemId() == R.id.settings){
                    Intent intent = new Intent(this, Settings.class);
                    startActivity(intent);
                }
                if (menuItem.getItemId() == R.id.about){
                    Intent intent = new Intent(this, About.class);
                    startActivity(intent);
                }
                return true;
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        return true;
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