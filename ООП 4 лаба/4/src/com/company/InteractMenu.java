package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InteractMenu {
    private final static Scanner in = new Scanner(System.in);
    private final static UniversityList UL = new UniversityList();

    public static void startMenu() {
        System.out.println();
        System.out.println("Что сделать со списком?");
        System.out.println("1 - добавить");
        System.out.println("2 - удалить");
        System.out.println("3 - найти");
        System.out.println("4 - вывести");
        System.out.println("5 - показать оценки");
        System.out.println("0 - выйти");

        String answer = getAnswer(Arrays.asList("1", "2", "3", "4", "5", "0"));
        startHub(answer);
    }

    private static String getAnswer(List<String> possible) {
        String answer = in.nextLine();
        while (!possible.contains(answer)) {
            System.out.println("нет такого варианта ответа, повторите ввод");
            answer = in.nextLine();
        }
        return answer;
    }

    private static int checkIntegerInput() {
        //проверка стринга на инт
        String input = in.nextLine();
        int result = -1;
        try {
            result = Integer.parseInt(input);
        }
        catch (NumberFormatException ex) {
            System.out.print("Вы ввели не число, повторите ввод : ");
            checkIntegerInput();
        }
        return result;
    }

    private static void startHub(String answerNumber) {
        switch (answerNumber) {
            case "1":
                addMenu();
                break;
            case "2":
                removeMenu();
                break;
            case "3":
                searchMenu();
                break;
            case "4":
                outputMenu();
                break;
            case "5":
                showMarksMenu();
                break;
            case "0":
                System.exit(0);
        }
    }

    private static void addMenu() {
        System.out.println();
        System.out.println("1 - добавить аттестацию");
        System.out.println("2 - добавить студента");
        System.out.println("0 - назад");

        String answer = getAnswer(Arrays.asList("1", "2", "0"));
        switch (answer) {
            case "1":
                addAttestation();
                break;
            case "2":
                addStudent();
                break;
        }
        startMenu();
    }

    private static void addAttestation() {
        System.out.println();
        String examOrTest, subject, teacherName, teacherSurname;
        int day, month, hour, minute;
        System.out.print("экзамен или зачет? : "); examOrTest = in.nextLine();
        System.out.print("название предмета : "); subject = in.nextLine();
        System.out.print("имя преподавателя : "); teacherName = in.nextLine();
        System.out.print("фамилия преподавателя : "); teacherSurname = in.nextLine();
        System.out.print("день проведения : "); day = checkIntegerInput();
        System.out.print("месяц проведения : "); month = checkIntegerInput();
        System.out.print("час проведения : "); hour = checkIntegerInput();
        System.out.print("минута проведения : "); minute = checkIntegerInput();
        UL.add(examOrTest, subject, day, month, hour, minute, teacherName, teacherSurname);
        addMenu();
    }

    private static void addStudent() {
        System.out.println();
        String name, surname, group;
        System.out.print("имя студента : "); name = in.nextLine();
        System.out.print("фамилия студента : "); surname = in.nextLine();
        System.out.print("группа студента : "); group = in.nextLine();
        UL.add(name, surname, group);
        addMenu();
    }

    private static void removeMenu() {
        System.out.println();
        System.out.println("1 - удалить аттестацию");
        System.out.println("2 - удалить студента");
        System.out.println("0 - назад");

        String answer = getAnswer(Arrays.asList("1", "2", "0"));
        switch (answer) {
            case "1":
                removeAttestation();
                break;
            case "2":
                removeStudent();
                break;
        }
        startMenu();
    }

    private static void removeAttestation() {
        System.out.println();
        System.out.println("1 - по всем данным");
        System.out.println("2 - по предмету");
        System.out.println("3 - по преподавателю");
        System.out.println("4 - по дню проведения");
        System.out.println("5 - по номеру в списке");
        System.out.println("0 - назад");
        String examOrTest, subject, teacherName, teacherSurname;
        int day, month, hour, minute;

        String answer = getAnswer(Arrays.asList("1", "2", "3", "4", "5", "0"));
        switch (answer) {
            case "1":
                System.out.print("название предмета : "); subject = in.nextLine();
                System.out.print("имя преподавателя : "); teacherName = in.nextLine();
                System.out.print("фамилия преподавателя : "); teacherSurname = in.nextLine();
                System.out.print("день проведения : "); day = checkIntegerInput();
                System.out.print("месяц проведения : "); month = checkIntegerInput();
                System.out.print("час проведения : "); hour = checkIntegerInput();
                System.out.print("минута проведения : "); minute = checkIntegerInput();
                UL.remove(subject, teacherName, teacherSurname, day, month, hour, minute);
                break;
            case "2":
                System.out.print("предмет : "); subject = in.nextLine();
                UL.remove(subject);
                break;
            case "3":
                System.out.print("имя : "); teacherName = in.nextLine();
                System.out.print("фамилия : "); teacherSurname = in.nextLine();
                UL.remove(teacherName, teacherSurname);
                break;
            case "4":
                System.out.print("день : "); day = checkIntegerInput();
                System.out.print("месяц : "); month = checkIntegerInput();
                UL.remove(day, month);
                break;
            case "5":
                UL.showAttestation();
                System.out.println();
                System.out.print("номер : "); int index = checkIntegerInput();
                UL.remove(index);
        }
        removeMenu();
    }

    private static void removeStudent() {
        System.out.println();
        System.out.println("1 - по имени, фамилии и группе");
        System.out.println("2 - по группе");
        System.out.println("3 - по номеру в списке");
        System.out.println("0 - назад");
        String name, surname, group;

        String answer = getAnswer(Arrays.asList("1", "2", "0"));
        switch (answer) {
            case "1":
                System.out.print("имя : "); name = in.nextLine();
                System.out.print("фамилия : "); surname = in.nextLine();
                System.out.print("группа : "); group = in.nextLine();
                UL.removeStudent(name, surname, group);
                break;
            case "2":
                System.out.print("группа : "); group = in.nextLine();
                UL.removeStudent(group);
                break;
            case "3":
                UL.showStudents();
                System.out.println();
                System.out.println("индекс : "); int index = checkIntegerInput();
                UL.removeStudent(index);
                break;
        }
        removeMenu();
    }

    private static void searchMenu() {
        System.out.println();
        System.out.println("1 - найти аттестацию");
        System.out.println("2 - найти студента");
        System.out.println("0 - назад");

        String answer = getAnswer(Arrays.asList("1", "2", "0"));
        switch (answer) {
            case "1":
                searchAttestation();
                break;
            case "2":
                searchStudent();
                break;
        }
        startMenu();
    }

    private static void searchAttestation() {
        System.out.println();
        System.out.println("1 - по предмету");
        System.out.println("2 - по дню сдачи");
        System.out.println("3 - по преподавателю");
        System.out.println("0 - назад");

        String answer = getAnswer(Arrays.asList("1", "2", "3", "0"));
        switch (answer) {
            case "1":
                String subject;
                System.out.print("предмет : "); subject = in.nextLine();
                UL.find(subject);
                break;
            case "2":
                int day, month;
                System.out.print("день : "); day = checkIntegerInput();
                System.out.print("месяц : "); month = checkIntegerInput();
                break;
            case "3":
                String teacherName, teacherSurname;
                System.out.print("имя : "); teacherName = in.nextLine();
                System.out.print("фамилия : "); teacherSurname = in.nextLine();
                UL.find(teacherName, teacherSurname);
                break;
        }
        searchMenu();
    }

    private static void searchStudent() {
        System.out.println();
        String group;
        System.out.print("группа : "); group = in.nextLine();
        UL.findStudent(group);
        searchMenu();
    }

    private static void outputMenu() {
        System.out.println();
        System.out.println("1 - вывести список аттестаций");
        System.out.println("2 - вывести список студентов");
        System.out.println("0 - назад");

        String answer = getAnswer(Arrays.asList("1", "2", "0"));
        switch (answer) {
            case "1":
                outputAttestation();
                break;
            case "2":
                outputStudent();
                break;
            case "0":
                startMenu();
                break;
        }
    }

    private static void outputAttestation() {
        System.out.println();
        UL.showAttestation();
        outputMenu();
    }

    private static void outputStudent() {
        System.out.println();
        UL.showStudents();
        outputMenu();
    }

    private static void showMarksMenu() {
        System.out.println();
        String name, surname, group;
        System.out.print("имя : "); name = in.nextLine();
        System.out.print("фамилия : "); surname = in.nextLine();
        System.out.print("группа : "); group = in.nextLine();
        UL.showMarks(name, surname, group);
        startMenu();
    }
}