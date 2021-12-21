package com.example.pgroupea03_android.dtos.note;

import java.time.LocalDateTime;
import java.util.Date;

public class DtoOutputNote {

    private Integer idNote;
    private Integer idTeacher;
    private Integer idStudent;
    private Integer idInterro;
    private String dateNote;
    private double result;
    private String message;

    public DtoOutputNote(Integer idNote, Integer idTeacher, Integer idStudent, Integer idInterro, String dateNote, double result, String message) {
        this.idNote = idNote;
        this.idTeacher = idTeacher;
        this.idStudent = idStudent;
        this.idInterro = idInterro;
        this.dateNote = dateNote;
        this.result = result;
        this.message = message;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
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

    public String getDateNote() {
        return dateNote;
    }

    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
