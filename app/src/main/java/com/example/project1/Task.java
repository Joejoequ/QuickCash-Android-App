package com.example.project1;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

public class Task {
    static final int PUBLISHED = 1;
    static final int ACCEPTED = 2;
    static final int UNPAID = 3;
    static final int FINISHED = 4;

    private String taskId;
    private String title;
    private String description;
    private Date workDate;
    private Date postDate;
    private int wage;
    private String publisher;
    private String worker;
    private int status;

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
        return this.status == PUBLISHED && workDate.after(new Date());
    }


    public String getFormattedDate(Date d) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss zzz");
        ft.setTimeZone(TimeZone.getTimeZone("America/Barbados"));
        return ft.format(d);
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

    public String getWorker() {
        return worker;
    }

    public int getStatus() {
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


    public Date getPostDate() {
        return postDate;
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
