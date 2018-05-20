package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/21/2018.
 */

public class Evaluation {
    private TypeOfClass subjectId;
    private int studentId;
    private float evaluation;
    private String dateAndTime;

    public Evaluation() {
    }

    public Evaluation(TypeOfClass subjectId, int studentId, float evaluation) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.evaluation = evaluation;
        this.dateAndTime = Util.getDate() + " " + Util.getTime();
    }

    public TypeOfClass getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(TypeOfClass subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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
}
