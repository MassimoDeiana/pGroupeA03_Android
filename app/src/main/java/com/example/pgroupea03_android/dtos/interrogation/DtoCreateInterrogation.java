package com.example.pgroupea03_android.dtos.interrogation;

public class DtoCreateInterrogation {

    private Integer idTeacher;

    private Integer idLesson;

    private String subject;

    private Integer total;

    public DtoCreateInterrogation(Integer idTeacher, Integer idLesson, String subject, Integer total) {
        this.idTeacher = idTeacher;
        this.idLesson = idLesson;
        this.subject = subject;
        this.total = total;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
