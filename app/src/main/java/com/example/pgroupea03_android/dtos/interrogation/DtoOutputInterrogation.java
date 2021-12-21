package com.example.pgroupea03_android.dtos.interrogation;

import android.os.Parcel;
import android.os.Parcelable;

public class DtoOutputInterrogation implements Parcelable {

    private Integer idInterro;

    private Integer idTeacher;

    private Integer idLesson;

    private String subject;

    private Integer total;

    public DtoOutputInterrogation(Integer idInterro, Integer idTeacher, Integer idLesson, String subject, Integer total) {
        this.idInterro = idInterro;
        this.idTeacher = idTeacher;
        this.idLesson = idLesson;
        this.subject = subject;
        this.total = total;
    }

    protected DtoOutputInterrogation(Parcel in) {
        if (in.readByte() == 0) {
            idInterro = null;
        } else {
            idInterro = in.readInt();
        }
        if (in.readByte() == 0) {
            idTeacher = null;
        } else {
            idTeacher = in.readInt();
        }
        if (in.readByte() == 0) {
            idLesson = null;
        } else {
            idLesson = in.readInt();
        }
        subject = in.readString();
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readInt();
        }
    }

    public static final Creator<DtoOutputInterrogation> CREATOR = new Creator<DtoOutputInterrogation>() {
        @Override
        public DtoOutputInterrogation createFromParcel(Parcel in) {
            return new DtoOutputInterrogation(in);
        }

        @Override
        public DtoOutputInterrogation[] newArray(int size) {
            return new DtoOutputInterrogation[size];
        }
    };

    public Integer getIdInterro() {
        return idInterro;
    }

    public void setIdInterro(Integer idInterro) {
        this.idInterro = idInterro;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
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

    @Override
    public String toString() {
        return subject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (idInterro == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idInterro);
        }
        if (idTeacher == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idTeacher);
        }
        if (idLesson == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idLesson);
        }
        parcel.writeString(subject);
        if (total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(total);
        }
    }
}
