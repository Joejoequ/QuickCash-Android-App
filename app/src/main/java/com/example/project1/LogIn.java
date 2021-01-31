package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity  {

    private EditText mUserNameEditText, mPasswordEditText;
    private Button mSigninButton;
    private Button switchToSignUp;

    private DatabaseReference dbUser;
    //java.util.UUID.randomUUID().toString()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mUserNameEditText = findViewById(R.id.userName);
        mPasswordEditText = findViewById(R.id.password);
        mSigninButton = findViewById(R.id.loginBtn);
        dbUser = FirebaseDatabase.getInstance().getReference("User");
        switchToSignUp = findViewById(R.id.toSignUp);


        switchToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchSignUp = new Intent(LogIn.this, SignUpPage.class);
                startActivity(switchSignUp);
            }
        });
    }



    static public boolean connection(){
        return false;
    }
    static public boolean checkAccount(String username, String password){
        return false;
    }
}
