package com.example.project1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TaskSort {

    ArrayList<Task> myTask = new ArrayList<>();


    public TaskSort(ArrayList<Task> tasks) {
        for (Task temp : tasks) {
            myTask.add(temp);
        }
    }


    // sort the task base on the post time
    public void sortTaskOnPostTime() {
        //Collections.sort(myTask, Task.postDateSort);
    }

}
