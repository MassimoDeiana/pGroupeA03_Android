package com.example.pgroupea03_android.dtos.interrogationreport;

public class DtoOutputInterrogationReport {

    private Integer idInterro;
    private Integer idStudent;
    private String name;
    private String firstName;
    private double result;
    private int total;
    private String message;

    public DtoOutputInterrogationReport(Integer idInterro, Integer idStudent, String name, String firstName, double result, int total, String message) {
        this.idInterro = idInterro;
        this.idStudent = idStudent;
        this.name = name;
        this.firstName = firstName;
        this.result = result;
        this.total = total;
        this.message = message;
    }

    public Integer getIdInterro() {
        return idInterro;
    }

    public void setIdInterro(Integer idInterro) {
        this.idInterro = idInterro;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
