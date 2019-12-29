package Lesson_02;


public class Basic_Homework_02 {
    public static void main(String[] args) {
        // Создание корректного массива
        String[][] arrStrCorrect = {{"12","105","23","1"},
                {"3","26","55","13"},
                {"233","2","143","106"},
                {"142","5","220","13"}};
        // Создание массива с некорректным размером (третья строка - пять элементов)
        String[][] arrStr1 = {{"12","105","23","1"},
                {"3","26","55","13"},
                {"233","2","143","106", "54"},
                {"142","5","220","13"}};
        // Создание массива с некорректным значением
        String[][] arrStr2 = {{"12","105","23","1"},
                {"3","26","55","13"},
                {"233","2","143","106"},
                {"142","AA","220","13"}};

        // Вызов метода на каждом массиве
        summArrExep(arrStrCorrect);
        summArrExep(arrStr1);
        summArrExep(arrStr2);


    }

    // Метод с отловом исключений
    public static void summArrExep (String[][] arr) {
        try {
            System.out.println("Сумма элементов массива: " + summArr(arr));
        } catch (MySizeDataException e1) {
            System.out.println(e1.getMessage());
        } catch (MyArrayDataException e2) {
            System.out.println(e2.getMessage());
            System.out.println("Строка: " + e2.getRow()+"; Столбец: " + e2.getColl() + ".");
        }
        System.out.println("--------------------------------");
    }


    // Метод подсчета суммы и генерации исключений
    public static int summArr (String[][] arr) throws MySizeDataException, MyArrayDataException {
        boolean correctColl = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                correctColl = false;
                break;
            }
        }
        if (arr.length != 4 || !correctColl) {
            throw new MySizeDataException ("Некорректный размер массива");
        }
        int summ = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    summ += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Некорректное значение в массиве", i + 1, j + 1);
                }
            }

        }
        return summ;
    }
}


// Исключение по размеру массива
class MySizeDataException extends Exception {
    public MySizeDataException(String message) {
        super(message);
    }
}

// Исключение по значениям в ячейках массива
class MyArrayDataException extends Exception {
    private int row;
    private int coll;

    public MyArrayDataException(String message, int row, int coll) {
        super(message);
        this.row = row;
        this.coll = coll;
    }

    public int getRow() {
        return row;
    }

    public int getColl() {
        return coll;
    }


}



