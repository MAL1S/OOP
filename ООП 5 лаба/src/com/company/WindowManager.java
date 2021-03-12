package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class WindowManager extends JFrame {
    JTable table;
    Vector<String> columnNames = new Vector<>();
    JFrame frame;
    JPanel panel, rightPanel;
    JButton addButton, removeButton, editButton, gradesButton, addAtButton, showAtButton;
    JScrollPane scrollPane;
    int rowCount;
    public final int COLUMN_COUNT = 6;
    Vector<Vector<String>> values;
    Vector<Student> studentData = new Vector<>();

    public WindowManager() {
        //init();
        columnNames.add("Номер зачетки");
        columnNames.add("ФИО");
        columnNames.add("Шифр группы");
        columnNames.add("Дата поступления");
        columnNames.add("Номер телефона");
        columnNames.add("Дата рождения");

        table = new JTable(new Vector<>(), columnNames);
        rowCount = 0;

        frame = new JFrame("Студенты");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //объявление панелей и их лейаутов
        panel = new JPanel();
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6, 1));
        panel.setLayout(new BorderLayout());

        //объявление кнопок
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(121, 50));
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                System.out.println(row);
                if (row == -1) {
                    System.out.println("Вы не выбрали запись");
                    return;
                }
                ((DefaultTableModel) table.getModel()).removeRow(row);
                Data data = refresh(row);
                Vector<Vector<String>> temp = data.vector;
                DefaultTableModel tableModel = data.tableModel;

                values = temp;
                table.setModel(tableModel);
            }
        });
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        gradesButton = new JButton("Show grades");
        gradesButton.addActionListener(new showGradesButtonListener());
        addAtButton = new JButton("Добавить запись о аттестации");
        addAtButton.addActionListener(new addAttestationButtonListener());
        showAtButton = new JButton("....");

        addButton.addActionListener(new addButtonListener());

        //добавление кнопок
        rightPanel.add(addButton);
        rightPanel.add(removeButton);
        rightPanel.add(editButton);
        rightPanel.add(gradesButton);
        rightPanel.add(addAtButton);
        rightPanel.add(showAtButton);

        //таблица
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(1).setMaxWidth(Integer.MAX_VALUE);
        table.getColumnModel().getColumn(0).setMinWidth(130);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(2).setMinWidth(110);
        table.getColumnModel().getColumn(3).setMinWidth(110);
        table.getColumnModel().getColumn(4).setMinWidth(110);
        table.getColumnModel().getColumn(5).setMinWidth(100);
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        //добавление контента в панели
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private Data refresh() {
        Vector<Vector<String>> temp = new Vector<Vector<String>>();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        for (int i = 0; i < rowCount; i++) {
            Vector<String> vector = new Vector<>(6);
            for (int j = 0; j < COLUMN_COUNT; j++) {
                vector.add(j, values.get(i).get(j));
            }
            temp.add(vector);
            tableModel.addRow(vector);
        }
        return new Data(temp, tableModel);
    }

    private Data refresh(int row) {
        Vector<Vector<String>> temp = values;
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        for (int i = 0; i < rowCount; i++) {
            if (i == row) continue;
            Vector<String> vector = new Vector<>(6);
            for (int j = 0; j < COLUMN_COUNT; j++) {
                vector.add(j, values.get(i).get(j));
            }
            temp.add(vector);
            tableModel.addRow(vector);
        }
        return new Data(temp, tableModel);
    }

    private class addButtonListener extends JFrame implements ActionListener {
        private JTextField personalId, name, groupId, enrolDate, mobileNumber, birthDate;
        private JLabel idL, nameL, groupIdL, enrolL, phoneL, birthL;
        JButton buttonApply, buttonCancel;

        @Override
        public void actionPerformed(ActionEvent e) {
            setTitle("Добавить запись");
            GridLayout grid = new GridLayout(7,2);
            grid.setVgap(10);
            JPanel addPanel = new JPanel(grid);

            idL = new JLabel("Зачетный номер");
            nameL = new JLabel("ФИО");
            groupIdL = new JLabel("Шифр группы");
            enrolL = new JLabel("Дата поступления");
            phoneL = new JLabel("Номер телефона");
            birthL = new JLabel("Дата рождения");


            //поля ввода
            personalId = new JTextField(10);
            name = new JTextField(40);
            groupId = new JTextField(8);
            enrolDate = new JTextField(8);
            mobileNumber = new JTextField(11);
            birthDate = new JTextField(8);

            buttonApply = new JButton("Принять");
            buttonApply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Data data = refresh();
                    Vector<Vector<String>> temp = data.vector;
                    DefaultTableModel tableModel = data.tableModel;

                    Vector<String> vector = new Vector<>(6);

                    vector.add(personalId.getText());
                    vector.add(name.getText());
                    vector.add(groupId.getText());
                    vector.add(enrolDate.getText());
                    vector.add(mobileNumber.getText());
                    vector.add(birthDate.getText());

                    temp.add(vector);
                    tableModel.addRow(vector);

                    values = temp;
                    table.setModel(tableModel);

                    studentData.add(new Student(name.getText(), groupId.getText()));
                }
            });

            buttonCancel = new JButton("Отмена");
            buttonCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });


            addPanel.add(idL);
            addPanel.add(personalId);
            addPanel.add(nameL);
            addPanel.add(name);
            addPanel.add(groupIdL);
            addPanel.add(groupId);
            addPanel.add(enrolL);
            addPanel.add(enrolDate);
            addPanel.add(phoneL);
            addPanel.add(mobileNumber);
            addPanel.add(birthL);
            addPanel.add(birthDate);
            addPanel.add(buttonApply);
            addPanel.add(buttonCancel);

            setContentPane(addPanel);
            setSize(400, 300);
            frame.setResizable(false);
            setVisible(true);
        }
    }

    private class showGradesButtonListener extends JFrame implements ActionListener {
        JTable gradeTable;
        Vector<String> columnVector;
        JPanel showPanel;

        private void gradesInit() {
            columnVector = new Vector<>();
            columnVector.add("Предмет");
            columnVector.add("Форма аттестации");
            columnVector.add("Оценка");
            columnVector.add("Преподаватель");
            columnVector.add("Дата экзамена");



            gradeTable = new JTable(new Vector<>(), columnVector);
            table.getColumnModel().getColumn(0).setMinWidth(130);
            table.getColumnModel().getColumn(1).setMinWidth(200);
            table.getColumnModel().getColumn(2).setMinWidth(110);
            table.getColumnModel().getColumn(3).setMinWidth(110);
            table.getColumnModel().getColumn(4).setMinWidth(110);

            JScrollPane showScrollPane = new JScrollPane(gradeTable);


            getContentPane().add(showScrollPane);
           //pack();

            setResizable(false);
            setLocationRelativeTo(null);
            setTitle("Оценки");
            setSize(600, 600);
            setVisible(true);
        }

        private void fillTableWithMarks() {
            int row = table.getSelectedRow();
            if (row == - 1) {
                System.out.println("Вы не выбрали запись");
                return;
            }
            String name = String.valueOf(values.get(row).get(1));

            for (Student student : studentData) {
                if (student.getName().equals(name)) {
                    DefaultTableModel tableModel = (DefaultTableModel) gradeTable.getModel();
                    for (var item : getMarksFromStudent(student)) {
                        tableModel.addRow(item);
                    }
                    gradeTable.setModel(tableModel);
                }
            }
        }

        private Vector<Vector<String>> getMarksFromStudent(Student student) {
            List<Attestation> keys = new ArrayList<>(student.sessionMarks.keySet());
            Vector<Vector<String>> studentOutput = new Vector<Vector<String>>();
            for (Attestation at : keys) {
                Vector<String> studentInfo = new Vector<>(5);

                studentInfo.add(at.toString());
                studentInfo.add(at.getSubject());
                studentInfo.add(student.sessionMarks.get(at));
                studentInfo.add(at.getDate());
                studentInfo.add(at.getTeacherName());

                studentOutput.add(studentInfo);
            }

            return studentOutput;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            gradesInit();
            fillTableWithMarks();
        }
    }

    private class addAttestationButtonListener extends JFrame implements ActionListener {
        JLabel at, sub, gr, da, teacher;
        JTextField attestationType, subject, grade, date, teacherName;
        JPanel aaPanel;
        JButton apply, cancel;

        private void addAttestationInit() {
            aaPanel = new JPanel(new GridLayout(6,2));
            attestationType = new JTextField(7);
            grade = new JTextField(5);
            subject = new JTextField(30);
            date = new JTextField(8);
            teacherName = new JTextField(40);

            at = new JLabel("Форма аттестации");
            sub = new JLabel("Предмет");
            gr = new JLabel("Оценка");
            da = new JLabel("Дата");
            teacher = new JLabel("Преподаватель");

            apply = new JButton("Принять");
            apply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setStudentData();
                }
            });
            cancel = new JButton("Отмена");
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            aaPanel.add(at);
            aaPanel.add(attestationType);
            aaPanel.add(sub);
            aaPanel.add(subject);
            aaPanel.add(gr);
            aaPanel.add(grade);
            aaPanel.add(da);
            aaPanel.add(date);
            aaPanel.add(teacher);
            aaPanel.add(teacherName);
            aaPanel.add(apply);
            aaPanel.add(cancel);

            getContentPane().add(aaPanel);
            setResizable(false);
            setTitle("Добавить оценки ученику");
            setSize(600, 400);
            setVisible(true);
        }

        public void setStudentData() {
            int row = table.getSelectedRow();
            if (row == - 1) {
                System.out.println("Вы не выбрали запись");
                return;
            }
            String name = String.valueOf(values.get(row).get(1));
            String textType = attestationType.getText();
            Attestation attestation;
            if (textType.equalsIgnoreCase("экзамен")) {
                attestation = new Exam(subject.getText(), date.getText(), teacherName.getText());
            }
            else {
                attestation = new Test(subject.getText(), date.getText(), teacherName.getText());
            }

            for (Student student : studentData) {
                if (student.getName().equals(name)) {
                    student.setMark(attestation, grade.getText());
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addAttestationInit();
        }
    }

    private class Data {
        Vector<Vector<String>> vector;
        DefaultTableModel tableModel;

        public Data(Vector<Vector<String>> vector, DefaultTableModel tableModel) {
            this.vector = vector;
            this.tableModel = tableModel;
        }
    }
}


