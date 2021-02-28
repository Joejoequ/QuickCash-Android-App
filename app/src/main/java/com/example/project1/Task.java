package com.example.project1;

import java.util.Date;

public class Task {
     static   final int PUBLISHED=1;
    static final int ACCEPTED=2;
    static final int UNPAID=3;
    static final int FINISHED=4;

    public String taskId;
    public String title;
    public String description;
    public Date workDate;
    public Date postDate;
    public int wage;
    public String publisher;
    public String worker;
    public String status;

    public Task(String title, String description, Date workDate,int wage,String publisher){

    }


    public void acceptTask(String worker){

    }

    public boolean available(){
        return true;
    }



}
