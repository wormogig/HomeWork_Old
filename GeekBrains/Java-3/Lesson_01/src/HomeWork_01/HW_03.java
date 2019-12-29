package HomeWork_01;

import java.util.ArrayList;
//
// Большая задача
//
public class HW_03 {
    public static void main(String[] args) {
        // Создание коробки с двумя яблоками
        Box<Apple> appleBox1 = new Box<Apple>();
        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());
        System.out.println(appleBox1.getWeight());

        // Создание коробки с одним яблоком
        Box<Apple> appleBox2 = new Box<Apple>();
        appleBox2.addFruit(new Apple());
        System.out.println(appleBox2.getWeight());

        // Создание коробки с двумя апельсинами
        Box<Orange> orangeBox = new Box<Orange>();
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        System.out.println(orangeBox.getWeight());

        // Сравнение коробок
        System.out.println(appleBox2.compare(orangeBox));

        // Пересыпка яблок из одной коробки в другую
        appleBox1.toBox(appleBox2);
        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());

        // Сравнение коробок
        System.out.println(appleBox2.compare(orangeBox));
    }
}

abstract class Fruit {
    public float getWeight() {
        return 0;
    }
}

class Apple extends Fruit {
    private float weight = 1.0f;

    public float getWeight() {
        return weight;
    }
}

class Orange extends Fruit {
    private float weight = 1.5f;

    public float getWeight() {
        return weight;
    }
}

class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<T>();


    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight () {
        if (fruits.size() > 0) {
            return fruits.get(0).getWeight() * fruits.size();
        } else {
            return 0;
        }

    }

    public boolean compare (Box<?> box) {
        return (Math.abs(this.getWeight() - box.getWeight()) < 0.001);
    }

    public void toBox (Box<T> box) {
        for (T fruit: this.fruits
             ) {
            box.addFruit(fruit);
        }
        this.fruits.clear();
    }
}