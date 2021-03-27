package Files;

import Students.Attestation.Attestation;
import Students.Attestation.Exam;
import Students.Attestation.Student;
import Students.Attestation.Test;
import View.StudentTableModel;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileManager {
    public static void writeToFile(FileWriter fw, StudentTableModel model, JFrame frame) {
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

    public static void readFromFile(FileReader fr, StudentTableModel model, JFrame frame) {
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
                    }
                    model.add(new Student(
                            studentData[0],
                            studentData[1] + " " + studentData[2] + " " + studentData[3],
                            studentData[4],
                            studentData[5],
                            studentData[6],
                            studentData[7]
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
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(frame, "Ошибка - формат данных в данном файле не корректен");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }
}
