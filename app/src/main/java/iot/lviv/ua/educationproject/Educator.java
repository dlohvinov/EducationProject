package iot.lviv.ua.educationproject;

public class Educator extends User{

    public static final String EDUCATOR_TOKEN = "EDUCATOR";

    public Educator(final String displayName, final String email, String uid) {
         super(displayName, email, uid);
         this.setRights(EDUCATOR_TOKEN);
    }
}
