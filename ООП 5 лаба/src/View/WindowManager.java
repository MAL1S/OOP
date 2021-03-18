package View;

import Students.Attestion.Attestation;
import Students.Attestion.Exam;
import Students.Attestion.Student;
import Students.Attestion.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class WindowManager extends JFrame {
    JTable table, markTable;
    Vector<String> columnNames = new Vector<>();
    Vector<String> columnVector = new Vector<>();
    JFrame frame;
    JPanel panel, rightPanel;
    JButton addButton, removeButton, editButton, gradesButton, addAtButton, showAtButton;
    JScrollPane scrollPane;
    String[] days28 = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28".split(" ");
    String[] days30 = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30".split(" ");
    String[] days31 = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23".split(" ");
    String[] months = "01 02 03 04 05 06 07 08 09 10 11 12".split(" ");
    String[] years = ("90 91 92 93 94 95 96 97 98 99 01 02 03 04 05 06 07 08 09 10 " +
            "11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30").split(" ");

    StudentTableModel model;
    MarksTableModel markModel;

    public WindowManager() {
        //init();
        frame = new JFrame("Студенты");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        columnNames.add("Номер зачетки");
//        columnNames.add("ФИО");
//        columnNames.add("Шифр группы");
//        columnNames.add("Дата поступления");
//        columnNames.add("Номер телефона");
//
//
//        columnVector.add("Предмет");
//        columnVector.add("Форма аттестации");
//        columnVector.add("Оценка");
//        columnVector.add("Преподаватель");
//        columnVector.add("Дата экзамена");


        //объявление панелей и их лейаутов
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6, 1));


        //объявление кнопок
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(121, 50));
        addButton.addActionListener(new addButtonListener());
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frame, "Вы не выбрали запись");
                    return;
                }
                model.remove(row);
            }
        });
        editButton = new JButton("Edit");
        editButton.addActionListener(new editButtonListener());
        gradesButton = new JButton("Show grades");
        gradesButton.addActionListener(new showMarksButtonListener());
        addAtButton = new JButton("Добавить запись о аттестации");
        addAtButton.addActionListener(new addAttestationButtonListener());
        showAtButton = new JButton("....");
        showAtButton.addActionListener(new search());

        //добавление кнопок
        rightPanel.add(addButton);
        rightPanel.add(removeButton);
        rightPanel.add(editButton);
        rightPanel.add(gradesButton);
        rightPanel.add(addAtButton);
        rightPanel.add(showAtButton);

        //таблица
        //tableInit();

        model = new StudentTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        markModel = new MarksTableModel(model);
        markTable = new JTable(markModel);

        //добавление контента в панели
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        setContentPane(panel);
        setPreferredSize(new Dimension(1000, 600));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

