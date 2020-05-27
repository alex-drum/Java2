package Lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    HashMap<String, ArrayList<String>> book = new HashMap<>();
    public PhoneBook() {
    }

    public void add(String surname, String num) {
        ArrayList<String> phones = new ArrayList<>();
        if (book.containsKey(surname)) {
            phones = book.get(surname);
        }
        phones.add(num);
        book.put(surname,phones);
    }

    public void get(String surname) {
        ArrayList<String> numbers = book.get(surname);
        System.out.print(surname + ": ");
        for (String num: numbers
             ) {
            System.out.print(num + "; ");
        }
        System.out.println( );
    }

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.add("Иванов", "+1-111-1111111");
        book.add("Петров", "+2-222-2222222");
        book.add("Сидоров", "+3-333-3333333");
        book.add("Иванов", "+4-444-4444444");
        book.add("Сидоров", "+5-555-5555555");
        book.add("Сидоров", "+9-999-9999999");

        book.get("Иванов");
        book.get("Петров");
        book.get("Сидоров");
    }
}
