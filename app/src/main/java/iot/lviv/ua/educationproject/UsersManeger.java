package iot.lviv.ua.educationproject;

/**
 * Created by Volodymyr on 01.04.2018.
 */

public class UsersManeger {

    public void createRepresentativeOfTheCluster(String firstName, String lastName){
        RepresentativeOfTheCluster representativeOfTheCluster = new RepresentativeOfTheCluster(firstName, lastName);
    }

    public void createStudent(String firstName, String lastName, String numberOfGroup){
        Student student = new Student(firstName, lastName, numberOfGroup, createId());
    }

    public int createId(){
        //Взаємодія з Firebase
        int id = 0;
        return id;
    }
}
