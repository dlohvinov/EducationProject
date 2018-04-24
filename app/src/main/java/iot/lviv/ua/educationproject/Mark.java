package iot.lviv.ua.educationproject;

/**
 * Created by Volodymyr on 18.04.2018.
 */

public class Mark {
    public String mark;
    public String userId;
    public String subject;

    public Mark() {
    }

    public Mark(String mark, String userId, String subject) {
        this.mark = mark;
        this.userId = userId;
        this.subject = subject;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
