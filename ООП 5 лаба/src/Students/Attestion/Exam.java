package Students.Attestion;

public class Exam extends Attestation {
    private int mark;

    public Exam(String subject, String date, String teacherName) {
        super(subject, date, teacherName);
    }

    @Override
    public String toString() {
        return "Экзамен";
    }
}