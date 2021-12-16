package com.example.pgroupea03_android.dtos.teacher;

public class DtoCreateTeacher {

    private String lastName,firstName,mail,token;

    public DtoCreateTeacher(String lastName, String firstName, String mail, String token) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.mail = mail;
        this.token = token;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
