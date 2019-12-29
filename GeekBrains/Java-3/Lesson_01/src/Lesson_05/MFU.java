package Lesson_05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MFU {

    Lock printLock = new ReentrantLock();
    Lock scanLock = new ReentrantLock();

    public void print(String doc, int n) {

        printLock.lock();
        System.out.println("Начало печати");
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Конец печати");
        printLock.unlock();


    }

    public void scan(String doc, int n) {

        scanLock.lock();
        System.out.println("Начало сканирования");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Конец сканирования");
        scanLock.unlock();

    }

    public static void main(String[] args) {
        MFU mfu = new MFU();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 1", 10);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 2", 5);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 2", 5);
            }
        }).start();

    }

}
