package com.example.project1;

import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
           //assertEquals("title",t.getTitle());

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
        String worker="Bob";
        t.acceptTask(worker);
        assertEquals(Task.ACCEPTED,t.getStatus());
        assertEquals(worker,t.getWorker());
    }

    @Test
    public void testAvailable() {
        assertEquals(Task.PUBLISHED,t.getStatus());
    }

    @Test
    public void testFormattedPostDate() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss zzz");
        ft.setTimeZone(TimeZone.getTimeZone("America/Barbados"));
        assertEquals(ft.format(workDate),t.getFormattedPostDate());
    }



}
