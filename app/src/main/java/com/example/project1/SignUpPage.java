package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpPage extends AppCompatActivity {
    private EditText mUserNameEditText, mPasswordEditText;
    private DatabaseReference dbUser;
    private String uName, pswd;
    private FirebaseDatabase database;
    private boolean isTaken = false;

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
                if (TextUtils.isEmpty(uName)) {
                    Toast.makeText(SignUpPage.this, "Please enter your user name.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pswd)) {
                    Toast.makeText(SignUpPage.this, "Please enter your user password.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isValidUserName()) {
                    Toast.makeText(SignUpPage.this, "userPlease check your Password or your User name.(Must contain number, Lower and upper letters)", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isValidPassword()) {
                    Toast.makeText(SignUpPage.this, "pswPlease check your Password or your User name.(Must contain number, Lower and upper letters)", Toast.LENGTH_SHORT).show();
                    return;
                } else if (isExistUserName(uName) == true) {
                    Toast.makeText(SignUpPage.this, "User name exist, please login.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(SignUpPage.this, "Register complete.", Toast.LENGTH_SHORT).show();
                    dbUser.child(user.userName).setValue(user);
                    Intent backToLogIn = new Intent(SignUpPage.this, LogIn.class);
                    startActivity(backToLogIn);
                }
            }
        });


    }

    private void getEditString() {
        uName = mUserNameEditText.getText().toString().trim();
        pswd = mPasswordEditText.getText().toString().trim();
    }

    // Check if enter a valid username, should be less than 16 digits
    public boolean isValidUserName() {
        getEditString();
        boolean validation = false;
        char[] convert = uName.toCharArray();

        if (convert.length <= 16 && convert.length >= 4) {
            int count = 0;
            for (int i = 0; i < convert.length; i++) {
                int ascii = convert[i];
                if ((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
                    count++;
                }
            }
            if (count == uName.length()) validation = true;
        }

        return validation;
    }

    // Check if enter a valid password, should be no less than 8 digits, no more than 16 letters
    public boolean isValidPassword() {
        getEditString();
        boolean validation = false;
        char[] convert = pswd.toCharArray();

        if (convert.length <= 16 && convert.length >= 8) {
            int count = 0;
            for (int i = 0; i < convert.length; i++) {
                int ascii = convert[i];
                if ((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
                    count++;
                }
            }
            if (count == pswd.length()) validation = true;
        }

        return validation;
    }


    public boolean isExistUserName(String username) {

        System.out.println("s1");
        dbUser.orderByChild("userName").equalTo(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    isTaken = false;
                } else {
                    isTaken = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SignUpPage.this, "DatabaseError, Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println(isTaken);
        return isTaken;
    }
//        dbUser.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(!snapshot.hasChild(username)){
//                    isTaken = true;
//                } else if(snapshot.hasChild(username)){
//                    isTaken = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(SignUpPage.this, "Connection Error, Please try agin.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        System.out.println(isTaken);
//        return isTaken;
//    }


}

