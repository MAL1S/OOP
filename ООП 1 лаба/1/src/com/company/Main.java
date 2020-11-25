package com.company;

import java.util.Scanner;

class Main {
    public static void secondTask()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a sentence");
        String str1 = input.nextLine();
        System.out.println("Input a string");
        String str2 = input.nextLine();
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int count = 0;
        boolean check = true;

        for (int i = 0; i < str1.length(); i++)
        {
            if (ch1[i] == ch2[count])
            {
                count++;
                if (count == str2.length())
                {
                    System.out.println("Yes");
                    check = false;
                    break;
                }
            }
            else count = 0;
        }
        if (check) System.out.println("No");
    }
    public static void firstTask() {
	    Scanner input = new Scanner(System.in);
	    System.out.println("Input a string");
	    String[] stringArray = input.nextLine().split(" ");

	    for (int i = 0; i < stringArray.length; i++)
        {
            for (int j = i; j < stringArray.length; j++)
            {
                char[] ch1 = stringArray[i].toCharArray();
                char[] ch2 = stringArray[j].toCharArray();
                if (stringArray[j].length() >= 3)
                {
                    if (ch1[2] >= ch2[2])
                    {
                        String str = String.copyValueOf(ch2);
                        stringArray[j] = stringArray[i];
                        stringArray[i] = str;
                    }
                }
                else
                {
                    if (ch1[ch1.length - 1] >= ch2[ch2.length - 1])
                    {
                        String str = String.copyValueOf(ch2);
                        stringArray[j] = stringArray[i];
                        stringArray[i] = str;
                    }
                }
            }
        }
        for (String s : stringArray) {
            System.out.println(s);
        }
    }
    public static void thirdTask()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a sentence");
        String sent = input.nextLine();
        String[] sentWords = sent.split(" ");
        int count = 1;
        for (String sentWord : sentWords) {
            if (count % 3 == 0) {
                int ind = sent.indexOf(sentWord);
                int length = sentWord.length();
                sent = sent.substring(0, ind) + sent.substring(ind + length + 1);
            }
            count++;
        }
        System.out.println(sent);
    }

    public static void main(String[] args) {
        firstTask();
        secondTask();
        thirdTask();
    }
}





