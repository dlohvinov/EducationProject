package iot.lviv.ua.educationproject;

public class CorruptionReport {
    private String studentName;
    private String lecturerName;
    private String reportText;
    private String dateAndTime;

    public CorruptionReport() {
    }

    public CorruptionReport(String studentName, String lecturerName, String reportText) {
        this.studentName = studentName;
        this.lecturerName = lecturerName;
        this.reportText = reportText;
        this.dateAndTime = Util.getDate() + " " + Util.getTime();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}

