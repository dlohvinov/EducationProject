package iot.lviv.ua.educationproject;

public class Student extends User {

    private String numberOfGroup;
    private int id;

    public Student(String firstName, String lastName, String numberOfGroup, int id) {
        super(firstName, lastName);
        this.numberOfGroup = numberOfGroup;
        this.id = id;
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }


}
