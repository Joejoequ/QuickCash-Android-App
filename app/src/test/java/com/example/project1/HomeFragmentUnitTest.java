package com.example.project1;
import com.example.project1.ui.home.HomeFragment;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.*;


public class HomeFragmentUnitTest {

    Date workDate=new Date();
    Task test1 = new Task("title","description",workDate,50,"publisher");
    Task test2 = new Task("title1","description",workDate,50,"publisher");
    Task test3 = new Task("ti","description",workDate,50,"publisher");
    HomeFragment homeFragment = new HomeFragment();

    //check if keyword is match with task title in the server
    @Test
    public void checkMatchT(){
        ArrayList<Task> testTask = new ArrayList<>();
        String keyword = "title";
        testTask.add(test1);testTask.add(test2);testTask.add(test3);
        ArrayList<Task> result = homeFragment.Search(testTask,keyword);
        assertTrue(result.size()==2);
    }

    //check if keyword is not match with task title in the server
    @Test
    public void checkMatchF(){
        ArrayList<Task> testTask = new ArrayList<>();
        String keyword = "abc";
        testTask.add(test1);testTask.add(test2);testTask.add(test3);
        ArrayList<Task> result = homeFragment.Search(testTask,keyword);
        assertTrue(result.size()==0);
    }
}