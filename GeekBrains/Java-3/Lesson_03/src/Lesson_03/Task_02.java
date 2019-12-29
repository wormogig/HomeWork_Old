package Lesson_03;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class Task_02 {
    public static void main(String[] args) {
        ArrayList<InputStream> arrIS = new ArrayList<>();
        long t0 = System.currentTimeMillis();
        try {
            for (int i = 1; i < 6; i++) {
                arrIS.add(new FileInputStream("src/Lesson_03/For_Task_02/" + i + ".txt"));
            }
            Enumeration<InputStream> e = Collections.enumeration(arrIS);
            SequenceInputStream sin = new SequenceInputStream(e);
            FileOutputStream out = new FileOutputStream("src/Lesson_03/For_Task_02/out.txt");
            int x;
            while ((x = sin.read()) != -1) {
                out.write(x);
            }
            for (InputStream in: arrIS
                    ) {
                in.close();
            }
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - t0));

    }
}
