package com.company;

import java.util.HashMap;

public interface StudentListManageable {
    void add(int index, Attestation attestation, String mark);

    void add(Student student);

    void remove(Student student, Attestation attestation);

    void remove(int index);

    int size();

    Student get(int index);

    void set(int index, Student student);
}