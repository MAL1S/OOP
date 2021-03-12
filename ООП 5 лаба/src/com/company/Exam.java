package com.company;

public class Exam extends Attestation implements Event {
    private int mark;

    public Exam(String subject, String date, String teacherName) {
        super(subject, date, teacherName);
    }

//    @Override
//    public int pass() {
//        mark = rnd.nextInt(5) + 1;
//        return mark;
//    }

    @Override
    public String toString() {
        return "Экзамен";
    }

    @Override
    public void arrange(int auditoriumNumber, int maxPeopleNumber) {
        System.out.printf("Экзамен пройдет в аудитории %d вместительностью %d человек%n", auditoriumNumber, maxPeopleNumber);
    }
}