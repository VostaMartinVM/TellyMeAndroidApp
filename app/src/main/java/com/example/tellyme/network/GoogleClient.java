package com.example.tellyme.network;
import android.content.Context;

import com.example.tellyme.BuildConfig;
import com.example.tellyme.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleClient {
    private static GoogleClient instance;
    private GoogleSignInClient googleSignInClient;

    public static GoogleClient getInstance(Context context) {
        if (instance == null)
        {
            instance = new GoogleClient(context);
        }
        return instance;
    }

    private GoogleClient(Context context)
    {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_google_web_client_id))
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
    }

    public GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }
}
