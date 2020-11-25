package com.company;

public class Main {

    private static void writeLine() {
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) {
        UniversityList UList = new UniversityList();
        UList.add("Иван", "Карбушев", "ИСТб-19-2");
        UList.add("Иван", "Егоров", "ИСТб-19-2");
        UList.add("Елизавета", "Барбаш", "ИСТб-19-1");
        UList.add();
        UList.add();
        UList.add();
        UList.add();
        UList.add();
        UList.add("экзамен", "проф. ориентация", 20, 12, 10, 00, "Сергей", "Бахвалов");
        UList.add("зачет", "английский язык", 22, 12, 8, 15, "Наталья", "Иванцова");


        UList.showAttestation(); writeLine();
        UList.showStudents(); writeLine();
        UList.showMarks("Иван", "Карбушев", "ИСТб-19-2"); writeLine();
        UList.showMarks("Иван", "Егоров", "ИСТб-19-2"); writeLine();
        UList.showMarks("Елизавета", "Барбаш", "ИСТб-19-1"); writeLine();
        UList.removeStudent("ИСТб-19-1");
        UList.removeStudent("Иван", "Егоров", "ИСТб-19-2");
        UList.add("Дарья", "Караваева", "ИСТб-19-2");
        UList.showStudents(); writeLine();
        UList.showMarks("Дарья", "Караваева", "ИСТб-19-2"); writeLine();
        UList.find("технологии программирования"); writeLine();
        UList.find(20, 12); writeLine();
        UList.findStudent("ИСТб-19-2"); writeLine();
        UList.remove("ООП");
        UList.remove("Сергей", "Бахвалов");
        UList.remove(UList.indexOf("английский язык", "Наталья", "Иванцова", 22, 12, 8, 15));
        UList.showAttestation(); writeLine();
        UList.showMarks("Иван", "Карбушев", "ИСТб-19-2"); writeLine();
    }
}
