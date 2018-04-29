package iot.lviv.ua.educationproject;

import java.io.Serializable;

public class Student extends User implements Serializable{

    private String numberOfGroup;

    public Student(String displayName, String email, String numberOfGroup) {
        super(displayName, email);
        this.numberOfGroup = numberOfGroup;
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }
}
