package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;


public class WindowManager extends JFrame {

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

        String[][] data = {
                {"Карбушев Иван Владимирович\n",
                        "Зачет\nЭкзамен",
                        "ООП\nТехнологии программирования",
                        "Аршинский В.Л.\nКаташевцев А.А.",
                        "21.12.2020\n24.12.2020",
                        "9:00\n10:00",
                        "Зачет\n5"}
        };

        String[] columnNames = {"ФИО", "Формат", "Предмет", "Преподаватель", "Дата", "Время", "Оценка"};

        JTable table = new JTable(data, columnNames);

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //объявление панелей и их лейаутов
        JPanel panel = new JPanel();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6, 1));
        panel.setLayout(new BorderLayout());

        //объявление кнопок
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(125, 50));
        JButton removeButton = new JButton("Remove");
        JButton editButton = new JButton("Edit");
        JButton button4 = new JButton("...");
        JButton button5 = new JButton("...");
        JButton button6 = new JButton("...");

        //добавление кнопок
        rightPanel.add(addButton);
        rightPanel.add(removeButton);
        rightPanel.add(editButton);
        rightPanel.add(button4);
        rightPanel.add(button5);
        rightPanel.add(button6);

        //таблица

        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer());
        //table.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        //добавление контента в панели
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(858, 600));
        frame.pack();
        frame.setVisible(true);
        //почему то, выдает много ошибок
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
