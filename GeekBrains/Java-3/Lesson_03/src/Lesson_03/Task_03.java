package Lesson_03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Task_03 {
    public static void main(String[] args) {
        final int SYMBOLS_ON_PAGE = 1800;
        byte[] arr = new byte[SYMBOLS_ON_PAGE];
        long t0 = System.currentTimeMillis();
        try (RandomAccessFile raf = new RandomAccessFile("src/Lesson_03/For_Task_03/ReadMe.txt", "r")) {
            int page = 4000;
            raf.seek((long) page * SYMBOLS_ON_PAGE);
            raf.read(arr);
            System.out.print(new String(arr,0,SYMBOLS_ON_PAGE,"UTF8"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - t0));
    }
}