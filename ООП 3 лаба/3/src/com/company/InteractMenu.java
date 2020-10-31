package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InteractMenu {

    public static void start() {
        System.out.println("Какую задачу вы хотели бы решить : ");
        System.out.println("____1) Сортировка предложение по третьей букве, если слово меньше, то по последней");
        System.out.println("____2) Определить, входит ли подстрока в строку");
        System.out.println("____3) Удалить в предложении каждое n-ное слово");
    }

    public static int taskNumber() {
        String num = InteractMenu.ifNumber(); //ввод и проверка на число
        while (num == null || !(num.equals("1") || num.equals("2") || num.equals("3"))) { //если введено было не число или неверный номер задания
            System.out.println("Вы ввели не число или нет задания с таким номером, повторите ввод");
            num = InteractMenu.ifNumber();
        }
        return Integer.parseInt(num); //номер задания
    }

    public static String ifNumber() { //проверяет, число ли введено вообще
        Scanner in = new Scanner(System.in);
        int number;
        try {
            number = in.nextInt(); //пробует ввести число
            return String.valueOf(number); //String, чтобы можно было возвратить null
        }
        catch (InputMismatchException e) { //если не получилось считать число, то возвратится null
            return null;
        }
    }

    public static String readWay() { //определяет, каким путем получать входные данные
        Scanner in = new Scanner(System.in);
        System.out.println("Ввести данные с консоли и прочитать их из файла?\n" +
                "(0 - из консоли. 1 - из файла)");
        String reply = in.nextLine();
        while (!reply.equals("0") && !reply.equals("1")) { //проверяет, введено ли 0 или 1
            System.out.println("Введите 0 или 1");
            reply = in.nextLine(); //если введены не 0 и 1, то запрашивает проверку снова
        }
        if (reply.equals("0")) return "console";
        else return "file";
    }

    public static boolean ifWrite() {
        Scanner in = new Scanner(System.in);
        System.out.println("Хотите ли сохранить результат решения в файл?" +
                "(0 - нет, 1 - да)");
        String reply = in.nextLine();
        while (!reply.equals("0") && !reply.equals("1")) { //пока ответ пользователя не будет равен 0 или 1
            System.out.println("Введите 0 или 1");
            reply = in.nextLine();
        }
        return !reply.equals("0"); //если пользователь не хочет делать запись данных в файл
    }

    public static void outData(String data) {
        if (ifWrite()) {
            FileHandler.writeFile(data);
        }
    }

    public static Data getData(int number) {
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
            String fileData = FileHandler.readFile();
            data = FileHandler.fileInformationSplit(fileData, number);
        }
        return data;
    }

    public static Data inputData(int num) { //ввод данных с консоли для задачи
        Scanner in = new Scanner(System.in);
        String input;
        Data data;
        switch (num) {
            case 1 -> { //если первая задача
                System.out.println("Введите предложение");
                input = in.nextLine();
                data = new Data(input);
                return data;
            }
            case 2 -> { //если вторая задача
                System.out.println("Введите предложение");
                input = in.nextLine();
                System.out.println("Введите подстроку");
                String subString = in.nextLine();
                data = new Data(input, subString);
                return data;
            }
            case 3 -> { //если третья задача
                System.out.println("Введите предложение");
                input = in.nextLine();
                System.out.println("Введите номер удаляемого слова");
                String temp;
                int number;
                temp = ifNumber(); //сразу проверка на то, число ли введено
                if (temp == null) break; //если нет, то выходим из кейса
                number = Integer.parseInt(temp); //преобразование строки в число, так как уже понятно, что это число
                if (number < 1 || number > input.split(" ").length) { //проверка на то, есть ли слово с таким номером в предложении
                    System.out.println("В предложении нет слова с таким номером");
                    break;
                }
                data = new Data(input, number);
                return data;
            }
            default -> { //если введен неверный номер задачи
                System.out.println("Вы ввели неверный номер задачи");
                System.out.println("Попробуйте еще раз");
            }
        }
        return null; //если введен неверный номер задачи, то просто возврат null
    }

    public static String solve(Data data, int number) {
        String res = ""; //данные для подачи на запись в файл
        if (data != null) {
            String[] sentence; //переменные
            String subString; //для
            int count; //задач
            sentence = data.getSentence().split(" "); //первая строка для всех одинакова, поэтому сразу ее назначаем

            if (number == 1) { //если задача 1-ая, 2-ая или 3-я
                res = InteractMenu.solveFirst(sentence).getStringOfArray();
            } else if (number == 2) {
                subString = data.getSubString();
                res = InteractMenu.solveSecond(sentence, subString);
            } else {
                count = data.getNumber();
                res = InteractMenu.solveThird(sentence, count).getStringOfArray();
            }
        }
        return res;
    }

    public static Sentence solveFirst(String[] sentence) { //решение первой задачи
        Sentence sent = new Sentence(sentence);
        sent.sort();
        System.out.println("Результат решения первой задачи :");
        System.out.println(sent);
        return sent; //возврат данных здесь и в последующих 2-ух задачах для возможной записи их в файл
    }

    public static String solveSecond(String[] sentence, String subString) { //решение второй задачи
        Sentence sent = new Sentence(sentence);
        System.out.println("Результат решения второй задачи :");
        String ans;
        if (sent.ifContains(subString)) ans = "Строка \"" + sent + "\"" +
                "\nсодержит подстроку \"" + subString + "\"";
        else ans = "Строка \"" + sent + "\"" +
                "\nне содержит подстроку \"" + subString + "\"";
        System.out.println(ans);
        return ans;
    }

    public static Sentence solveThird(String[] sentence, int number) { //решение третьей задачи
        Sentence sent = new Sentence(sentence);
        sent.delWords(number);
        System.out.println("Результат решения третьей задачи :");
        System.out.println(sent);
        return sent;
    }

    public static void wishLuck() {
        System.out.println("Удачи");
    }
}
