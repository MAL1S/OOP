package Students.Attestation;

public class Test extends Attestation {
    public Test(String subject, String date, String teacherName) {
        super(subject, date, teacherName);
    }

    @Override
    public String toString() {
        return "Зачет";
    }
}