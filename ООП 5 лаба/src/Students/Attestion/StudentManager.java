package Students.Attestion;

import Students.Attestion.Attestation;
import Students.Attestion.Student;
import Students.Attestion.StudentListManageable;

import java.util.ArrayList;
import java.util.List;

public class StudentManager implements StudentListManageable {
    private final List<Student> studentList;

    public StudentManager() {
        studentList = new ArrayList<>();
    }

    @Override
    public void add(int index, Attestation attestation, String mark) {
        System.out.println(studentList.size());
        Student student = studentList.get(index);
        student.setMark(attestation, mark);
    }

    @Override
    public void add(Student student) {
        studentList.add(student);
    }

    @Override
    public void remove(Student student, Attestation attestation) {
        for (Student stud : studentList) {
            if (stud.getName().equals(student.getName())) {
                List<Attestation> keys = new ArrayList<>(stud.getSessionMarks().keySet());

                for (Attestation at : keys) {
                    if (at.toString().equals(attestation.toString()) && at.equals(attestation)) {
                        stud.deleteAttestation(attestation);
                    }
                }
            }
        }
    }

    @Override
    public void remove(int index) {
        studentList.remove(index);
    }

    @Override
    public int size() {
        return studentList.size();
    }

    @Override
    public Student get(int index) {
        return studentList.get(index);
    }

    @Override
    public void set(int index, Student student) {
        studentList.set(index, student);
    }

    @Override
    public void clearAll() {
        studentList.clear();
    }
}
