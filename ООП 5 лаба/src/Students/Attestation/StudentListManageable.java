package Students.Attestation;

public interface StudentListManageable {
    void add(int index, Attestation attestation, String mark);

    void add(Student student);

    void remove(int index);

    int size();

    Student get(int index);

    void set(int index, Student student);

    void clearAll();
}