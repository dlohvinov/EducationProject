package iot.lviv.ua.educationproject;

/**
 * Created by Volodymyr on 01.04.2018.
 */

public class UserManager {


    private static UserManager userManager;
    private static User currentUser;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
            return userManager;
        } else {
            return userManager;
        }
    }

    public User getCurrentUser() {
        if (currentUser == null) {
            currentUser = new User();
            return currentUser;
        } else {
            return currentUser;
        }
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void pushUserToDatabase() {
        FirebaseManager.getInstance().getRootDatabaseReference().child("Users")
                .child(currentUser.getUid()).setValue(currentUser);
    }
}
