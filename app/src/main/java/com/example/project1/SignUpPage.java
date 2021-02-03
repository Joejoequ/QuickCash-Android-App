package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity {
    private EditText mUserNameEditText, mPasswordEditText;
    private DatabaseReference dbUser;
    private String uName, pswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mUserNameEditText = findViewById(R.id.userName);
        mPasswordEditText = findViewById(R.id.password);
        dbUser = FirebaseDatabase.getInstance().getReference("User");

        Button backToLogIn = findViewById(R.id.BacktoLogin);
        backToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backToLogIn = new Intent(SignUpPage.this, LogIn.class);
                startActivity(backToLogIn);
            }
        });

        Button create = findViewById(R.id.submit);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User(mUserNameEditText.getText().toString().trim(),mPasswordEditText.getText().toString());
                dbUser.child(java.util.UUID.randomUUID().toString()).setValue(u);
                Intent backToLogIn = new Intent(SignUpPage.this, LogIn.class);
                startActivity(backToLogIn);
            }
        });


    }

    private void getEditString(){
        uName = mUserNameEditText.getText().toString().trim();
        pswd = mPasswordEditText.getText().toString().trim();
    }



}

