package com.example.oceny;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Grade implements Parcelable{
    private String subject;
    private int grade;
    private boolean checked;

    public Grade(String subject, int grade) {
        this.subject = subject;
        this.setGrade(grade);
        this.checked = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Grade(Parcel in) {
        subject = in.readString();
        grade = in.readInt();
        checked = in.readBoolean();
    }

    public static final Creator<Grade> CREATOR = new Creator<Grade>() {
        @Override
        public Grade createFromParcel(Parcel in) {
            return new Grade(in);
        }

        @Override
        public Grade[] newArray(int size) {
            return new Grade[size];
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(subject);
        dest.writeInt(grade);
        dest.writeBoolean(checked);
    }
}
