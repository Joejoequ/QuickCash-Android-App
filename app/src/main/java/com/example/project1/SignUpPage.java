package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                getEditString();
                User user = new User(uName, pswd);
                if(TextUtils.isEmpty(uName)){
                    Toast.makeText(SignUpPage.this, "Please enter your user name.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pswd)){
                    Toast.makeText(SignUpPage.this, "Please enter your user password.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (user.isValidUserName(user)){
                    Toast.makeText(SignUpPage.this, "Please check your Password or your User name.(Must contain number, Lower and upper letters)", Toast.LENGTH_SHORT).show();
                    return;
                } else if(user.isValidPassword(user)){
                    Toast.makeText(SignUpPage.this, "Please check your Password or your User name.(Must contain number, Lower and upper letters)", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dbUser.child(java.util.UUID.randomUUID().toString()).setValue(user);
                    Intent backToLogIn = new Intent(SignUpPage.this, LogIn.class);
                    startActivity(backToLogIn);
                }
            }
        });


    }

    private void getEditString(){
        uName = mUserNameEditText.getText().toString().trim();
        pswd = mPasswordEditText.getText().toString().trim();
    }


//    private void isExistUserName(){
//
//    }


}

