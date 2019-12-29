package Lesson_05;

public class Lesson_05 {
    public static void main(String[] args) {
        mathArr();
        mathArrThread(3);

    }

    // Метод для решения задачи в однопоточном варианте
    public static void mathArr () {
        int size = 10000000;
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.print("Время заполнения массива вычисленными значениями в однопоточном варианте ");
        System.out.println(System.currentTimeMillis() - a +" мс");

//        // Тест вывода значений массива для сравнения результатв с многопоточным вариантом реализации
//        for (int i = 10000000/2 - 100; i < 10000000/2; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
    }

    // Метод для решения задачи в многопоточном варианте (в сигнатуре - количество потоков)
    public static void mathArrThread (int part) {
        int size = 10000000;
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        // Границы разбивки массива
        int[] arrLimit = new int[part + 1];
        arrLimit[0] = 0;
        arrLimit[part] = size - 1;
        for (int i = 1; i < part; i++) {
            arrLimit[i] = i * size/ part;
        }
        // Формирование разбивки массива
        float[][] arrThread = new float[part][];
        for (int i = 0; i < part; i++) {
            arrThread[i] = new float[arrLimit[i+1]-arrLimit[i]];
            System.arraycopy(arr, arrLimit[i], arrThread[i], 0, arrLimit[i+1]-arrLimit[i]);
        }

        // Формирование массива потоков
        ThreadForArr[] thread = new ThreadForArr[part];
        for (int i = 0; i < part; i++) {
            thread[i] = new ThreadForArr(arrLimit[i], arrThread[i]);
            thread[i].start();
        }
        // Ожидание окончания работы поток
        try {
            for (int i = 0; i < thread.length; i++) {
                thread[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Склейка массива
        for (int i = 0; i < part; i++) {
            System.arraycopy(arrThread[i], 0, arr, arrLimit[i], arrLimit[i+1]-arrLimit[i]);
        }
        // Вывод результата
        System.out.print("Время заполнения массива вычисленными значениями в многопоточном варианте (n = " + part + ") ");
        System.out.println(System.currentTimeMillis() - a +" мс");

//        // Тест вывода значений массива для сравнения результатв с однопоточным вариантом реализации
//        for (int i = 10000000/2 - 100; i < 10000000/2; i++)   {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
    }
}


// Класс потоков
class ThreadForArr extends Thread {
    int start;

    float[] arr;

    public ThreadForArr(int start, float[] arr) {
        this.start = start;
        this.arr = arr;
    }

    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (i + start) / 5) * Math.cos(0.2f + (i + start) / 5) * Math.cos(0.4f + (i + start) / 2));
        }
    }
}



