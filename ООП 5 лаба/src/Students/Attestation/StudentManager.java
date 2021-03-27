package Students.Attestation;

import java.util.ArrayList;
import java.util.List;

public class StudentManager implements StudentListManageable {
    private final List<Student> studentList;

    public StudentManager() {
        studentList = new ArrayList<>();
    }

    @Override
    public void add(int index, Attestation attestation, String mark) {
        Student student = studentList.get(index);
        student.setMark(attestation, mark);
    }

    @Override
    public void add(Student student) {
        studentList.add(student);
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
