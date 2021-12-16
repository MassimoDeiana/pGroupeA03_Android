package com.example.pgroupea03_android.dtos.student;

public class DtoOutputTokenStudent {
    private Integer idStudent;
    private String token;

    public DtoOutputTokenStudent(Integer idStudent, String token) {
        this.idStudent = idStudent;
        this.token = token;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
