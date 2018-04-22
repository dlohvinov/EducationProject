package iot.lviv.ua.educationproject;

/**
 * Created by Volodymyr on 01.04.2018.
 */

public class UserManager {

    private static UserManager userManager;

    private UserManager(){}

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
            return userManager;
        } else {
            return userManager;
        }
    }

    public void pushStudentToDatabase(Student student) {
        FirebaseManager.getInstance().getRootDatabaseReference().push().child("Users").child("Students").setValue(student);
    }

    public void pushEducatorToDatabase(Educator educator) {
        FirebaseManager.getInstance().getRootDatabaseReference().push().child("Users").child("Cluster").setValue(educator);
    }
}
