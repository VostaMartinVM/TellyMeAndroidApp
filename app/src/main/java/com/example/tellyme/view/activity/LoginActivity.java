package com.example.tellyme.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tellyme.Authentication.AuthWithEmailAndPass;
import com.example.tellyme.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();
        switchToCreateAcc();
    }

    private EditText email;
    private EditText password;
    private TextView errorMsg;

    private void login()
    {
        Button login = findViewById(R.id.login_button);
        login.setOnClickListener(view -> {
            AuthWithEmailAndPass auth = new AuthWithEmailAndPass();
            email = findViewById(R.id.email_login_field);
            password = findViewById(R.id.password_login_field);
            errorMsg = findViewById(R.id.error_msg_login);

            auth.login(this, email.getText().toString().trim(), password.getText().toString().trim(), errorMsg);
        });
    }

    private void switchToCreateAcc()
    {
        TextView createAccount = findViewById(R.id.switch_to_create_account);
        createAccount.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }
}