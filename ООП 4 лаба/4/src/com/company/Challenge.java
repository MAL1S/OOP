package com.company;

import java.util.ArrayList;
import java.util.List;

public class Challenge {
    public final List<Attestation> challengeList = new ArrayList<>();

    public void add(Exam exam) {
        challengeList.add(exam);
    }

    public void add(Test test) {
        challengeList.add(test);
    }

    public void remove(String subject, String teacherName, String teacherSurname, int day, int month, int hour, int minute) {
        challengeList.removeIf(item -> item.getSubject().equals(subject) && item.getTeacherName().equals(teacherName)
                && item.getTeacherSurname().equals(teacherSurname) && item.getDay() == day && item.getMonth() == month
                && item.getHour() == hour && item.getMinute() == minute);
    }

    public void remove(String subject) {
        challengeList.removeIf(item -> item.getSubject().equals(subject));
    }

    public void remove(String teacherName, String teacherSurname) {
        challengeList.removeIf(item -> item.getTeacherName().equals(teacherName) && item.getTeacherSurname().equals(teacherSurname));
    }

    public void remove(int day, int month) {
        challengeList.removeIf(item -> item.getDay() == day && item.getMonth() == month);
    }

    public void remove(int ind) {
        challengeList.remove(ind);
    }

    /*public int indexOf(String subject, String teacherName, String teacherSurname, int day, int month, int hour, int minute) {
        for (var item : challengeList) {
            if (item.getSubject().equals(subject) && item.getTeacherName().equals(teacherName)
                    && item.getTeacherSurname().equals(teacherSurname) && item.getDay() == day && item.getMonth() == month
                    && item.getHour() == hour && item.getMinute() == minute)
                return challengeList.indexOf(item);
        }
        return -1;
    }*/

    public int indexOf(String subject) {
        for (var item : challengeList) {
            if (item.getSubject().equals(subject))
                return challengeList.indexOf(item);
        }
        return -1;
    }

    public Attestation find(int day, int month) {
        for (var item : challengeList) {
            if (item.getDay() == day && item.getMonth() == month) {
                String addZero = "";
                if (item.getMinute() < 10) addZero = "0";
                System.out.printf("%s %s %s в %d:%s%d%n",
                        item.getSubject(), item.getTeacherName(), item.getTeacherSurname(), item.getHour(), addZero, item.getMinute());
            }
        }
        return null;
    }

    public Attestation find(String subject) {
        for (var item : challengeList) {
            if (item.getSubject().equals(subject)) {
                String addZero = "";
                if (item.getMinute() < 10) addZero = "0";
                System.out.printf("%s %s %d числа %d месяца в %d:%s%d%n",
                        item.getTeacherName(), item.getTeacherSurname(),  item.getDay(), item.getMonth(), item.getHour(), addZero, item.getMinute());
            }
        }
        return null;
    }

    public Attestation find(String teacherName, String teacherSurname) {
        for (var item : challengeList) {
            if (item.getTeacherName().equals(teacherName) && item.getTeacherSurname().equals(teacherSurname)) {
                String addZero = "";
                if (item.getMinute() < 10) addZero = "0";
                System.out.printf("%s %d числа %d месяца в %d:%s%d%n",
                        item.getSubject(),  item.getDay(), item.getMonth(), item.getHour(), addZero, item.getMinute());
            }
        }
        return null;
    }

    public void showList() {
        int count = 1;
        System.out.println("Аттестационный список:");
        for (var item : challengeList) {
            String testOrExam;
            if (item instanceof Exam) {
                testOrExam = "Экзамен";
            } else {
                testOrExam = "Зачет";
            }
            String addZero = "";
            if (item.getMinute() < 10) addZero = "0";
            System.out.printf("%d)%s по %s у преподавателя %s %s пройдет %d месяца, %d числа в %d:%s%d%n",
                    count++, testOrExam, item.getSubject(), item.getTeacherSurname(), item.getTeacherName(), item.getMonth(), item.getDay(), item.getHour(), addZero, item.getMinute());

        }
    }
}
