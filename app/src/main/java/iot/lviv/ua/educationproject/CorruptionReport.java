package iot.lviv.ua.educationproject;

public class CorruptionReport {
    private String studentContacts;
    private String lecturerName;
    private String reportText;
    private String dateAndTime;
    private boolean visibility;

    public CorruptionReport() {
    }

    public CorruptionReport(String studentContacts, String lecturerName, String reportText) {
        this.studentContacts = studentContacts;
        this.lecturerName = lecturerName;
        this.reportText = reportText;
        this.dateAndTime = Util.getDate() + " " + Util.getTime();
        this.visibility = false;
    }

    public String getStudentContacts() {
        return studentContacts;
    }

    public void setStudentContacts(String studentContacts) {
        this.studentContacts = studentContacts;
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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}

