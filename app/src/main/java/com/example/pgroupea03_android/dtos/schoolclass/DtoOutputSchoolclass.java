package com.example.pgroupea03_android.dtos.schoolclass;

public class DtoOutputSchoolclass {

    private Integer idclass;
    private String name;
    private int year;
    private int nbStudent;

    public DtoOutputSchoolclass(Integer idclass, String name, int year, int nbStudent) {
        this.idclass = idclass;
        this.name = name;
        this.year = year;
        this.nbStudent = nbStudent;
    }

    public Integer getIdclass() {
        return idclass;
    }

    public void setIdclass(Integer idclass) {
        this.idclass = idclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNbStudent() {
        return nbStudent;
    }

    public void setNbStudent(int nbStudent) {
        this.nbStudent = nbStudent;
    }

    @Override
    public String toString() {
        return "DtoOutputSchoolclass{" +
                "idClass=" + idclass +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", nbStudent=" + nbStudent +
                '}';
    }
}
