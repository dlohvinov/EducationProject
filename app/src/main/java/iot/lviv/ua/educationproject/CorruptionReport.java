package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 3/26/2018.
 */

    public class CorruptionReport {
    private String studentName;
    private String lectorName;
    private String reportText;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLectorName() {
        return lectorName;
    }

    public void setLectorName(String lectorName) {
        this.lectorName = lectorName;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }
}
