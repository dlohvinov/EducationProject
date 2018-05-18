package iot.lviv.ua.educationproject;

import java.io.Serializable;

public class Student extends User {

    private String numberOfGroup;


    public Student(String displayName, String email, String uid, String numberOfGroup) {
        super(displayName, email, uid);
        this.numberOfGroup = numberOfGroup;
        this.setRights(STUDENT_TOKEN);
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }
}
