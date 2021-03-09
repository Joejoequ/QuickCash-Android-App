package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        TextView title = findViewById(R.id.taskDetailTitle);
        TextView description = findViewById(R.id.taskDetailDescription);
        TextView postDay = findViewById(R.id.taskPostDay);
        TextView workDay = findViewById(R.id.taskWorkDay);
        TextView wage = findViewById(R.id.taskWageDetail);
        TextView publisher = findViewById(R.id.taskPublisherDetail);
        TextView worker = findViewById(R.id.taskWorkerDetail);
        TextView status = findViewById(R.id.taskStatus);
        Button editButton = findViewById(R.id.taskEdit);

        getTaskData();
        title.setText(taskTitle);
        description.setText(taskDescription);
        postDay.setText(taskPostDay);
        workDay.setText(taskWorkDay);
        wage.setText(taskWage);
        publisher.setText(taskPublisher);
        worker.setText(taskWorker);
        status.setText(taskStatus);

    }


    public void getTaskData() {
        if (hasDataPassing()) {
            taskTitle = getIntent().getStringExtra("taskTitle");
            taskDescription = getIntent().getStringExtra("taskDes");
            taskPostDay = "Post Day: " + getIntent().getStringExtra("postDay");
            taskWorkDay = "Work Day: " + getIntent().getStringExtra("workDay");
            taskWage = "Wages: " + getIntent().getStringExtra("wage");
            taskPublisher = "Publisher: " + getIntent().getStringExtra("publisher");
            taskWorker = "Worker: " + getIntent().getStringExtra("worker");
            taskStatus = "Task Status: " + getIntent().getStringExtra("status");

        } else {
            Toast.makeText(this,"No data passing", Toast.LENGTH_LONG).show();
        }


    }

    public boolean hasDataPassing() {
        boolean hasData = true;

        if ((!getIntent().hasExtra("taskTitle")) || (!getIntent().hasExtra("taskDes")) || (!getIntent().hasExtra("postDay"))
             || (!getIntent().hasExtra("workDay")) || (!getIntent().hasExtra("wage")) || (!getIntent().hasExtra("publisher"))) {
            hasData = false;
        }

        return hasData;
    }

}