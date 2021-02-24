package com.company;

public class Test extends Attestation implements Event {
    private int ifPassed;

    public Test(String subject, int day, int month, int hour, int minute, String teacherFirstName, String teacherSecondName) {
        super(subject, day, month, hour, minute, teacherFirstName, teacherSecondName);
    }

    @Override
    public int pass() {
        ifPassed = rnd.nextInt(2);
        return ifPassed;
    }

    @Override
    public void arrange(int auditoriumNumber, int maxPeopleNumber) {
        System.out.printf("Зачет пройдет в аудитории %d вместительностью %d человек%n", auditoriumNumber, maxPeopleNumber);
    }
}
