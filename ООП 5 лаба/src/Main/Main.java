package Main;

import View.WindowManager;

public class Main {

    public static void main(String[] args) {
        new WindowManager();
    }

    public static boolean checkOccurrence1(String tableRowData, String mask) {
        String[] arr = mask.split("\\*");
        boolean check = true;
        for (var item : arr) {
            if (tableRowData.contains(item)) {
                tableRowData = tableRowData.substring(tableRowData.indexOf(item) + item.length());
            } else {
                check = false;
                break;
            }
        }
        return check;
    }
}
