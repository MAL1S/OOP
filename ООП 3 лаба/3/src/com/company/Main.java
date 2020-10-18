package com.company;

import java.util.Scanner;

class Main{
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String reply = "Yes";
        while (reply.toLowerCase().equals("yes") || reply.toLowerCase().equals("y") ||
                reply.toLowerCase().equals("да") || reply.toLowerCase().equals("1") ||
                reply.toLowerCase().equals("+") || reply.toLowerCase().equals("lf") ||
                reply.toLowerCase().equals("нуы") || reply.toLowerCase().equals("н") ||
                reply.toLowerCase().equals("=")) { //проверка различных ответов пользователя на вопрос о повторном запуске программы

            InteractMenu.start();
            String num = InteractMenu.ifNumber(); //ввод и проверка на число
            while (num == null || !(num.equals("1") || num.equals("2") || num.equals("3"))) { //если введено было не число или неверный номер задания
                System.out.println("Вы ввели не число или нет задания с таким номером, повторите ввод");
                num = InteractMenu.ifNumber();
            }
            int number = Integer.parseInt(num); //номер задания

            String way = InteractMenu.readWay();
            Data data;
            if (way.equals("console")) { //если считывать с консоли
                data = InteractMenu.inputData(number);
                while (data == null) { //если входные данные ведены неверно
                    System.out.println("Повторите ввод заново");
                    data = InteractMenu.inputData(number);
                }
            }
            else { //иначе если читать с файла
                String fileData = InteractMenu.readFile();
                data = InteractMenu.fileInformationSplit(fileData, number);
            }

            String res = ""; //данные для подачи на запись в файл
            if (data != null) {
                String[] sentence; //переменные
                String subString; //для
                int count; //задач
                sentence = data.getSentence().split(" "); //первая строка для всех одинакова, поэтому сразу ее назначаем

                if (number == 1) { //если задача 1-ая, 2-ая или 3-я
                    res = InteractMenu.solveFirst(sentence).getStringArray().toString();
                } else if (number == 2) {
                    subString = data.getSubString();
                    res = InteractMenu.solveSecond(sentence, subString);
                } else {
                    count = data.getNumber();
                    res = InteractMenu.solveThird(sentence, count).getStringArray().toString();
                }
            }
            InteractMenu.writeFile(res); //запись в файл
            System.out.println("Решить какую-нибудь задачу еще раз?");
            reply = in.nextLine(); //заново проийтись по программе или выйти из нее
        }
        System.out.println("Удачи!");
    }
}






