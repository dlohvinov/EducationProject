package iot.lviv.ua.educationproject;

public class Student extends User {

    private String numberOfGroup;

    public Student(String displayName, String uid, String numberOfGroup) {
        super(displayName, uid);
        this.numberOfGroup = numberOfGroup;
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }


}
