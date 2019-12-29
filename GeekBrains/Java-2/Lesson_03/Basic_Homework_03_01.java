package Lesson_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Basic_Homework_03_01 {

    public static void main(String[] args) {
        repWords();
    }

    public static void repWords () {
        Random random = new Random();
        int n = 20;
        ArrayList<String> arrWords = new ArrayList<>();
        String[] arrStr = {"cat", "dog", "dragon", "bike", "car", "home", "cup", "star", "trek"};
        int length = arrStr.length;
        for (int i = 0; i < n; i++) {
            arrWords.add(arrStr[random.nextInt(length)]);
        }
        System.out.println(arrWords);
        HashMap<String, Integer> words = new HashMap<>();
        for (String word : arrWords) {
            Integer res = words.get(word);
            words.put(word, res == null ? 1 : res + 1);
        }
        System.out.println(words);

    }
}


