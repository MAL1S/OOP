package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class WindowManager extends JFrame {
    JTable table;
    String[] columnNames = {"Номер зачетки", "ФИО", "Шифр группы", "Дата поступления", "Номер телефона", "Дата рождения"};
    JFrame frame;
    JPanel panel, rightPanel;
    JButton addButton, removeButton, editButton, button4, button5, button6;
    JScrollPane scrollPane;

    public WindowManager() {
        /*setSize(800, 600);
        setVisible(true);

        String[][] data = {
                {"1", "2", "3"},
                {"4", "5", "6"}
        };

        String[] columnNames = {"raz", "dva"};

        JTable table = new JTable(data, columnNames);
        JButton button1 = new JButton("1");
        button1.setSize(150, 100);
        JButton button2 = new JButton("2");
        button2.setSize(150, 100);
        add(table, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button1);
        panel.add(button2);
        add(panel, BorderLayout.EAST);*/



        table = new JTable(new String[][]{}, columnNames);

        //JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Test frame");
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
        editButton = new JButton("Edit");
        button4 = new JButton("...");
        button5 = new JButton("...");
        button6 = new JButton("...");

        addButton.addActionListener(new addButtonListener());

        //добавление кнопок
        rightPanel.add(addButton);
        rightPanel.add(removeButton);
        rightPanel.add(editButton);
        rightPanel.add(button4);
        rightPanel.add(button5);
        rightPanel.add(button6);

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
        frame.setPreferredSize(new Dimension(900, 600));
        frame.pack();
        frame.setVisible(true);
        //почему то, выдает много ошибок
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void addRow(Vector<String> values) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(values);
    }

    private class addButtonListener extends JFrame implements ActionListener {
        private Dialog dialog;
        private JTextField personalId, name, groupId, enrolDate, mobileNumber, birthDate;
        JPanel addPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
            addPanel = new JPanel();
            addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

            //поля ввода
            personalId = new JTextField(10);
            name = new JTextField(40);
            groupId = new JTextField(10);
            enrolDate = new JTextField(8);
            mobileNumber = new JTextField(11);
            birthDate = new JTextField(8);

            addPanel.add(personalId);
            addPanel.add(name);
            addPanel.add(groupId);
            addPanel.add(enrolDate);
            addPanel.add(mobileNumber);
            addPanel.add(birthDate);

            setContentPane(addPanel);
            setSize(300, 300);
            setVisible(true);
        }
    }
}

//http://java-online.ru/swing-jtextfield.xhtml
