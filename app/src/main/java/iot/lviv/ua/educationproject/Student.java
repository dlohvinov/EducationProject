package iot.lviv.ua.educationproject;

public class Student extends User {

    private String numberOfGroup;

    public Student(String firstName, String lastName, String numberOfGroup) {
        super(firstName, lastName);
        this.numberOfGroup = numberOfGroup;
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }


}
