package com.example.pgroupea03_android.dtos.result;

public class DtoOutputResult {
    private Integer idStudent;
    private Integer idInterro;
    private String interro;
    private double result;
    private int total;
    private Integer idLesson;
    private String message;

    public DtoOutputResult(Integer idStudent, Integer idInterro, String interro, double result, int total, Integer idLesson, String message) {
        this.idStudent = idStudent;
        this.idInterro = idInterro;
        this.interro = interro;
        this.result = result;
        this.total = total;
        this.idLesson = idLesson;
        this.message = message;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public Integer getIdInterro() {
        return idInterro;
    }

    public void setIdInterro(Integer idInterro) {
        this.idInterro = idInterro;
    }

    public String getInterro() {
        return interro;
    }

    public void setInterro(String interro) {
        this.interro = interro;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Integer getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(Integer idLesson) {
        this.idLesson = idLesson;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DtoOutputResult{" +
                "idStudent=" + idStudent +
                ", idInterro=" + idInterro +
                ", interro='" + interro + '\'' +
                ", result=" + result +
                ", total=" + total +
                ", idLesson=" + idLesson +
                ", message='" + message + '\'' +
                '}';
    }
}
