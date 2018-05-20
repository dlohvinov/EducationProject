package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/21/2018.
 */

public class Evaluation {
    private TypeOfClass subjectId;
    private int studentId;
    private float evaluation;
    private String dateAndTime;
    private boolean show;

    public Evaluation() {
    }

    public Evaluation(TypeOfClass subjectId, int studentId, float evaluation) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.evaluation = evaluation;
        this.dateAndTime = Util.getDate() + " " + Util.getTime();
        this.show = true;
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

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
