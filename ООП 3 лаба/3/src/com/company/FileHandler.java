package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {
    public static String readFile() { //выполняет чтение из файла и возвращает данные из него
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        StringBuilder res = new StringBuilder(); //производится много конкатенаций строк, поэтому лучше использовать StringBuilder
        while (flag) { //пока небудет введен верный путь файла
            try {
                System.out.println("Введите путь файла с начальными данными");
                String path = in.nextLine();
                FileInputStream fis = new FileInputStream(path); //поток чтения из файла
                flag = false;
                int symbol;
                while ((symbol = fis.read()) != -1) { //побайтное чтение из файла
                    res.append((char) symbol);
                }
                fis.close(); //закрытие файла
                if (res.length() == 0) return null; //если файл пустой, возвращается null
            } catch (IOException e) {
                System.out.println("Файла по такому пути не существует, введите путь заново");
            }
        }
        return res.toString(); //возврат StringBuilder в виде String
    }

    public static Data fileInformationSplit(String fileData, int taskNumber) { //разделение информации из файла (она изначально должна быть разделена знаками ";")
        if (fileData == null) { //если файл пустой, то даже нет смысла разделить ничего
            System.out.println("Файл пустой");
            return null;
        }
        String[] arrData = fileData.split(";"); //разделение строк по символу их разделения
        Data data; //объект моего класса данных
        if (arrData.length == 1 && taskNumber == 1) { //если введена только одна строка без единых знаков ";" (т.е. для первого задания)
            data = new Data(arrData[0]); //создание экземпляра класса данных, здесь и далее (см. Data.java)
        }
        else if (arrData.length == 2) { //для 2-го и 3-го задания, т.к. там две строки в обоих заданиях
            String temp = arrData[1]; //второе предложение
            int number;
            if (taskNumber == 3) {
                try {
                    number = Integer.parseInt(temp); //попытка преобразовать 2-ое предложение в число
                    data = new Data(arrData[0], number); //запись данных для задания
                } catch (NumberFormatException e) {
                    System.out.println("В файле введены неверные входные данные для третьего задания");
                    return null; //иначе нельзя решить данное задание с такими входными данными
                }
            }
            else {
                data = new Data(arrData[0], temp); //иначе просто добавить это как строку
            }
        }
        else {
            System.out.println("Файл должен содержать строго только входные данные для решения задач, разделенные знаком \";\"" +
                    "или для данной задачи введеные некорректные данные в файле");
            data = null; //нельзя решить задачу с такими входными данными
        }
        return data; //возврат данных для решения задачи
    }

    public static void writeFile(String data) { //запись данных в файл
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название существующего файла или место, где нужно создать файл");
        String path = in.nextLine(); //путь файла
        try {
            if (path.equals("0")) return; //если пользователь ввел 0 в catch
            FileOutputStream fos = new FileOutputStream(path); //поток вывода данных в файл
            PrintWriter pw = new PrintWriter(fos); //запись текстовых данных в файл, чтобы кодировка была русская,
            //иначе будет происходить побайтовая запись данных и будут выводится непонятные символы
            pw.write(data); //запись данных в файл
            pw.close();
            fos.close(); //закрытие потоков вывода
        }catch (IOException e) {
            System.out.println("Вы ввели название каталога или файл не может быть создан или открыт по некоторым причинам" +
                    "повторите ввод");
            path = in.nextLine(); //иначе просьба выполнить повторый ввод и возможность отменить это действие
        }
    }
}
