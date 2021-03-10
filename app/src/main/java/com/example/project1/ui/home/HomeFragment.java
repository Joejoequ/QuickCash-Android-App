package com.example.project1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1.R;
import com.example.project1.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DatabaseReference dbTask = FirebaseDatabase.getInstance().getReference();;
    public ArrayList<Task> allTitles = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

   /*     Query query = dbTask.child("Task").orderByChild("publisher");
        query.addListenerForSingleValueEvent(valueEventListener);
*/
        Query query = dbTask.child("Task").orderByChild("publisher");
        query.addListenerForSingleValueEvent(valueEventListener);
        return root;

    }

    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                for (DataSnapshot taskSnapshot : snapshot.getChildren()) {
//                        String title = taskSnapshot.child("title").getValue().toString();
//                        Date workDate = (Date)taskSnapshot.child("workDate").getValue();
//                        String description = taskSnapshot.child("description").getValue().toString();
//                        int wage = (int)taskSnapshot.child("wage").getValue();
//                        String publisher = taskSnapshot.child("publisher").getValue().toString();
//                        Task task = new Task(title,description,workDate,wage,publisher);
                    Task task = taskSnapshot.getValue(Task.class);
                    System.out.println("11111111111111111111111"+task.getTaskId());
                    System.out.println(task.getWorkDate());
                    // append task to task list
                    allTitles.add(task);
                }

            } else {
                System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                // The user has not posted any task
                String message =  "has not posted any task yet";
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(),"DatabaseError, please try again later", Toast.LENGTH_LONG).show();
        }
    };

    public ArrayList getAllTask(){
        return allTitles;
    }
    public ArrayList<Task> Search(ArrayList<Task> tasks, String keyword){
        ArrayList<Task> afterCompare = new ArrayList<>();

        for(int i = 0; i<tasks.size(); i++){
            if(tasks.get(i).getTitle().contains(keyword)){
                afterCompare.add(tasks.get(i));
            }
        }

        return afterCompare;
    }
}