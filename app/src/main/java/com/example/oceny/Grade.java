package com.example.oceny;

public class Grade {
    private String subject;
    private int grade;

    public Grade(String subject, int grade) {
        this.subject = subject;
        this.setGrade(grade);
    }

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
}
