package com.example.project1;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskUnitTest {
     static Task t;
     static Date workDate;
     static Date currentDate;
@BeforeClass
public static void setup(){
    currentDate=new Date();
    workDate=new Date();
    workDate.setMonth(6);
    t=new Task("title","description",workDate,50,"publisher");
}

        @Test
        public void testTitle() {
           assertEquals("title",t.getTitle());

        }

    @Test
    public void testDescription() {
        assertEquals("description",t.getDescription());
    }

    @Test
    public void testWage() {

        assertEquals(50,t.getWage());
    }

    @Test
    public void testWorkDate() {

        assertEquals(workDate,t.getWorkDate());
    }

    @Test
    public void testPublisher() {
        assertEquals("publisher",t.getPublisher());
    }

    @Test
    public void testTaskID() {
        assertNotNull(t.getTaskId());
    }

    @Test
    public void testStatus() {
        assertEquals(Task.PUBLISHED,t.getStatus());
    }
    @Test
    public void testAcceptTask() {
        Task task=new Task("title","description",workDate,50,"publisher");
        String worker="Bob";
        task.acceptTask(worker);
        assertEquals(Task.ACCEPTED,task.getStatus());
        assertEquals(worker,task.getWorker());
    }

    @Test
    public void testAvailable() {
        assertTrue(t.available());
    }

    @Test
    public void testAcceptedTask() {
        Task task=new Task("title","description",workDate,50,"publisher");
        task.acceptTask("BOB");
        assertFalse(task.available());
    }

    @Test
    public void testPastDueTask() {
        Date pastDate=new Date();
        pastDate.setYear(100); //set year to 2000
        Task pastTask=new Task("title","description",pastDate,50,"publisher");
        assertFalse(pastTask.available());
    }

    @Test
    public void testFormattedPostDate() {
    Date d=new Date();
    d.setYear(121);
        d.setMonth(2);
        d.setYear(121);
        d.setDate(2);
        d.setHours(7);
        d.setMinutes(30);
        d.setSeconds(23);

        assertEquals("2021-03-01 07:30:23",t.getFormattedDate(d).substring(0,19));
    }



}
