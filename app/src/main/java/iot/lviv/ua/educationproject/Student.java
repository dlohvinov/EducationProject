package iot.lviv.ua.educationproject;

public class Student extends User {

    private String numberOfGroup;

    public Student(String displayName, String email, String numberOfGroup) {
        super(displayName, email);
        this.numberOfGroup = numberOfGroup;
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }
}
