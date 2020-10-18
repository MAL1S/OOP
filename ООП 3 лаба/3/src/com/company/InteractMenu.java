package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InteractMenu {

    public static void start() {
        System.out.println("Какую задачу вы хотели бы решить : ");
        System.out.println("____1) Сортировка предложение по третьей букве, если слово меньше, то по последней");
        System.out.println("____2) Определить, входит ли подстрока в строку");
        System.out.println("____3) Удалить в предложении каждое n-ное слово");
    }

    public static String ifNumber() { //проверяет, число ли введено вообще
        Scanner in = new Scanner(System.in);
        int number;
        try {
            number = in.nextInt(); //пробует ввести число
            return String.valueOf(number);
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

    public static void writeFile(String str) { //запись данных в файл
        Scanner in = new Scanner(System.in);
        System.out.println("Хотите ли сохранить результат решения в файл?" +
                "(0 - нет, 1 - да)");
        String reply = in.nextLine();
        while (!reply.equals("0") && !reply.equals("1")) { //пока ответ пользователя не будет равен 0 или 1
            System.out.println("Введите 0 или 1");
            reply = in.nextLine();
        }
        if (reply.equals("0")) return; //если пользователь не хочет делать запись данных в файл
        System.out.println("Введите название существующего файла или место, где нужно создать файл");
        String path = in.nextLine(); //путь файла
        try {
            if (path.equals("0")) return; //если пользователь ввел 0 в catch
            FileOutputStream fos = new FileOutputStream(path); //поток вывода данных в файл
            PrintWriter pw = new PrintWriter(fos); //запись текстовых данных в файл, чтобы кодировка была русская,
            //иначе будет происходить побайтовая запись данных и будут выводится непонятные символы
            pw.write(str); //запись данных в файл
            pw.close();
            fos.close(); //закрытие потоков вывода
        }catch (IOException e) {
            System.out.println("Вы ввели название каталога или файл не может быть создан или открыт по некоторым причинам" +
                    "повторите ввод");
            path = in.nextLine(); //иначе просьба выполнить повторый ввод и возможность отменить это действие
        }

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
}
