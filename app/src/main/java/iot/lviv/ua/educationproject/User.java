package iot.lviv.ua.educationproject;


import com.google.firebase.database.Exclude;

public class User {

    private String dispayName;
    private String email;
    private String uid;
    private String rights;
    private boolean isAllowed;

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

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public boolean isAllowed() {
        return isAllowed;
    }
}
