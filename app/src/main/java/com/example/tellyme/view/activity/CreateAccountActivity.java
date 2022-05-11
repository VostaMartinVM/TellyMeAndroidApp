package com.example.tellyme.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tellyme.Authentication.AuthWithEmailAndPass;
import com.example.tellyme.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        switchToLogin();
        createAccount();
    }

    private void switchToLogin()
    {
        TextView login = findViewById(R.id.switch_to_login);
        login.setOnClickListener(view -> {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void createAccount()
    {
        Button createAccount = findViewById(R.id.create_account);
        EditText email = findViewById(R.id.email_field);
        EditText username = findViewById(R.id.username_field);
        EditText password = findViewById(R.id.password_field);
        EditText confirmPass = findViewById(R.id.confirm_pass_field);
        createAccount.setOnClickListener(view -> {
            AuthWithEmailAndPass auth = new AuthWithEmailAndPass();
            auth.createAccount(this, email.getText().toString().trim(), username.getText().toString().trim()
                    , password.getText().toString().trim(), confirmPass.getText().toString().trim());
        });
    }
}