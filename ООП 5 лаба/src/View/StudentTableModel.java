package View;

import Students.Attestion.Attestation;
import Students.Attestion.Student;
import Students.Attestion.StudentManager;

import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {
    private final StudentManager studentList;

    public StudentTableModel() {
        studentList = new StudentManager();
    }

    @Override
    public int getRowCount() {
        return studentList.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                //номер зачетки
                return "Номер зачетки";
            case 1:
                //ФИО
                return "ФИО";
            case 2:
                //шифр группы
                return "Шифр группы";
            case 3:
                //дата поступления
                return "Дата поступления";
            case 4:
                //номер телефона
                return "Номер телефона";
            case 5:
                //дата рождения
                return "Дата рождения";
        }
        return super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = studentList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                //номер зачетки
                return student.getStudentId();
            case 1:
                //ФИО
                return student.getName();
            case 2:
                //шифр группы
                return student.getGroup();
            case 3:
                //дата поступления
                return student.getEnrolDate();
            case 4:
                //номер телефона
                return student.getPhoneNumber();
            case 5:
                //дата рождения
                return student.getBirthDate();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    public Student getStudentList(int index) {
        return studentList.get(index);
    }

    public void add(Student student) {
        studentList.add(student);
        this.fireTableDataChanged();
    }

    public void remove(int index) {
        studentList.remove(index);
        this.fireTableDataChanged();
    }

    public void edit(int index, Student student) {
        studentList.set(index, student);
        this.fireTableDataChanged();
    }

    public void add(int index, Attestation attestation, String mark) {
        studentList.add(index, attestation, mark);
    }

    public int getMarksCount(int index) {
        return studentList.get(index).getSessionMarks().size();
    }
}
