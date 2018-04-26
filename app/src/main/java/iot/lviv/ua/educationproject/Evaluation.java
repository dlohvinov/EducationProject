package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/21/2018.
 */

public class Evaluation {
    private int subjectId;
    private int studentId;
    private int lectureEvaluation;
    private int practiceEvaluation;
    private int laboratoryEvaluation;
    //TODO: ADD A DATE

    public Evaluation() {
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLectureEvaluation() {
        return lectureEvaluation;
    }

    public void setLectureEvaluation(int lectureEvaluation) {
        this.lectureEvaluation = lectureEvaluation;
    }

    public int getPracticeEvaluation() {
        return practiceEvaluation;
    }

    public void setPracticeEvaluation(int practiceEvaluation) {
        this.practiceEvaluation = practiceEvaluation;
    }

    public int getLaboratoryEvaluation() {
        return laboratoryEvaluation;
    }

    public void setLaboratoryEvaluation(int laboratoryEvaluation) {
        this.laboratoryEvaluation = laboratoryEvaluation;
    }
}
