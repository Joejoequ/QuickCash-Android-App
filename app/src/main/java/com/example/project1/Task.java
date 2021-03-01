package com.example.project1;

import java.util.Date;

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


    public String getFormattedPostDate() {
        return "";
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

}
