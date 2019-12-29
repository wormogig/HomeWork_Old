package Lesson_03;


import java.io.*;

public class Task_01 {

    public static void main(String[] args) {
        File file = new File("src/Lesson_03/For_Task_01/for_read.txt");
        long t0 = System.currentTimeMillis();
        try (FileInputStream in = new FileInputStream(file)) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char)x);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - t0));
    }
}
