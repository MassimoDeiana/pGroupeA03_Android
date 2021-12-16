package com.example.pgroupea03_android.dtos.student;

import java.util.Date;

public class DtoOutputStudent {
    private Integer idStudent,idClass;
    private String name,firstName,mail;
    private Date birthDate;

    public DtoOutputStudent(int idStudent, int idClass, String name, String firstName, String mail, Date birthDate) {
        this.idStudent = idStudent;
        this.idClass = idClass;
        this.name = name;
        this.firstName = firstName;
        this.mail = mail;
        this.birthDate = birthDate;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
