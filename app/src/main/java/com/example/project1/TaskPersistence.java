package com.example.project1;

import com.example.project1.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TaskPersistence {
    Task task;
    public TaskPersistence(Task t) {
        this.task = t;
    }

    public boolean saveToFile(String file) {

        return true;
    }

    public boolean saveToDatabase(String db2Save) {
        DatabaseReference dbTask = FirebaseDatabase.getInstance().getReference(db2Save);
        dbTask.child(task.getTaskId()).setValue(task);

        return true;
    }






}
