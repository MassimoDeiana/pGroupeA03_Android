package com.example.pgroupea03_android.dtos.interrogation;

public class DtoOutputInterrogation {

    private Integer idInterro;

    private Integer idLesson;

    private String subject;

    private Integer total;

    public DtoOutputInterrogation(Integer idInterro, Integer idLesson, String subject, Integer total) {
        this.idInterro = idInterro;
        this.idLesson = idLesson;
        this.subject = subject;
        this.total = total;
    }

    public Integer getIdInterro() {
        return idInterro;
    }

    public void setIdInterro(Integer idInterro) {
        this.idInterro = idInterro;
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
