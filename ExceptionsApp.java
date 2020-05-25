package Lesson2;

import java.util.Arrays;

public class ExceptionsApp {

    public static void culcSum(String[][] str) throws MyArraySizeException, MyArrayDataException {
        if (str.length == 4 && str[0].length == 4) {
            System.out.println("Array size is OK!");
            int sum = 0;

            for (String[] line : str) {
                for (String s: line
                ) {
                    if (isParsible(s)) {
                        sum += Integer.parseInt(s);
                    } else {
                        int x = Arrays.asList(str).indexOf(line)+1;
                        int y = Arrays.asList(line).indexOf(s)+1;
                        System.out.println("Not a numeric in cell [" + x + "][" + y + "].");
                        throw new MyArrayDataException("Failed to parse!");
                    }
                }
            }
            System.out.println("The sum of chars in string array is " + sum + ".");
        } else {
            throw new MyArraySizeException("Array size must be 4x4!");
        }
    }

    private static boolean isParsible(String s) {
        boolean isNumeric = s.chars().allMatch( Character::isDigit );
        return isNumeric;
    }

    public static void main(String[] args) {
        int x;
        x = 4;
        String[][] str = new String[x][x];
        int num = 1;

        String[] stringArr1 = {"1", "2", "3", "4"};
        String[] stringArr2 = {"5", "6", "7", "8"};
        String[] stringArr3 = {"9", "bla", "11", "12"};
        String[] stringArr4 = {"13", "14", "15", "16"};
        str[0] = stringArr1;
        str[1] = stringArr2;
        str[2] = stringArr3;
        str[3] = stringArr4;

        try {
            culcSum(str);
        } catch (MyArraySizeException e) {
            System.out.println(e);
        } catch (MyArrayDataException e) {
            System.out.println(e);
        }
    }
}