//    private void tableInit() {
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        table.getColumnModel().getColumn(1).setMaxWidth(Integer.MAX_VALUE);
//        table.getColumnModel().getColumn(0).setMinWidth(130);
//        table.getColumnModel().getColumn(1).setMinWidth(200);
//        table.getColumnModel().getColumn(2).setMinWidth(110);
//        table.getColumnModel().getColumn(3).setMinWidth(110);
//        table.getColumnModel().getColumn(4).setMinWidth(110);
//        table.getColumnModel().getColumn(5).setMinWidth(100);
//        table.setFillsViewportHeight(true);
//    }
//
//    private Data refresh() {
//        Vector<Vector<String>> temp = new Vector<Vector<String>>();
//        DefaultTableModel tableModel = new DefaultTableModel(new Vector<>(), columnNames);
//
//        for (int i = 0; i < rowCount; i++) {
//            Vector<String> vector = new Vector<>();
//            for (int j = 0; j < COLUMN_COUNT; j++) {
//                vector.add(j, values.get(i).get(j));
//            }
//            temp.add(vector);
//            tableModel.addRow(vector);
//        }
//        return new Data(temp, tableModel);
//    }
//
//    private Data refresh(int row, Vector<String> editedRow) {
//        Vector<Vector<String>> temp = new Vector<Vector<String>>();
//        DefaultTableModel tableModel = new DefaultTableModel(new Vector<>(), columnNames);
//
//        for (int i = 0; i < rowCount; i++) {
//            Vector<String> vector = new Vector<>();
//            if (i == row) vector = editedRow;
//            else {
//                for (int j = 0; j < COLUMN_COUNT; j++) {
//                    vector.add(j, values.get(i).get(j));
//                }
//            }
//            temp.add(vector);
//            tableModel.addRow(vector);
//        }
//        return new Data(temp, tableModel);
//    }
//
//    private Data refresh(int row) {
//        Vector<Vector<String>> temp = values;
//        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//
//        for (int i = 0; i < rowCount; i++) {
//            if (i == row) continue;
//            Vector<String> vector = new Vector<>();
//            for (int j = 0; j < COLUMN_COUNT; j++) {
//                vector.add(j, values.get(i).get(j));
//            }
//            temp.add(vector);
//            tableModel.addRow(vector);
//        }
//        return new Data(temp, tableModel);
//    }
//
    private class addButtonListener extends JFrame implements ActionListener  {
        JTextField personalId, name, groupId, enrolDate, mobileNumber, birthDate;
        JLabel idL, nameL, groupIdL, enrolL, phoneL, birthL;
        JLabel dots;
        JButton buttonApply, buttonCancel;
        //JComboBox boxDays, boxMonths, boxYears;

        @Override
        public void actionPerformed(ActionEvent e) {
            setTitle("Добавить запись");
            GridLayout grid = new GridLayout(7, 2);
            grid.setVgap(10);
            JPanel addPanel = new JPanel(grid);

            idL = new JLabel("Зачетный номер");
            nameL = new JLabel("ФИО");
            groupIdL = new JLabel("Шифр группы");
            enrolL = new JLabel("Дата поступления");
            phoneL = new JLabel("Номер телефона");
            birthL = new JLabel("Дата рождения");

//            boxDays = new JComboBox<>(days31);
//            boxMonths = new JComboBox<>(months);
//            boxYears = new JComboBox<>(years);
//            dots = new JLabel(":");

            //поля ввода
            personalId = new JTextField(10);
            personalId.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (personalId.getText().length() > 7) personalId.setText(personalId.getText().substring(0, 7));
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if ((personalId.getText().length() == 2 || personalId.getText().length() == 5)
                            && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                        personalId.setText(personalId.getText() + ":");
                    }
                }
            });
            personalId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    name.setText("123123");
                }
            });
            name = new JTextField(40);
            groupId = new JTextField(8);
            enrolDate = new JTextField(8);
            mobileNumber = new JTextField(11);
            birthDate = new JTextField(8);

            buttonApply = new JButton("Принять");
            buttonApply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (personalId.getText().equals("") ||
                            name.getText().equals("") ||
                            groupId.getText().equals("") ||
                            enrolDate.getText().equals("") ||
                            mobileNumber.getText().equals("") ||
                            birthDate.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(frame, "Не все поля заполнены");
                        return;
                    }

                    Student student = new Student(personalId.getText(),
                            name.getText(),
                            groupId.getText(),
                            enrolDate.getText(),
                            mobileNumber.getText(),
                            birthDate.getText());
                    model.add(student);
                    dispose();
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
            setResizable(false);
            setVisible(true);
        }
    }

    private class editButtonListener extends JFrame implements ActionListener {
        private JTextField personalId, name, groupId, enrolDate, mobileNumber, birthDate;
        private JLabel idL, nameL, groupIdL, enrolL, phoneL, birthL;
        JButton buttonApply, buttonCancel;
        int row;

        @Override
        public void actionPerformed(ActionEvent e) {
            row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Вы не выбрали запись");
                return;
            }

            setTitle("Редактировать запись");
            GridLayout grid = new GridLayout(7, 2);
            grid.setVgap(10);
            JPanel editPanel = new JPanel(grid);

            idL = new JLabel("Зачетный номер");
            nameL = new JLabel("ФИО");
            groupIdL = new JLabel("Шифр группы");
            enrolL = new JLabel("Дата поступления");
            phoneL = new JLabel("Номер телефона");
            birthL = new JLabel("Дата рождения");


            //поля ввода
            personalId = new JTextField(10);
            personalId.setText((String) model.getValueAt(row, 0));
            name = new JTextField(40);
            name.setText((String) model.getValueAt(row, 1));
            groupId = new JTextField(8);
            groupId.setText((String) model.getValueAt(row, 2));
            enrolDate = new JTextField(8);
            enrolDate.setText((String) model.getValueAt(row, 3));
            mobileNumber = new JTextField(11);
            mobileNumber.setText((String) model.getValueAt(row, 4));
            birthDate = new JTextField(8);
            birthDate.setText((String) model.getValueAt(row, 5));

            buttonApply = new JButton("Принять");
            buttonApply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Student student = new Student(personalId.getText(),
                            name.getText(),
                            groupId.getText(),
                            enrolDate.getText(),
                            mobileNumber.getText(),
                            birthDate.getText());
                    model.edit(row, student);
                    dispose();
                }
            });

            buttonCancel = new JButton("Отмена");
            buttonCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });


            editPanel.add(idL);
            editPanel.add(personalId);
            editPanel.add(nameL);
            editPanel.add(name);
            editPanel.add(groupIdL);
            editPanel.add(groupId);
            editPanel.add(enrolL);
            editPanel.add(enrolDate);
            editPanel.add(phoneL);
            editPanel.add(mobileNumber);
            editPanel.add(birthL);
            editPanel.add(birthDate);
            editPanel.add(buttonApply);
            editPanel.add(buttonCancel);

            setContentPane(editPanel);
            setSize(400, 300);
            setResizable(false);
            setVisible(true);
        }
    }

    private class addAttestationButtonListener extends JFrame implements ActionListener {
        JLabel at, sub, gr, da, teacher;
        JTextField attestationType, subject, grade, date, teacherName;
        JPanel aaPanel;
        JButton apply, cancel;
        int row;

        @Override
        public void actionPerformed(ActionEvent e) {
            row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Вы не выбрали запись");
                return;
            }

            GridLayout grid = new GridLayout(6, 2);
            grid.setVgap(10);
            aaPanel = new JPanel(grid);
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

            cancel = new JButton("Отмена");

            apply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (attestationType.getText().equals("") ||
                            grade.getText().equals("") ||
                            subject.getText().equals("") ||
                            date.getText().equals("") ||
                            teacherName.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(frame, "Вы не выбрали запись");
                        return;
                    }

                    Attestation attestation;
                    if (attestationType.getText().equals("экзамен")) {
                        attestation = new Exam(subject.getText(), date.getText(), teacherName.getText());
                    }
                    else {
                        attestation = new Test(subject.getText(), date.getText(), teacherName.getText());
                    }
                    model.add(row, attestation, grade.getText());
                    dispose();
                }
            });

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

            setContentPane(aaPanel);
            setSize(400, 300);
            setResizable(false);
            setVisible(true);
        }
    }

    //NOT READY
    private class showMarksButtonListener extends JFrame implements ActionListener {
        int row;

        private void gradeTableInit() {
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setMinWidth(130);
            table.getColumnModel().getColumn(1).setMinWidth(200);
            table.getColumnModel().getColumn(2).setMinWidth(110);
            table.getColumnModel().getColumn(3).setMinWidth(110);
            table.getColumnModel().getColumn(4).setMinWidth(110);
            table.setFillsViewportHeight(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Вы не выбрали запись");
                return;
            }

            //gradeTableInit();
            setContentPane(new JScrollPane(markTable));
            markModel.setRows(model.getMarksCount(row));
            markModel.setIndex(row);
            pack();

            setResizable(false);
            setLocationRelativeTo(null);
            setTitle("Оценки");
            setSize(600, 600);
            setVisible(true);
        }
    }

    private class search extends JFrame implements ActionListener {
        JTextField id, name, group, enrolDate, phoneNumber, birthDate;
        JLabel idL, nameL, groupIdL, enrolL, phoneL, birthL;
        JButton apply, cancel;
        List<Integer> rows = new ArrayList<>();

        public boolean checkOccurrence(String tableData, String mask) {
            if (mask.equals("")) return true;
            tableData = tableData.toLowerCase();
            mask = mask.toLowerCase();
            boolean isFirstStar = mask.charAt(0) == '*';
            boolean isLastStar = mask.charAt(mask.length()-1) == '*';
            String[] arr = mask.split("\\*");

            if (!isFirstStar) {
                for (int i = 0; i < arr[0].length(); i++) {
                    if (tableData.charAt(i) != arr[0].charAt(i)) return false;
                }
            }
            if (!isLastStar) {
                for (int i = tableData.length()-1, j = arr[arr.length-1].length()-1;
                     i > tableData.length()-1-arr[arr.length-1].length();
                     i--, j--)
                {
                    if (tableData.charAt(i) != arr[arr.length-1].charAt(j)) return false;
                }
            }
            for (var item : arr) {
                if (tableData.contains(item)) {
                    tableData = tableData.substring(tableData.indexOf(item) + item.length());
                } else {
                    return false;
                }
            }
            return true;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setTitle("Найти записи");
            GridLayout grid = new GridLayout(7, 2);
            grid.setVgap(10);
            JPanel searchPanel = new JPanel(grid);

            idL = new JLabel("Зачетный номер");
            nameL = new JLabel("ФИО");
            groupIdL = new JLabel("Шифр группы");
            enrolL = new JLabel("Дата поступления");
            phoneL = new JLabel("Номер телефона");
            birthL = new JLabel("Дата рождения");

            id = new JTextField();

            name = new JTextField();

            group = new JTextField();

            enrolDate = new JTextField();

            phoneNumber = new JTextField();

            birthDate = new JTextField();


            apply = new JButton("Принять");
            apply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String t1T = id.getText();
                    String t2T = name.getText();
                    String t3T = group.getText();
                    String t4T = enrolDate.getText();
                    String t5T = phoneNumber.getText();
                    String t6T = birthDate.getText();

                    for (int i = 0; i < model.getRowCount(); i++) {
                        Student student = model.getStudent(i);
                        if (checkOccurrence(student.getStudentId(), t1T) &&
                                checkOccurrence(student.getName(), t2T) &&
                                checkOccurrence(student.getGroup(), t3T) &&
                                checkOccurrence(student.getEnrolDate(), t4T) &&
                                checkOccurrence(student.getPhoneNumber(), t5T) &&
                                checkOccurrence(student.getBirthDate(), t6T))
                        {
                            rows.add(i);
                            System.out.println(i);
                        }
                        dispose();
                    }
                }
            });

            cancel = new JButton("Отмена");
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            searchPanel.add(idL);
            searchPanel.add(id);
            searchPanel.add(nameL);
            searchPanel.add(name);
            searchPanel.add(groupIdL);
            searchPanel.add(group);
            searchPanel.add(enrolL);
            searchPanel.add(enrolDate);
            searchPanel.add(phoneL);
            searchPanel.add(phoneNumber);
            searchPanel.add(birthL);
            searchPanel.add(birthDate);
            searchPanel.add(apply);
            searchPanel.add(cancel);

            setContentPane(searchPanel);
            setSize(400, 300);
            setResizable(false);
            setVisible(true);
        }
    }
}



