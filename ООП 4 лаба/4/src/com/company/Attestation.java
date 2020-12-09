package com.company;

import java.util.Random;

public abstract class Attestation {
    protected String subject;
    protected int day;
    protected int month;
    protected int hour;
    protected int minute;
    protected String teacherName;
    protected String teacherSurname;
    protected static Random rnd = new Random();

    public Attestation(String subject, int day, int month, int hour, int minute,
    String teacherName, String teacherSurname) {
        this.subject = subject;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
    }

    public String getSubject() {
        return subject;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setTeacherFirstName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTeacherSecondName(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public abstract int pass();

    public void getInfo() {
        System.out.printf("Аттестация по предмету - %s%n", subject);
        System.out.printf("Начало в %d%n:%d%n %d%n месяца, %d%n числа", hour, minute, month, day);
        System.out.printf("Проведет аттестацию %s%n %s%n", teacherSurname, teacherName);
    }
}
