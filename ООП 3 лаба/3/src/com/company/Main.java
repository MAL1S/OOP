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
            int number = InteractMenu.taskNumber();
            Data data = InteractMenu.getData(number);
            String res = InteractMenu.solve(data, number);
            InteractMenu.outData(res); //вывод в файл, если надо пользователю

            System.out.println("Решить какую-нибудь задачу еще раз?");
            reply = in.nextLine(); //заново проийтись по программе или выйти из нее
        }
        InteractMenu.wishLuck();
    }
}






