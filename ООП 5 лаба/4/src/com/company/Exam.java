package com.company;

public class Exam extends Attestation implements Event {
    private int mark;

    public Exam(String subject, int day, int month, int hour, int minute, String teacherFirstName, String teacherSecondName) {
        super(subject, day, month, hour, minute, teacherFirstName, teacherSecondName);
    }

    @Override
    public int pass() {
        mark = rnd.nextInt(5) + 1;
        return mark;
    }

    @Override
    public void arrange(int auditoriumNumber, int maxPeopleNumber) {
        System.out.printf("Экзамен пройдет в аудитории %d вместительностью %d человек%n", auditoriumNumber, maxPeopleNumber);
    }
}
