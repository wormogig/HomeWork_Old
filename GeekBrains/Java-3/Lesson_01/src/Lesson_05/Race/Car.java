package Lesson_05.Race;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static Boolean winFlag;

    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier sb;

    static Lock lock = new ReentrantLock();


    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier sb) {
        this.race = race;
        this.speed = speed;
        this.sb = sb;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            sb.await();
            winFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        lock.lock();
        if (winFlag) {
            System.out.println(this.name + " - WIN!");
            winFlag = false;
        }
        lock.unlock();
        try {
            sb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
