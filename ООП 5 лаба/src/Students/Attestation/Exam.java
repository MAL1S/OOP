package Students.Attestation;

public class Exam extends Attestation {
    public Exam(String subject, String date, String teacherName) {
        super(subject, date, teacherName);
    }

    @Override
    public String toString() {
        return "Экзамен";
    }
}