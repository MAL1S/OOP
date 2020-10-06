package com.company;

import java.util.Scanner;

class Main{
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите предложение для последующих операций над ним");
        String[] inputData = in.nextLine().split(" ");
        System.out.println("Введите искомую подстроку для 2 задачи");
        String subStr = in.nextLine();

        Sentence sent1 = new Sentence();
        Sentence sent2 = new Sentence(inputData);
        Sentence sent3 = new Sentence(sent1);

        //решение второй задачи
        System.out.println("\nРезультаты поиска подстроки в строке:\n");
        if (sent1.ifContains(subStr)) System.out.println("Да, \"" + sent1.toString() + "\"" + " содержит \"" + subStr + "\"");
        else System.out.println("Нет, \"" + sent1.toString() + "\"" + " не содержит \"" + subStr + "\"");
        if (sent2.ifContains(subStr)) System.out.println("Да, \"" + sent2.toString() + "\"" + " содержит \"" + subStr + "\"");
        else System.out.println("Нет, \"" + sent2.toString() + "\"" + " не содержит \"" + subStr + "\"");
        if (sent3.ifContains(subStr)) System.out.println("Да, \"" + sent3.toString() + "\"" + " содержит \"" + subStr + "\"");
        else System.out.println("Нет, \"" + sent3.toString() + "\"" + " не содержит \"" + subStr + "\"");

        //решение первой задачи
        System.out.println("\nРезультаты сортировки предложения:\n");
        sent1.sort();
        sent2.sort();
        sent3.sort();

        System.out.println(sent1.toString());
        System.out.println(sent2.toString());
        System.out.println(sent3.toString());

        //решение третьей задачи
        System.out.println("\nРезультаты удаления каждого 3-го слова из предложения:\n");
        sent1.delWords(3);
        sent2.delWords(3);
        sent3.delWords(3);

        System.out.println(sent1.toString());
        System.out.println(sent2.toString());
        System.out.println(sent3.toString());
    }
}






