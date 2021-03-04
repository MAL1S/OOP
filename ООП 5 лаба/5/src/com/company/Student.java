package com.company;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private String surname;
    private String group;
    private int passed = 0;
    private int failed = 0;
    private int examCount = 0;
    private int sumOfMarks = 0;
    public final Map<String, String> sessionMarks = new HashMap();

    public Student(String name, String surname, String group) {
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public double getAvgMark() {
        if (examCount == 0) return -1;
        return (double)sumOfMarks / examCount;
    }

    public int getExamCount() {
        return examCount;
    }

    public void setExamCount(int examCount) {
        this.examCount = examCount;
    }

    public int getSumOfMarks() {
        return sumOfMarks;
    }

    public void setSumOfMarks(int sumOfMarks) {
        this.sumOfMarks = sumOfMarks;
    }
}
