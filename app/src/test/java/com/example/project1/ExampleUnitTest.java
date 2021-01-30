package com.example.project1;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//kessel123
public class ExampleUnitTest {
    static MainActivity mainActivity;
    static SignUpPage signUp;
    static User user;

    @BeforeClass
    public static void setup(){
        mainActivity = new MainActivity();
        signUp = new SignUpPage();
        user = new User();
    }

    /**AT3:1-1**/
    @Test
    public void checkIfUserNameIsEmpty() {
        User temp1 = new User("","abc");
        assertTrue(user.isEmptyPassword(temp1));
        User temp2 = new User("abd","def");
        assertFalse(user.isEmptyPassword(temp2));
    }

    /**AT3:1-2**/
    @Test
    public void checkIfPasswordIsEmpty() {
        User temp1 = new User("abc","");
        assertTrue(user.isEmptyPassword(temp1));
        User temp2 = new User("abd","deff");
        assertFalse(user.isEmptyPassword(temp2));
    }

    @Test
    /**AT3:1-3**/
    public void checkValidUsername() {
        User temp1 = new User("and123","1111");
        assertTrue(user.isValidUserName(temp1));
        User temp2 = new User("ans@#<", "1111");
        assertFalse(user.isValidUserName(temp2));
        // check long UserName
        User temp3 = new User("aaaaaaaaaaaaaaaaaaaa","1111");
        assertFalse(user.isValidUserName(temp3));
    }

    @Test
    /**AT3:1-4**/
    public void checkValidPassword() {
        // check short password
        User temp1 = new User("abc","1111");
        assertFalse(user.isEmptyPassword(temp1));
        // check long password
        User temp2 = new User("abd","11111111111111111111");
        assertFalse(user.isValidPassword(temp2));
        // password only contain letters of number
        User temp4 = new User("abc","1111111111");
        User temp5 = new User("abc","aaaaaaaaaa");
        assertFalse(user.isValidPassword(temp4));
        assertFalse(user.isValidPassword(temp5));
        // valid password
        User temp3 = new User("abd","123adf@#$12");
        assertTrue(user.isValidPassword(temp3));
    }
}