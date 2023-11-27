package com.example.oceny;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GradeModel implements Parcelable{
    private String subject;
    private int grade;

    public GradeModel(String subject, int grade) {
        this.subject = subject;
        this.setGrade(grade);
    }

    protected GradeModel(Parcel in) {
        subject = in.readString();
        grade = in.readInt();
    }

    public static final Creator<GradeModel> CREATOR = new Creator<GradeModel>() {
        @Override
        public GradeModel createFromParcel(Parcel in) {
            return new GradeModel(in);
        }

        @Override
        public GradeModel[] newArray(int size) {
            return new GradeModel[size];
        }
    };

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGrade(int grade) {
        if(grade > 5 || grade < 2)
            throw new IllegalArgumentException("grade should be between 2 and 5");
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(subject);
        dest.writeInt(grade);
    }
}
