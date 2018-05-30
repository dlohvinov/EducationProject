package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/7/2018.
 */


//TEMPLATE
//Havent ended because of abscence of scale logic
public class Subject {
    private String name;
    private Subjects id;

    public Subject() {
    }

    private String subjectName;
    private String lectorName;
    private int progress;

    public Subject(String subjectName, String lectorName, int progress, Subjects id) {
        this.subjectName = subjectName;
        this.lectorName = lectorName;
        this.progress = progress;
        this.id = id;
    }

    public String getLectorName() {
        return lectorName;

    }
    public String getSubjectName() {
        return subjectName;
    }


    public int getProgress() {
        return progress;
    }

    public Subjects getId() {
        return id;
    }

    public void setId(Subjects id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
