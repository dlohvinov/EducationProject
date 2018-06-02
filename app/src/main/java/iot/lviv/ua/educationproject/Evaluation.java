package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/21/2018.
 */

public class Evaluation {
    private TypeOfClass typeOfClass;
    private String  studentId;
    private int evaluation;
    private String dateAndTime;
    private Subjects subjectId;

    public Evaluation() {
    }

    public Evaluation(TypeOfClass typeOfClass, String studentId, int evaluation, Subjects subjectId) {
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
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
