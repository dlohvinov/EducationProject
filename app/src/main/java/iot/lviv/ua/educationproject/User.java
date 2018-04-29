package iot.lviv.ua.educationproject;

public class User {

    private String dispayName;
    private String email;
    private boolean adminRequest;
    public User(){}

    public User(String displayName, String email) {
        this.dispayName = displayName;
        this.email = email;
    }
}
