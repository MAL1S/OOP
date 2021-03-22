package View;

import Students.Attestion.Attestation;
import Students.Attestion.Exam;
import Students.Attestion.Student;
import Students.Attestion.Test;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.rmi.activation.ActivationInstantiator;
import java.util.*;
import java.util.List;


public class WindowManager extends JFrame {
    JTable table, markTable;
    JFrame frame;
    JPanel panel, rightPanel;
    JButton addButton, removeButton, editButton, gradesButton, addAtButton, showAtButton, readButton, writeButton;
    JScrollPane scrollPane;
    StudentTableModel model;
    MarksTableModel markModel;

    public WindowManager() {
        //init();
        frame = new JFrame("Студенты");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(8, 1));

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
        addAtButton = new JButton("Add attestation");
        addAtButton.addActionListener(new addAttestationButtonListener());
        showAtButton = new JButton("Find students by mask");
        showAtButton.addActionListener(new search());
        readButton = new JButton("read data from file");
        readButton.addActionListener(new readFileSelector());
        writeButton = new JButton("write data to file");
        writeButton.addActionListener(new writeFileSelector());

        //добавление кнопок
        rightPanel.add(addButton);
        rightPanel.add(removeButton);
        rightPanel.add(editButton);
        rightPanel.add(gradesButton);
        rightPanel.add(addAtButton);
        rightPanel.add(showAtButton);
        rightPanel.add(readButton);
        rightPanel.add(writeButton);

        model = new StudentTableModel();
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(1).setMaxWidth(Integer.MAX_VALUE);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        scrollPane = new JScrollPane(table);

