package iot.lviv.ua.educationproject;

/**
 * Created by Volodymyr on 01.04.2018.
 */

public class UserManager {

    private static UserManager userManager;

    public void createStudent(String firstName, String lastName, String numberOfGroup){
        Student student = new Student(firstName, lastName, numberOfGroup);
    }
    
    private UserManager(){}

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
            return userManager;
        } else {
            return userManager;
        }
    }
}
