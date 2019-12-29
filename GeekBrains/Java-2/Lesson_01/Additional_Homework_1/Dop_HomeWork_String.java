package GB_Java2_01;


import java.util.Scanner;


public class Dop_HomeWork_String {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество обрабатываемых строк");
        int n = scanner.nextInt();
        String[] arrStr = new String[n];
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            arrStr[i] = scanner.nextLine();
        }
        int[] numVowels = new int[n];
        char sym;
        int num;
        for (int i = 0; i < n; i++) {
            num = 0;
            for (int j = 0; j < arrStr[i].length(); j++) {
                sym = arrStr[i].charAt(j);
                if (sym == 'a' || sym == 'e'|| sym == 'i'|| sym == 'o'|| sym == 'u'|| sym == 'y')  {
                    num++;
                }
            }
            numVowels[i] = num;
        }
        for (int i = 0; i < n; i++) {
            System.out.println("В строке №" + i + " " + numVowels[i] + " гласных");
        }
    }
}
