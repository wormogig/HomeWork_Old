package Lesson_08;


import java.util.Scanner;

public class InterviewTask {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int N = scanner.nextInt();
        int[] counter = new int[N];
        for (int i = 0; i < k; i++) {
            int t = scanner.nextInt();
            counter[t - 1] ++;
        }
        for (int n:counter
             ) {
            System.out.print(n + " ");
        }

    }
}
