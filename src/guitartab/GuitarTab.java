package guitartab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GuitarTab {

    public static void main(String[] args) {
        Scanner in = null;
        File file = new File("C:\\Users\\mike spad\\Documents\\NetBeansProjects\\guitarTab\\src\\guitartab\\guitartab.txt");
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("File not found.");
        }

        String HiEString = in.nextLine();
        String BString = in.nextLine();
        String GString = in.nextLine();
        String DString = in.nextLine();
        String AString = in.nextLine();
        String LowEString = in.nextLine();

        int length = BString.length();

        String[] strings = new String[]{HiEString, BString,
            GString, DString, AString, LowEString};

        int[] stringValues = new int[]{8, 3, 11, 6, 1, 8};
        int i = 0, j;
        String temp;
        ArrayList<Integer> numOutput = new ArrayList<Integer>();

        while (i < length - 1) {
            for (j = 0; j < 6; j++) {
                temp = "" + strings[j].charAt(i);

                if (isNumeric(temp)) {
                    if (isNumeric("" + strings[j].charAt(i + 1))) {
                        temp += strings[j].charAt(i + 1);
                    }
                    try {
                        numOutput.add(Integer.parseInt(temp) + stringValues[j]);

                    } catch (NumberFormatException e) {
                    }

                }
            }
            i++;
        }

        ArrayList<String> finalOutput = new ArrayList<String>();

        Iterator iterator = numOutput.iterator();

        String note = "";
        int tempInt;
        while (iterator.hasNext()) {
            tempInt = (int) (iterator.next()) % 12;

            switch (tempInt) {

                case 0:
                    note = "G#";
                    break;
                case 1:
                    note = "A";
                    break;
                case 2:
                    note = "A#";
                    break;
                case 3:
                    note = "B";
                    break;
                case 4:
                    note = "C";
                    break;
                case 5:
                    note = "C#";
                    break;
                case 6:
                    note = "D";
                    break;
                case 7:
                    note = "D#";
                    break;
                case 8:
                    note = "E";
                    break;
                case 9:
                    note = "F";
                    break;
                case 10:
                    note = "F#";
                    break;
                case 11:
                    note = "G";
                    break;

            }
            finalOutput.add(note);
        }

        System.out.print(finalOutput);
        in.close();

    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