        markModel = new MarksTableModel(model);
        markTable = new JTable(markModel);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        setContentPane(panel);
        setPreferredSize(new Dimension(1000, 600));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class addButtonListener extends JFrame implements ActionListener  {
        JTextField personalId, name, groupId, enrolDate, mobileNumber, birthDate;
        JLabel idL, nameL, groupIdL, enrolL, phoneL, birthL;
        JButton buttonApply, buttonCancel;

        public void fieldsActionsInit() {
            personalId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonApply.doClick();
                }
            });
            name.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonApply.doClick();
                }
            });
            groupId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonApply.doClick();
                }
            });
            enrolDate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonApply.doClick();
                }
            });
            mobileNumber.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonApply.doClick();
                }
            });
            birthDate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonApply.doClick();
                }
            });
        }

        public void addingComponentsToPanel() {
            GridLayout grid = new GridLayout(7, 2);
            grid.setVgap(10);
            JPanel addPanel = new JPanel(grid);
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
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setTitle("Добавить запись");

            idL = new JLabel("Зачетный номер");
            nameL = new JLabel("ФИО");
            groupIdL = new JLabel("Шифр группы");
            enrolL = new JLabel("Дата поступления");
            phoneL = new JLabel("Номер телефона");
            birthL = new JLabel("Дата рождения");


            personalId = new JTextField(10);
//            personalId.addKeyListener(new KeyListener() {
//                @Override
//                public void keyTyped(KeyEvent e) {
//                    if (personalId.getText().length() > 7) personalId.setText(personalId.getText().substring(0, 7));
//                }
//
//                @Override
//                public void keyPressed(KeyEvent e) {
//                    if (e.getKeyCode() < 48 || e.getKeyCode() >57) {
//                        personalId.setText(personalId.getText().substring(0, personalId.getText().length()-2));
//                    }
//                    if ((personalId.getText().length() == 2 || personalId.getText().length() == 5)
//                            && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
//                        personalId.setText(personalId.getText() + ":");
//                    }
//                }
//
//                @Override
//                public void keyReleased(KeyEvent e) {
//
//                }
//            });
            name = new JTextField(40);
            groupId = new JTextField(8);
            enrolDate = new JTextField(8);
            mobileNumber = new JTextField(11);
            birthDate = new JTextField(8);
            fieldsActionsInit();

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

            addingComponentsToPanel();
            setSize(400, 300);
            setResizable(false);
            setVisible(true);
        }
    }

    private class editButtonListener extends JFrame implements ActionListener {
        JTextField personalId, name, groupId, enrolDate, mobileNumber, birthDate;
        JLabel idL, nameL, groupIdL, enrolL, phoneL, birthL;
        JButton buttonApply, buttonCancel;
        int row;

        public void addingComponentsToPanel() {
            GridLayout grid = new GridLayout(7, 2);
            grid.setVgap(10);
            JPanel editPanel = new JPanel(grid);
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
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Вы не выбрали запись");
                return;
            }

            setTitle("Редактировать запись");

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

            addingComponentsToPanel();
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

        public void addingComponentsToPanel() {
            GridLayout grid = new GridLayout(6, 2);
            grid.setVgap(10);
            aaPanel = new JPanel(grid);
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
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Вы не выбрали запись");
                return;
            }

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

            addingComponentsToPanel();
            setSize(400, 300);
            setResizable(false);
            setVisible(true);
        }
    }

    private class showMarksButtonListener extends JFrame implements ActionListener {
        int row;

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
        JTable foundTable;

        public boolean checkOccurrence(String tableData, String mask) {
            if (mask.equals("")) return true;
            tableData = tableData.toLowerCase();
            mask = mask.toLowerCase();
            boolean isFirstStar = mask.charAt(0) == '*';
            boolean isLastStar = mask.charAt(mask.length()-1) == '*';
            String[] arr = mask.split("\\*");
            int sumOfMask = 0;
            for (String item : arr) {
                sumOfMask += item.length();
            }
            if (tableData.length() < sumOfMask) return false;
            if (arr.length == 1) {
                return tableData.equals(mask);
            }

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
                    System.out.println(tableData + " " + arr[arr.length-1]);
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

        private void showResultOfSearch() {
            StudentTableModel foundStudents = new StudentTableModel();
            foundStudents.clearAll();
            for (int number : rows) {
                foundStudents.add(model.getStudent(number));
            }
            foundTable = new JTable(foundStudents);
            JFrame tableFrame = new JFrame("Найденные студенты");
            tableFrame.setContentPane(new JScrollPane(foundTable));
            tableFrame.setSize(800, 600);
            tableFrame.setResizable(false);
            tableFrame.setLocationRelativeTo(null);
            tableFrame.setVisible(true);
        }

        private void addingComponentsToPanel() {
            GridLayout grid = new GridLayout(7, 2);
            grid.setVgap(10);
            JPanel searchPanel = new JPanel(grid);
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
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setTitle("Найти записи");

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
                    rows.clear();

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
                        }
                    }
                    showResultOfSearch();
                    dispose();
                }
            });

            cancel = new JButton("Отмена");
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            addingComponentsToPanel();
            setSize(400, 300);
            setResizable(false);
            setVisible(true);
        }
    }

    private class writeFileSelector implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try (FileWriter fw = new FileWriter(fc.getSelectedFile())) {
                    writeToFile(fw);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }

    private class readFileSelector implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try (FileReader fr = new FileReader(fc.getSelectedFile())) {
                    readFromFile(fr);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }

    private void writeToFile(FileWriter fw) {
        try {
            //ТУТ НАПИСАТЬ ПОДЗАГОЛОВКИ ТОГО, ЧТО ВЫВОДИТСЯ
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < model.getRowCount(); i++) {
                Student stud = model.getStudent(i);
                bw.write(stud.getStudentId() + " " +
                        stud.getName() + " " +
                        stud.getGroup() + " " +
                        stud.getEnrolDate() + " " +
                        stud.getPhoneNumber() + " " +
                        stud.getBirthDate() +
                        "\n");
                List<Attestation> list = new ArrayList<>(stud.getSessionMarks().keySet());
                for (var item : list) {
                    bw.write(item + " " +
                            item.getSubject() + " " +
                            stud.getSessionMarks().get(item) + " " +
                            item.getDate() + " " +
                            item.getTeacherName() +
                            "\n");
                }
                bw.write("----------------------\n");
            }
            bw.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void readFromFile(FileReader fr) {
        try {
            model.clearAll();
            BufferedReader br = new BufferedReader(fr);
            String line;
            int studInd = 0;
            boolean studLine = true;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == '-') {
                    studLine = true;
                    studInd++;
                    continue;
                }
                String[] studentData = line.split(" ");
                if (studLine) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        Student s = model.getStudent(i);
                        System.out.println(s.getName());
                    }
                    model.add(new Student(
                            studentData[0],
                            studentData[1],
                            studentData[2],
                            studentData[3],
                            studentData[4],
                            studentData[5]
                    ));
                    studLine = false;
                }
                else {
                    Attestation at;
                    if (studentData[0].equals("экзамен")) at = new Exam(studentData[1], studentData[3], studentData[4]);
                    else at = new Test(studentData[1], studentData[3], studentData[4]);
                    model.add(studInd,  at, studentData[2]);
                }
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}



