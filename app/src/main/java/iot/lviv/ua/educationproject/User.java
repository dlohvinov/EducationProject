package iot.lviv.ua.educationproject;


import com.google.firebase.database.Exclude;

public class User {

    private String dispayName;
    private String email;
    private String uid;
    public User(){}


    public User(String displayName, String email, String uid) {
        this.dispayName = displayName;
        this.email = email;
        this.uid = uid;
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

    @Exclude
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
