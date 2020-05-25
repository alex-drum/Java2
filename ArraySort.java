package Lesson3;

import java.util.LinkedHashSet;

public class ArraySort {

    public static void main(String[] args) {
        String[] strings = {"Это", "массив", "из", "повторяющихся",
                "слов", "слов", "повторяющихся", "слов"};
        LinkedHashSet sortedSet = new LinkedHashSet();

        for (String str: strings
             ) {sortedSet.add(str);
        }
        System.out.println("Уникальные слова в массиве: " + sortedSet);
        System.out.println("------------------");

        for (Object uniqueWord: sortedSet
             ) { int counter = 0;
            for (String str: strings
                 ) { if (uniqueWord.equals(str)) {
                     counter++;
                    }
                }
            if (counter == 1) {
                System.out.println("\"" + uniqueWord + "\" - "
                        + counter + " раз.");
            } else {
            System.out.println("\"" + uniqueWord + "\" - "
                    + counter + " раза.");
            }
        }
    }
}
