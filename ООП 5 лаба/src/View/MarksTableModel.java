package View;

import Students.Attestion.Attestation;
import Students.Attestion.Exam;
import Students.Attestion.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MarksTableModel extends AbstractTableModel {
    private final StudentTableModel mainModel;
    private int index;
    private int rows;

    public MarksTableModel(StudentTableModel model) {
        mainModel = model;
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Предмет";
            case 1:
                return "Форма аттестации";
            case 2:
                return "Оценка";
            case 3:
                return "Преподаватель";
            case 4:
                return "Дата экзамена";
        }
        return super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student st = mainModel.getStudentList(index);
        HashMap<Attestation, String> marks = st.getSessionMarks();
        List<Attestation> keys = new ArrayList<>(marks.keySet());
        Attestation at = keys.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return at.getSubject();
            case 1:
                return at instanceof Exam ? "экзамен" : "зачет";
            case 2:
                return marks.get(at);
            case 3:
                return at.getTeacherName();
            case 4:
                return at.getDate();
        }
        return null;
    }

    public void setIndex(int index) {
        this.index = index;
        this.fireTableDataChanged();
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
