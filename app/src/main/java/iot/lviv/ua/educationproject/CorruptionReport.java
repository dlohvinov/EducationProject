package iot.lviv.ua.educationproject;

public class CorruptionReport {
    private String studentName;
    private String lecturerName;
    private String reportText;
    private String dataAndTime;

    public CorruptionReport() {
    }

    public CorruptionReport(String studentName, String lecturerName, String reportText) {
        this.studentName = studentName;
        this.lecturerName = lecturerName;
        this.reportText = reportText;
        this.dataAndTime = Util.getDate() + " " +  Util.getTime();
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

    public String getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }
}

