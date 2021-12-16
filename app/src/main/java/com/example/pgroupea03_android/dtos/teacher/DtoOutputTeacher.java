package com.example.pgroupea03_android.dtos.teacher;

public class DtoOutputTeacher {

    private Integer idTeacher;
    private String name, firstname,mail;

    public DtoOutputTeacher(Integer idTeacher, String lastName, String firstName, String mail) {
        this.idTeacher = idTeacher;
        this.name = lastName;
        this.firstname = firstName;
        this.mail = mail;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "DtoOutputTeacher{" +
                "idTeacher=" + idTeacher +
                ", lastName='" + name + '\'' +
                ", firstName='" + firstname + '\'' +
                ", mail='" + mail + '\'' + '}';
    }
}
