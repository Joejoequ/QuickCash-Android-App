package com.example.project1;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

public class Task {
    static final String PUBLISHED = "Published";
    static final String ACCEPTED = "Accepted";
    static final String UNPAID = "Unpaid";
    static final String FINISHED = "Finished";

    private String taskId;
    private String title;
    private String description;
    private Date workDate;
    private Date postDate;
    private int wage;
    private String publisher;
    private String worker;
    private String status;

    public Task(String title, String description, Date workDate, int wage, String publisher) {
        this.taskId = java.util.UUID.randomUUID().toString();
        this.workDate = workDate;
        this.title = title;
        this.description = description;
        this.postDate = new Date();
        this.wage = wage;
        this.publisher = publisher;
        this.status = Task.PUBLISHED;

    }


    public void acceptTask(String worker) {
        this.worker = worker;
        this.status = ACCEPTED;

    }

    public boolean available() {
        return this.status .equals( PUBLISHED) && workDate.after(new Date());
    }




    public String formattedPostDate(){
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss zzz");
        ft.setTimeZone(TimeZone.getTimeZone("America/Barbados"));
        return ft.format(postDate);
    }



    public String formattedWorkDate(){
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        return ft.format(workDate);
    }
    //getters and setters
    public String getTitle() {
        return title;
    }

    public String getTaskId() {
        return taskId;
    }

    public Date getWorkDate() {
        return workDate;

    }
    public Date getPostDate() {
        return postDate;

    }
    public String getWorker() {
        return worker;
    }

    public String getStatus() {
        return status;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public int getWage() {
        return wage;
    }




    public static Comparator<Task> postDateSort = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {

            // if task 1 is posted before t2
            if (t1.postDate.compareTo(t2.postDate) > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    };


}
