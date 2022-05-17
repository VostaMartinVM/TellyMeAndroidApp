package com.example.tellyme.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;


import java.io.File;
import java.io.IOException;

public class ImageRepository {
    private static ImageRepository instance;
    private final FirebaseUser user;
    private StorageReference mStorageRef;
    private String[] fileFullName = new String[2];
    private Bitmap imageBitmap;

    private ImageRepository()
    {
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public static ImageRepository getInstance() {
        if (instance == null)
        {
            instance = new ImageRepository();
        }
        return instance;
    }

    @SuppressWarnings({"unused", "rawtypes"})
    public void uploadImage(Context context, Uri imageUri, String imageName)
    {
        mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://tellyme-4ec38.appspot.com/"
                + user.getUid());
        StorageReference fileRef = mStorageRef.child(imageName + "." + getFileExtension(context, imageUri));
        StorageTask mUploadTask = fileRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {});
    }

    private String getFileExtension(Context context, Uri uri)
    {
        ContentResolver contentResolver = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public Bitmap getImage(String imageName, ImageView imageView){
        System.out.println(fileFullName[0]);
        mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://tellyme-4ec38.appspot.com/"
                + user.getUid());
        mStorageRef.listAll().addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {

                ListResult listResult = task.getResult();
                for (int i = 0; i < listResult.getItems().size(); i++) {
                    StorageReference item = listResult.getItems().get(i);
                    fileFullName = item.getName().split("\\.");
                    String name = fileFullName[0];
                    String extension = fileFullName[1];
                    if (name.matches(imageName)){
                        try {
                            getImageHelper(imageName, extension, imageView);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return imageBitmap;
    }

    private void getImageHelper(String imageName, String extension, ImageView imageView) throws IOException {
        mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://tellyme-4ec38.appspot.com/"
                + user.getUid() + "/" + imageName + "." + extension);

        final File localFile = File.createTempFile(imageName, extension);
        mStorageRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
            imageBitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
            imageView.setImageBitmap(imageBitmap);
        });
    }

}