package iot.lviv.ua.educationproject;

import com.google.firebase.database.Exclude;

public class User {

    private String dispayName;
    @Exclude
    private String email;
    public User(){}


    public User(String displayName, String email) {
        this.dispayName = displayName;
        this.email = email;
    }

    public String getDispayName() {
        return dispayName;
    }

    public void setDispayName(String dispayName) {
        this.dispayName = dispayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
