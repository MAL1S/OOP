package com.company;

public class Exam extends Attestation{
    private int mark;

    public Exam() {
        super(subjects[counter],
                rnd.nextInt(28) + 1,
                rnd.nextInt(12) + 1,
                rnd.nextInt(25),
                rnd.nextInt(61),
                teacherNames[counter],
                teacherSurnames[counter]);
        if (counter < 5) counter++; //чтобы избежать дублирования предметов
    }

    public Exam(String subject, int day, int month, int hour, int minute, String teacherFirstName, String teacherSecondName) {
        super(subject, day, month, hour, minute, teacherFirstName, teacherSecondName);
    }

    @Override
    public int pass() {
        mark = rnd.nextInt(5) + 1;
        return mark;
    }
}
