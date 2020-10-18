package com.company;

public class Sentence {

    private String[] stringArray;

    public Sentence() { //базовый конструктор
        this.stringArray = new String[]{"I", "am", "keen", "on", "programming", "on", "java"};
    }

    public Sentence(String[] stringArray) { //конструктор с входными данными
        this.stringArray = stringArray;
    }

    public Sentence(Sentence sent){
        this.stringArray = sent.stringArray;
    }

    public void swapValues(int ind1, int ind2) { //обмен переменных массива по индексам
        String temp = stringArray[ind2];
        stringArray[ind2] = stringArray[ind1];
        stringArray[ind1] = temp;
    }

    public void sort() { //метод сортировки массива
                         // по третьей букве, если слово меньше трех букв, то по последней.
        for (int i = 0; i < stringArray.length; i++)
        {
            for (int j = i; j < stringArray.length; j++)
            {
                String str1 = stringArray[i]; //слова из массива строк, чтобы
                String str2 = stringArray[j]; //удобнее обращаться к их символам

                if (str1.length() >= 3) //если 1 слово длиннее или равно 3 буквам
                {
                    //если 2 слово длиннее или равно 3 буквам и 3-ья буква 1-го слово больше, чем 2-ая
                    if (str2.length() >= 3 && str1.charAt(2) > str2.charAt(2)) swapValues(i, j);
                    //иначе если 3-я буква 1-го слова больше последней буквы 2-го слова
                    else if (str2.length() < 3 && str1.charAt(2) > str2.charAt(str2.length() - 1)) swapValues(i ,j);
                }
                else
                {
                    //аналогично, как выше
                    if (str2.length() >= 3 && str1.charAt(str1.length() - 1) > str2.charAt(2)) swapValues(i, j);
                    else if (str2.length() < 3 && str1.charAt(str1.length() - 1) > str2.charAt(str2.length() - 1)) swapValues(i ,j);
                }
            }
        }
    }

    public boolean ifContains(String subStr) //метод на проверку, содержит ли строка подстроку
    {
        int count = 0; //счетчик для того, чтобы считать текущий индекс подстроки
        String sent = new Sentence(stringArray).toString(); //получение строки из объекта, потому что здесь нужна именно строка, а не массив строк
        for (int i = 0; i < sent.length(); i++)
        {
            if (sent.charAt(i) == subStr.charAt(count)) //если текущий символ строки совпадает с символом подстроки
            {
                count++;
                //если подстрока полностью содержится в строке, т.е. количество совпадающих символов в строке и подстроке равно длине подстроки
                if (count == subStr.length())
                {
                    return true;
                }
            }
            else count = 0; //иначе подстрока не содержится полностью и индекс подстроки обнуляется
        }
        return false;
    }

    public void delWords(int number) //удаление каждого number-го слова в массиве
    {
        int count = 1;
        for (int i = 0; i < stringArray.length; i++){
            if (count++ % number == 0) stringArray[i] = "";
        }
    }

    @Override //переопределение метода toString() для текущего класса
    public String toString() {
        String out = "";
        for (String str : stringArray) {
            out += str;
            if (!str.equals("")) out += " ";
        }
        return out;
    }
}
