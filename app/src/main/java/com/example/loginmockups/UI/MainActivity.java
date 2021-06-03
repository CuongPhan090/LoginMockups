package com.example.loginmockups.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginmockups.Model.Authenticator;
import com.example.loginmockups.R;

public class MainActivity extends AppCompatActivity {
    private EditText usernameText;
    private EditText passwordText;
    private Button logInButton;
    private Button forgetPasswordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.userNameEditText);
        passwordText = findViewById(R.id.passwordEditText);
        logInButton = findViewById(R.id.logInButton);
        forgetPasswordButton = findViewById(R.id.forgetPasswordButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                if (Authenticator.isMatch(username, password)) {
                    Intent loggedInIntent = new Intent(MainActivity.this, LoggedInActivity.class);
//                    loggedInIntent.putExtra("name", username);
                    startActivity(loggedInIntent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Incorrect username or password, please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

        forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resetPasswordIntent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                startActivity(resetPasswordIntent);
            }
        });

    }
}