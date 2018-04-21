package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/21/2018.
 */

public class Rate {
    private int subjectId;
    private int studentId;
    private int lectureRate;
    private int practiceRate;
    private int laboratoryRate;
    //date


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

    public int getLectureRate() {
        return lectureRate;
    }

    public void setLectureRate(int lectureRate) {
        this.lectureRate = lectureRate;
    }

    public int getPracticeRate() {
        return practiceRate;
    }

    public void setPracticeRate(int practiceRate) {
        this.practiceRate = practiceRate;
    }

    public int getLaboratoryRate() {
        return laboratoryRate;
    }

    public void setLaboratoryRate(int laboratoryRate) {
        this.laboratoryRate = laboratoryRate;
    }
}
