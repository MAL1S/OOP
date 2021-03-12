package com.company;

public class Test extends Attestation implements Event {
    private int ifPassed;

    public Test(String subject, String date, String teacherName) {
        super(subject, date, teacherName);
    }

//    @Override
//    public int pass() {
//        ifPassed = rnd.nextInt(2);
//        return ifPassed;
//    }

    @Override
    public String toString() {
        return "Зачет";
    }

    @Override
    public void arrange(int auditoriumNumber, int maxPeopleNumber) {
        System.out.printf("Зачет пройдет в аудитории %d вместительностью %d человек%n", auditoriumNumber, maxPeopleNumber);
    }
}