package Lesson_03;

import java.util.ArrayList;
import java.util.HashMap;

public class Basic_Homework_03_02 {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "+79256617586");
        phoneBook.add("Иванов", "+74951239848");
        phoneBook.add("Петров", "+79168889898");
        phoneBook.get("Иванов");
        phoneBook.get("Медведев");
        phoneBook.get("Петров");
    }
}


class PhoneBook {

    private HashMap<String, ArrayList<String>> book;
    private ArrayList<String> def;

    public PhoneBook() {
        book = new HashMap<>();
        def = new ArrayList<>();
        def.add("Такой фамилии нет в справочнике");
    }

    public void add (String surname, String number) {
        if (this.book.containsKey(surname)) {
            this.book.get(surname).add(number);
        } else {
            this.book.put(surname, new ArrayList<>());
            this.book.get(surname).add(number);
        }
    }

    public void get (String surname) {
        System.out.println(surname +" - "+ this.book.getOrDefault(surname, def));
    }
}