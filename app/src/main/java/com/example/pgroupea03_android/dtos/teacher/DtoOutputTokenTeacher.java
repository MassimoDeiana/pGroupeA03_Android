package com.example.pgroupea03_android.dtos.teacher;

public class DtoOutputTokenTeacher {
    private Integer idTeacher;
    private String token;

    public DtoOutputTokenTeacher(Integer idTeacher, String token) {
        this.idTeacher = idTeacher;
        this.token = token;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
