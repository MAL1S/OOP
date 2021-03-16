package Students.Attestion;

public class Test extends Attestation {
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
}