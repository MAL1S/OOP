package Main;

import View.WindowManager;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        new WindowManager();
    }

    public boolean checkOccurrence(String tableData, String mask) {
        tableData = tableData.toLowerCase();
        mask = mask.toLowerCase();
        boolean isFirstStar = mask.charAt(0) == '*';
        boolean isLastStar = mask.charAt(mask.length()-1) == '*';
        String[] arr = mask.split("\\*");

        if (!isFirstStar) {
            for (int i = 0; i < arr[0].length(); i++) {
                System.out.println("1 - " + tableData.charAt(i) + " " + arr[0].charAt(i));
                if (tableData.charAt(i) != arr[0].charAt(i)) return false;
            }
        }
        if (!isLastStar) {
            for (int i = tableData.length()-1, j = arr[arr.length-1].length()-1;
                 i > tableData.length()-1-arr[arr.length-1].length();
                 i--, j--)
            {
                System.out.println("2 - " + tableData.charAt(i) + " " + arr[arr.length-1].charAt(j));
                if (tableData.charAt(i) != arr[arr.length-1].charAt(j)) return false;
            }
        }
        for (var item : arr) {
            System.out.println("here");
            if (tableData.contains(item)) {
                tableData = tableData.substring(tableData.indexOf(item) + item.length());
            } else {
                return false;
            }
        }
        return true;
    }
}
