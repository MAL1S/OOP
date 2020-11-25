package com.company;

import java.util.Random;

public abstract class Attestation {
    private String subject;
    private int day;
    private int month;
    private int hour;
    private int minute;
    private String teacherName;
    private String teacherSurname;
    protected static Random rnd = new Random();
    protected final static String[] subjects = new String[]{"ООП", "мобильная разработка", "компьютерная графика", "технологии программирования", "дискретная математика"};
    protected final static String[] teacherNames = new String[]{"Татьяна", "Вадим", "Анастасия", "Зинаида",  "Людмила"};
    protected final static String[] teacherSurnames = new String[]{"Маланова", "Аршинский", "Исаева", "Бахвалова",  "Носырева"};
    protected static int counter = 0; //для определения фамилии и имени преподавателя

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
