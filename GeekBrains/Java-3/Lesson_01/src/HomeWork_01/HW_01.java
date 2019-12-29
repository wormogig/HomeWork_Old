package HomeWork_01;

import java.util.ArrayList;

public class HW_01 {
    //
    // Метод, меняющий элементы местами (Задание № 1)
    //
    public static void invert (ArrayList array, int first, int second) {
        if ((first < array.size()) && (second < array.size())) {
            array.add(array.get(first));
            array.set(first, array.get(second));
            array.set(second, array.get(array.size()-1));
            array.remove(array.size()-1);
        }
    }

    //
    // Метод, преобразующий массив в ArrayList (Задание № 2)
    //
    public static ArrayList convertToAL (Object[] array){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        //Тест первого задания
        ArrayList<Integer> arrInt = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrInt.add(i+2);
        }
        invert(arrInt, 4, 10);
        printArray(arrInt);
        //Тест второго задания(и первого на другом типе данных)
        String[] arrStr = {"Восьмая дверь", "Полуночная", "Пустота", "Тишина", "Новый порядок"};
        ArrayList arrList = convertToAL(arrStr);
        invert(arrList, 0, 4);
        printArray(arrList);
        System.out.println(arrList.get(0).getClass());

    }

    //
    // Метод, распечатывающий массив
    //
    public static void printArray(ArrayList array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
    }
}
