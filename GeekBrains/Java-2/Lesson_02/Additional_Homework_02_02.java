package Lesson_02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

enum BMI {
    UNDER, NORMAL, OVER, OBESE;
}

public class Additional_Homework_02_02 {

    public static void main(String[] args) {
        try {
//            Scanner scanner = new Scanner(new File("src\\Lesson_02\\BMI.txt"));
            Scanner scanner = new Scanner(new File("src\\Lesson_02\\BMI.txt"));
            String[] line;
            while (scanner.hasNext()) {
                line = scanner.nextLine().split(" ");
                System.out.printf("Вес: %.0f; Рост: %.2f; ", Float.parseFloat(line[0]),Float.parseFloat(line[1]));
                System.out.println("Результат: " + idenBMI(Float.parseFloat(line[0]),Float.parseFloat(line[1]))+ ".");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static BMI idenBMI (float weight, float height) {
        float bmi = weight / (height * height);
        return intToBMI(bmi);
    }


    public static BMI intToBMI (float bmi) {
        if (bmi < 18.5) {
            return BMI.UNDER;
        } else if (bmi < 25) {
            return BMI.NORMAL;
        } else if (bmi < 30) {
            return BMI.OVER;
        } else {
            return BMI.OBESE;
        }
    }


}
