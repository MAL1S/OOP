package com.company;

import java.util.Random;

public abstract class Attestation {
    protected String subject;
    protected String date;
    protected String teacherName;

    public Attestation(String subject, String date, String teacherName) {
        this.subject = subject;
        this.date = date;
        this.teacherName = teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return this.date;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    //public abstract int pass();
}