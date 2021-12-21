package com.example.pgroupea03_android.dtos.lesson;

import java.util.Date;

public class DtoOutputLesson {

    private Integer idLesson;

    private String subject;

    public DtoOutputLesson(Integer idLesson, String subject) {
        this.idLesson = idLesson;
        this.subject = subject;
    }

    public Integer getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(Integer idLesson) {
        this.idLesson = idLesson;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return subject;
    }
}
