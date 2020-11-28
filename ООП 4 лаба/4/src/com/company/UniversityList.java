package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniversityList {
    private final static Challenge challenges = new Challenge();
    private final static List<Student> students = new ArrayList<>();

    public void add(String examOrTest, String subject, int day, int month, int hour, int minute,
                    String teacherName, String teacherSurname) {
        //потому что если нет студентов, то некому сдавать экзамены и зачеты
        if (students.isEmpty()) {
            System.out.println("Нет студентов");
            return;
        }
        if (examOrTest.toLowerCase().equals("экзамен")) challenges.add(new Exam(subject, day, month, hour, minute, teacherName, teacherSurname));
        else if (examOrTest.toLowerCase().equals("зачет")) challenges.add(new Test(subject, day, month, hour, minute, teacherName, teacherSurname));
        else return;
        for (var student : students) {
            setExams(student);
        }
    }

    public void add(String name, String surname, String group) {
        Student student = new Student(name, surname, group);
        students.add(student);
        setExams(student);
    }

    public void add() {
        Random rnd = new Random();
        int num = rnd.nextInt(2);
        if (num == 1) challenges.challengeList.add(new Exam());
        else challenges.challengeList.add(new Test());
    }

    private void setExams(Student student) {
        for (var exam : challenges.challengeList) {
            if (student.sessionMarks.get(exam.getSubject()) != null) continue;
            int mark = exam.pass();
            String examMark;
            if (exam instanceof Exam) {
                if (mark < 3) { examMark = "не сдан"; student.setFailed(student.getFailed() + 1); }
                else { examMark = String.valueOf(mark); student.setPassed(student.getPassed() + 1); }
                student.setSumOfMarks(student.getSumOfMarks() + mark);
                student.setExamCount(student.getExamCount() + 1);
            }
            else {
                if (mark == 1) { examMark = "сдан"; student.setPassed(student.getPassed() + 1); }
                else { examMark = "не сдан"; student.setFailed(student.getFailed() + 1); }
            }
            student.sessionMarks.put(exam.getSubject(), examMark);
        }
    }

    public double getAvg(String name, String surname) {
        for (var student : students) {
            if (student.getName().equals(name) && student.getSurname().equals(surname)) return student.getAvgMark();
        }
        return 0;
    }

    public void showAttestation() {
        challenges.showList();
    }

    public void showMarks(String name, String surname, String group) {
        System.out.printf("%s %s:%n", name, surname);
        for (var student : students) {
            if (student.getName().equals(name) && student.getSurname().equals(surname) && student.getGroup().equals(group)) {
                List<String> keys = new ArrayList<>(student.sessionMarks.keySet());
                for (String key : keys) {
                    String value = student.sessionMarks.get(key);
                    System.out.println(key + " " + value);
                }
                System.out.printf("Средний балл = %f%n", student.getAvgMark());
                return;
            }
        }
        System.out.println("Нет такого студента");
    }

    public void showStudents() {
        for (var student : students) {
            System.out.printf("%s %s, %s%n", student.getName(), student.getSurname(), student.getGroup());
        }
    }

    public void remove(String subject, String teacherName, String teacherSurname, int day, int month, int hour, int minute) {
        challenges.remove(subject, teacherName, teacherSurname, day, month, hour, minute);

        updateExForStudents();
    }

    public void remove(String subject) {
        boolean check = false;
        for (var item : challenges.challengeList) {
            if (item.getSubject().equals(subject)) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.printf("Нет аттестации по предмету %s%n", subject);
            return;
        }
        challenges.remove(subject);
        updateExForStudents();
    }

    public void remove(String teacherName, String teacherSurname) {
        boolean check = false;
        for (var item : challenges.challengeList) {
            if (item.getTeacherName().equals(teacherName) && item.getTeacherSurname().equals(teacherSurname)) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.printf("У преподавателя %s %s нет никаких аттестаций%n", teacherName, teacherSurname);
            return;
        }
        challenges.remove(teacherName, teacherSurname);
        updateExForStudents();
    }

    public void remove(int day, int month) {
        boolean check = false;
        for (var item : challenges.challengeList) {
            if (item.getDay() == day && item.getMonth() == month) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.printf("%d числа %d месяца нет никаких аттестаций%n", day, month);
            return;
        }
        challenges.remove(day, month);
        updateExForStudents();
    }

    public void remove(int ind) {
        if (challenges.challengeList.size() <= ind-1) {
            System.out.printf("Нет такой аттестации с индексом %d%n", ind);
            return;
        }
        challenges.remove(ind-1);
        updateExForStudents();
    }

    public void removeStudent(String name, String surname, String group) {
        boolean check = false;
        for (var student : students) {
            if (student.getName().equals(name) && student.getSurname().equals(surname) && student.getGroup().equals(group)) {
                check = true;
                break;
            }
        }
        if (check)
            students.removeIf(student -> student.getName().equals(name) && student.getSurname().equals(surname) && student.getGroup().equals(group));
        else System.out.printf("Нет студента %s %s в группе %s%n", name, surname, group);
    }

    public void removeStudent(String group) {
        boolean check = false;
        for (var student : students) {
            if (student.getGroup().equals(group)) {
                check = true;
                break;
            }
        }
        if(check)
            students.removeIf(student -> student.getGroup().equals(group));
        else System.out.printf("Нет такой группы - %s%n", group);
    }

    public void removeStudent(int index) {
        students.remove(index);
    }

    private void updateExForStudents() {
        for (var student : students) {
            List<String> keys = new ArrayList<>(student.sessionMarks.keySet());
            for (String key : keys) {
                if (challenges.indexOf(key) == -1) {
                    student.sessionMarks.remove(key);
                }
            }
        }
    }

    /*
    public int indexOf(String subject, String teacherName, String teacherSurname, int day, int month, int hour, int minute) {
        return  challenges.indexOf(subject, teacherName, teacherSurname, day, month, hour, minute);
    }
    */

    public void find(String subject) {
        challenges.find(subject);
    }

    public void find(int day, int month) {
        challenges.find(day, month);
    }

    public void find(String teacherName, String teacherSurname) {
        challenges.find(teacherName, teacherSurname);
    }

    public void findStudent(String group) {
        for (var student : students) {
            if (student.getGroup().equals(group)) System.out.printf("%s %s%n", student.getName(), student.getSurname());
        }
    }
}
