package com.example.pgroupea03_android.dtos.lesson;

import java.util.Date;

public class DtoCreateLesson {

    private String subject;

    public DtoCreateLesson(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
