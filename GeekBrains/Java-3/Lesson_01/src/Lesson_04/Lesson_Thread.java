package Lesson_04;

public class Lesson_Thread {

    public final Object objSyn = new Object();
    public volatile char currentChar = 'A';

    public static void main(String[] args) {
        Lesson_Thread lessonThread = new Lesson_Thread();

        Thread tA = new Thread(new Runnable() {
            @Override
            public void run() {
                lessonThread.print('A');
            }
        });

        Thread tB = new Thread(new Runnable() {
            @Override
            public void run() {
                lessonThread.print('B');
            }
        });
        Thread tC = new Thread(new Runnable() {
            @Override
            public void run() {
                lessonThread.print('C');
            }
        });
        tA.start();
        tB.start();
        tC.start();

    }

    public void print(char ch) {
        synchronized (objSyn) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != ch) {
                        objSyn.wait();
                    }
                    System.out.print(ch);
                    switch (ch) {
                        case 'A':
                            currentChar = 'B';
                            break;
                        case 'B' :
                            currentChar = 'C';
                            break;
                        case 'C' :
                            currentChar = 'A';
                            break;
                    }
                    objSyn.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
