package com.example.project1.ui.mypost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1.MainActivity;
import com.example.project1.R;
import com.example.project1.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class MyPostFragment extends Fragment {

    private MyPostViewModel MypostViewModel;
    private DatabaseReference dbTask;
    private LinearLayout myPostLL;
    private Button refresh;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MypostViewModel =
                new ViewModelProvider(this).get(MyPostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypost, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        MypostViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        dbTask = FirebaseDatabase.getInstance().getReference();
        myPostLL = root.findViewById(R.id.myPostLinear);

        // array list to store all post task for a given user
        ArrayList<Task> myPost = new ArrayList<>();
        // get the current logged user
        MainActivity activity = (MainActivity) getActivity();
        String userName = activity.getUserName();

        return root;
    }

    public ArrayList<Task> getPostTask(String user) {
        ArrayList<Task> userTask = new ArrayList<>();
        Query query = dbTask.child("Task").orderByChild("publisher").equalTo(user);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot taskSnapshot : snapshot.getChildren()) {
                        String title = snapshot.child("title").getValue().toString();
                        Date workDate = (Date)snapshot.child("workDate").getValue();
                        String description = snapshot.child("description").getValue().toString();
                        int wage = (int)snapshot.child("wage").getValue();
                        String publisher = snapshot.child("publisher").getValue().toString();
                        Task task = new Task(title,description,workDate,wage,publisher);
                        // append task to task list
                        userTask.add(task);
                    }

                } else {
                    // The user has not posted any task
                    String message = user + "has not posted any task yet";
                    Toast.makeText(getContext(), "message", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"DatabaseError, please try again later", Toast.LENGTH_LONG).show();
            }
        });
        return userTask;
    }




}