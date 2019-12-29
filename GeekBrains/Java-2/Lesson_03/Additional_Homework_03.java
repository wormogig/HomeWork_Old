package Lesson_03;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Additional_Homework_03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pass = scanner.nextLine();
        System.out.println(validPass(pass));
    }

    public static boolean validPass (String pass) {
//        Pattern p1 = Pattern.compile(".{8,}");
//        Pattern p2 = Pattern.compile(".*[a-z]+.*");
//        Pattern p3 = Pattern.compile(".*[A-Z]+.*");
//        Pattern p4 = Pattern.compile(".*\\d+.*");
//
//        Matcher m1 = p1.matcher(pass);
//        Matcher m2 = p2.matcher(pass);
//        Matcher m3 = p3.matcher(pass);
//        Matcher m4 = p4.matcher(pass);
//
//        return m1.matches() && m2.matches() && m3.matches() && m4.matches();
        Pattern[] p = {Pattern.compile(".{8,}"),
                Pattern.compile(".*[a-z]+.*"),
                Pattern.compile(".*[A-Z]+.*"),
                Pattern.compile(".*\\d+.*")};
        Matcher m;
        Boolean res = true;
        for (int i = 0; i < p.length ; i++) {
            m = p[i].matcher(pass);
            res = res && m.matches();
        }
        return res;
    }
}
