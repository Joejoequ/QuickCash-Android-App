package com.example.project1;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class LogInUnitTest {

    @Test
    //if Login Activity successfully linked to database
    public void DatabaseConnection() {

        assertEquals("Connection Error with Firebase Realtime Database", true, LogIn.connection());

    }

    @Test
    //UserName does not exist in database
    public void checkAccountTest1() {
        String UserName="lgtest";
        String password="00";

        assertFalse(LogIn.checkAccount(UserName,password));

    }

    @Test
    //UserName exists, but Password is not correct
    public void checkAccountTest2() {
        String UserName="logintest";
        String password="1002";
        assertFalse(LogIn.checkAccount(UserName,password));

    }

    @Test
// UserName and Password is both correct
    public void checkAccountTest3() {
        String UserName="logintest";
        String password="123456";
        assertTrue(LogIn.checkAccount(UserName,password));

    }






}
