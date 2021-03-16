package Students.Attestion;

public class Exam extends Attestation {
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
}