package com.example.loginmockups.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.loginmockups.R;

public class ResetPasswordActivity extends AppCompatActivity {
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.setClickable(false);
//                The background color on the button not working.
//                submitButton.setBackgroundColor(0xFFFF0000);
//                submitButton.setTextColor(Color.BLUE);
                Toast.makeText(
                        ResetPasswordActivity.this,
                        "Check your email inbox for verification code.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}