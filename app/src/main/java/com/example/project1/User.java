package com.example.project1;

public class User {
    public String userName;
    public String password;

    public User(){

    }
    // Initialization of user
    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    // User can change their password as they want
    public void changePassword(String password) {
        this.password = password;
    }

    // User can change their userName as they want
    public void changeName(String name) {
        this.userName = name;
    }
    // Check if enter an empty userName
    public boolean isEmptyUserName(User user) {
        return true;
    }

    // Check if enter an empty password
    public boolean isEmptyPassword(User user) {
        return true;
    }

    // Check if enter a valid username, should be less than 16 digits
    public boolean isValidUserName() {
        boolean validation = false;
        char[] convert = userName.toCharArray();

        if(convert.length<=16&&convert.length>=4){
            int count = 0;
            for(int i = 0; i<convert.length; i++){
                int ascii = convert[i];
                if((ascii>=48&&ascii<=57)||(ascii>=65&&ascii<=90)||(ascii>=97&&ascii<=122)){
                    count++;
                }
            }
            if(count == userName.length()) validation = true;
        }

        return validation;
    }

    // Check if enter a valid password, should be no less than 8 digits, no more than 16 letters
    // The password need to include at least a combination of letters and number
    public boolean isValidPassword() {
        boolean validation = false;
        char[] convert = password.toCharArray();

        if(convert.length<=16&&convert.length>=8){
            int count = 0;
            for(int i = 0; i<convert.length; i++){
                int ascii = convert[i];
                if((ascii>=48&&ascii<=57)||(ascii>=65&&ascii<=90)||(ascii>=97&&ascii<=122)){
                    count++;
                }
            }
            if(count == password.length()) validation = true;
        }

        return validation;
    }

}
