package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // event handle to sign up
        Button switchToSignUp = findViewById(R.id.toSignUp);
        switchToSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // change to sign up page
        Intent switchSignUp = new Intent(this, SignUpPage.class);
        startActivity(switchSignUp);
    }
}
