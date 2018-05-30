package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/21/2018.
 */

public class Evaluation {
    private TypeOfClass typeOfClass;
    private User studentId;
    private float evaluation;
    private String dateAndTime;
    private Subjects subjectId;

    public Evaluation() {
    }

    public Evaluation(TypeOfClass typeOfClass, User studentId, float evaluation, Subjects subjectId) {
        this.typeOfClass = typeOfClass;
        this.studentId = studentId;
        this.evaluation = evaluation;
        this.dateAndTime = Util.getDate() + " " + Util.getTime();
        this.subjectId = subjectId;
    }

    public TypeOfClass getTypeOfClass() {
        return typeOfClass;
    }

    public void setTypeOfClass(TypeOfClass typeOfClass) {
        this.typeOfClass = typeOfClass;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
    }
}
