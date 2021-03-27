
package Students.Attestation;

import java.util.HashMap;

public class Student {
    private final String name;
    private final String enrolDate;
    private final String birthDate;
    private final HashMap<Attestation, String> sessionMarks = new HashMap<>();
    private final String studentId;
    private String phoneNumber;
    private String group;


    public Student(String studentId, String name, String group, String enrolDate, String phoneNumber, String birthDate) {
        this.studentId = studentId;
        this.name = name;
        this.group = group;
        this.enrolDate = enrolDate;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public void setMark(Attestation attestation, String mark) {
        sessionMarks.put(attestation, mark);
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getEnrolDate() {
        return enrolDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HashMap<Attestation, String> getSessionMarks() {
        return sessionMarks;
    }

    public void deleteAttestation(Attestation attestation) {
        sessionMarks.remove(attestation);
    }
}
