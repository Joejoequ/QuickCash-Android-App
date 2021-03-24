package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PostDetail extends AppCompatActivity {
    String taskTitle;
    String taskDescription;
    String taskPostDay;
    String taskWorkDay;
    String taskWage;
    String taskPublisher;
    String taskWorker;
    String taskStatus;

    TextView title;
    TextView description;
    TextView postDay;
    TextView workDay;
    TextView wage;
    TextView publisher;
    TextView worker;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        title = findViewById(R.id.taskDetailTitle);
        description = findViewById(R.id.taskDetailDescription);
        postDay = findViewById(R.id.taskPostDay);
        workDay = findViewById(R.id.taskWorkDay);
        wage = findViewById(R.id.taskWageDetail);
        publisher = findViewById(R.id.taskPublisherDetail);
        worker = findViewById(R.id.taskWorkerDetail);
        status = findViewById(R.id.taskStatus);

        getTaskData();
        setTaskData();

    }

    /**
     * The method will capture all task information which passed from myPost.
     */
    public void getTaskData() {
        if (hasDataPassing()) {
            taskTitle = getIntent().getStringExtra("taskTitle");
            taskDescription = getIntent().getStringExtra("taskDes");
            taskPostDay = "Post Day: " + getIntent().getStringExtra("postDay");
            taskWorkDay = "Work Day: " + getIntent().getStringExtra("workDay");
            taskWage = "Wages: " + getIntent().getStringExtra("wage");
            taskPublisher = "Publisher: " + getIntent().getStringExtra("publisher");
            String assignWorker;
            if (getIntent().getStringExtra("worker") == null) {
                assignWorker = Task.NOWORKER;
            } else {
                assignWorker =  getIntent().getStringExtra("worker");
            }
            taskWorker = "Worker: " + assignWorker;
            taskStatus = "Task Status: " + getIntent().getStringExtra("status");

        } else {
            Toast.makeText(this,"No data passing", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * This method will check if there is some necessary task information passing from myPost.
     * @return -- True if all necessary task information are passed
     */
    public boolean hasDataPassing() {
        boolean hasData = true;

        if ((!getIntent().hasExtra("taskTitle")) || (!getIntent().hasExtra("taskDes")) || (!getIntent().hasExtra("postDay"))
             || (!getIntent().hasExtra("workDay")) || (!getIntent().hasExtra("wage")) || (!getIntent().hasExtra("publisher"))) {
            hasData = false;
        }

        return hasData;
    }

    /**
     * This method will display all task information in the PostDetail UI
     */
    public void setTaskData() {
        title.setText(taskTitle);
        description.setText(taskDescription);
        postDay.setText(taskPostDay);
        workDay.setText(taskWorkDay);
        wage.setText(taskWage);
        publisher.setText(taskPublisher);
        worker.setText(taskWorker);
        status.setText(taskStatus);
    }


}